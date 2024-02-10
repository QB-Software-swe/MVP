package it.qbsoftware.boot.handlers;

import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;

import it.qbsoftware.core.util.RequestResponse;
import it.qbsoftware.core.util.JmapSingleton;

public class WellKnownHandler extends Handler.Abstract {
    public static final String HANDLER_ENDPOINT_NAME = "/.well-known/jmap";

    @Override
    public boolean handle(Request request, Response response, Callback callback) throws Exception {
        response.getHeaders().put(HttpHeader.CONTENT_TYPE, "application/json; charset=UTF-8");

        RequestResponse jmapResponse = JmapSingleton.INSTANCE.getJmap().requestSession(request.getHeaders().get(HttpHeader.AUTHORIZATION));
        response.setStatus(jmapResponse.responseCode());

        Content.Sink.write(response, true, jmapResponse.payload(), callback);
        return true;
    }

}
