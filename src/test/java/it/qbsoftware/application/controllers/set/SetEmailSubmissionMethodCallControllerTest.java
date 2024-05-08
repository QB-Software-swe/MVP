package it.qbsoftware.application.controllers.set;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.method.response.set.SetEmailMethodResponseAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.set.SetEmailSubmissionMethodResponseAdapter;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.domain.methodresponse.SetEmailSubmissionMethodResponse;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailSubmissionMethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.set.SetEmailSubmissionMethodCallUsecase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.submission.SetEmailSubmissionMethodCall;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;
import rs.ltt.jmap.common.method.response.email.SetEmailMethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SetEmailSubmissionMethodCallControllerTest {

    @Mock SetEmailSubmissionMethodCallUsecase setEmailSubmissionMethodCallUsecase;

    @Mock ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock SetEmailSubmissionMethodResponseAdapter setEmailSubmissionMethodResponseAdapter;

    @Mock SetEmailMethodResponseAdapter setEmailMethodResponseAdapter;

    @Mock SetEmailMethodResponse setEmailMethodResponse;

    @Mock MethodCall methodCall;

    @Mock SetEmailSubmissionMethodResponse setEmailSubmissionMethodResponse;

    @Mock
    rs.ltt.jmap.common.method.response.submission.SetEmailSubmissionMethodResponse
            setEmailSubmissionMethodResponse2;

    @Mock SetEmailSubmissionMethodResponsePort setEmailSubmissionMethodResponsePort;

    @Mock SetEmailMethodResponsePort setEmailMethodResponsePort;

    @Mock SetEmailSubmissionMethodCall setEmailSubmissionMethodCall;

    @InjectMocks SetEmailSubmissionMethodCallController setEmailSubmissionMethodCallController;

    @Test
    public void testHandleWithAccountNotFoundException() throws Exception {
        HandlerRequest handlerRequest =
                new HandlerRequest(setEmailSubmissionMethodCall, previousResponses);

        when(setEmailSubmissionMethodCallUsecase.call(any(), any()))
                .thenThrow(AccountNotFoundException.class);

        MethodResponse[] result = setEmailSubmissionMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleWithSetNotFoundException() throws Exception {
        HandlerRequest handlerRequest =
                new HandlerRequest(setEmailSubmissionMethodCall, previousResponses);

        when(setEmailSubmissionMethodCallUsecase.call(any(), any()))
                .thenThrow(SetNotFoundException.class);

        MethodResponse[] result = setEmailSubmissionMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleSuper() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(methodCall, previousResponses);

        MethodResponse[] result = setEmailSubmissionMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof UnknownMethodMethodErrorResponse);
    }
}
