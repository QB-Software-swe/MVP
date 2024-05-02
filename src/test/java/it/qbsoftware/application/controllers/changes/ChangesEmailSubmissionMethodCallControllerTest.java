package it.qbsoftware.application.controllers.changes;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.adapters.in.jmaplib.method.response.changes.ChangesEmailSubmissionMethodResponseAdapter;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesEmailSubmissionMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.response.submission.ChangesEmailSubmissionMethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ChangesEmailSubmissionMethodCallControllerTest {

    @Mock
    ChangesEmailSubmissionMethodCallUsecase changesEmailSubmissionMethodCallUsecase;

    @Mock
    ListMultimapPort<String,ResponseInvocationPort> previousResponses;

    @Mock
    ChangesEmailSubmissionMethodResponseAdapter changesEmailSubmissionMethodResponseAdapter;

    @Mock
    MethodCall methodCall;

    @Mock
    ChangesEmailSubmissionMethodResponse changesEmailSubmissionMethodResponse;

    @InjectMocks
    ChangesEmailSubmissionMethodCallController changesEmailSubmissionMethodCallController;

}
