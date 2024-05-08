package it.qbsoftware.application.config;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class JmapEndpointTest {
    @Test
    public void testApiEndPoint() {
        CommonJmapUrlConfiguration jmapEndpoint = new CommonJmapUrlConfiguration();
        assertEquals("/api", jmapEndpoint.apiUrl());
    }

    @Test
    public void testDownloadEndPoint() {
        CommonJmapUrlConfiguration jmapEndpoint = new CommonJmapUrlConfiguration();
        assertEquals("/upload", jmapEndpoint.uploadUrl());
    }

    @Test
    public void testEventSourceEndPoint() {
        CommonJmapUrlConfiguration jmapEndpoint = new CommonJmapUrlConfiguration();
        assertEquals("/download", jmapEndpoint.downloadUrl());
    }

    @Test
    public void testUploadEndPoint() {
        CommonJmapUrlConfiguration jmapEndpoint = new CommonJmapUrlConfiguration();
        assertNull(jmapEndpoint.eventSourceUrl());
    }
}
