package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import rs.ltt.jmap.common.method.error.InvalidResultReferenceMethodErrorResponse;

public class InvalidResultReferenceMethodErrorResponseAdapter implements InvalidResultReferenceMethodErrorResponsePort {
    InvalidResultReferenceMethodErrorResponse invalidResultReferenceMethodErrorResponse;

    public InvalidResultReferenceMethodErrorResponseAdapter(InvalidResultReferenceMethodErrorResponse invalidResultReferenceMethodErrorResponse){
        this.invalidResultReferenceMethodErrorResponse = invalidResultReferenceMethodErrorResponse;
    }
}
