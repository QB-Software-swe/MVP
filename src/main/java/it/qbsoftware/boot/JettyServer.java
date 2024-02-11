package it.qbsoftware.boot;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import it.qbsoftware.boot.handlers.ApiHandler;
import it.qbsoftware.boot.handlers.DownloadHandler;
import it.qbsoftware.boot.handlers.UploadHandler;
import it.qbsoftware.boot.handlers.WellKnownHandler;
import it.qbsoftware.core.util.GenPocData;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.slf4j.LoggerFactory;

public class JettyServer {

    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
    }

    public static void main(String[] args) {
        GenPocData.generate();

        QueuedThreadPool queuedThreadPool = new QueuedThreadPool();
        queuedThreadPool.setName("JMAPServer");

        Server server = new Server(queuedThreadPool);

        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setPort(9999);
        server.addConnector(serverConnector);

        // Server end points
        ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
        contextHandlerCollection.addHandler(
                new ContextHandler(new WellKnownHandler(), WellKnownHandler.HANDLER_CONTEXT_PATH));
        contextHandlerCollection.addHandler(new ContextHandler(new ApiHandler()));
        contextHandlerCollection.addHandler(new ContextHandler(new UploadHandler()));
        contextHandlerCollection.addHandler(new ContextHandler(new DownloadHandler()));
        server.setHandler(contextHandlerCollection);

        try {
            server.start();
        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(1);
        }
    }
}
