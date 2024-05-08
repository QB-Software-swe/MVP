package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.ports.in.jmap.capability.CapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.ClassAccountCapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourcePort;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import rs.ltt.jmap.common.SessionResource;
import rs.ltt.jmap.common.SessionResource.SessionResourceBuilder;
import rs.ltt.jmap.common.entity.Capability;

public class SessionResourceBuilderAdapterTest {

    @Test
    void testAccounts() throws NoSuchFieldException, SecurityException {
        SessionResourceBuilder sessionResourceBuilder = mock(SessionResourceBuilder.class);
        SessionResourceBuilderAdapter sessionResourceBuilderAdapter =
                new SessionResourceBuilderAdapter();

        Field sessionResourceBuilderField =
                sessionResourceBuilderAdapter.getClass().getDeclaredField("sessionResourceBuilder");
        sessionResourceBuilderField.setAccessible(true);
        sessionResourceBuilderAdapter.sessionResourceBuilder = sessionResourceBuilder;

        sessionResourceBuilderAdapter.accounts(anyMap());
        verify(sessionResourceBuilder).accounts(anyMap());
    }

    @Test
    void testApiUrl() throws NoSuchFieldException, SecurityException {
        String apiString = "apiString";

        SessionResourceBuilder sessionResourceBuilder = mock(SessionResourceBuilder.class);
        SessionResourceBuilderAdapter sessionResourceBuilderAdapter =
                new SessionResourceBuilderAdapter();

        Field sessionResourceBuilderField =
                sessionResourceBuilderAdapter.getClass().getDeclaredField("sessionResourceBuilder");
        sessionResourceBuilderField.setAccessible(true);

        sessionResourceBuilderAdapter.sessionResourceBuilder = sessionResourceBuilder;

        sessionResourceBuilderAdapter.apiUrl(apiString);
        verify(sessionResourceBuilder).apiUrl(apiString);
    }

    @Test
    void testBuild() throws NoSuchFieldException, SecurityException {
        SessionResourceBuilder sessionResourceBuilder = mock(SessionResourceBuilder.class);
        SessionResource sessionResource = mock(SessionResource.class);

        when(sessionResourceBuilder.build()).thenReturn(sessionResource);

        SessionResourceBuilderAdapter sessionResourceBuilderAdapter =
                new SessionResourceBuilderAdapter();

        Field sessionResourceBuilderField =
                sessionResourceBuilderAdapter.getClass().getDeclaredField("sessionResourceBuilder");
        sessionResourceBuilderField.setAccessible(true);

        sessionResourceBuilderAdapter.sessionResourceBuilder = sessionResourceBuilder;

        SessionResourcePort result = sessionResourceBuilderAdapter.build();
        assertEquals(sessionResource, ((SessionResourceAdapter) result).sessionResource);
    }

    @Test
    void testCapabilities() throws NoSuchFieldException, SecurityException {
        SessionResourceBuilder sessionResourceBuilder = mock(SessionResourceBuilder.class);
        SessionResource sessionResource = mock(SessionResource.class);

        when(sessionResourceBuilder.build()).thenReturn(sessionResource);

        SessionResourceBuilderAdapter sessionResourceBuilderAdapter =
                new SessionResourceBuilderAdapter();

        Field sessionResourceBuilderField =
                sessionResourceBuilderAdapter.getClass().getDeclaredField("sessionResourceBuilder");
        sessionResourceBuilderField.setAccessible(true);

        sessionResourceBuilderAdapter.sessionResourceBuilder = sessionResourceBuilder;

        CapabilityPort[] capabilities = new CapabilityPort[0];

        sessionResourceBuilderAdapter.capabilities(capabilities);
        verify(sessionResourceBuilder)
                .capabilities(
                        Arrays.asList(capabilities).stream()
                                .map(c -> ((CapabilityAdapter) c).adaptee())
                                .collect(Collectors.toMap(Capability::getClass, c -> c)));
    }

    @Test
    void testDownloadUrl() throws NoSuchFieldException, SecurityException {
        final String downloadUrl = "downloadUrl";

        SessionResourceBuilder sessionResourceBuilder = mock(SessionResourceBuilder.class);
        SessionResourceBuilderAdapter sessionResourceBuilderAdapter =
                new SessionResourceBuilderAdapter();

        Field sessionResourceBuilderField =
                sessionResourceBuilderAdapter.getClass().getDeclaredField("sessionResourceBuilder");
        sessionResourceBuilderField.setAccessible(true);

        sessionResourceBuilderAdapter.sessionResourceBuilder = sessionResourceBuilder;

        sessionResourceBuilderAdapter.downloadUrl(downloadUrl);
        verify(sessionResourceBuilder).downloadUrl(downloadUrl);
    }

