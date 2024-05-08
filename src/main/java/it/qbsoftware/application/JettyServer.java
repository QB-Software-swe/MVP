package it.qbsoftware.application;

import com.google.inject.Guice;
import com.google.inject.Injector;
import it.qbsoftware.application.controllers.other.SessionController;
import it.qbsoftware.application.handlers.ApiHandler;
import it.qbsoftware.application.handlers.WellKnownHandler;
import it.qbsoftware.application.module.ControllerModule;
import it.qbsoftware.application.module.JettyHandlerModule;
import it.qbsoftware.application.module.JmapLibAdapterModule;
import it.qbsoftware.application.module.MongoRepositoryAdapterModule;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

public class JettyServer {
    public static void main(String[] args) {
        Injector controllerInjector =
                Guice.createInjector(
                        new ControllerModule(),
                        new JmapLibAdapterModule(),
                        new MongoRepositoryAdapterModule(),
                        new JettyHandlerModule());

        var genDate = controllerInjector.getInstance(GenData.class);
        genDate.generate();

        Server server = new Server();
        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setPort(9999);
        server.addConnector(serverConnector);

        ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
        contextHandlerCollection.addHandler(
                new ContextHandler(
                        new WellKnownHandler(
                                controllerInjector.getInstance(SessionController.class)),
                        WellKnownHandler.CONTEXT_PATH));
        contextHandlerCollection.addHandler(
                new ContextHandler(controllerInjector.getInstance(ApiHandler.class)));

        server.setHandler(contextHandlerCollection);
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
