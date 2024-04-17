package it.qbsoftware.application.controllers;

import rs.ltt.jmap.common.method.MethodCall;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;

public record HandlerRequest(MethodCall methodCall,
        ListMultimapPort<String, ResponseInvocationPort> previousResponses) {
}