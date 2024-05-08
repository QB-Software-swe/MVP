package it.qbsoftware.adapters.in.jmaplib.method.call.query;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.entity.JmapComparatorAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.JmapFilterAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.JmapComparatorPort;
import it.qbsoftware.business.ports.in.jmap.entity.JmapFilterPort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.entity.Comparator;
import rs.ltt.jmap.common.method.call.email.QueryEmailMethodCall;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class QueryEmailMethodCallAdapterTest {

    @Mock private QueryEmailMethodCall queryEmailMethodCall;

    @InjectMocks private QueryEmailMethodCallAdapter queryEmailMethodCallAdapter;

    @Test
    public void testGetAccountId() {
        when(queryEmailMethodCall.getAccountId()).thenReturn("account123");

        String accountId = queryEmailMethodCallAdapter.getAccountId();
        assertEquals("account123", accountId);
    }

    @Test
    public void testGetAnchor() {
        when(queryEmailMethodCall.getAnchor()).thenReturn("anchor");

        String anchor = queryEmailMethodCallAdapter.getAnchor();
        assertEquals("anchor", anchor);
    }

    @Test
    public void testGetAnchorOffset() {
        when(queryEmailMethodCall.getAnchorOffset()).thenReturn(1L);

        Long offSet = queryEmailMethodCallAdapter.getAnchorOffset();
        assertEquals(1L, offSet);
    }

    @Test
    public void testGetCalculateTotal() {
        when(queryEmailMethodCall.getCalculateTotal()).thenReturn(true);

        Boolean total = queryEmailMethodCallAdapter.getCalculateTotal();
        assertEquals(true, total);
    }

    @Test
    public void testGetCollapseThreads() {
        when(queryEmailMethodCall.getCollapseThreads()).thenReturn(true);

        Boolean thread = queryEmailMethodCallAdapter.getCollapseThreads();
        assertEquals(true, thread);
    }

    @Test
    public void testGetFilter() {
        JmapFilterPort<EmailPort> result = queryEmailMethodCallAdapter.getFilter();

        assertTrue(result instanceof JmapFilterAdapter<EmailPort>);
    }

    @Test
    public void testGetLimit() {
        when(queryEmailMethodCall.getLimit()).thenReturn(1L);

        Long limit = queryEmailMethodCallAdapter.getLimit();
        assertEquals(1L, limit);
    }

    @Test
    public void testGetPosition() {
        when(queryEmailMethodCall.getPosition()).thenReturn(1L);

        Long position = queryEmailMethodCallAdapter.getPosition();
        assertEquals(1L, position);
    }

    @Test
    public void testGetSortNull() {
        when(queryEmailMethodCall.getSort()).thenReturn(null);
        JmapComparatorPort[] result = queryEmailMethodCallAdapter.getSort();

        assertNull(result);
    }

    @Test
    public void testGetSortNotNull() {
        Comparator comparator = mock(Comparator.class);
        Comparator[] comparators = new Comparator[] {comparator};
        when(queryEmailMethodCall.getSort()).thenReturn(comparators);

        JmapComparatorPort[] result = queryEmailMethodCallAdapter.getSort();

        assertTrue(result instanceof JmapComparatorAdapter[]);
    }
}
