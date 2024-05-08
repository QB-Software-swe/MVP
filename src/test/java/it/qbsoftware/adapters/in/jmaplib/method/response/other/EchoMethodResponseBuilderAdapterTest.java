package it.qbsoftware.adapters.in.jmaplib.method.response.other;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

import it.qbsoftware.business.ports.in.jmap.method.response.other.EchoMethodResponsePort;
import java.lang.reflect.Field;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.response.core.EchoMethodResponse.EchoMethodResponseBuilder;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class EchoMethodResponseBuilderAdapterTest {
    @Mock private EchoMethodResponseBuilder echoMethodResponseBuilder;

    @InjectMocks private EchoMethodResponseBuilderAdapter echoMethodResponseBuilderAdapter;

    @Test
    public void testBuild() {
        EchoMethodResponsePort result = echoMethodResponseBuilderAdapter.build();

        assertNotNull(result);
    }

    @Test
    public void testPayload()
            throws NoSuchFieldException,
                    SecurityException,
                    IllegalArgumentException,
                    IllegalAccessException {
        String payload = "payload";

        EchoMethodResponseBuilderAdapter adapter = new EchoMethodResponseBuilderAdapter();

        Field field =
                EchoMethodResponseBuilderAdapter.class.getDeclaredField(
                        "echoMethodResponseBuilder");
        field.setAccessible(true);
        field.set(adapter, echoMethodResponseBuilder);
        EchoMethodResponseBuilder echoMethodResponseBuilder =
                (EchoMethodResponseBuilder) field.get(adapter);

        adapter.payload(payload);
        verify(echoMethodResponseBuilder).libraryName(payload);
    }
}
