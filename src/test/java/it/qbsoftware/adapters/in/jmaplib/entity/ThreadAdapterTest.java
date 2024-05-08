package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.ports.in.jmap.entity.ThreadBuilderPort;
import java.util.List;
import org.junit.jupiter.api.Test;
import rs.ltt.jmap.common.entity.Thread;

public class ThreadAdapterTest {
    @Test
    void testAdaptee() {
        Thread thread = mock(Thread.class);
        ThreadAdapter adapter = new ThreadAdapter(thread);

        assertEquals(thread, adapter.adaptee());
    }

    @Test
    void testGetEmailIds() {
        Thread thread = mock(Thread.class);
        ThreadAdapter adapter = new ThreadAdapter(thread);

        adapter.getEmailIds();
        verify(thread).getEmailIds();
    }

    @Test
    void testGetId() {
        Thread thread = mock(Thread.class);
        ThreadAdapter adapter = new ThreadAdapter(thread);

        adapter.getId();
        verify(thread).getId();
    }

    @Test
    void testToBuilder() {
        Thread thread = mock(Thread.class);
        ThreadAdapter adapter = new ThreadAdapter(thread);
        List<String> emailIds = List.of("emailId1", "emailId2");

        when(thread.getId()).thenReturn("id");
        when(thread.getEmailIds()).thenReturn(emailIds);

        ThreadBuilderPort builder = adapter.toBuilder();

        assertEquals(ThreadBuilderAdapter.class, builder.getClass());
    }
}
