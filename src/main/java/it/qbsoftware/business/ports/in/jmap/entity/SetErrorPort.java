package it.qbsoftware.business.ports.in.jmap.entity;

public interface SetErrorPort {
    public void invalidPropertiesErorr(final String message);

    public void notFoundError(final String message);
}
