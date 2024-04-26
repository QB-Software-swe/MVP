package it.qbsoftware.business.domain.methodcall.statematch;

import it.qbsoftware.business.domain.exception.StateMismatchException;

public interface IfInStateMatch {

    void methodStateMatchCurrent(String methodCallState, String currentObjectState)
            throws StateMismatchException;

}