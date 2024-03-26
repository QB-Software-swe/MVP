package it.qbsoftware.domain.command;

import rs.ltt.jmap.common.method.MethodResponse;

public interface MethodCallCommand {
    MethodResponse[] execute();   
}