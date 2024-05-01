package it.qbsoftware.business.services.set;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
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

public class SetEmailSubmissionMethodCallService implements SetEmailSubmissionMethodCallUsecase {
        private final AccountStateRepository accountStateRepository;
        private final EmailSubmissionBuilderPort emailSubmissionBuilderPort;
        private final UpdateEmail updateEmail;
        private final SetEmailSubmissionMethodResponseBuilderPort setEmailSubmissionMethodResponseBuilderPort;
        private final SetEmailMethodResponseBuilderPort setEmailMethodResponseBuilderPort;

        public SetEmailSubmissionMethodCallService(
                final AccountStateRepository accountStateRepository,
                final   EmailSubmissionBuilderPort emailSubmissionBuilderPort,
                final      SetEmailMethodResponseBuilderPort setEmailMethodResponseBuilderPort,
                final     SetEmailSubmissionMethodResponseBuilderPort setEmailSubmissionMethodResponseBuilderPort,
                final       UpdateEmail updateEmail) {
                this.accountStateRepository = accountStateRepository;
                this.emailSubmissionBuilderPort = emailSubmissionBuilderPort;
                this.updateEmail = updateEmail;
                this.setEmailSubmissionMethodResponseBuilderPort = setEmailSubmissionMethodResponseBuilderPort;
                this.setEmailMethodResponseBuilderPort = setEmailMethodResponseBuilderPort;
        }

        @Override
        public SetEmailSubmissionMethodResponse call(
                        final SetEmailSubmissionMethodCallPort setEmailSubmissionMethodCallPort,
                        final ListMultimapPort<String, ResponseInvocationPort> previousResponse)
                        throws AccountNotFoundException, SetNotFoundException {
                final String accountId = setEmailSubmissionMethodCallPort.accountId();
                final AccountState preAccountState = accountStateRepository.retrive(accountId);
                final HashMap<String, EmailSubmissionPort> emailSubmissionCreated = new HashMap<String, EmailSubmissionPort>();

                final HashMap<String, String> successSubmissionEmailIdResolve = new HashMap<>();
                for (final Map.Entry<String, EmailSubmissionPort> target : setEmailSubmissionMethodCallPort.getCreate()
                                .entrySet()) {
                        final String submissionId = accountId + "/" + UUID.randomUUID().toString();

                        final String emailSubmissionTargetId = target.getValue().getEmailId();
                        successSubmissionEmailIdResolve.put("#" + target.getKey(), emailSubmissionTargetId);

                        emailSubmissionCreated.put(target.getKey(),
                                        emailSubmissionBuilderPort.reset().id(submissionId).build());
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
