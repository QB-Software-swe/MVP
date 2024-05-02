package it.qbsoftware.application.controllers.other;

import it.qbsoftware.adapters.in.jmaplib.entity.SessionResourceAdapter;
import it.qbsoftware.application.controllers.changes.ChangesEmailSubmissionMethodCallController;
import it.qbsoftware.business.ports.in.jmap.EndPointConfiguration;
import it.qbsoftware.business.ports.in.jmap.capability.CapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourcePort;
import it.qbsoftware.business.ports.in.usecase.SessionUsecase;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class SessionController {
    private final Logger logger = LoggerFactory.getLogger(SessionController.class);
    final SessionUsecase sessionUsecase;
    final EndPointConfiguration endPointConfiguration;
    final CapabilityPort[] serverCapabilities;
    final Gson gson;

    @Inject
    public SessionController(final SessionUsecase sessionUsecase, final EndPointConfiguration endPointConfiguration,
            @Named("serverCapabilities") final CapabilityPort[] serverCapabilities, final Gson gson) {
        this.sessionUsecase = sessionUsecase;
        this.endPointConfiguration = endPointConfiguration;
        this.serverCapabilities = serverCapabilities;
        this.gson = gson;
    }

    public String call(final String user) {
        logger.info("handle method call recived");
        Optional<SessionResourcePort> optionalSessionResource = sessionUsecase.call(user, endPointConfiguration,
                serverCapabilities);

        if (optionalSessionResource.isPresent()) {
            return gson.toJson(((SessionResourceAdapter) optionalSessionResource.get()).adaptee());
        }

        return null;
    }
}
