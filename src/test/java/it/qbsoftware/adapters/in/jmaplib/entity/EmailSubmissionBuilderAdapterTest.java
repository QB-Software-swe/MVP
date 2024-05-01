package it.qbsoftware.adapters.in.jmaplib.entity;


import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class EmailSubmissionBuilderAdapterTest {

    @Test
    public void testEmailSubmissionBuilderAdapter() {
        EmailSubmissionBuilderAdapter adapter = new EmailSubmissionBuilderAdapter();
        assertNotNull(adapter);
    }
}