    @Test
    void testEventSourceUrl() throws NoSuchFieldException, SecurityException {
        final String eventSourceUrl = "eventSourceUrl";

        SessionResourceBuilder sessionResourceBuilder = mock(SessionResourceBuilder.class);
        SessionResourceBuilderAdapter sessionResourceBuilderAdapter =
                new SessionResourceBuilderAdapter();

        Field sessionResourceBuilderField =
                sessionResourceBuilderAdapter.getClass().getDeclaredField("sessionResourceBuilder");
        sessionResourceBuilderField.setAccessible(true);

        sessionResourceBuilderAdapter.sessionResourceBuilder = sessionResourceBuilder;

        sessionResourceBuilderAdapter.eventSourceUrl(eventSourceUrl);
        verify(sessionResourceBuilder).eventSourceUrl(eventSourceUrl);
    }

    @Test
    void testPrimaryAccounts() throws NoSuchFieldException, SecurityException {
        SessionResourceBuilder sessionResourceBuilder = mock(SessionResourceBuilder.class);
        SessionResourceBuilderAdapter sessionResourceBuilderAdapter =
                new SessionResourceBuilderAdapter();

        Field sessionResourceBuilderField =
                sessionResourceBuilderAdapter.getClass().getDeclaredField("sessionResourceBuilder");
        sessionResourceBuilderField.setAccessible(true);
        sessionResourceBuilderAdapter.sessionResourceBuilder = sessionResourceBuilder;

        Map<ClassAccountCapabilityPort, String> primaryAccounts = new HashMap<>();

        sessionResourceBuilderAdapter.primaryAccounts(primaryAccounts);
        verify(sessionResourceBuilder).primaryAccounts(anyMap());
    }

    @Test
    void testReset() {
        SessionResourceBuilderAdapter sessionResourceBuilderAdapter =
                new SessionResourceBuilderAdapter();

        try (MockedStatic<SessionResource> sessionResourceStatic =
                Mockito.mockStatic(SessionResource.class)) {
            assertEquals(sessionResourceBuilderAdapter.reset(), sessionResourceBuilderAdapter);
            sessionResourceStatic.verify(SessionResource::builder);
        }
    }

    @Test
    void testState() throws NoSuchFieldException, SecurityException {
        String state = "state";

        SessionResourceBuilder sessionResourceBuilder = mock(SessionResourceBuilder.class);
        SessionResourceBuilderAdapter sessionResourceBuilderAdapter =
                new SessionResourceBuilderAdapter();

        Field sessionResourceBuilderField =
                sessionResourceBuilderAdapter.getClass().getDeclaredField("sessionResourceBuilder");
        sessionResourceBuilderField.setAccessible(true);

        sessionResourceBuilderAdapter.sessionResourceBuilder = sessionResourceBuilder;

        sessionResourceBuilderAdapter.state(state);
        verify(sessionResourceBuilder).state(state);
    }

    @Test
    void testUploadUrl() throws NoSuchFieldException, SecurityException {
        String uploadUrl = "uploadUrl";

        SessionResourceBuilder sessionResourceBuilder = mock(SessionResourceBuilder.class);
        SessionResourceBuilderAdapter sessionResourceBuilderAdapter =
                new SessionResourceBuilderAdapter();

        Field sessionResourceBuilderField =
                sessionResourceBuilderAdapter.getClass().getDeclaredField("sessionResourceBuilder");
        sessionResourceBuilderField.setAccessible(true);

        sessionResourceBuilderAdapter.sessionResourceBuilder = sessionResourceBuilder;

        sessionResourceBuilderAdapter.uploadUrl(uploadUrl);
        verify(sessionResourceBuilder).uploadUrl(uploadUrl);
    }

    @Test
    void testUsername() throws NoSuchFieldException, SecurityException {
        String username = "username";

        SessionResourceBuilder sessionResourceBuilder = mock(SessionResourceBuilder.class);
        SessionResourceBuilderAdapter sessionResourceBuilderAdapter =
                new SessionResourceBuilderAdapter();

        Field sessionResourceBuilderField =
                sessionResourceBuilderAdapter.getClass().getDeclaredField("sessionResourceBuilder");
        sessionResourceBuilderField.setAccessible(true);

        sessionResourceBuilderAdapter.sessionResourceBuilder = sessionResourceBuilder;

        sessionResourceBuilderAdapter.username(username);
        verify(sessionResourceBuilder).username(username);
    }
}
