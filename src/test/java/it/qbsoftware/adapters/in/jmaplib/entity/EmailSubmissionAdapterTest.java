package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import rs.ltt.jmap.common.entity.EmailSubmission;

public class EmailSubmissionAdapterTest {
    @Test
    public void testAdaptee() {
        EmailSubmission emailSubmission = mock(EmailSubmission.class);
        EmailSubmissionAdapter emailSubmissionAdapter = new EmailSubmissionAdapter(emailSubmission);

        assertEquals(emailSubmission, emailSubmissionAdapter.adaptee());
    }

    @Test
    public void testGetId() {
        EmailSubmission emailSubmission = mock(EmailSubmission.class);
        EmailSubmissionAdapter emailSubmissionAdapter = new EmailSubmissionAdapter(emailSubmission);

        emailSubmissionAdapter.getId();
        verify(emailSubmission).getId();
    }

    @Test
    public void testGetEmailId() {
        EmailSubmission emailSubmission = mock(EmailSubmission.class);
        EmailSubmissionAdapter emailSubmissionAdapter = new EmailSubmissionAdapter(emailSubmission);

        emailSubmissionAdapter.getEmailId();
        verify(emailSubmission).getEmailId();
    }
}
