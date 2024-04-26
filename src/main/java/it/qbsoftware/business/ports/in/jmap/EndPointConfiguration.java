package it.qbsoftware.business.ports.in.jmap;

public interface EndPointConfiguration {
    public String apiEndPoint();

    public String uploadEndPoint();

    public String downloadEndPoint();

    public String eventSourceEndPoint();
}
