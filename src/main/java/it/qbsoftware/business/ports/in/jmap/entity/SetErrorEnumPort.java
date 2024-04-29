package it.qbsoftware.business.ports.in.jmap.entity;

public interface SetErrorEnumPort {
    public SetErrorPort singleton();

    public SetErrorPort invalidProperties();

    public SetErrorPort notFound();

    public SetErrorPort invalidPatch();
}
