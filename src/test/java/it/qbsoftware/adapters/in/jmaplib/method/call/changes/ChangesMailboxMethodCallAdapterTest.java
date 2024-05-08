package it.qbsoftware.adapters.in.jmaplib.method.call.changes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.call.mailbox.ChangesMailboxMethodCall;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ChangesMailboxMethodCallAdapterTest {

    @Mock private ChangesMailboxMethodCall changesMailboxMethodCall;

    @InjectMocks private ChangesMailboxMethodCallAdapter changesMailboxMethodCallAdapter;

    @Test
    public void testAccountId() {
        when(changesMailboxMethodCall.getAccountId()).thenReturn("accountId");

        String accountId = changesMailboxMethodCallAdapter.accountId();
        assertEquals("accountId", accountId);
    }

    @Test
    public void testGetSinceState() {
        when(changesMailboxMethodCall.getSinceState()).thenReturn("state");

        String state = changesMailboxMethodCallAdapter.getSinceState();
        assertEquals("state", state);
    }

    @Test
    public void testGetMaxChanges() {
        when(changesMailboxMethodCall.getMaxChanges()).thenReturn(1L);

        Long changes = changesMailboxMethodCallAdapter.getMaxChanges();
        assertEquals(1L, changes);
    }
}
