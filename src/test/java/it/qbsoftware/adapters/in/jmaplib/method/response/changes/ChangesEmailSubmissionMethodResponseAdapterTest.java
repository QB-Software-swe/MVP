package it.qbsoftware.adapters.in.jmaplib.method.response.changes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.response.submission.ChangesEmailSubmissionMethodResponse;

public class ChangesEmailSubmissionMethodResponseAdapterTest {
    @Mock private ChangesEmailSubmissionMethodResponse changesEmailSubmissionMethodResponse;

    @InjectMocks
    private ChangesEmailSubmissionMethodResponseAdapter changesEmailSubmissionMethodResponseAdapter;

    @Test
    public void testAdaptee() {
        ChangesEmailSubmissionMethodResponseAdapter changesEmailSubmissionMethodResponseAdapter =
                new ChangesEmailSubmissionMethodResponseAdapter(
                        changesEmailSubmissionMethodResponse);

        assertEquals(
                changesEmailSubmissionMethodResponse,
                changesEmailSubmissionMethodResponseAdapter.adaptee());
    }
}
