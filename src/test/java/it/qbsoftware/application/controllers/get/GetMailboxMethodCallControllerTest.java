package it.qbsoftware.application.controllers.get;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetMailboxMethodResponseAdapter;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.usecase.get.GetMailboxMethodCallUsecase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.mailbox.GetMailboxMethodCall;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidResultReferenceMethodErrorResponse;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;
import rs.ltt.jmap.common.method.response.mailbox.GetMailboxMethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GetMailboxMethodCallControllerTest {

    @Mock GetMailboxMethodCallUsecase getMailboxMethodCallUsecase;

    @Mock ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock GetMailboxMethodResponseAdapter getMailboxMethodResponseAdapter;

    @Mock MethodCall methodCall;

    @Mock GetMailboxMethodResponse getMailboxMethodResponse;

    @Mock GetMailboxMethodCall getMailboxMethodCall;

    @InjectMocks GetMailboxMethodCallController getMailboxMethodCallController;

    @Test
    public void testHandle() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(getMailboxMethodCall, previousResponses);

        when(getMailboxMethodCallUsecase.call(any(), any()))
                .thenReturn(getMailboxMethodResponseAdapter);
        when(getMailboxMethodResponseAdapter.adaptee()).thenReturn(getMailboxMethodResponse);

        MethodResponse[] result = getMailboxMethodCallController.handle(handlerRequest);

        assertEquals(getMailboxMethodResponse, result[0]);
    }

    @Test
    public void testHandleWithAccountNotFoundException() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(getMailboxMethodCall, previousResponses);

        when(getMailboxMethodCallUsecase.call(any(), any()))
                .thenThrow(AccountNotFoundException.class);

        MethodResponse[] result = getMailboxMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleWithInvalidResultReferenceExecption() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(getMailboxMethodCall, previousResponses);

        when(getMailboxMethodCallUsecase.call(any(), any()))
                .thenThrow(InvalidResultReferenceExecption.class);

        MethodResponse[] result = getMailboxMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidResultReferenceMethodErrorResponse);
    }

    @Test
    public void testHandleWithInvalidArgumentsException() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(getMailboxMethodCall, previousResponses);

        when(getMailboxMethodCallUsecase.call(any(), any()))
                .thenThrow(InvalidArgumentsException.class);

        MethodResponse[] result = getMailboxMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleSuper() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(methodCall, previousResponses);

        MethodResponse[] result = getMailboxMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof UnknownMethodMethodErrorResponse);
    }
}
