package it.qbsoftware.application.handlers;

import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;

import com.google.gson.JsonSyntaxException;
import com.google.inject.Inject;

import it.qbsoftware.application.ApiRequestDispatch;

public class ApiHandler extends Handler.Abstract {
    public static String CONTEXT_PATH = "";
    private static String ENDPOINT_PATH = "/api";

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

        String requestPayload = Content.Source.asString(request);
        String responsePayload = null;
        try {
            responsePayload = apiRequestDispatch.Dispatch(requestPayload);
        } catch (final JsonSyntaxException jsonSyntaxException) {
            responsePayload = "{\"type\":\"urn:ietf:params:jmap:error:notJSON\",\"status\":400,\"detail\":\"See https://jmap.io/spec-core.html#errors\"}";
            response.setStatus(400);
            Content.Sink.write(response, true, responsePayload, callback);
            return true;
        }

        response.getHeaders().put(HttpHeader.CONTENT_TYPE, "application/json; charset=UTF-8");
        Content.Sink.write(response, true, responsePayload, callback);
        return true;
    }

}
