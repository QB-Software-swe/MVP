package it.qbsoftware.adapters.in.jmaplib.method.response.get;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.response.email.GetEmailMethodResponse;

public class GetEmailMethodResponseAdapterTest {

    @Mock private GetEmailMethodResponse getEmailMethodResponse;

    @InjectMocks private GetEmailMethodResponseAdapter getEmailMethodResponseAdapter;

    @Test
    public void testAdaptee() {
        GetEmailMethodResponseAdapter getEmailMethodResponseAdapter =
                new GetEmailMethodResponseAdapter(getEmailMethodResponse);

        assertEquals(getEmailMethodResponse, getEmailMethodResponseAdapter.adaptee());
    }
}
