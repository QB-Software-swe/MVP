package it.qbsoftware.adapters.in.jmaplib.method.response.get;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.entity.ThreadAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetThreadMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetThreadMethodResponsePort;
import java.lang.reflect.Field;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import rs.ltt.jmap.common.method.response.thread.GetThreadMethodResponse;
import rs.ltt.jmap.common.method.response.thread.GetThreadMethodResponse.GetThreadMethodResponseBuilder;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GetThreadMethodResponseBuilderAdapterTest {

    @Mock private GetThreadMethodResponseBuilder getThreadMethodResponseBuilder;

    @InjectMocks
    private GetThreadMethodResponseBuilderAdapter getThreadMethodResponseBuilderAdapter;

    @Test
    public void testBuild() {
        GetThreadMethodResponsePort result = getThreadMethodResponseBuilderAdapter.build();

        assertNotNull(result);
    }

    @Test
    public void testList() {

        ThreadPort threadPort = mock(ThreadAdapter.class);
        ThreadPort[] threadList = new ThreadPort[] {threadPort};

        getThreadMethodResponseBuilderAdapter.list(threadList);

        verify(getThreadMethodResponseBuilder).list(any());
    }

    @Test
    public void testNotFound() {
        String[] notFoundString = {"n1", "n2"};
        when(getThreadMethodResponseBuilder.notFound(notFoundString))
                .thenReturn(getThreadMethodResponseBuilder);

        GetThreadMethodResponseBuilderPort result =
                getThreadMethodResponseBuilderAdapter.notFound(notFoundString);
        assertTrue(result instanceof GetThreadMethodResponseBuilderAdapter);
        verify(getThreadMethodResponseBuilder).notFound(notFoundString);
        assertNotNull(result);
    }

    @Test
    public void testReset() {

        try (MockedStatic<GetThreadMethodResponse> getThreadMethodResponseStatic =
                Mockito.mockStatic(GetThreadMethodResponse.class)) {
            assertEquals(
                    getThreadMethodResponseBuilderAdapter.reset(),
                    getThreadMethodResponseBuilderAdapter);
            getThreadMethodResponseStatic.verify(GetThreadMethodResponse::builder);
        }
    }

    @Test
    public void testState() {

        when(getThreadMethodResponseBuilder.state("stato"))
                .thenReturn(getThreadMethodResponseBuilder);

        GetThreadMethodResponseBuilderPort result =
                getThreadMethodResponseBuilderAdapter.state("stato");
        assertTrue(result instanceof GetThreadMethodResponseBuilderAdapter);
        verify(getThreadMethodResponseBuilder).state("stato");
        assertNotNull(result);
    }

    @Test
    public void testConstructor()
            throws NoSuchFieldException,
                    SecurityException,
                    IllegalArgumentException,
                    IllegalAccessException {
        GetThreadMethodResponseBuilderAdapter getThreadMethodResponseBuilderAdapter =
                new GetThreadMethodResponseBuilderAdapter();

        Field field =
                GetThreadMethodResponseBuilderAdapter.class.getDeclaredField(
                        "getThreadMethodResponseBuilder");
        field.setAccessible(true);
        var getThreadMethodResponse = field.get(getThreadMethodResponseBuilderAdapter);
        assertNotNull(getThreadMethodResponse);
    }
}
