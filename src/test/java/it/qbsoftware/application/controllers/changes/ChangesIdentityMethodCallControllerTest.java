package it.qbsoftware.application.controllers.changes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.method.response.changes.ChangesIdentityMethodResponseAdapter;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesIdentityMethodCallUsecase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.identity.ChangesIdentityMethodCall;
import rs.ltt.jmap.common.method.error.CannotCalculateChangesMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;
import rs.ltt.jmap.common.method.response.identity.ChangesIdentityMethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ChangesIdentityMethodCallControllerTest {

    @Mock ChangesIdentityMethodCallUsecase changesIdentityMethodCallUsecase;

    @Mock ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock ChangesIdentityMethodResponseAdapter changesIdentityMethodResponseAdapter;

    @Mock MethodCall methodCall;

    @Mock ChangesIdentityMethodResponse changesIdentityMethodResponse;

    @InjectMocks ChangesIdentityMethodCallController changesIdentityMethodCallController;

    @Test
    public void testHandle() throws Exception {
        ChangesIdentityMethodCall changesIdentityMethodCall =
                new ChangesIdentityMethodCall("null", "null", 1L);
        HandlerRequest handlerRequest =
                new HandlerRequest(changesIdentityMethodCall, previousResponses);

        when(changesIdentityMethodCallUsecase.call(any(), any()))
                .thenReturn(changesIdentityMethodResponseAdapter);
        when(changesIdentityMethodResponseAdapter.adaptee())
                .thenReturn(changesIdentityMethodResponse);

        MethodResponse[] result = changesIdentityMethodCallController.handle(handlerRequest);

        assertEquals(changesIdentityMethodResponse, result[0]);
    }

    @Test
    public void testHandleWithAccountNotFoundException() throws Exception {
        ChangesIdentityMethodCall changesIdentityMethodCall =
                new ChangesIdentityMethodCall("null", "null", 1L);
        HandlerRequest handlerRequest =
                new HandlerRequest(changesIdentityMethodCall, previousResponses);

        when(changesIdentityMethodCallUsecase.call(any(), any()))
                .thenThrow(AccountNotFoundException.class);

        MethodResponse[] result = changesIdentityMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleWithCannotCalculateChangesException() throws Exception {
        ChangesIdentityMethodCall changesIdentityMethodCall =
                new ChangesIdentityMethodCall("null", "null", 1L);
        HandlerRequest handlerRequest =
                new HandlerRequest(changesIdentityMethodCall, previousResponses);

        when(changesIdentityMethodCallUsecase.call(any(), any()))
                .thenThrow(CannotCalculateChangesException.class);

        MethodResponse[] result = changesIdentityMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof CannotCalculateChangesMethodErrorResponse);
    }

    @Test
    public void testHandleWithInvalidArgumentsException() throws Exception {
        ChangesIdentityMethodCall changesIdentityMethodCall =
                new ChangesIdentityMethodCall("null", "null", 1L);
        HandlerRequest handlerRequest =
                new HandlerRequest(changesIdentityMethodCall, previousResponses);

        when(changesIdentityMethodCallUsecase.call(any(), any()))
                .thenThrow(InvalidArgumentsException.class);

        MethodResponse[] result = changesIdentityMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleSuper() throws Exception {

        HandlerRequest handlerRequest = new HandlerRequest(methodCall, previousResponses);

        MethodResponse[] result = changesIdentityMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof UnknownMethodMethodErrorResponse);
    }
}
