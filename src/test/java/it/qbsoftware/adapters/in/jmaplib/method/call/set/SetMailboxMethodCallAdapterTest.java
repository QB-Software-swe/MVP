package it.qbsoftware.adapters.in.jmaplib.method.call.set;

import org.junit.Test;
import rs.ltt.jmap.common.entity.Mailbox;
import rs.ltt.jmap.common.method.call.mailbox.SetMailboxMethodCall;
import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;

public class SetMailboxMethodCallAdapterTest {

    String accountId = "account";
    String ifInState = "state";
    Map<String, Mailbox> create = new HashMap<>();
    Map<String, Map<String, Object>> update = new HashMap<>();
    
    SetMailboxMethodCall setMailboxMethodCall = SetMailboxMethodCall.builder()
            .accountId(accountId)
            .ifInState(ifInState)
            .create(create)
            .update(update)
            .build();


    @Test
    public void testAccountId() {
        assertEquals(accountId, setMailboxMethodCall.getAccountId());
    }

    @Test
    public void testGetCreate() {
        assertEquals(ifInState, setMailboxMethodCall.getIfInState());
    }

    @Test
    public void testGetUpdate() {
        assertEquals(update, setMailboxMethodCall.getUpdate());
    }

    @Test
    public void testIfInState() {
        assertEquals(create, setMailboxMethodCall.getCreate());
    }
}
