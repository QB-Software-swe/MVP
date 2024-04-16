package it.qbsoftware.application;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Guice;
import com.google.inject.Injector;

import it.qbsoftware.adapters.jmaplib.AccountBuilderAdapter;
import it.qbsoftware.adapters.jmaplib.CapabilityAdapter;
import it.qbsoftware.adapters.jmaplib.SessionResourceBuilderAdapter;
import it.qbsoftware.adapters.jmaplib.UserSessionResourceRepositoryAdapter;
import it.qbsoftware.application.controllers.SessionController;
import it.qbsoftware.application.handlers.ApiHandler;
import it.qbsoftware.application.handlers.WellKnownHandler;
import it.qbsoftware.business.ports.in.jmap.EndPointConfiguration;
import it.qbsoftware.business.ports.in.jmap.capabilities.CapabilityPort;
import it.qbsoftware.business.services.SessionService;
import rs.ltt.jmap.common.entity.capability.CoreCapability;
import rs.ltt.jmap.common.entity.capability.MailCapability;
import rs.ltt.jmap.gson.JmapAdapters;

public class JettyServer {
    public static void main(String[] args) {

        ///// DI FATTA A MANO //////////
        Gson gson;
        {
            GsonBuilder gsonBuilder = new GsonBuilder();
            JmapAdapters.register(gsonBuilder);
            gson = gsonBuilder.create();
        }

        SessionController sessionController = new SessionController(
                new SessionService(new SessionResourceBuilderAdapter(), new AccountBuilderAdapter(),
                        new UserSessionResourceRepositoryAdapter()),
                new EndPointConfiguration() {

                    @Override
                    public String apiEndPoint() {
                        return "/api";
                    }

                    @Override
                    public String uploadEndPoint() {
                        return "/upload";
                    }

                    @Override
                    public String downloadEndPoint() {
                        return "/download";
                    }

                    @Override
                    public String eventSourceEndPoint() {
                        return "/eventsource";
                    }

                }, new CapabilityPort[] { new CapabilityAdapter(CoreCapability.builder().build()),
                        new CapabilityAdapter(MailCapability.builder().build()) },
                gson);

        /////// CODICE ///////
        Server server = new Server();
        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setPort(9999);
        server.addConnector(serverConnector);

        ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
        contextHandlerCollection.addHandler(new ContextHandler(
                new WellKnownHandler(sessionController), WellKnownHandler.CONTEXT_PATH));
        contextHandlerCollection.addHandler(new ContextHandler(new ApiHandler()));

        server.setHandler(contextHandlerCollection);
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
