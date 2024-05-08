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
import rs.ltt.jmap.common.method.call.thread.GetThreadMethodCall;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GetThreadMethodCallAdapterTest {

    @Mock private GetThreadMethodCall getThreadMethodCall;

    @InjectMocks private GetThreadMethodCallAdapter getThreadMethodCallAdapter;

    @Test
    public void testAccountId() {
        when(getThreadMethodCall.getAccountId()).thenReturn("accountId");

        String accountId = getThreadMethodCallAdapter.accountId();
        assertEquals("accountId", accountId);
    }

    @Test
    public void testGetIds() {
        String[] idStrings = {"1", "2", "3"};
        when(getThreadMethodCall.getIds()).thenReturn(idStrings);

        String[] ids = getThreadMethodCallAdapter.getIds();
        assertEquals(idStrings, ids);
    }

    @Test
    public void testGetIdsReference() {
        ResultReference resultReference = mock(ResultReference.class);
        when(getThreadMethodCall.getIdsReference()).thenReturn(resultReference);

        InvocationResultReferencePort result = getThreadMethodCallAdapter.getIdsReference();
        assertTrue(result instanceof InvocationResultReferencePort);
    }

    @Test
    public void testGetIdsReferenceNull() {
        when(getThreadMethodCall.getIdsReference()).thenReturn(null);

        InvocationResultReferencePort result = getThreadMethodCallAdapter.getIdsReference();
        assertNull(result);
    }

    @Test
    public void testGetProperties() {
        String[] propertiesStrings = getThreadMethodCall.getProperties();
        when(getThreadMethodCall.getProperties()).thenReturn(propertiesStrings);

        String[] properties = getThreadMethodCallAdapter.getProperties();
        assertEquals(propertiesStrings, properties);
    }
}
