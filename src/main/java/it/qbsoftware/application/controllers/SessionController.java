package it.qbsoftware.application.controllers;

import it.qbsoftware.adapters.jmaplib.SessionResourceAdapter;
import it.qbsoftware.business.ports.in.jmap.EndPointConfiguration;
import it.qbsoftware.business.ports.in.jmap.SessionResourcePort;
import it.qbsoftware.business.ports.in.jmap.capabilities.CapabilityPort;
import it.qbsoftware.business.ports.in.usecase.SessionUsecase;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class SessionController {
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
        Optional<SessionResourcePort> optionalSessionResource = sessionUsecase.call(user, endPointConfiguration,
                serverCapabilities);

        if (optionalSessionResource.isPresent()) {
            return gson.toJson(((SessionResourceAdapter) optionalSessionResource.get()).adaptee());
        }

        return null;
    }
}
