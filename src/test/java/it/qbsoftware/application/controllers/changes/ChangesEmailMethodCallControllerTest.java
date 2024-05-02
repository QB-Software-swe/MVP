package it.qbsoftware.application.controllers.changes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.adapters.in.jmaplib.method.response.changes.ChangesEmailMethodResponseAdapter;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesEmailMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.ChangesEmailMethodCall;
import rs.ltt.jmap.common.method.error.CannotCalculateChangesMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;
import rs.ltt.jmap.common.method.response.email.ChangesEmailMethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ChangesEmailMethodCallControllerTest {

    @Mock
    ChangesEmailMethodCallUsecase changesEmailMethodCallUsecase;

    @Mock
    ListMultimapPort<String,ResponseInvocationPort> previousResponses;

    @Mock
    ChangesEmailMethodResponseAdapter changesEmailMethodResponseAdapter;

    @Mock
    MethodCall methodCall;

    @Mock
    ChangesEmailMethodResponse changesEmailMethodResponse;

    @InjectMocks
    ChangesEmailMethodCallController changesEmailMethodCallController;


    @Test
    public void testHandle() throws Exception {
        ChangesEmailMethodCall changesEmailMethodCall = new ChangesEmailMethodCall("null", "null", 1L);
        HandlerRequest handlerRequest = new HandlerRequest(changesEmailMethodCall, previousResponses);

        when(changesEmailMethodCallUsecase.call(any(),any())).thenReturn(changesEmailMethodResponseAdapter);
        when(changesEmailMethodResponseAdapter.adaptee()).thenReturn(changesEmailMethodResponse);

        MethodResponse[] result = changesEmailMethodCallController.handle(handlerRequest);

        assertEquals(changesEmailMethodResponse, result[0]);
    }

    
    @Test
    public void testHandleWithAccountNotFoundException() throws Exception {
        ChangesEmailMethodCall changesEmailMethodCall = new ChangesEmailMethodCall("null", "null", 1L);
        HandlerRequest handlerRequest = new HandlerRequest(changesEmailMethodCall, previousResponses);

        when(changesEmailMethodCallUsecase.call(any(),any())).thenThrow(AccountNotFoundException.class);

        MethodResponse[] result = changesEmailMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleWithCannotCalculateChangesException() throws Exception {
        ChangesEmailMethodCall changesEmailMethodCall = new ChangesEmailMethodCall("null", "null", 1L);
        HandlerRequest handlerRequest = new HandlerRequest(changesEmailMethodCall, previousResponses);

        when(changesEmailMethodCallUsecase.call(any(),any())).thenThrow(CannotCalculateChangesException.class);

        MethodResponse[] result = changesEmailMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof CannotCalculateChangesMethodErrorResponse);
    }

    @Test
    public void testHandleWithInvalidArgumentsException() throws Exception {
        ChangesEmailMethodCall changesEmailMethodCall = new ChangesEmailMethodCall("null", "null", 1L);
        HandlerRequest handlerRequest = new HandlerRequest(changesEmailMethodCall, previousResponses);

        when(changesEmailMethodCallUsecase.call(any(),any())).thenThrow(InvalidArgumentsException.class);

        MethodResponse[] result = changesEmailMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleSuper() throws Exception {

        HandlerRequest handlerRequest = new HandlerRequest(methodCall, previousResponses);

        MethodResponse[] result = changesEmailMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof UnknownMethodMethodErrorResponse);
    }


}
