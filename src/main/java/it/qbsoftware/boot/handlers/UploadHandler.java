package it.qbsoftware.boot.handlers;

import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;

public class UploadHandler extends Handler.Abstract {
  public static final String HANDLER_ENDPOINT_NAME = "/upload";

  @Override
  public boolean handle(Request request, Response response, Callback callback) throws Exception {
    response.setStatus(404); // Not implemented
    Content.Sink.write(response, true, "", callback);
    return true;
  }
}
