package it.qbsoftware.adapters.in.jmaplib.method.response.get;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.response.mailbox.GetMailboxMethodResponse;

public class GetMailboxMethodResponseAdapterTest {

    @Mock private GetMailboxMethodResponse getMailboxMethodResponse;

    @InjectMocks private GetMailboxMethodResponseAdapter getMailboxMethodResponseAdapter;

    @Test
    public void testAdaptee() {
        GetMailboxMethodResponseAdapter getMailboxMethodResponseAdapter =
                new GetMailboxMethodResponseAdapter(getMailboxMethodResponse);

        assertEquals(getMailboxMethodResponse, getMailboxMethodResponseAdapter.adaptee());
    }
}
