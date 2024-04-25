package it.qbsoftware.business.services;

import java.util.Optional;

import it.qbsoftware.business.ports.in.jmap.EndPointConfiguration;
import it.qbsoftware.business.ports.in.jmap.capability.CapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourceBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourcePort;
import it.qbsoftware.business.ports.in.usecase.SessionUsecase;
import it.qbsoftware.business.ports.out.jmap.UserSessionResourceRepository;

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
        public Optional<SessionResourcePort> call(final String username,
                        final EndPointConfiguration endPointConfiguration,
                        final CapabilityPort[] serverCapabilities) {

                sessionResourceBuilderPort.reset();

                sessionResourceBuilderPort
                                .apiUrl(endPointConfiguration.apiEndPoint())
                                .uploadUrl(endPointConfiguration.uploadEndPoint())
                                .downloadUrl(endPointConfiguration.downloadEndPoint())
                                .eventSourceUrl(endPointConfiguration.eventSourceEndPoint());
                sessionResourceBuilderPort.capabilities(serverCapabilities);

                Optional<SessionResourcePort> oldSessionData = userSessionResourceRepository.retrieve(username);
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