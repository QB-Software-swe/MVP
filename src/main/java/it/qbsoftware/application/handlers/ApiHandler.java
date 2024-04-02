package it.qbsoftware.application.handlers;

import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;

import it.qbsoftware.application.ApiRequestDispatch;

public class ApiHandler extends Handler.Abstract {
    public static String CONTEXT_PATH = "";
    private static String ENDPOINT_PATH = "/api";

    @Override
    public boolean handle(Request request, Response response, Callback callback) throws Exception {
        Boolean isPost = request.getMethod().equals("POST");
        Boolean isTheRightEndpoint = Request.getPathInContext(request).equals(ENDPOINT_PATH);

        if(!(isPost && isTheRightEndpoint)) {
            return false;
        }

        String requestPayload = Content.Source.asString(request);
        response.getHeaders().put(HttpHeader.CONTENT_TYPE, "application/json; charset=UTF-8");


        String responsePayload = new ApiRequestDispatch().Dispatch(requestPayload); //TODO

        Content.Sink.write(response, true, responsePayload, callback);
        return true;
    }

}
