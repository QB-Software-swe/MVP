package it.qbsoftware.application.controllers.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.method.response.query.QueryEmailMethodResponseAdapter;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.query.QueryAnchorNotFoundException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.usecase.query.QueryEmailMethodCallUsecase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.QueryEmailMethodCall;
import rs.ltt.jmap.common.method.error.AnchorNotFoundMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;
import rs.ltt.jmap.common.method.response.email.QueryEmailMethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class QueryEmailMethodCallControllerTest {

    @Mock QueryEmailMethodCallUsecase queryEmailMethodCallUsecase;

    @Mock ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock QueryEmailMethodResponseAdapter queryEmailMethodResponseAdapter;

    @Mock MethodCall methodCall;

    @Mock QueryEmailMethodResponse queryEmailMethodResponse;

    @Mock QueryEmailMethodCall queryEmailMethodCall;

    @InjectMocks QueryEmailMethodCallController queryEmailMethodCallController;

    @Test
    public void testHandle() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(queryEmailMethodCall, previousResponses);

        when(queryEmailMethodCallUsecase.call(any(), any()))
                .thenReturn(queryEmailMethodResponseAdapter);
        when(queryEmailMethodResponseAdapter.adaptee()).thenReturn(queryEmailMethodResponse);

        MethodResponse[] result = queryEmailMethodCallController.handle(handlerRequest);

        assertEquals(queryEmailMethodResponse, result[0]);
    }

    @Test
    public void testHandleWithAccountNotFoundException() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(queryEmailMethodCall, previousResponses);

        when(queryEmailMethodCallUsecase.call(any(), any()))
                .thenThrow(AccountNotFoundException.class);

        MethodResponse[] result = queryEmailMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof InvalidArgumentsMethodErrorResponse);
    }

    @Test
    public void testHandleWithQueryAnchorNotFoundException() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(queryEmailMethodCall, previousResponses);

        when(queryEmailMethodCallUsecase.call(any(), any()))
                .thenThrow(QueryAnchorNotFoundException.class);

        MethodResponse[] result = queryEmailMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof AnchorNotFoundMethodErrorResponse);
    }

    @Test
    public void testHandleSuper() throws Exception {
        HandlerRequest handlerRequest = new HandlerRequest(methodCall, previousResponses);

        MethodResponse[] result = queryEmailMethodCallController.handle(handlerRequest);

        assertTrue(result[0] instanceof UnknownMethodMethodErrorResponse);
    }
}
