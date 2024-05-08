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
import rs.ltt.jmap.common.method.call.mailbox.GetMailboxMethodCall;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GetMailboxMethodCallAdapterTest {

    @Mock private GetMailboxMethodCall getMailboxMethodCall;

    @InjectMocks private GetMailboxMethodCallAdapter getMailboxMethodCallAdapter;

    @Test
    public void testAccountId() {
        when(getMailboxMethodCall.getAccountId()).thenReturn("accountId");

        String accountId = getMailboxMethodCallAdapter.accountId();
        assertEquals("accountId", accountId);
    }

    @Test
    public void testGetIds() {
        String[] idStrings = {"1", "2", "3"};
        when(getMailboxMethodCall.getIds()).thenReturn(idStrings);

        String[] ids = getMailboxMethodCallAdapter.getIds();
        assertEquals(idStrings, ids);
    }

    @Test
    public void testGetIdsReference() {
        ResultReference resultReference = mock(ResultReference.class);
        when(getMailboxMethodCall.getIdsReference()).thenReturn(resultReference);

        InvocationResultReferencePort result = getMailboxMethodCallAdapter.getIdsReference();
        assertTrue(result instanceof InvocationResultReferencePort);
    }

    @Test
    public void testGetIdsReferenceNull() {
        when(getMailboxMethodCall.getIdsReference()).thenReturn(null);

        InvocationResultReferencePort result = getMailboxMethodCallAdapter.getIdsReference();
        assertNull(result);
    }

    @Test
    public void testGetProperties() {
        String[] propertiesStrings = getMailboxMethodCall.getProperties();
        when(getMailboxMethodCall.getProperties()).thenReturn(propertiesStrings);

        String[] properties = getMailboxMethodCallAdapter.getProperties();
        assertEquals(propertiesStrings, properties);
    }
}
