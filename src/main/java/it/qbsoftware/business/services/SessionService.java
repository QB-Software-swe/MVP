package it.qbsoftware.business.services;

import java.util.HashMap;
import java.util.Optional;

import it.qbsoftware.business.ports.in.jmap.EndPointConfiguration;
import it.qbsoftware.business.ports.in.jmap.SessionResourceBuilderPort;
import it.qbsoftware.business.ports.in.jmap.SessionResourcePort;
import it.qbsoftware.business.ports.in.jmap.capabilities.CapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.AccountBuilderPort;
import it.qbsoftware.business.ports.in.usecase.SessionUsecase;
import it.qbsoftware.business.ports.out.jmap.UserSessionResourceRepository;

public class SessionService implements SessionUsecase {
        final SessionResourceBuilderPort sessionResourceBuilderPort;
        final AccountBuilderPort accountBuilderPort;
        final UserSessionResourceRepository userSessionResourceRepository;

        public SessionService(
                        final SessionResourceBuilderPort sessionResourceBuilderPort,
                        final AccountBuilderPort accountBuilderPort,
                        final UserSessionResourceRepository userSessionResourceRepository) {
                this.sessionResourceBuilderPort = sessionResourceBuilderPort;
                this.accountBuilderPort = accountBuilderPort;
                this.userSessionResourceRepository = userSessionResourceRepository;
        }

        @Override
        public Optional<SessionResourcePort> call(final String username,
                        final EndPointConfiguration endPointConfiguration,
                        final HashMap<Class<? extends CapabilityPort>, CapabilityPort> serverCapabilities) {

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
                                        .state(oldSessionData.get().state()); // FIXME: se c'è una variazione va
                                                                              // segnalata con un cambio di stato e
                                                                              // salvata nel db

                        return Optional.of(sessionResourceBuilderPort.build());
                }

                return Optional.empty();
        }
}