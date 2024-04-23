package it.qbsoftware.adapters.in.jmaplib.error;

import it.qbsoftware.business.ports.in.jmap.error.AccountNotFoundMethodErrorResponsePort;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;

//FIXME: non esiste 
public class AccountNotFoundMethodErrorResponseAdapter implements AccountNotFoundMethodErrorResponsePort {
    final InvalidArgumentsMethodErrorResponse invalidArgumentsMethodErrorResponse = new InvalidArgumentsMethodErrorResponse();

    public InvalidArgumentsMethodErrorResponse invalidArgumentsMethodErrorResponse() {
        return this.invalidArgumentsMethodErrorResponse;
    }
}
