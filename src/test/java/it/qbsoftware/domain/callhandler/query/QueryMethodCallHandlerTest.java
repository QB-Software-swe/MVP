package it.qbsoftware.domain.callhandler.query;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.command.MethodCallCommand;
import it.qbsoftware.domain.command.UnknownCommand;
import it.qbsoftware.domain.command.query.QueryEmailCommand;
import it.qbsoftware.domain.command.query.QueryMailboxCommand;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.call.email.QueryEmailMethodCall;
import rs.ltt.jmap.common.method.call.mailbox.QueryMailboxMethodCall;

public class QueryMethodCallHandlerTest {
    @Test
    public void testQueryEmailMethodCall() {
        QueryMethodCallHandler queryMethodCallHandler = new QueryMethodCallHandler();
        HandlerRequest handlerRequest = new HandlerRequest(QueryEmailMethodCall.builder().accountId("0").build(), null);

        MethodCallCommand methodCallCommand = queryMethodCallHandler.handle(handlerRequest);

        assertSame(methodCallCommand.getClass(), QueryEmailCommand.class);
    }

    @Test
    public void testQueryMailboxMethodCall() {
        QueryMethodCallHandler queryMethodCallHandler = new QueryMethodCallHandler();
        HandlerRequest handlerRequest = new HandlerRequest(QueryMailboxMethodCall.builder().accountId("0").build(),
                null);

        MethodCallCommand methodCallCommand = queryMethodCallHandler.handle(handlerRequest);

        assertSame(methodCallCommand.getClass(), QueryMailboxCommand.class);
    }

    @Test
    public void testInvalidRequest() {
        QueryMethodCallHandler queryMethodCallHandler = new QueryMethodCallHandler();
        HandlerRequest handlerRequest = new HandlerRequest(new MethodCall() {
        }, null);

        MethodCallCommand methodCallCommand = queryMethodCallHandler.handle(handlerRequest);

        assertSame(methodCallCommand.getClass(), UnknownCommand.class);
    }
}
