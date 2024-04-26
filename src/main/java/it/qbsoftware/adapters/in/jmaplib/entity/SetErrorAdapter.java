package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import rs.ltt.jmap.common.entity.SetError;
import rs.ltt.jmap.common.entity.SetErrorType;

public class SetErrorAdapter implements SetErrorPort {
    private SetError setError;

    public SetErrorAdapter(final SetError setError) {
        this.setError = setError;
    }

    @Override
    public void invalidPropertiesErorr(String message) {
        this.setError = new SetError(SetErrorType.INVALID_PROPERTIES, message);
    }

    @Override
    public void notFoundError(String message) {
        this.setError = new SetError(SetErrorType.NOT_FOUND, message);
    }

    public SetError adaptee() {
        return this.setError;
    }
}
