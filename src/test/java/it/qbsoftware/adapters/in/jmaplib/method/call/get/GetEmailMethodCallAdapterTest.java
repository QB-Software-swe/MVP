package it.qbsoftware.adapters.in.jmaplib.method.call.get;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import rs.ltt.jmap.common.method.call.email.GetEmailMethodCall;

public class GetEmailMethodCallAdapterTest {

    private String accountId = "45";
    private String[] ids = {"1", "2", "3"};
    private String[] properties = {"prop1", "prop2", "prop3"};
    private String bodyProperties = "bodyProp";
    private Boolean fetchTextBodyValues = true;
    private Boolean fetchHtmlBodyValues = true;
    private Boolean fetchAllBodyValues = true;
    private Long maxBodyValueBytes = 100L;

   GetEmailMethodCall getEmailMethodCall = GetEmailMethodCall.builder()
                 .accountId(accountId)
                 .ids(ids)
                 .properties(properties)
                 .bodyProperties(new String[]{bodyProperties})
                 .fetchTextBodyValues(fetchTextBodyValues)
                 .fetchHTMLBodyValues(fetchHtmlBodyValues)
                 .fetchAllBodyValues(fetchAllBodyValues)
                 .maxBodyValueBytes(maxBodyValueBytes)
                 .build();
    

    @Test
    public void testAccountId() {
        assertEquals(accountId, getEmailMethodCall.getAccountId());
    }


    @Test
    public void testGetBodyProperties() {
        assertArrayEquals(new String[]{bodyProperties}, getEmailMethodCall.getBodyProperties());
    }

    @Test
    public void testGetFetchAllBodyValues() {
        assertEquals(fetchAllBodyValues, getEmailMethodCall.getFetchAllBodyValues());
    }

    @Test
    public void testGetFetchHtmlBodyValues() {
        assertEquals(fetchHtmlBodyValues, getEmailMethodCall.getFetchHTMLBodyValues());
    }

    @Test
    public void testGetFetchTextBodyValues() {
        assertEquals(fetchTextBodyValues, getEmailMethodCall.getFetchTextBodyValues());
    }

    @Test
    public void testGetIds() {
        assertArrayEquals(ids, getEmailMethodCall.getIds());
    }

    @Test
    public void testGetIdsReference() {
        assertEquals(null, getEmailMethodCall.getIdsReference());
    }

    @Test
    public void testGetMaxBodyValueBytes() {
        assertEquals(maxBodyValueBytes, getEmailMethodCall.getMaxBodyValueBytes());
    }

    @Test
    public void testGetProperties() {
        assertArrayEquals(properties, getEmailMethodCall.getProperties());
    }
}
