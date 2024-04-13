package it.qbsoftware.business.ports.in.jmap;

public interface ChangesMailboxMethodCallPort {
    public String accountId();

    public String getSinceState();

    public Long getMaxChanges();
}
