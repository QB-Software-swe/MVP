package it.qbsoftware.adapters.in.jmaplib;

import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import rs.ltt.jmap.common.method.error.InvalidResultReferenceMethodErrorResponse;

public class InvalidResultReferenceMethodErrorResponseAdapter implements InvalidResultReferenceMethodErrorResponsePort {
    InvalidResultReferenceMethodErrorResponse invalidResultReferenceMethodErrorResponse;

    public InvalidResultReferenceMethodErrorResponseAdapter(){
        this.invalidResultReferenceMethodErrorResponse = new InvalidResultReferenceMethodErrorResponse();
    }
}
