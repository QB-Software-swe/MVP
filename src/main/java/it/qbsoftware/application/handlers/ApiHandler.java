package it.qbsoftware.application.handlers;

import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonSyntaxException;
import com.google.inject.Inject;

import it.qbsoftware.application.ApiRequestDispatch;

public class ApiHandler extends Handler.Abstract {
    public static String CONTEXT_PATH = "";
    private static String ENDPOINT_PATH = "/api";
    private final Logger logger = LoggerFactory.getLogger(ApiHandler.class);

    private final ApiRequestDispatch apiRequestDispatch;

    @Inject
    public ApiHandler(final ApiRequestDispatch apiRequestDispatch) {
        this.apiRequestDispatch = apiRequestDispatch;
    }

    @Override
    public boolean handle(Request request, Response response, Callback callback) throws Exception {
        Boolean isPost = request.getMethod().equals("POST");
        Boolean isTheRightEndpoint = Request.getPathInContext(request).equals(ENDPOINT_PATH);

        if (!(isPost && isTheRightEndpoint)) {
            return false;
        }
        logger.info("Request to /api");

        String requestPayload = Content.Source.asString(request);
        logger.debug("Request to /api, payload = " + requestPayload);
        String responsePayload = null;
        try {
            logger.info("MethodCalls sent to dispatcher");
            responsePayload = apiRequestDispatch.Dispatch(requestPayload);
        } catch (final JsonSyntaxException jsonSyntaxException) {
            logger.info("JsonRequest is invalid, error 400");
            logger.debug(jsonSyntaxException.getMessage());
            responsePayload = "{\"type\":\"urn:ietf:params:jmap:error:notJSON\",\"status\":400,\"detail\":\"See https://jmap.io/spec-core.html#errors\"}";
            response.setStatus(400);
            Content.Sink.write(response, true, responsePayload, callback);
            return true;
        }

        logger.info("Request to /api, dispatcher generate a response");
        logger.debug("Response from /api, payload = " + responsePayload);
        response.getHeaders().put(HttpHeader.CONTENT_TYPE, "application/json; charset=UTF-8");
        Content.Sink.write(response, true, responsePayload, callback);
        return true;
    }

}
