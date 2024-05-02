package it.qbsoftware.application.handlers;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import it.qbsoftware.application.controllers.other.SessionController;

public class WellKnownHandler extends Handler.Abstract {
    final SessionController sessionController;

    private final Logger logger = LoggerFactory.getLogger(WellKnownHandler.class);
    public static String CONTEXT_PATH = "/.well-known";
    private static String ENDPOINT_PATH = "/jmap";

    @Inject
    public WellKnownHandler(final SessionController sessionController) {
        this.sessionController = sessionController;
    }

    @Override
    public boolean handle(Request request, Response response, Callback callback) throws Exception {
        Boolean isGet = request.getMethod().equals("GET");
        Boolean isTheRightEndpoint = Request.getPathInContext(request).equals(ENDPOINT_PATH);

        if (!(isGet && isTheRightEndpoint)) {
            return false;
        }


        logger.info("Request to /.well-known");
        String user = null;
        //String password = null;
        {
            final String auth = request.getHeaders().get(HttpHeader.AUTHORIZATION);
            if (auth != null && auth.toLowerCase().startsWith("basic")) {
                String base64Cred = auth.substring("Basic".length()).trim();
                byte[] credDecod = Base64.getDecoder().decode(base64Cred);
                String creds = new String(credDecod, StandardCharsets.UTF_8);
                final String[] values = creds.split(":", 2);
                user = values[0];
                //password = values[1];
            }
        }

        logger.debug("Request basic auth, user = " + user);

        if (user == null) {
            logger.info("Request response 401, invalid user name");
            response.setStatus(401);
            Content.Sink.write(response, true, "Invalid Auth", callback);
            return true;
        }

        String responsePayload = sessionController.call(user);
        if (responsePayload == null) {
            logger.info("Request response 401, user not found");
            response.setStatus(401);
            Content.Sink.write(response, true, "Invalid Auth", callback);
            return true;
        }

        logger.info("Request response: user session resource");
        response.getHeaders().put(HttpHeader.CONTENT_TYPE, "application/json; charset=UTF-8");
        Content.Sink.write(response, true, responsePayload, callback);
        return true;
    }

}
