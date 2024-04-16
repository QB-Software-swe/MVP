package it.qbsoftware.application.handlers;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;

import com.google.inject.Inject;

import it.qbsoftware.application.controllers.SessionController;

public class WellKnownHandler extends Handler.Abstract {
    final SessionController sessionController;

    public static String CONTEXT_PATH = ".well-known/";
    private static String ENDPOINT_PATH = "/jmap";

    @Inject
    public WellKnownHandler(final SessionController sessionController) {
        this.sessionController = sessionController;
    }

    @Override
    public boolean handle(Request request, Response response, Callback callback) throws Exception {
        Boolean isPost = request.getMethod().equals("POST");
        Boolean isTheRightEndpoint = Request.getPathInContext(request).equals(ENDPOINT_PATH);

        if (!(isPost && isTheRightEndpoint)) {
            return false;
        }

        String user = null;
        {
            final String auth = request.getHeaders().get(HttpHeader.AUTHORIZATION);
            if (auth != null && auth.toLowerCase().startsWith("basic")) {
                String base64Cred = auth.substring("Basic".length()).trim();
                byte[] credDecod = Base64.getDecoder().decode(base64Cred);
                String creds = new String(credDecod, StandardCharsets.UTF_8);
                final String[] values = creds.split(":", 2);
                user = values[0];
            }
        }

        String responsePayload = sessionController.call(user);
        if (responsePayload == null) {
            response.setStatus(401);
            Content.Sink.write(response, true, "Account not found", callback);
        }

        response.getHeaders().put(HttpHeader.CONTENT_TYPE, "application/json; charset=UTF-8");

        Content.Sink.write(response, true, responsePayload, callback);
        return true;
    }

}
