package it.qbsoftware.adapters.in.jmaplib.method.response.get;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.response.thread.GetThreadMethodResponse;

public class GetThreadMethodResponseAdapterTest {

    @Mock private GetThreadMethodResponse getThreadMethodResponse;

    @InjectMocks private GetThreadMethodResponseAdapter getThreadMethodResponseAdapter;

    @Test
    public void testAdaptee() {
        GetThreadMethodResponseAdapter getThreadMethodResponseAdapter =
                new GetThreadMethodResponseAdapter(getThreadMethodResponse);

        assertEquals(getThreadMethodResponse, getThreadMethodResponseAdapter.adaptee());
    }
}
