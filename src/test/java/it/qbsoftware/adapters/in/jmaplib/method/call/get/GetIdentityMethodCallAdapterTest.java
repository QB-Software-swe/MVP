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
import rs.ltt.jmap.common.method.call.identity.GetIdentityMethodCall;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GetIdentityMethodCallAdapterTest {

    @Mock private GetIdentityMethodCall getIdentityMethodCall;

    @InjectMocks private GetIdentityMethodCallAdapter getIdentityMethodCallAdapter;

    @Test
    public void testAccountId() {
        when(getIdentityMethodCall.getAccountId()).thenReturn("accountId");

        String accountId = getIdentityMethodCallAdapter.accountId();
        assertEquals("accountId", accountId);
    }

    @Test
    public void testGetIds() {
        String[] idStrings = {"1", "2", "3"};
        when(getIdentityMethodCall.getIds()).thenReturn(idStrings);

        String[] ids = getIdentityMethodCallAdapter.getIds();
        assertEquals(idStrings, ids);
    }

    @Test
    public void testGetIdsReference() {
        ResultReference resultReference = mock(ResultReference.class);
        when(getIdentityMethodCall.getIdsReference()).thenReturn(resultReference);

        InvocationResultReferencePort result = getIdentityMethodCallAdapter.getIdsReference();
        assertTrue(result instanceof InvocationResultReferencePort);
    }

    @Test
    public void testGetIdsReferenceNull() {
        when(getIdentityMethodCall.getIdsReference()).thenReturn(null);

        InvocationResultReferencePort result = getIdentityMethodCallAdapter.getIdsReference();
        assertNull(result);
    }

    @Test
    public void testGetProperties() {
        String[] propertiesStrings = getIdentityMethodCall.getProperties();
        when(getIdentityMethodCall.getProperties()).thenReturn(propertiesStrings);

        String[] properties = getIdentityMethodCallAdapter.getProperties();
        assertEquals(propertiesStrings, properties);
    }
}
