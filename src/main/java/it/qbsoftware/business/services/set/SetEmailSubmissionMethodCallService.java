package it.qbsoftware.business.services.set;

import java.util.HashMap;
import java.util.UUID;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.methodcall.process.set.update.UpdateEmail;
import it.qbsoftware.business.domain.methodcall.process.set.update.UpdatedResult;
import it.qbsoftware.business.domain.methodresponse.SetEmailSubmissionMethodResponse;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailSubmissionMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailSubmissionMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.usecase.set.SetEmailSubmissionMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.EmailSubmissionRepository;

public class SetEmailSubmissionMethodCallService implements SetEmailSubmissionMethodCallUsecase {
        private final AccountStateRepository accountStateRepository;
        private final EmailSubmissionRepository emailSubmissionRepository;
        private final EmailSubmissionBuilderPort emailSubmissionBuilderPort;
        private final UpdateEmail updateEmail;
        private final SetEmailSubmissionMethodResponseBuilderPort setEmailSubmissionMethodResponseBuilderPort;
        private final SetEmailMethodResponseBuilderPort setEmailMethodResponseBuilderPort;

        public SetEmailSubmissionMethodCallService(
                        final AccountStateRepository accountStateRepository,
                        final EmailSubmissionRepository emailSubmissionRepository,
                        final SetEmailMethodResponseBuilderPort setEmailMethodResponseBuilderPort,
                        final SetEmailSubmissionMethodResponseBuilderPort setEmailSubmissionMethodResponseBuilderPort,
                        final UpdateEmail updateEmail, final EmailSubmissionBuilderPort emailSubmissionBuilderPort) {
                this.accountStateRepository = accountStateRepository;
                this.emailSubmissionRepository = emailSubmissionRepository;
                this.emailSubmissionBuilderPort = emailSubmissionBuilderPort;
                this.updateEmail = updateEmail;
                this.setEmailSubmissionMethodResponseBuilderPort = setEmailSubmissionMethodResponseBuilderPort;
                this.setEmailMethodResponseBuilderPort = setEmailMethodResponseBuilderPort;
        }

        @Override
        public SetEmailSubmissionMethodResponse call(
                        final SetEmailSubmissionMethodCallPort setEmailSubmissionMethodCallPort,
                        final ListMultimapPort<String, ResponseInvocationPort> previousResponse)
                        throws AccountNotFoundException {
                final String accountId = setEmailSubmissionMethodCallPort.accountId();
                final AccountState preAccountState = accountStateRepository.retrive(accountId);
                final HashMap<String, EmailSubmissionPort> emailSubmissionCreated = new HashMap<String, EmailSubmissionPort>();

                final HashMap<String, String> successSubmissionEmailIdResolve = new HashMap<>();
                for (final var x : setEmailSubmissionMethodCallPort.getCreate().entrySet()) {
                        final String emailRef = x.getValue().getEmailId();
                        System.out.println("#" + x.getKey());
                        System.out.println(emailRef);
                        successSubmissionEmailIdResolve.put("#" + x.getKey(), emailRef);

                        emailSubmissionCreated.put(x.getKey(),
                                        emailSubmissionBuilderPort.reset().id(UUID.randomUUID().toString()).build());
                }

                // OnSuccess
                final UpdatedResult<EmailPort> updateEmailResultOnSuccess = updateEmail.update(
                                setEmailSubmissionMethodCallPort,
                                previousResponse, successSubmissionEmailIdResolve);

                final AccountState postAccountState = accountStateRepository.retrive(accountId);
                return new SetEmailSubmissionMethodResponse(
                                setEmailSubmissionMethodResponseBuilderPort
                                                .reset()
                                                .oldState(preAccountState.state())
                                                .newState(postAccountState.state())
                                                .created(emailSubmissionCreated)
                                                .build(),
                                setEmailMethodResponseBuilderPort
                                                .reset()
                                                .accountId(accountId)
                                                .oldState(preAccountState.state())
                                                .newState(postAccountState.state())
                                                .updated(updateEmailResultOnSuccess.updated())
                                                .build());
        }

}
