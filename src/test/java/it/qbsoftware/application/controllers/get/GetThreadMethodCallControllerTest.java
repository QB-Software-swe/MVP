package it.qbsoftware.application.controllers.get;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetThreadMethodResponseAdapter;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.usecase.get.GetThreadMethodCallUsecase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.thread.GetThreadMethodCall;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidResultReferenceMethodErrorResponse;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;
import rs.ltt.jmap.common.method.response.thread.GetThreadMethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GetThreadMethodCallControllerTest {

    @Mock GetThreadMethodCallUsecase getThreadMethodCallUsecase;

    @Mock ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock GetThreadMethodResponseAdapter getThreadMethodResponseAdapter;

    @Mock MethodCall methodCall;

    @Mock GetThreadMethodResponse getThreadMethodResponse;

    @Mock GetThreadMethodCall getThreadMethodCall;

    @InjectMocks GetThreadMethodCallController getThreadMethodCallController;

    @Test
    public void testHandle() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(getThreadMethodCall, previousResponses);

        when(getThreadMethodCallUsecase.call(any(), any()))
                .thenReturn(getThreadMethodResponseAdapter);
        when(getThreadMethodResponseAdapter.adaptee()).thenReturn(getThreadMethodResponse);

        MethodResponse[] result = getThreadMethodCallController.handle(handlerRequest);

        assertEquals(getThreadMethodResponse, result[0]);
    }

    @Test
    public void testHandleWithAccountNotFoundException() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(getThreadMethodCall, previousResponses);

        when(getThreadMethodCallUsecase.call(any(), any()))
                .thenThrow(AccountNotFoundException.class);

        MethodResponse[] result = getThreadMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleWithInvalidResultReferenceExecption() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(getThreadMethodCall, previousResponses);

        when(getThreadMethodCallUsecase.call(any(), any()))
                .thenThrow(InvalidResultReferenceExecption.class);

        MethodResponse[] result = getThreadMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidResultReferenceMethodErrorResponse);
    }

    @Test
    public void testHandleWithInvalidArgumentsException() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(getThreadMethodCall, previousResponses);

        when(getThreadMethodCallUsecase.call(any(), any()))
                .thenThrow(InvalidArgumentsException.class);

        MethodResponse[] result = getThreadMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleSuper() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(methodCall, previousResponses);

        MethodResponse[] result = getThreadMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof UnknownMethodMethodErrorResponse);
    }
}
