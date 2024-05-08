package it.qbsoftware.application.controllers.set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.method.response.set.SetIdentityMethodResponseAdapter;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.StateMismatchException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.usecase.set.SetIdentityMethodCallUsecase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.identity.SetIdentityMethodCall;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.StateMismatchMethodErrorResponse;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;
import rs.ltt.jmap.common.method.response.identity.SetIdentityMethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SetIdentityMethodCallControllerTest {

    @Mock SetIdentityMethodCallUsecase setIdentityMethodCallUsecase;

    @Mock ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock SetIdentityMethodResponseAdapter setIdentityMethodResponseAdapter;

    @Mock MethodCall methodCall;

    @Mock SetIdentityMethodResponse setIdentityMethodResponse;

    @Mock SetIdentityMethodCall setIdentityMethodCall;

    @InjectMocks SetIdentityMethodCallController setIdentityMethodCallController;

    @Test
    public void testHandle() throws Exception {
        HandlerRequest handlerRequest =
                new HandlerRequest(setIdentityMethodCall, previousResponses);

        when(setIdentityMethodCallUsecase.call(any(), any()))
                .thenReturn(setIdentityMethodResponseAdapter);
        when(setIdentityMethodResponseAdapter.adaptee()).thenReturn(setIdentityMethodResponse);

        MethodResponse[] result = setIdentityMethodCallController.handle(handlerRequest);

        assertEquals(setIdentityMethodResponse, result[0]);
    }

    @Test
    public void testHandleWithAccountNotFoundException() throws Exception {
        HandlerRequest handlerRequest =
                new HandlerRequest(setIdentityMethodCall, previousResponses);

        when(setIdentityMethodCallUsecase.call(any(), any()))
                .thenThrow(AccountNotFoundException.class);

        MethodResponse[] result = setIdentityMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleWithStateMismatchException() throws Exception {
        HandlerRequest handlerRequest =
                new HandlerRequest(setIdentityMethodCall, previousResponses);

        when(setIdentityMethodCallUsecase.call(any(), any()))
                .thenThrow(StateMismatchException.class);

        MethodResponse[] result = setIdentityMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof StateMismatchMethodErrorResponse);
    }

    @Test
    public void testHandleSuper() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(methodCall, previousResponses);

        MethodResponse[] result = setIdentityMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof UnknownMethodMethodErrorResponse);
    }
}
