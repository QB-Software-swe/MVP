package it.qbsoftware.application.controllers.get;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetIdentityMethodResponseAdapter;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.usecase.get.GetIdentityMethodCallUsecase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.identity.GetIdentityMethodCall;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidResultReferenceMethodErrorResponse;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;
import rs.ltt.jmap.common.method.response.identity.GetIdentityMethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GetIdentityMethodCallControllerTest {

    @Mock GetIdentityMethodCallUsecase getIdentityMethodCallUsecase;

    @Mock ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock GetIdentityMethodResponseAdapter getIdentityMethodResponseAdapter;

    @Mock MethodCall methodCall;

    @Mock GetIdentityMethodResponse getIdentityMethodResponse;

    @Mock GetIdentityMethodCall getIdentityMethodCall;

    @InjectMocks GetIdentityMethodCallController getIdentityMethodCallController;

    @Test
    public void testHandle() throws Exception {
        HandlerRequest handlerRequest =
                new HandlerRequest(getIdentityMethodCall, previousResponses);

        when(getIdentityMethodCallUsecase.call(any(), any()))
                .thenReturn(getIdentityMethodResponseAdapter);
        when(getIdentityMethodResponseAdapter.adaptee()).thenReturn(getIdentityMethodResponse);

        MethodResponse[] result = getIdentityMethodCallController.handle(handlerRequest);

        assertEquals(getIdentityMethodResponse, result[0]);
    }

    @Test
    public void testHandleWithAccountNotFoundException() throws Exception {
        HandlerRequest handlerRequest =
                new HandlerRequest(getIdentityMethodCall, previousResponses);

        when(getIdentityMethodCallUsecase.call(any(), any()))
                .thenThrow(AccountNotFoundException.class);

        MethodResponse[] result = getIdentityMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleWithInvalidResultReferenceExecption() throws Exception {
        HandlerRequest handlerRequest =
                new HandlerRequest(getIdentityMethodCall, previousResponses);

        when(getIdentityMethodCallUsecase.call(any(), any()))
                .thenThrow(InvalidResultReferenceExecption.class);

        MethodResponse[] result = getIdentityMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidResultReferenceMethodErrorResponse);
    }

    @Test
    public void testHandleWithInvalidArgumentsException() throws Exception {
        HandlerRequest handlerRequest =
                new HandlerRequest(getIdentityMethodCall, previousResponses);

        when(getIdentityMethodCallUsecase.call(any(), any()))
                .thenThrow(InvalidArgumentsException.class);

        MethodResponse[] result = getIdentityMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleSuper() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(methodCall, previousResponses);

        MethodResponse[] result = getIdentityMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof UnknownMethodMethodErrorResponse);
    }
}
