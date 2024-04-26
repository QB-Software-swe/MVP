package it.qbsoftware.business.domain.methodcall.statematch;

import it.qbsoftware.business.domain.exception.StateMismatchException;

public class StandardIfInStateMatch implements IfInStateMatch {
    @Override
    public void methodStateMatchCurrent(final String methodCallState, final String currentObjectState)
            throws StateMismatchException {
        if (methodCallState != null && methodCallState != currentObjectState) {
            throw new StateMismatchException();
        }
    }
}
