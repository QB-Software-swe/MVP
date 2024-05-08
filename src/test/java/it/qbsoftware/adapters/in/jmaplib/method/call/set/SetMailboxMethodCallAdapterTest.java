package it.qbsoftware.adapters.in.jmaplib.method.call.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.entity.Mailbox;
import rs.ltt.jmap.common.method.call.mailbox.SetMailboxMethodCall;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SetMailboxMethodCallAdapterTest {

    @Mock private SetMailboxMethodCall setMailboxMethodCall;

    @InjectMocks private SetMailboxMethodCallAdapter setMailboxMethodCallAdapter;

    @Test
    public void testAccountId() {
        when(setMailboxMethodCall.getAccountId()).thenReturn("accountId");

        String accountId = setMailboxMethodCallAdapter.accountId();
        assertEquals("accountId", accountId);
    }

    @Test
    public void testGetCreate() {
        Mailbox mailbox = mock(Mailbox.class);
        Map<String, Mailbox> mailboxMap = new HashMap<>();
        mailboxMap.put("key", mailbox);
        when(setMailboxMethodCall.getCreate()).thenReturn(mailboxMap);

        Map<String, MailboxPort> create = setMailboxMethodCallAdapter.getCreate();
        assertEquals(mailboxMap.size(), create.size());
    }

    @Test
    public void testGetDestroy() {
        String[] destroyString = setMailboxMethodCall.getDestroy();
        when(setMailboxMethodCall.getDestroy()).thenReturn(destroyString);

        String[] destroy = setMailboxMethodCallAdapter.getDestroy();
        assertEquals(destroyString, destroy);
    }

    @Test
    public void testGetUpdate() {
        Map<String, Map<String, Object>> updateMap = Map.of("accountId", Map.of("key", "value"));
        when(setMailboxMethodCall.getUpdate()).thenReturn(updateMap);

        Map<String, Map<String, Object>> update = setMailboxMethodCallAdapter.getUpdate();
        assertEquals(updateMap, update);
    }

    @Test
    public void testIfInState() {
        when(setMailboxMethodCall.getIfInState()).thenReturn("state");

        String state = setMailboxMethodCallAdapter.ifInState();
        assertEquals("state", state);
    }
}
