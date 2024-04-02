package it.qbsoftware.application;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

import it.qbsoftware.application.handlers.ApiHandler;

public class JettyServer {
    public static void main(String[] args) {
        Server server = new Server();
        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setPort(9999);
        server.addConnector(serverConnector);

        ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
        contextHandlerCollection.addHandler(new ContextHandler(new ApiHandler()));

        server.setHandler(contextHandlerCollection);
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
