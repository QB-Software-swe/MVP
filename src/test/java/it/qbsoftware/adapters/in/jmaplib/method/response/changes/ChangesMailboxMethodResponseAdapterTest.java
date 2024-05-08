package it.qbsoftware.adapters.in.jmaplib.method.response.changes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.response.mailbox.ChangesMailboxMethodResponse;

public class ChangesMailboxMethodResponseAdapterTest {
    @Mock private ChangesMailboxMethodResponse changesMailboxMethodResponse;

    @InjectMocks private ChangesMailboxMethodResponseAdapter changesMailboxMethodResponseAdapter;

    @Test
    public void testAdaptee() {
        ChangesMailboxMethodResponseAdapter changesMailboxMethodResponseAdapter =
                new ChangesMailboxMethodResponseAdapter(changesMailboxMethodResponse);

        assertEquals(changesMailboxMethodResponse, changesMailboxMethodResponseAdapter.adaptee());
    }
}
