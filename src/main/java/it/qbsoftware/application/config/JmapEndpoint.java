package it.qbsoftware.application.config;

import it.qbsoftware.business.ports.in.jmap.EndPointConfiguration;

public class JmapEndpoint implements EndPointConfiguration {

    @Override
    public String apiEndPoint() {
        return "/api";
    }

    @Override
    public String uploadEndPoint() {
        return "/upload";
    }

    @Override
    public String downloadEndPoint() {
        return "/download";
    }

    @Override
    public String eventSourceEndPoint() {
        return "/eventsource";
    }
}
