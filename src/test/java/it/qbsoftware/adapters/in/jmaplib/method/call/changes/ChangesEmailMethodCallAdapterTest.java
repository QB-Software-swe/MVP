package it.qbsoftware.adapters.in.jmaplib.method.call.changes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.call.email.ChangesEmailMethodCall;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ChangesEmailMethodCallAdapterTest {

    @Mock private ChangesEmailMethodCall changesEmailMethodCall;

    @InjectMocks private ChangesEmailMethodCallAdapter changesEmailMethodCallAdapter;

    @Test
    public void testAccountId() {
        when(changesEmailMethodCall.getAccountId()).thenReturn("accountId");

        String accountId = changesEmailMethodCallAdapter.accountId();
        assertEquals("accountId", accountId);
    }

    @Test
    public void testGetSinceState() {
        when(changesEmailMethodCall.getSinceState()).thenReturn("state");

        String state = changesEmailMethodCallAdapter.getSinceState();
        assertEquals("state", state);
    }

    @Test
    public void testGetMaxChanges() {
        when(changesEmailMethodCall.getMaxChanges()).thenReturn(1L);

        Long changes = changesEmailMethodCallAdapter.getMaxChanges();
        assertEquals(1L, changes);
    }
}
