package it.qbsoftware.adapters.in.jmaplib.method.call.other;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.call.core.EchoMethodCall;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class EchoMethodCallAdapterTest {

    @Mock private EchoMethodCall echoMethodCall;

    @InjectMocks private EchoMethodCallAdapter echoMethodCallAdapter;

    @Test
    public void testPayload() {
        when(echoMethodCall.getLibraryName()).thenReturn("payload");

        String payload = echoMethodCallAdapter.payload();
        assertEquals("payload", payload);
    }
}
