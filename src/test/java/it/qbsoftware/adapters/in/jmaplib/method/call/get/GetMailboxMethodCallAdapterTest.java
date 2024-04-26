package it.qbsoftware.adapters.in.jmaplib.method.call.get;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import rs.ltt.jmap.common.method.call.mailbox.GetMailboxMethodCall;

public class GetMailboxMethodCallAdapterTest {

    private String accountId = "account";
    private String[] ids = {"1", "2", "3"};
    private String[] properties = {"prop1", "prop2", "prop3"};

    GetMailboxMethodCall getMailboxMethodCall = GetMailboxMethodCall.builder()
                  .accountId(accountId)
                  .ids(ids)
                  .properties(properties)
                  .build();

    @Test
    public void testAccountId() {
        assertEquals(accountId, getMailboxMethodCall.getAccountId());
    }

    @Test
    public void testGetIds() {
        assertArrayEquals(ids, getMailboxMethodCall.getIds());
    }

    @Test
    public void testGetIdsReference() {
        assertEquals(null, getMailboxMethodCall.getIdsReference());
    }

    @Test
    public void testGetProperties() {
        assertArrayEquals(properties, getMailboxMethodCall.getProperties());
    }
}
