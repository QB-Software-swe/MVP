package it.qbsoftware.adapters.in.jmaplib.method.call.get;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.Request.Invocation.ResultReference;
import rs.ltt.jmap.common.method.call.email.GetEmailMethodCall;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GetEmailMethodCallAdapterTest {

    @Mock private GetEmailMethodCall getEmailMethodCall;

    @InjectMocks private GetEmailMethodCallAdapter getEmailMethodCallAdapter;

    @Test
    public void testAccountId() {
        when(getEmailMethodCall.getAccountId()).thenReturn("accountId");

        String accountId = getEmailMethodCallAdapter.accountId();
        assertEquals("accountId", accountId);
    }

    @Test
    public void testGetBodyProperties() {
        String[] propertiesStrings = {"1", "2", "3"};
        when(getEmailMethodCall.getBodyProperties()).thenReturn(propertiesStrings);

        String[] properties = getEmailMethodCallAdapter.getBodyProperties();
        assertEquals(propertiesStrings, properties);
    }

    @Test
    public void testGetFetchAllBodyValues() {
        when(getEmailMethodCall.getFetchAllBodyValues()).thenReturn(true);

        Boolean allBody = getEmailMethodCallAdapter.getFetchAllBodyValues();
        assertEquals(true, allBody);
    }

    @Test
    public void testGetFetchHtmlBodyValues() {
        when(getEmailMethodCall.getFetchHTMLBodyValues()).thenReturn(true);

        Boolean htmlBody = getEmailMethodCallAdapter.getFetchHtmlBodyValues();
        assertEquals(true, htmlBody);
    }

    @Test
    public void testGetFetchTextBodyValues() {
        when(getEmailMethodCall.getFetchTextBodyValues()).thenReturn(true);

        Boolean textBody = getEmailMethodCallAdapter.getFetchTextBodyValues();
        assertEquals(true, textBody);
    }

    @Test
    public void testGetIds() {
        String[] idStrings = {"1", "2", "3"};
        when(getEmailMethodCall.getIds()).thenReturn(idStrings);

        String[] ids = getEmailMethodCallAdapter.getIds();
        assertEquals(idStrings, ids);
    }

    @Test
    public void testGetIdsReference() {
        ResultReference resultReference = mock(ResultReference.class);
        when(getEmailMethodCall.getIdsReference()).thenReturn(resultReference);

        InvocationResultReferencePort result = getEmailMethodCallAdapter.getIdsReference();
        assertTrue(result instanceof InvocationResultReferencePort);
    }

    @Test
    public void testGetIdsReferenceNull() {
        when(getEmailMethodCall.getIdsReference()).thenReturn(null);

        InvocationResultReferencePort result = getEmailMethodCallAdapter.getIdsReference();
        assertNull(result);
    }

    @Test
    public void testGetMaxBodyValueBytes() {
        when(getEmailMethodCall.getMaxBodyValueBytes()).thenReturn(1L);

        Long valueBytes = getEmailMethodCallAdapter.getMaxBodyValueBytes();
        assertEquals(1L, valueBytes);
    }

    @Test
    public void testGetProperties() {
        String[] propertiesStrings = getEmailMethodCall.getProperties();
        when(getEmailMethodCall.getProperties()).thenReturn(propertiesStrings);

        String[] properties = getEmailMethodCallAdapter.getProperties();
        assertEquals(propertiesStrings, properties);
    }
}
