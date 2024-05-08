package it.qbsoftware.application.controllers.other;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.method.response.other.EchoMethodResponseAdapter;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.usecase.EchoMethodCallUsecase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.core.EchoMethodCall;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;
import rs.ltt.jmap.common.method.response.core.EchoMethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class EchoMethodCallControllerTest {

    @Mock EchoMethodCallUsecase echoMethodCallUsecase;

    @Mock ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock EchoMethodResponseAdapter echoMethodResponseAdapter;

    @Mock MethodCall methodCall;

    @Mock EchoMethodResponse echoMethodResponse;

    @Mock EchoMethodCall echoMethodCall;

    @InjectMocks EchoMethodCallController echoMethodCallController;

    @Test
    public void testHandle() {
        HandlerRequest handlerRequest = new HandlerRequest(echoMethodCall, previousResponses);

        when(echoMethodCallUsecase.call(any())).thenReturn(echoMethodResponseAdapter);
        when(echoMethodResponseAdapter.adaptee()).thenReturn(echoMethodResponse);

        MethodResponse[] result = echoMethodCallController.handle(handlerRequest);

        assertEquals(echoMethodResponse, result[0]);
    }

    @Test
    public void testHandleSuper() {
        HandlerRequest handlerRequest = new HandlerRequest(methodCall, previousResponses);

        MethodResponse[] result = echoMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof UnknownMethodMethodErrorResponse);
    }
}
