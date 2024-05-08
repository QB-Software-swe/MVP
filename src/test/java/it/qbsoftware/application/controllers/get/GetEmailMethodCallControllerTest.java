package it.qbsoftware.application.controllers.get;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetEmailMethodResponseAdapter;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.usecase.get.GetEmailMethodCallUsecase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.GetEmailMethodCall;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidResultReferenceMethodErrorResponse;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;
import rs.ltt.jmap.common.method.response.email.GetEmailMethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GetEmailMethodCallControllerTest {

    @Mock GetEmailMethodCallUsecase getEmailMethodCallUsecase;

    @Mock ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock GetEmailMethodResponseAdapter getEmailMethodResponseAdapter;

    @Mock MethodCall methodCall;

    @Mock GetEmailMethodResponse getEmailMethodResponse;

    @Mock GetEmailMethodCall getEmailMethodCall;

    @InjectMocks GetEmailMethodCallController getEmailMethodCallController;

    @Test
    public void testHandle() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(getEmailMethodCall, previousResponses);

        when(getEmailMethodCallUsecase.call(any(), any()))
                .thenReturn(getEmailMethodResponseAdapter);
        when(getEmailMethodResponseAdapter.adaptee()).thenReturn(getEmailMethodResponse);

        MethodResponse[] result = getEmailMethodCallController.handle(handlerRequest);

        assertEquals(getEmailMethodResponse, result[0]);
    }

    @Test
    public void testHandleWithAccountNotFoundException() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(getEmailMethodCall, previousResponses);

        when(getEmailMethodCallUsecase.call(any(), any()))
                .thenThrow(AccountNotFoundException.class);

        MethodResponse[] result = getEmailMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleWithInvalidResultReferenceExecption() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(getEmailMethodCall, previousResponses);

        when(getEmailMethodCallUsecase.call(any(), any()))
                .thenThrow(InvalidResultReferenceExecption.class);

        MethodResponse[] result = getEmailMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidResultReferenceMethodErrorResponse);
    }

    @Test
    public void testHandleWithInvalidArgumentsException() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(getEmailMethodCall, previousResponses);

        when(getEmailMethodCallUsecase.call(any(), any()))
                .thenThrow(InvalidArgumentsException.class);

        MethodResponse[] result = getEmailMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleSuper() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(methodCall, previousResponses);

        MethodResponse[] result = getEmailMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof UnknownMethodMethodErrorResponse);
    }
}
