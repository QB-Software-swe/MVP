package it.qbsoftware.domain.callhandler.querychanges;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.command.MethodCallCommand;
import it.qbsoftware.domain.command.UnknownCommand;
import it.qbsoftware.domain.command.querychanges.QueryChangesEmailCommand;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.call.email.QueryChangesEmailMethodCall;

public class QueryChangesMethodCallHandlerTest {
    @Test
    public void testQueryChangesMethodCall() {
        QueryChangesMethodCallHandler queryChangesMethodCallHandler = new QueryChangesMethodCallHandler();
        HandlerRequest handlerRequest = new HandlerRequest(
                QueryChangesEmailMethodCall.builder().accountId("0").sinceQueryState("0").build(), null);

        MethodCallCommand resultCommand = queryChangesMethodCallHandler.handle(handlerRequest);

        assertSame(resultCommand.getClass(), QueryChangesEmailCommand.class);
    }

    @Test
    public void testInvalidRequest() {
        QueryChangesMethodCallHandler queryChangesMethodCallHandler = new QueryChangesMethodCallHandler();
        HandlerRequest handlerRequest = new HandlerRequest(new MethodCall() {
        }, null);

        MethodCallCommand resultCommand = queryChangesMethodCallHandler.handle(handlerRequest);

        assertSame(resultCommand.getClass(), UnknownCommand.class);
    }
}
