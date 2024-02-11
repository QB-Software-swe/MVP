package it.qbsoftware.boot.handlers;

import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;

public class UploadHandler extends Handler.Abstract {
    public static final String HANDLER_ENDPOINT = "/upload";
    public static final String HANDLER_CONTEXT_PATH = "/";

    @Override
    public boolean handle(Request request, Response response, Callback callback) throws Exception {
        if (!(request.getMethod().equals("POST")
                && Request.getPathInContext(request).equals(HANDLER_ENDPOINT))) {
            return false;
        }
        response.setStatus(404); // Not implemented
        Content.Sink.write(response, true, "", callback);
        return true;
    }
}
