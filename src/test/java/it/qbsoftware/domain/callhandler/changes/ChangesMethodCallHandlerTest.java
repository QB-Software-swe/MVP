package it.qbsoftware.domain.callhandler.changes;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.command.MethodCallCommand;
import it.qbsoftware.domain.command.UnknownCommand;
import it.qbsoftware.domain.command.changes.ChangesEmailCommand;
import it.qbsoftware.domain.command.changes.ChangesMailboxCommand;
import it.qbsoftware.domain.command.changes.ChangesThreadCommand;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.call.email.ChangesEmailMethodCall;
import rs.ltt.jmap.common.method.call.mailbox.ChangesMailboxMethodCall;
import rs.ltt.jmap.common.method.call.thread.ChangesThreadMethodCall;

public class ChangesMethodCallHandlerTest {
    @Test
    public void testChangesEmailMethodCall() {
        ChangesMethodCallHandler changesMethodCallHandler = new ChangesMethodCallHandler();
        HandlerRequest handlerRequest = new HandlerRequest(ChangesEmailMethodCall.builder().sinceState("0").accountId("0").build(), null);

        MethodCallCommand methodCallCommand = changesMethodCallHandler.handle(handlerRequest);

        assertSame(methodCallCommand.getClass(), ChangesEmailCommand.class);
    }

    @Test
    public void testChangesMailboxMethodCall() {
        ChangesMethodCallHandler changesMethodCallHandler = new ChangesMethodCallHandler();
        HandlerRequest handlerRequest = new HandlerRequest(ChangesMailboxMethodCall.builder().sinceState("0").accountId("0").build(), null);

        MethodCallCommand methodCallCommand = changesMethodCallHandler.handle(handlerRequest);

        assertSame(methodCallCommand.getClass(), ChangesMailboxCommand.class);
    }

    @Test
    public void testChangesThreadMethodCall() {
        ChangesMethodCallHandler changesMethodCallHandler = new ChangesMethodCallHandler();
        HandlerRequest handlerRequest = new HandlerRequest(ChangesThreadMethodCall.builder().sinceState("0").accountId("0").build(), null);

        MethodCallCommand methodCallCommand = changesMethodCallHandler.handle(handlerRequest);

        assertSame(methodCallCommand.getClass(), ChangesThreadCommand.class);
    }

    @Test
    public void testInvalidRequest() {
        ChangesMethodCallHandler changesMethodCallHandler = new ChangesMethodCallHandler();
        HandlerRequest handlerRequest = new HandlerRequest(new MethodCall() {
        }, null);

        MethodCallCommand methodCallCommand = changesMethodCallHandler.handle(handlerRequest);

        assertSame(methodCallCommand.getClass(), UnknownCommand.class);
    }
}
