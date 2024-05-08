package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;
import java.lang.reflect.Field;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import rs.ltt.jmap.common.entity.Thread;
import rs.ltt.jmap.common.entity.Thread.ThreadBuilder;

public class ThreadBuilderAdapterTest {
    @Test
    void testBuild() {
        ThreadBuilder threadBuilder = mock(ThreadBuilder.class);
        ThreadBuilderAdapter adapter = new ThreadBuilderAdapter(threadBuilder);

        Thread thread = mock(Thread.class);

        when(threadBuilder.build()).thenReturn(thread);

        ThreadPort threadPort = adapter.build();

        assertEquals(ThreadAdapter.class, threadPort.getClass());
    }

    @Test
    void testClearEmailIds() {
        ThreadBuilder threadBuilder = mock(ThreadBuilder.class);
        ThreadBuilderAdapter adapter = new ThreadBuilderAdapter(threadBuilder);

        adapter.clearEmailIds();
        verify(threadBuilder).clearEmailIds();
    }

    @Test
    void testEmailIds() {
        List<String> emailIds = List.of("emailId1", "emailId2");
        ThreadBuilder threadBuilder = mock(ThreadBuilder.class);
        ThreadBuilderAdapter adapter = new ThreadBuilderAdapter(threadBuilder);

        adapter.emailIds(emailIds);
        verify(threadBuilder).emailIds(emailIds);
    }

    @Test
    void testId() {
        ThreadBuilder threadBuilder = mock(ThreadBuilder.class);
        ThreadBuilderAdapter adapter = new ThreadBuilderAdapter(threadBuilder);

        adapter.id("id");
        verify(threadBuilder).id("id");
    }

    @Test
    void testReset() {
        ThreadBuilder threadBuilder = mock(ThreadBuilder.class);
        threadBuilder = Thread.builder();
        ThreadBuilderAdapter adapter = new ThreadBuilderAdapter(threadBuilder);

        try (MockedStatic<Thread> identityStatic = Mockito.mockStatic(Thread.class)) {
            identityStatic.when(Thread::builder).thenReturn(threadBuilder);
            assertEquals(adapter.reset(), adapter);
            identityStatic.verify(Thread::builder);
        }
    }

    @Test
    void testConstructor() throws Exception {
        ThreadBuilderAdapter adapter = new ThreadBuilderAdapter();

        Field threadBuilderField = adapter.getClass().getDeclaredField("threadBuilder");
        threadBuilderField.setAccessible(true);
        ThreadBuilder threadBuilder = (ThreadBuilder) threadBuilderField.get(adapter);

        assertNotNull(adapter);
        assertNotNull(threadBuilder);
    }
}
