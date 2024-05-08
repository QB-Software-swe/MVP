package it.qbsoftware.application.config;

import it.qbsoftware.business.ports.in.jmap.JmapUrlConfiguration;

public class CommonJmapUrlConfiguration implements JmapUrlConfiguration {

    @Override
    public String apiUrl() {
        return "/api";
    }

    @Override
    public String uploadUrl() {
        return "/upload";
    }

    @Override
    public String downloadUrl() {
        return "/download";
    }

    @Override
    public String eventSourceUrl() {
        return null;
    }
}
