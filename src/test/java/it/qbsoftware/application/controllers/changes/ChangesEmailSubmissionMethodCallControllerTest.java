package it.qbsoftware.application.controllers.changes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.adapters.in.jmaplib.method.response.changes.ChangesEmailSubmissionMethodResponseAdapter;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesEmailSubmissionMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.submission.ChangesEmailSubmissionMethodCall;
import rs.ltt.jmap.common.method.error.CannotCalculateChangesMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;
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


    @Test
    public void testHandle() throws Exception {
        ChangesEmailSubmissionMethodCall changesEmailSubmissionMethodCall = new ChangesEmailSubmissionMethodCall("null", "null", 1L);
        HandlerRequest handlerRequest = new HandlerRequest(changesEmailSubmissionMethodCall, previousResponses);

        when(changesEmailSubmissionMethodCallUsecase.call(any(),any())).thenReturn(changesEmailSubmissionMethodResponseAdapter);
        when(changesEmailSubmissionMethodResponseAdapter.adaptee()).thenReturn(changesEmailSubmissionMethodResponse);

        MethodResponse[] result = changesEmailSubmissionMethodCallController.handle(handlerRequest);

        assertEquals(changesEmailSubmissionMethodResponse, result[0]);
    }

    
    @Test
    public void testHandleWithAccountNotFoundException() throws Exception {
        ChangesEmailSubmissionMethodCall changesEmailSubmissionMethodCall = new ChangesEmailSubmissionMethodCall("null", "null", 1L);
        HandlerRequest handlerRequest = new HandlerRequest(changesEmailSubmissionMethodCall, previousResponses);

        when(changesEmailSubmissionMethodCallUsecase.call(any(),any())).thenThrow(AccountNotFoundException.class);

        MethodResponse[] result = changesEmailSubmissionMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleWithCannotCalculateChangesException() throws Exception {
        ChangesEmailSubmissionMethodCall changesEmailSubmissionMethodCall = new ChangesEmailSubmissionMethodCall("null", "null", 1L);
        HandlerRequest handlerRequest = new HandlerRequest(changesEmailSubmissionMethodCall, previousResponses);

        when(changesEmailSubmissionMethodCallUsecase.call(any(),any())).thenThrow(CannotCalculateChangesException.class);

        MethodResponse[] result = changesEmailSubmissionMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof CannotCalculateChangesMethodErrorResponse);
    }

    @Test
    public void testHandleWithInvalidArgumentsException() throws Exception {
        ChangesEmailSubmissionMethodCall changesEmailSubmissionMethodCall = new ChangesEmailSubmissionMethodCall("null", "null", 1L);
        HandlerRequest handlerRequest = new HandlerRequest(changesEmailSubmissionMethodCall, previousResponses);

        when(changesEmailSubmissionMethodCallUsecase.call(any(),any())).thenThrow(InvalidArgumentsException.class);

        MethodResponse[] result = changesEmailSubmissionMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleSuper() throws Exception {

        HandlerRequest handlerRequest = new HandlerRequest(methodCall, previousResponses);

        MethodResponse[] result = changesEmailSubmissionMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof UnknownMethodMethodErrorResponse);
    }


}
