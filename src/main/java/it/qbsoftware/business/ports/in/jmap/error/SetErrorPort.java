package it.qbsoftware.business.ports.in.jmap.error;

public interface SetErrorPort {
    public void invalidPropertiesErorr(final String message);

    public void notFoundError(final String message);
}
