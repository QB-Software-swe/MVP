package it.qbsoftware.application.controllers;

import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import rs.ltt.jmap.common.method.MethodCall;

public record HandlerRequest(
        MethodCall methodCall,
        ListMultimapPort<String, ResponseInvocationPort> previousResponses) {}
