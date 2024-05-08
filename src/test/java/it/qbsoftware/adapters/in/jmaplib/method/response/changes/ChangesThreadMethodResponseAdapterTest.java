package it.qbsoftware.adapters.in.jmaplib.method.response.changes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.response.thread.ChangesThreadMethodResponse;

public class ChangesThreadMethodResponseAdapterTest {
    @Mock private ChangesThreadMethodResponse changesThreadMethodResponse;

    @InjectMocks private ChangesThreadMethodResponseAdapter changesThreadMethodResponseAdapter;

    @Test
    public void testAdaptee() {
        ChangesThreadMethodResponseAdapter changesThreadMethodResponseAdapter =
                new ChangesThreadMethodResponseAdapter(changesThreadMethodResponse);

        assertEquals(changesThreadMethodResponse, changesThreadMethodResponseAdapter.adaptee());
    }
}
