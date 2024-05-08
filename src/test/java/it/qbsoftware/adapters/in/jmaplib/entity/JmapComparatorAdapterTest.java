package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import rs.ltt.jmap.common.entity.Comparator;

public class JmapComparatorAdapterTest {

    @Test
    public void testConstructor() {
        Comparator comparator = mock(Comparator.class);
        JmapComparatorAdapter jmapComparatorAdapter = new JmapComparatorAdapter(comparator);

        assertNotNull(jmapComparatorAdapter);
    }

    @Test
    public void testAdaptee() {
        Comparator comparator = mock(Comparator.class);
        JmapComparatorAdapter jmapComparatorAdapter = new JmapComparatorAdapter(comparator);

        assertEquals(comparator, jmapComparatorAdapter.adaptee());
    }
}
