package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartPort;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import rs.ltt.jmap.common.entity.EmailBodyPart;

public class EmailBodyPartBuilderAdapterTest {

    @Test
    void testBlobId() {
        final String blobId = "blobId";
        EmailBodyPartBuilderAdapter emailBodyPartBuilderAdapter = new EmailBodyPartBuilderAdapter();

        EmailBodyPartBuilderPort result = emailBodyPartBuilderAdapter.blobId(blobId);
        assertNotNull(result);
    }

    @Test
    void testBuild() {
        EmailBodyPartBuilderAdapter emailBodyPartBuilderAdapter = new EmailBodyPartBuilderAdapter();

        EmailBodyPartPort result = emailBodyPartBuilderAdapter.build();
        assertNotNull(result);
    }

    @Test
    void testCharset() {
        final String charset = "charset";
        EmailBodyPartBuilderAdapter emailBodyPartBuilderAdapter = new EmailBodyPartBuilderAdapter();

        EmailBodyPartBuilderPort result = emailBodyPartBuilderAdapter.charset(charset);
        assertNotNull(result);
    }

    @Test
    void testName() {
        final String name = "name";
        EmailBodyPartBuilderAdapter emailBodyPartBuilderAdapter = new EmailBodyPartBuilderAdapter();

        EmailBodyPartBuilderPort result = emailBodyPartBuilderAdapter.name(name);
        assertNotNull(result);
    }

    @Test
    void testReset() {
        EmailBodyPartBuilderAdapter emailBodyPartBuilderAdapter = new EmailBodyPartBuilderAdapter();

        try (MockedStatic<EmailBodyPart> emailBodyPartStatic =
                Mockito.mockStatic(EmailBodyPart.class)) {
            assertEquals(emailBodyPartBuilderAdapter.reset(), emailBodyPartBuilderAdapter);
            emailBodyPartStatic.verify(EmailBodyPart::builder);
        }
    }

    @Test
    void testSize() {
        final Long size = 1L;
        EmailBodyPartBuilderAdapter emailBodyPartBuilderAdapter = new EmailBodyPartBuilderAdapter();

        EmailBodyPartBuilderPort result = emailBodyPartBuilderAdapter.size(size);
        assertNotNull(result);
    }

    @Test
    void testType() {
        final String type = "type";
        EmailBodyPartBuilderAdapter emailBodyPartBuilderAdapter = new EmailBodyPartBuilderAdapter();

        EmailBodyPartBuilderPort result = emailBodyPartBuilderAdapter.type(type);
        assertNotNull(result);
    }
}
