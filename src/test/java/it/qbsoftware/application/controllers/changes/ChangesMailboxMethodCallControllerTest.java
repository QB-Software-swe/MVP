package it.qbsoftware.application.controllers.changes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.method.response.changes.ChangesMailboxMethodResponseAdapter;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesMailboxMethodCallUsecase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.mailbox.ChangesMailboxMethodCall;
import rs.ltt.jmap.common.method.error.CannotCalculateChangesMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;
import rs.ltt.jmap.common.method.response.mailbox.ChangesMailboxMethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ChangesMailboxMethodCallControllerTest {

    @Mock ChangesMailboxMethodCallUsecase changesMailboxMethodCallUsecase;

    @Mock ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock ChangesMailboxMethodResponseAdapter changesMailboxMethodResponseAdapter;

    @Mock MethodCall methodCall;

    @Mock ChangesMailboxMethodResponse changesMailboxMethodResponse;

    @InjectMocks ChangesMailboxMethodCallController changesMailboxMethodCallController;

    @Test
    public void testHandle() throws Exception {
        ChangesMailboxMethodCall changesMailboxMethodCall =
                new ChangesMailboxMethodCall("null", "null", 1L);
        HandlerRequest handlerRequest =
                new HandlerRequest(changesMailboxMethodCall, previousResponses);

        when(changesMailboxMethodCallUsecase.call(any(), any()))
                .thenReturn(changesMailboxMethodResponseAdapter);
        when(changesMailboxMethodResponseAdapter.adaptee())
                .thenReturn(changesMailboxMethodResponse);

        MethodResponse[] result = changesMailboxMethodCallController.handle(handlerRequest);

        assertEquals(changesMailboxMethodResponse, result[0]);
    }

    @Test
    public void testHandleWithAccountNotFoundException() throws Exception {
        ChangesMailboxMethodCall changesMailboxMethodCall =
                new ChangesMailboxMethodCall("null", "null", 1L);
        HandlerRequest handlerRequest =
                new HandlerRequest(changesMailboxMethodCall, previousResponses);

        when(changesMailboxMethodCallUsecase.call(any(), any()))
                .thenThrow(AccountNotFoundException.class);

        MethodResponse[] result = changesMailboxMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleWithCannotCalculateChangesException() throws Exception {
        ChangesMailboxMethodCall changesMailboxMethodCall =
                new ChangesMailboxMethodCall("null", "null", 1L);
        HandlerRequest handlerRequest =
                new HandlerRequest(changesMailboxMethodCall, previousResponses);

        when(changesMailboxMethodCallUsecase.call(any(), any()))
                .thenThrow(CannotCalculateChangesException.class);

        MethodResponse[] result = changesMailboxMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof CannotCalculateChangesMethodErrorResponse);
    }

    @Test
    public void testHandleWithInvalidArgumentsException() throws Exception {
        ChangesMailboxMethodCall changesMailboxMethodCall =
                new ChangesMailboxMethodCall("null", "null", 1L);
        HandlerRequest handlerRequest =
                new HandlerRequest(changesMailboxMethodCall, previousResponses);

        when(changesMailboxMethodCallUsecase.call(any(), any()))
                .thenThrow(InvalidArgumentsException.class);

        MethodResponse[] result = changesMailboxMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleSuper() throws Exception {

        HandlerRequest handlerRequest = new HandlerRequest(methodCall, previousResponses);

        MethodResponse[] result = changesMailboxMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof UnknownMethodMethodErrorResponse);
    }
}
