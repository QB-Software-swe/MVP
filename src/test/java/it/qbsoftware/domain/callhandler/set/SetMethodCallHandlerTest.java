package it.qbsoftware.domain.callhandler.set;

import org.junit.Test;

import static org.junit.Assert.assertSame;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.command.MethodCallCommand;
import it.qbsoftware.domain.command.UnknownCommand;
import it.qbsoftware.domain.command.set.SetEmailCommand;
import it.qbsoftware.domain.command.set.SetMailboxCommand;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.call.email.SetEmailMethodCall;
import rs.ltt.jmap.common.method.call.mailbox.SetMailboxMethodCall;

public class SetMethodCallHandlerTest {

    @Test
    public void testSetEmailMethodCall() {
        SetMethodCallHandler setMethodCallHandler = new SetMethodCallHandler();
        HandlerRequest handlerRequest = new HandlerRequest(SetEmailMethodCall.builder().accountId("0").build(), null);

        MethodCallCommand methodCallCommand = setMethodCallHandler.handle(handlerRequest);

        assertSame(methodCallCommand.getClass(), SetEmailCommand.class);
    }

    @Test
    public void testSetMailboxMethodCall() {
        SetMethodCallHandler setMethodCallHandler = new SetMethodCallHandler();
        HandlerRequest handlerRequest = new HandlerRequest(SetMailboxMethodCall.builder().accountId("0").build(), null);

        MethodCallCommand methodCallCommand = setMethodCallHandler.handle(handlerRequest);

        assertSame(methodCallCommand.getClass(), SetMailboxCommand.class);
    }

    @Test
    public void testInvalidRequest() {
        SetMethodCallHandler setMethodCallHandler = new SetMethodCallHandler();
        HandlerRequest handlerRequest = new HandlerRequest(new MethodCall() {
        }, null);

        MethodCallCommand methodCallCommand = setMethodCallHandler.handle(handlerRequest);

        assertSame(methodCallCommand.getClass(), UnknownCommand.class);
    }
}