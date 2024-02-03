package it.qbsoftware.boot;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import it.qbsoftware.boot.handlers.apiHandler;
import it.qbsoftware.boot.handlers.wellKnownHandler;

/**
 * JMAPServer
 */
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
        contextHandlerCollection.addHandler(new ContextHandler(
            new wellKnownHandler(),
            wellKnownHandler.HANDLER_ENDPOINT_NAME
        ));
        contextHandlerCollection.addHandler(new ContextHandler(
            new apiHandler(),
            apiHandler.HANDLER_ENDPOINT_NAME
        ));
        server.setHandler(contextHandlerCollection);

        try {
            server.start();
        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(1);
        }
    }
}