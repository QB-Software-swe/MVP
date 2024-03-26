package it.qbsoftware.domain.callhandler.get;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.command.MethodCallCommand;
import it.qbsoftware.domain.command.UnknownCommand;
import it.qbsoftware.domain.command.get.GetEmailCommand;
import it.qbsoftware.domain.command.get.GetIdentityCommand;
import it.qbsoftware.domain.command.get.GetMailboxCommand;
import it.qbsoftware.domain.command.get.GetThreadCommand;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.call.email.GetEmailMethodCall;
import rs.ltt.jmap.common.method.call.identity.GetIdentityMethodCall;
import rs.ltt.jmap.common.method.call.mailbox.GetMailboxMethodCall;
import rs.ltt.jmap.common.method.call.thread.GetThreadMethodCall;

public class GetMethodCallHandlerTest {
    @Test
    public void testGetEmailMethodCall() {
        GetMethodCallHandler getMethodCallHandler = new GetMethodCallHandler();
        HandlerRequest handlerRequest = new HandlerRequest(GetEmailMethodCall.builder().accountId("0").build(), null);

        MethodCallCommand methodCallCommand = getMethodCallHandler.handle(handlerRequest);

        assertSame(methodCallCommand.getClass(), GetEmailCommand.class);
    }

    @Test
    public void testGetIdentiyMethodCall() {
        GetMethodCallHandler getMethodCallHandler = new GetMethodCallHandler();
        HandlerRequest handlerRequest = new HandlerRequest(GetIdentityMethodCall.builder().accountId("0").build(), null);

        MethodCallCommand methodCallCommand = getMethodCallHandler.handle(handlerRequest);

        assertSame(methodCallCommand.getClass(), GetIdentityCommand.class);
    }

    @Test
    public void testGetMailboxMethodCall() {
        GetMethodCallHandler getMethodCallHandler = new GetMethodCallHandler();
        HandlerRequest handlerRequest = new HandlerRequest(GetMailboxMethodCall.builder().accountId("0").build(), null);

        MethodCallCommand methodCallCommand = getMethodCallHandler.handle(handlerRequest);

        assertSame(methodCallCommand.getClass(), GetMailboxCommand.class);
    }

    @Test
    public void testGetThreadMethodCall() {
        GetMethodCallHandler getMethodCallHandler = new GetMethodCallHandler();
        HandlerRequest handlerRequest = new HandlerRequest(GetThreadMethodCall.builder().accountId("0").build(), null);

        MethodCallCommand methodCallCommand = getMethodCallHandler.handle(handlerRequest);

        assertSame(methodCallCommand.getClass(), GetThreadCommand.class);
    }

    @Test
    public void testInvalidRequest() {
        GetMethodCallHandler getMethodCallHandler = new GetMethodCallHandler();
        HandlerRequest handlerRequest = new HandlerRequest(new MethodCall() {
        }, null);

        MethodCallCommand methodCallCommand = getMethodCallHandler.handle(handlerRequest);

        assertSame(methodCallCommand.getClass(), UnknownCommand.class);
    }
}
