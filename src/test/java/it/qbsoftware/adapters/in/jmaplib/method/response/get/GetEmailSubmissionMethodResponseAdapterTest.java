package it.qbsoftware.adapters.in.jmaplib.method.response.get;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.response.submission.GetEmailSubmissionMethodResponse;

public class GetEmailSubmissionMethodResponseAdapterTest {

    @Mock private GetEmailSubmissionMethodResponse getEmailSubmissionMethodResponse;

    @InjectMocks
    private GetEmailSubmissionMethodResponseAdapter getEmailSubmissionMethodResponseAdapter;

    @Test
    public void testAdaptee() {
        GetEmailSubmissionMethodResponseAdapter getEmailSubmissionMethodResponseAdapter =
                new GetEmailSubmissionMethodResponseAdapter(getEmailSubmissionMethodResponse);

        assertEquals(
                getEmailSubmissionMethodResponse,
                getEmailSubmissionMethodResponseAdapter.adaptee());
    }
}
