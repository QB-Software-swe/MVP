package it.qbsoftware.application.controllers;

import rs.ltt.jmap.common.method.MethodCall;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.utils.ListMultimapPort;

public record HandlerRequest(MethodCall methodCall,
        ListMultimapPort<String, ResponseInvocationPort> previousResponses) {
}