package it.qbsoftware.adapters.in.jmaplib.error;

import it.qbsoftware.business.ports.in.jmap.error.StateMismatchMethodErrorResponsePort;
import rs.ltt.jmap.common.method.error.StateMismatchMethodErrorResponse;

public class StateMismatchMethodErrorResponseAdapter implements StateMismatchMethodErrorResponsePort{
    StateMismatchMethodErrorResponse stateMismatchMethodErrorResponse;

    public StateMismatchMethodErrorResponseAdapter(){
        this.stateMismatchMethodErrorResponse = new StateMismatchMethodErrorResponse();
    }

}
