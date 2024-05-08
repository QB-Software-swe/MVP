package it.qbsoftware.adapters.in.jmaplib.method.call.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.entity.Identity;
import rs.ltt.jmap.common.method.call.identity.SetIdentityMethodCall;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SetIdentityMethodCallAdapterTest {

    @Mock private SetIdentityMethodCall setIdentityMethodCall;

    @InjectMocks private SetIdentityMethodCallAdapter setIdentityMethodCallAdapter;

    @Test
    public void testAccountId() {
        when(setIdentityMethodCall.getAccountId()).thenReturn("accountId");

        String accountId = setIdentityMethodCallAdapter.accountId();
        assertEquals("accountId", accountId);
    }

    @Test
    public void testGetCreate() {
        Identity identity = mock(Identity.class);
        Map<String, Identity> identityMap = new HashMap<>();
        identityMap.put("key", identity);
        when(setIdentityMethodCall.getCreate()).thenReturn(identityMap);

        Map<String, IdentityPort> create = setIdentityMethodCallAdapter.getCreate();
        assertEquals(identityMap.size(), create.size());
    }

    @Test
    public void testGetDestroy() {
        String[] destroyString = setIdentityMethodCall.getDestroy();
        when(setIdentityMethodCall.getDestroy()).thenReturn(destroyString);

        String[] destroy = setIdentityMethodCallAdapter.getDestroy();
        assertEquals(destroyString, destroy);
    }

    @Test
    public void testGetUpdate() {
        Map<String, Map<String, Object>> updateMap = Map.of("accountId", Map.of("key", "value"));
        when(setIdentityMethodCall.getUpdate()).thenReturn(updateMap);

        Map<String, Map<String, Object>> update = setIdentityMethodCallAdapter.getUpdate();
        assertEquals(updateMap, update);
    }

    @Test
    public void testIfInState() {
        when(setIdentityMethodCall.getIfInState()).thenReturn("state");

        String state = setIdentityMethodCallAdapter.ifInState();
        assertEquals("state", state);
    }
}
