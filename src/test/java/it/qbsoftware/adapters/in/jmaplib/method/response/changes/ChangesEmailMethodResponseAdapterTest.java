package it.qbsoftware.adapters.in.jmaplib.method.response.changes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.response.email.ChangesEmailMethodResponse;

public class ChangesEmailMethodResponseAdapterTest {
    @Mock private ChangesEmailMethodResponse changesEmailMethodResponse;

    @InjectMocks private ChangesEmailMethodResponseAdapter changesEmailMethodResponseAdapter;

    @Test
    public void testAdaptee() {
        ChangesEmailMethodResponseAdapter changesEmailMethodResponseAdapter =
                new ChangesEmailMethodResponseAdapter(changesEmailMethodResponse);

        assertEquals(changesEmailMethodResponse, changesEmailMethodResponseAdapter.adaptee());
    }
}
