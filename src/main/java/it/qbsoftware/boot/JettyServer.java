package it.qbsoftware.boot;

import it.qbsoftware.boot.handlers.ApiHandler;
import it.qbsoftware.boot.handlers.DownloadHandler;
import it.qbsoftware.boot.handlers.UploadHandler;
import it.qbsoftware.boot.handlers.WellKnownHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

/** JMAPServer */
public class JettyServer {
  public static void main(String[] args) {
    QueuedThreadPool queuedThreadPool = new QueuedThreadPool();
    queuedThreadPool.setName("JMAPServer");

    Server server = new Server(queuedThreadPool);

    ServerConnector serverConnector = new ServerConnector(server);
    serverConnector.setPort(9999);
    server.addConnector(serverConnector);

    // Server end points
    ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
    contextHandlerCollection.addHandler(
        new ContextHandler(new WellKnownHandler(), WellKnownHandler.HANDLER_ENDPOINT_NAME));
    contextHandlerCollection.addHandler(
        new ContextHandler(new ApiHandler(), ApiHandler.HANDLER_ENDPOINT_NAME));
    contextHandlerCollection.addHandler(
        new ContextHandler(new UploadHandler(), UploadHandler.HANDLER_ENDPOINT_NAME));
    contextHandlerCollection.addHandler(
        new ContextHandler(new DownloadHandler(), DownloadHandler.HANDLER_ENDPOINT_NAME));
    server.setHandler(contextHandlerCollection);

    try {
      server.start();
    } catch (Exception exception) {
      exception.printStackTrace();
      System.exit(1);
    }
  }
}
