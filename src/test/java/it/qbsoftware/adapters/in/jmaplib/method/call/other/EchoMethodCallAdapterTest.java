package it.qbsoftware.adapters.in.jmaplib.method.call.other;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import rs.ltt.jmap.common.method.call.core.EchoMethodCall;

public class EchoMethodCallAdapterTest {

    String libraryName = "libraryName";
    EchoMethodCall echoMethodCall = EchoMethodCall.builder()
            .libraryName(libraryName)
            .build();

    @Test
    public void testPayload() {
        assertEquals(libraryName, echoMethodCall.getLibraryName());
    }
}
