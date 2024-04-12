package it.qbsoftware.business.ports.in.jmap.entity;

import rs.ltt.jmap.common.method.MethodCall;

public interface ResultReferencePort {
    String getId();
    String getPath();
    Class<? extends MethodCall> getClazz();
}
