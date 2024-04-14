package it.qbsoftware.adapters.jmaplib.error;

import it.qbsoftware.business.ports.in.jmap.error.SetErrorPort;
import rs.ltt.jmap.common.entity.SetError;
import rs.ltt.jmap.common.entity.SetErrorType;

public class SetErrorAdapter implements SetErrorPort{
    SetError setError;

    public SetErrorAdapter(){
        this.setError = new SetError(null, null);
    }

    @Override
    public void invalidPropertiesErorr(String message) {
       this.setError = new SetError(SetErrorType.INVALID_PROPERTIES, message);
    }

    @Override
    public void notFoundError(String message) {
        this.setError = new SetError(SetErrorType.NOT_FOUND, message);
    }
    

}
