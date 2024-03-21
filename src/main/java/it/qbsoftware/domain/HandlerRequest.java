package it.qbsoftware.domain;

import com.google.common.collect.ListMultimap;

import rs.ltt.jmap.common.Response;
import rs.ltt.jmap.common.method.MethodCall;

public record HandlerRequest(MethodCall methodCall, ListMultimap<String, Response.Invocation> previousResponses) {
}