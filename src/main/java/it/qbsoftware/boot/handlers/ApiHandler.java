package it.qbsoftware.boot.handlers;

import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;

import it.qbsoftware.core.SingletonJmap;
import it.qbsoftware.core.utils.RequestResponse;

public class ApiHandler extends Handler.Abstract {
    public static final String HANDLER_ENDPOINT_NAME = "/api";

    @Override
    public boolean handle(Request request, Response response, Callback callback) throws Exception {
        String requestPayload = Content.Source.asString(request);
        response.getHeaders().put(HttpHeader.CONTENT_TYPE, "application/json; charset=UTF-8");
        
        RequestResponse jmapResponse = SingletonJmap.INSTANCE.getJmap().request(requestPayload);
        response.setStatus(jmapResponse.responseCode());
        
        Content.Sink.write(response, true, jmapResponse.payload(), callback);
        return true;
    }
    
}
