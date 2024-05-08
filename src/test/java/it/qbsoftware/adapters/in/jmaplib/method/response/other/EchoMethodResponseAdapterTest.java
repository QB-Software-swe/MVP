package it.qbsoftware.adapters.in.jmaplib.method.response.other;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.response.core.EchoMethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class EchoMethodResponseAdapterTest {
    @Mock private EchoMethodResponse echoMethodResponse;

    @InjectMocks private EchoMethodResponseAdapter echoMethodResponseAdapter;

    @Test
    public void testAdaptee() {
        EchoMethodResponseAdapter echoMethodResponseAdapter =
                new EchoMethodResponseAdapter(echoMethodResponse);

        assertEquals(echoMethodResponse, echoMethodResponseAdapter.adaptee());
    }
}
