package it.qbsoftware.boot;

import com.google.common.collect.ArrayListMultimap;

import it.qbsoftware.domain.callhandler.HandlerRequest;
import it.qbsoftware.domain.callhandler.MethodCallHandlerBase;
import it.qbsoftware.domain.callhandler.get.GetMethodCallHandler;
import it.qbsoftware.domain.command.UnknownCommand;
import rs.ltt.jmap.common.method.call.identity.GetIdentityMethodCall;

public class Boot {
    public static void main(String[] args) {
        MethodCallHandlerBase getMethodCallHandler = new GetMethodCallHandler();
        var h = new HandlerRequest(GetIdentityMethodCall.builder().accountId("0").build(), ArrayListMultimap.create());
        if(UnknownCommand.class == getMethodCallHandler.handle(h).getClass()) {
            System.out.println("Unk");
        } else { 
            System.out.println(getMethodCallHandler.handle(h).getClass());
        }
    }
}
