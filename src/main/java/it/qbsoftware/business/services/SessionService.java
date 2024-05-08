package it.qbsoftware.business.services;

import it.qbsoftware.business.ports.in.jmap.JmapUrlConfiguration;
import it.qbsoftware.business.ports.in.jmap.capability.CapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourceBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourcePort;
import it.qbsoftware.business.ports.in.usecase.SessionUsecase;
import it.qbsoftware.business.ports.out.jmap.UserSessionResourceRepository;
import java.util.Optional;

public class SessionService implements SessionUsecase {
    private final SessionResourceBuilderPort sessionResourceBuilderPort;
    private final UserSessionResourceRepository userSessionResourceRepository;

    public SessionService(
            final SessionResourceBuilderPort sessionResourceBuilderPort,
            final UserSessionResourceRepository userSessionResourceRepository) {
        this.sessionResourceBuilderPort = sessionResourceBuilderPort;
        this.userSessionResourceRepository = userSessionResourceRepository;
    }

    @Override
    public Optional<SessionResourcePort> call(
            final String username,
            final JmapUrlConfiguration endPointConfiguration,
            final CapabilityPort[] serverCapabilities) {

        sessionResourceBuilderPort.reset();

        sessionResourceBuilderPort
                .apiUrl(endPointConfiguration.apiUrl())
                .uploadUrl(endPointConfiguration.uploadUrl())
                .downloadUrl(endPointConfiguration.downloadUrl())
                .eventSourceUrl(endPointConfiguration.eventSourceUrl());
        sessionResourceBuilderPort.capabilities(serverCapabilities);

        Optional<SessionResourcePort> oldSessionData =
                userSessionResourceRepository.retrieve(username);
        if (oldSessionData.isPresent()) {
            sessionResourceBuilderPort
                    .username(username)
                    .accounts(oldSessionData.get().accounts())
                    .primaryAccounts(oldSessionData.get().primaryAccounts())
                    .state(oldSessionData.get().state());

            return Optional.of(sessionResourceBuilderPort.build());
        }

        return Optional.empty();
    }
}
