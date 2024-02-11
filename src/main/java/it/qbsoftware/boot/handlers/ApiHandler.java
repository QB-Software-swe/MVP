package it.qbsoftware.boot.handlers;

import it.qbsoftware.core.util.JmapSingleton;
import it.qbsoftware.core.util.RequestResponse;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;

public class ApiHandler extends Handler.Abstract {
    public static final String HANDLER_ENDPOINT = "/api";
    public static final String HANDLER_CONTEXT_PATH = "/";

    @Override
    public boolean handle(Request request, Response response, Callback callback) throws Exception {
        if (!(request.getMethod().equals("POST")
                && Request.getPathInContext(request).equals(HANDLER_ENDPOINT))) {
            return false;
        }

        String requestPayload = Content.Source.asString(request);
        response.getHeaders().put(HttpHeader.CONTENT_TYPE, "application/json; charset=UTF-8");

        RequestResponse jmapResponse = JmapSingleton.INSTANCE.getJmap().request(requestPayload);
        response.setStatus(jmapResponse.responseCode());

        Content.Sink.write(response, true, jmapResponse.payload(), callback);
        return true;
    }
}
