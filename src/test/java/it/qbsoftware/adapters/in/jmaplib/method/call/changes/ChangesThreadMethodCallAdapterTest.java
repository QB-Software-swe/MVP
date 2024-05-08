package it.qbsoftware.adapters.in.jmaplib.method.call.changes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.call.thread.ChangesThreadMethodCall;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ChangesThreadMethodCallAdapterTest {

    @Mock private ChangesThreadMethodCall changesThreadMethodCall;

    @InjectMocks private ChangesThreadMethodCallAdapter changesThreadMethodCallAdapter;

    @Test
    public void testAccountId() {
        when(changesThreadMethodCall.getAccountId()).thenReturn("accountId");

        String accountId = changesThreadMethodCallAdapter.accountId();
        assertEquals("accountId", accountId);
    }

    @Test
    public void testGetSinceState() {
        when(changesThreadMethodCall.getSinceState()).thenReturn("state");

        String state = changesThreadMethodCallAdapter.getSinceState();
        assertEquals("state", state);
    }

    @Test
    public void testGetMaxChanges() {
        when(changesThreadMethodCall.getMaxChanges()).thenReturn(1L);

        Long changes = changesThreadMethodCallAdapter.getMaxChanges();
        assertEquals(1L, changes);
    }
}
