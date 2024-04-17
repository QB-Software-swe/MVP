package it.qbsoftware.application;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

import com.google.inject.Guice;
import com.google.inject.Injector;

import it.qbsoftware.adapters.out.UserSessionResourceRepositoryAdapter;
import it.qbsoftware.application.controllers.other.SessionController;
import it.qbsoftware.application.handlers.ApiHandler;
import it.qbsoftware.application.handlers.WellKnownHandler;

public class JettyServer {
    public static void main(String[] args) {
        Injector controllerInjector = Guice.createInjector(new ControllerModule());

        var genDate = new GenData(controllerInjector.getInstance(UserSessionResourceRepositoryAdapter.class));
        genDate.generate();

        Server server = new Server();
        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setPort(9999);
        server.addConnector(serverConnector);

        ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
        contextHandlerCollection.addHandler(new ContextHandler(
                new WellKnownHandler(controllerInjector.getInstance(SessionController.class)),
                WellKnownHandler.CONTEXT_PATH));
        contextHandlerCollection.addHandler(new ContextHandler(new ApiHandler()));

        server.setHandler(contextHandlerCollection);
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
