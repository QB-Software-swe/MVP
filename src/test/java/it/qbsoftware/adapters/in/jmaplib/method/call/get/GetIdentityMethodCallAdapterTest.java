package it.qbsoftware.adapters.in.jmaplib.method.call.get;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import rs.ltt.jmap.common.method.call.identity.GetIdentityMethodCall;

public class GetIdentityMethodCallAdapterTest {

    private String accountId = "account";
    private String[] ids = {"1", "2", "3"};
    private String[] properties = {"prop1", "prop2", "prop3"};
    

    GetIdentityMethodCall getIdentityMethodCall = GetIdentityMethodCall.builder()
                  .accountId(accountId)
                  .ids(ids)
                  .properties(properties)
                  .build();

    @Test
    public void testAccountId() {
        assertEquals(accountId, getIdentityMethodCall.getAccountId());
    }

    @Test
    public void testGetIds() {
        assertArrayEquals(ids, getIdentityMethodCall.getIds());
    }

    @Test
    public void testGetIdsReference() {
        assertEquals(null, getIdentityMethodCall.getIdsReference());
    }

    @Test
    public void testGetProperties() {
        assertArrayEquals(properties, getIdentityMethodCall.getProperties());
    }
}
