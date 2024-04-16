package it.qbsoftware.adapters.in.jmaplib.error;

import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import it.qbsoftware.business.ports.in.jmap.error.InvalidArgumentsMethodErrorResponsePort;

public class InvalidArgumentsMethodErrorResponseAdapter implements InvalidArgumentsMethodErrorResponsePort {
    final InvalidArgumentsMethodErrorResponse invalidArgumentsMethodErrorResponse = new InvalidArgumentsMethodErrorResponse();

    public InvalidArgumentsMethodErrorResponse invalidArgumentsMethodErrorResponse() {
        return this.invalidArgumentsMethodErrorResponse;
    }
}
