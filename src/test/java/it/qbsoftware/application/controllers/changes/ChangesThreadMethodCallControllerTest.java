package it.qbsoftware.application.controllers.changes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.adapters.in.jmaplib.method.response.changes.ChangesThreadMethodResponseAdapter;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesThreadMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.thread.ChangesThreadMethodCall;
import rs.ltt.jmap.common.method.error.CannotCalculateChangesMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;
import rs.ltt.jmap.common.method.response.thread.ChangesThreadMethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ChangesThreadMethodCallControllerTest {

    @Mock
    ChangesThreadMethodCallUsecase changesThreadMethodCallUsecase;

    @Mock
    ListMultimapPort<String,ResponseInvocationPort> previousResponses;

    @Mock
    ChangesThreadMethodResponseAdapter changesThreadMethodResponseAdapter;

    @Mock
    MethodCall methodCall;

    @Mock
    ChangesThreadMethodResponse changesThreadMethodResponse;

    @InjectMocks
    ChangesThreadMethodCallController changesThreadMethodCallController;


    @Test
    public void testHandle() throws Exception {
        ChangesThreadMethodCall changesThreadMethodCall = new ChangesThreadMethodCall("null", "null", 1L);
        HandlerRequest handlerRequest = new HandlerRequest(changesThreadMethodCall, previousResponses);

        when(changesThreadMethodCallUsecase.call(any(),any())).thenReturn(changesThreadMethodResponseAdapter);
        when(changesThreadMethodResponseAdapter.adaptee()).thenReturn(changesThreadMethodResponse);

        MethodResponse[] result = changesThreadMethodCallController.handle(handlerRequest);

        assertEquals(changesThreadMethodResponse, result[0]);
    }

    
    @Test
    public void testHandleWithAccountNotFoundException() throws Exception {
        ChangesThreadMethodCall changesThreadMethodCall = new ChangesThreadMethodCall("null", "null", 1L);
        HandlerRequest handlerRequest = new HandlerRequest(changesThreadMethodCall, previousResponses);

        when(changesThreadMethodCallUsecase.call(any(),any())).thenThrow(AccountNotFoundException.class);

        MethodResponse[] result = changesThreadMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleWithCannotCalculateChangesException() throws Exception {
        ChangesThreadMethodCall changesThreadMethodCall = new ChangesThreadMethodCall("null", "null", 1L);
        HandlerRequest handlerRequest = new HandlerRequest(changesThreadMethodCall, previousResponses);

        when(changesThreadMethodCallUsecase.call(any(),any())).thenThrow(CannotCalculateChangesException.class);

        MethodResponse[] result = changesThreadMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof CannotCalculateChangesMethodErrorResponse);
    }

    @Test
    public void testHandleWithInvalidArgumentsException() throws Exception {
        ChangesThreadMethodCall changesThreadMethodCall = new ChangesThreadMethodCall("null", "null", 1L);
        HandlerRequest handlerRequest = new HandlerRequest(changesThreadMethodCall, previousResponses);

        when(changesThreadMethodCallUsecase.call(any(),any())).thenThrow(InvalidArgumentsException.class);

        MethodResponse[] result = changesThreadMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleSuper() throws Exception {

        HandlerRequest handlerRequest = new HandlerRequest(methodCall, previousResponses);

        MethodResponse[] result = changesThreadMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof UnknownMethodMethodErrorResponse);
    }


}
