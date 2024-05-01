package it.qbsoftware.business.services.set;

import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailSubmissionMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.set.SetEmailSubmissionMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.EmailSubmissionRepository;
import kotlin.NotImplementedError;

public class SetEmailSubmissionMethodCallService implements SetEmailSubmissionMethodCallUsecase {
    private final AccountStateRepository accountStateRepository;
    private final EmailSubmissionRepository emailSubmissionRepository;

    public SetEmailSubmissionMethodCallService(final AccountStateRepository accountStateRepository, final EmailSubmissionRepository emailSubmissionRepository) {
        this.accountStateRepository = accountStateRepository;
        this.emailSubmissionRepository = emailSubmissionRepository;}

    @Override
    public MethodResponsePort[] call(final SetEmailSubmissionMethodCallPort setEmailSubmissionMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponse) {
                throw new NotImplementedError();
    }

}
