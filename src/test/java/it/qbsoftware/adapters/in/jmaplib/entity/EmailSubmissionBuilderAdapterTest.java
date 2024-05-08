package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import rs.ltt.jmap.common.entity.EmailSubmission;
import rs.ltt.jmap.common.entity.EmailSubmission.EmailSubmissionBuilder;

public class EmailSubmissionBuilderAdapterTest {

    @Test
    public void testEmailSubmissionBuilderAdapter() {
        EmailSubmissionBuilderAdapter adapter = new EmailSubmissionBuilderAdapter();
        assertNotNull(adapter);
    }

    @Test
    public void testId() throws NoSuchFieldException, IllegalAccessException {
        final String id = "idTest";
        EmailSubmissionBuilderAdapter adapter = new EmailSubmissionBuilderAdapter();
        adapter.id(id);

        Field idField = adapter.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        assertEquals(id, idField.get(adapter));
    }

    @Test
    public void testBuild() {
        EmailSubmissionBuilderAdapter adapter = mock(EmailSubmissionBuilderAdapter.class);
        adapter.build();
        assertNotNull(adapter);
        verify(adapter).build();
    }

    @Test
    public void testEmailId() throws NoSuchFieldException, IllegalAccessException {
        String emailId = "emailId";

        EmailSubmissionBuilder emailSubmissionBuilder = mock(EmailSubmissionBuilder.class);
        EmailSubmissionBuilderAdapter emailSubmissionBuilderAdapter =
                new EmailSubmissionBuilderAdapter();

        Field emailSubmissionBuilderField =
                emailSubmissionBuilderAdapter.getClass().getDeclaredField("emailSubmissionBuilder");
        emailSubmissionBuilderField.setAccessible(true);

        emailSubmissionBuilderField.set(emailSubmissionBuilderAdapter, emailSubmissionBuilder);

        emailSubmissionBuilderAdapter.emailId(emailId);
        verify(emailSubmissionBuilder).emailId("emailId");
    }

    @Test
    public void testThreadId() throws NoSuchFieldException, IllegalAccessException {
        String threadId = "threadId";

        EmailSubmissionBuilder emailSubmissionBuilder = mock(EmailSubmissionBuilder.class);
        EmailSubmissionBuilderAdapter emailSubmissionBuilderAdapter =
                new EmailSubmissionBuilderAdapter();

        Field emailSubmissionBuilderField =
                emailSubmissionBuilderAdapter.getClass().getDeclaredField("emailSubmissionBuilder");
        emailSubmissionBuilderField.setAccessible(true);

        emailSubmissionBuilderField.set(emailSubmissionBuilderAdapter, emailSubmissionBuilder);

        emailSubmissionBuilderAdapter.threadId(threadId);
        verify(emailSubmissionBuilder).threadId("threadId");
    }

    @Test
    public void testSentAt() throws NoSuchFieldException, IllegalAccessException {
        Instant instant = Instant.now();

        EmailSubmissionBuilder emailSubmissionBuilder = mock(EmailSubmissionBuilder.class);
        EmailSubmissionBuilderAdapter emailSubmissionBuilderAdapter =
                new EmailSubmissionBuilderAdapter();

        Field emailSubmissionBuilderField =
                emailSubmissionBuilderAdapter.getClass().getDeclaredField("emailSubmissionBuilder");
        emailSubmissionBuilderField.setAccessible(true);

        emailSubmissionBuilderField.set(emailSubmissionBuilderAdapter, emailSubmissionBuilder);

        emailSubmissionBuilderAdapter.sentAt(instant);
        verify(emailSubmissionBuilder).sendAt(instant);
    }

    @Test
    public void testReset() {
        EmailSubmissionBuilderAdapter identityBuilderAdapter = new EmailSubmissionBuilderAdapter();

        try (MockedStatic<EmailSubmission> identityStatic =
                Mockito.mockStatic(EmailSubmission.class)) {
            assertEquals(identityBuilderAdapter.reset(), identityBuilderAdapter);
            identityStatic.verify(EmailSubmission::builder);
        }
    }

    @Test
    public void testDeliveryStatus() {
        EmailSubmissionBuilderAdapter adapter = new EmailSubmissionBuilderAdapter();

        Map<String, String> deliveryStatus = new HashMap<>();
        deliveryStatus.put("key", "value");
        deliveryStatus.put("key2", "value2");

        adapter.deliveryStatus(deliveryStatus);
        assertNotNull(adapter);
    }

    @Test
    public void testBuild1() {
        EmailSubmissionBuilder emailSubmissionBuilder = mock(EmailSubmissionBuilder.class);
        EmailSubmission emailSubmission = mock(EmailSubmission.class);

        when(emailSubmissionBuilder.build()).thenReturn(emailSubmission);

        EmailSubmissionBuilderAdapter adapter = new EmailSubmissionBuilderAdapter();
        EmailSubmissionPort result = adapter.build1();

        assertNotNull(result);
    }
}
