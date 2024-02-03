package it.qbsoftware.core;

import com.google.common.collect.ListMultimap;

import rs.ltt.jmap.common.Response;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.core.EchoMethodCall;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;

public class JMAP {

    public MethodResponse[] dispatch(
      final MethodCall methodCall,
      final ListMultimap<String, Response.Invocation> previousResponses) 
      {
        return switch(methodCall) {
            case EchoMethodCall echoCall -> {
                yield new MethodResponse[] {new UnknownMethodMethodErrorResponse()};
            }

            default -> {
                yield new MethodResponse[] {new UnknownMethodMethodErrorResponse()};
            }
        };
    }
}
