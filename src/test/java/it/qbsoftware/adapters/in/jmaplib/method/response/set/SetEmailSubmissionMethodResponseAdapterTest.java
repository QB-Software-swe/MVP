package it.qbsoftware.adapters.in.jmaplib.method.response.set;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.response.submission.SetEmailSubmissionMethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SetEmailSubmissionMethodResponseAdapterTest {
    @Mock private SetEmailSubmissionMethodResponse setEmailSubmissionMethodResponse;

    @InjectMocks
    private SetEmailSubmissionMethodResponseAdapter setEmailSubmissionMethodResponseAdapter;

    @Test
    public void testAdaptee() {
        SetEmailSubmissionMethodResponseAdapter setEmailSubmissionMethodResponseAdapter =
                new SetEmailSubmissionMethodResponseAdapter(setEmailSubmissionMethodResponse);

        assertEquals(
                setEmailSubmissionMethodResponse,
                setEmailSubmissionMethodResponseAdapter.adaptee());
    }
}
