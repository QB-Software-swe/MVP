package it.qbsoftware.boot;

import com.google.common.collect.ArrayListMultimap;

import it.qbsoftware.domain.CallableMethods.UnknownMethodCall;
import it.qbsoftware.domain.Handlers.GetMethodCallHandler;
import it.qbsoftware.domain.Handlers.HandlerRequest;
import it.qbsoftware.domain.Handlers.MethodCallHandlerBase;
import rs.ltt.jmap.common.method.call.email.ChangesEmailMethodCall;

public class Boot {
    public static void main(String[] args) {
        MethodCallHandlerBase getMethodCallHandler = new GetMethodCallHandler();
        var h = new HandlerRequest(ChangesEmailMethodCall.builder().sinceState("0").accountId("0").build(), ArrayListMultimap.create());
        if(UnknownMethodCall.class == getMethodCallHandler.handle(h).getClass()) {
            System.out.println("Unk");
        } else { 
            System.out.println("Not Unk");
        }
    }
}
