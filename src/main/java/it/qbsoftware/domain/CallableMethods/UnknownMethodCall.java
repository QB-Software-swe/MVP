package it.qbsoftware.domain.CallableMethods;

import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;

public class UnknownMethodCall extends AbstractCallableMethodCall{

    @Override
    public MethodResponse[] call() throws Exception {
        return new MethodResponse[] {new UnknownMethodMethodErrorResponse()};
    }
    
}
