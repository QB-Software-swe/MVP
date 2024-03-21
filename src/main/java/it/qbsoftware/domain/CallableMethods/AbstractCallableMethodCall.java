package it.qbsoftware.domain.CallableMethods;

import java.util.concurrent.Callable;

import com.google.common.collect.ListMultimap;

import rs.ltt.jmap.common.Response;
import rs.ltt.jmap.common.method.MethodResponse;

public abstract class AbstractCallableMethodCall implements Callable<MethodResponse[]> {
    protected ListMultimap<String, Response.Invocation> previousResponses;

    public AbstractCallableMethodCall(ListMultimap<String, Response.Invocation> previousResponses) {
        this.previousResponses = previousResponses;
    }
}
