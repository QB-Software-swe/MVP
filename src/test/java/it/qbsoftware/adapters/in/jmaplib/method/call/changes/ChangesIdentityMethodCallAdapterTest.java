package it.qbsoftware.adapters.in.jmaplib.method.call.changes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.call.identity.ChangesIdentityMethodCall;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ChangesIdentityMethodCallAdapterTest {

    @Mock private ChangesIdentityMethodCall changesIdentityMethodCall;

    @InjectMocks private ChangesIdentityMethodCallAdapter changesIdentityMethodCallAdapter;

    @Test
    public void testAccountId() {
        when(changesIdentityMethodCall.getAccountId()).thenReturn("accountId");

        String accountId = changesIdentityMethodCallAdapter.accountId();
        assertEquals("accountId", accountId);
    }

    @Test
    public void testGetSinceState() {
        when(changesIdentityMethodCall.getSinceState()).thenReturn("state");

        String state = changesIdentityMethodCallAdapter.getSinceState();
        assertEquals("state", state);
    }

    @Test
    public void testGetMaxChanges() {
        when(changesIdentityMethodCall.getMaxChanges()).thenReturn(1L);

        Long changes = changesIdentityMethodCallAdapter.getMaxChanges();
        assertEquals(1L, changes);
    }
}
