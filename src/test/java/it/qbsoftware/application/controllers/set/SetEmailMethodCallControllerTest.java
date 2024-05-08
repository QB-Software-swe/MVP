package it.qbsoftware.application.controllers.set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.method.response.set.SetEmailMethodResponseAdapter;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.StateMismatchException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.usecase.set.SetEmailMethodCallUsecase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.SetEmailMethodCall;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.StateMismatchMethodErrorResponse;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;
import rs.ltt.jmap.common.method.response.email.SetEmailMethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SetEmailMethodCallControllerTest {

    @Mock SetEmailMethodCallUsecase setEmailMethodCallUsecase;

    @Mock ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock SetEmailMethodResponseAdapter setEmailMethodResponseAdapter;

    @Mock MethodCall methodCall;

    @Mock SetEmailMethodResponse setEmailMethodResponse;

    @Mock SetEmailMethodCall setEmailMethodCall;

    @InjectMocks SetEmailMethodCallController setEmailMethodCallController;

    @Test
    public void testHandle() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(setEmailMethodCall, previousResponses);

        when(setEmailMethodCallUsecase.call(any(), any()))
                .thenReturn(setEmailMethodResponseAdapter);
        when(setEmailMethodResponseAdapter.adaptee()).thenReturn(setEmailMethodResponse);

        MethodResponse[] result = setEmailMethodCallController.handle(handlerRequest);

        assertEquals(setEmailMethodResponse, result[0]);
    }

    @Test
    public void testHandleWithAccountNotFoundException() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(setEmailMethodCall, previousResponses);

        when(setEmailMethodCallUsecase.call(any(), any()))
                .thenThrow(AccountNotFoundException.class);

        MethodResponse[] result = setEmailMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleWithStateMismatchException() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(setEmailMethodCall, previousResponses);

        when(setEmailMethodCallUsecase.call(any(), any())).thenThrow(StateMismatchException.class);

        MethodResponse[] result = setEmailMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof StateMismatchMethodErrorResponse);
    }

    @Test
    public void testHandleSuper() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(methodCall, previousResponses);

        MethodResponse[] result = setEmailMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof UnknownMethodMethodErrorResponse);
    }
}
