package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import rs.ltt.jmap.common.entity.SetError;
import rs.ltt.jmap.common.entity.SetErrorType;

public class SetErrorEnumAdapter implements SetErrorEnumPort {

    @Override
    public SetErrorPort singleton() {
        return new SetErrorAdapter(new SetError(SetErrorType.SINGLETON, ""));
    }

    @Override
    public SetErrorPort invalidProperties() {
        return new SetErrorAdapter(new SetError(SetErrorType.INVALID_PROPERTIES, ""));
    }

    @Override
    public SetErrorPort notFound() {
        return new SetErrorAdapter(new SetError(SetErrorType.NOT_FOUND, ""));
    }

    @Override
    public SetErrorPort invalidPatch() {
        return new SetErrorAdapter(new SetError(SetErrorType.INVALID_PATCH, ""));
    }

}
