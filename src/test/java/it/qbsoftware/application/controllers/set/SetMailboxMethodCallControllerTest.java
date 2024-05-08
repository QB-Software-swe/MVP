package it.qbsoftware.application.controllers.set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.method.response.set.SetMailboxMethodResponseAdapter;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.StateMismatchException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.usecase.set.SetMailboxMethodCallUsecase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.mailbox.SetMailboxMethodCall;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.StateMismatchMethodErrorResponse;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;
import rs.ltt.jmap.common.method.response.mailbox.SetMailboxMethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SetMailboxMethodCallControllerTest {

    @Mock SetMailboxMethodCallUsecase setMailboxMethodCallUsecase;

    @Mock ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock SetMailboxMethodResponseAdapter setMailboxMethodResponseAdapter;

    @Mock MethodCall methodCall;

    @Mock SetMailboxMethodResponse setMailboxMethodResponse;

    @Mock SetMailboxMethodCall setMailboxMethodCall;

    @InjectMocks SetMailboxMethodCallController setMailboxMethodCallController;

    @Test
    public void testHandle() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(setMailboxMethodCall, previousResponses);

        when(setMailboxMethodCallUsecase.call(any(), any()))
                .thenReturn(setMailboxMethodResponseAdapter);
        when(setMailboxMethodResponseAdapter.adaptee()).thenReturn(setMailboxMethodResponse);

        MethodResponse[] result = setMailboxMethodCallController.handle(handlerRequest);

        assertEquals(setMailboxMethodResponse, result[0]);
    }

    @Test
    public void testHandleWithAccountNotFoundException() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(setMailboxMethodCall, previousResponses);

        when(setMailboxMethodCallUsecase.call(any(), any()))
                .thenThrow(AccountNotFoundException.class);

        MethodResponse[] result = setMailboxMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleWithStateMismatchException() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(setMailboxMethodCall, previousResponses);

        when(setMailboxMethodCallUsecase.call(any(), any()))
                .thenThrow(StateMismatchException.class);

        MethodResponse[] result = setMailboxMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof StateMismatchMethodErrorResponse);
    }

    @Test
    public void testHandleSuper() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(methodCall, previousResponses);

        MethodResponse[] result = setMailboxMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof UnknownMethodMethodErrorResponse);
    }
}
