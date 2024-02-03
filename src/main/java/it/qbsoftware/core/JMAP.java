package it.qbsoftware.core;

import com.google.common.collect.ListMultimap;

import rs.ltt.jmap.common.Response;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.core.EchoMethodCall;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;
import rs.ltt.jmap.common.method.response.core.EchoMethodResponse;

public class JMAP {
    
    public MethodResponse[] dispatch(
      final MethodCall methodCall,
      final ListMultimap<String, Response.Invocation> previousResponses) 
      {
        return switch(methodCall) {
            case EchoMethodCall echoCall -> {
                yield execute( echoCall, previousResponses);
            }


            default -> {
                yield new MethodResponse[] {new UnknownMethodMethodErrorResponse()};
            }
        };
    }

    

    private MethodResponse[] execute(EchoMethodCall methodCall, ListMultimap<String, Invocation> previousResponses) {
         return new MethodResponse[] {
            EchoMethodResponse.builder().libraryName(methodCall.getLibraryName()).build()
        };
    }
}
