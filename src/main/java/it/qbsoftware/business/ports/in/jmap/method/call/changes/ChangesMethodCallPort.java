package it.qbsoftware.business.ports.in.jmap.method.call.changes;

public interface ChangesMethodCallPort {
    public String accountId();

    public String getSinceState();

    public Long getMaxChanges();
}
