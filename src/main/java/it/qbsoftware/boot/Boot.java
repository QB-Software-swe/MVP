package it.qbsoftware.boot;

import com.google.common.collect.ArrayListMultimap;

import it.qbsoftware.domain.CallableMethods.CallableUnknownMethodCall;
import it.qbsoftware.domain.MethodCallHandlers.HandlerRequest;
import it.qbsoftware.domain.MethodCallHandlers.MethodCallHandlerBase;
import it.qbsoftware.domain.MethodCallHandlers.GetMethodCallHandlers.GetMethodCallHandler;
import rs.ltt.jmap.common.method.call.email.ChangesEmailMethodCall;

public class Boot {
    public static void main(String[] args) {
        MethodCallHandlerBase getMethodCallHandler = new GetMethodCallHandler();
        var h = new HandlerRequest(ChangesEmailMethodCall.builder().sinceState("0").accountId("0").build(), ArrayListMultimap.create());
        if(CallableUnknownMethodCall.class == getMethodCallHandler.handle(h).getClass()) {
            System.out.println("Unk");
        } else { 
            System.out.println("Not Unk");
        }
    }
}
