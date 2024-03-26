package it.qbsoftware.domain.callhandler;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import it.qbsoftware.domain.command.EchoCommand;
import it.qbsoftware.domain.command.MethodCallCommand;
import it.qbsoftware.domain.command.UnknownCommand;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.call.core.EchoMethodCall;

public class EchoMethodCallHandlerTest {
    @Test
    public void testValidRequest() {
        EchoMethodCallHandler echoMethodCallHandler = new EchoMethodCallHandler();
        HandlerRequest handlerRequest = new HandlerRequest(EchoMethodCall.builder().build(), null);

        MethodCallCommand resultCommand = echoMethodCallHandler.handle(handlerRequest);

        assertSame(resultCommand.getClass(), EchoCommand.class);
    }

    @Test
    public void testInvalidRequest() {
        EchoMethodCallHandler echoMethodCallHandler = new EchoMethodCallHandler();
        HandlerRequest handlerRequest = new HandlerRequest(new MethodCall() {
        }, null);

        MethodCallCommand resultCommand = echoMethodCallHandler.handle(handlerRequest);

        assertSame(resultCommand.getClass(), UnknownCommand.class);
    }
}
