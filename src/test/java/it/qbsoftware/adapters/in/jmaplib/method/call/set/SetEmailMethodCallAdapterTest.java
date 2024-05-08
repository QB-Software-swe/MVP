package it.qbsoftware.adapters.in.jmaplib.method.call.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.method.call.email.SetEmailMethodCall;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SetEmailMethodCallAdapterTest {

    @Mock private SetEmailMethodCall setEmailMethodCall;

    @InjectMocks private SetEmailMethodCallAdapter setEmailMethodCallAdapter;

    @Test
    public void testAccountId() {
        when(setEmailMethodCall.getAccountId()).thenReturn("accountId");

        String accountId = setEmailMethodCallAdapter.accountId();
        assertEquals("accountId", accountId);
    }

    @Test
    public void testGetCreate() {
        Email email = mock(Email.class);
        Map<String, Email> emailMap = new HashMap<>();
        emailMap.put("key", email);
        when(setEmailMethodCall.getCreate()).thenReturn(emailMap);

        Map<String, EmailPort> create = setEmailMethodCallAdapter.getCreate();
        assertEquals(emailMap.size(), create.size());
    }

    @Test
    public void testGetCreateNull() {
        when(setEmailMethodCall.getCreate()).thenReturn(null);

        Map<String, EmailPort> create = setEmailMethodCallAdapter.getCreate();
        assertEquals(null, create);
    }

    @Test
    public void testGetDestroy() {
        String[] destroyString = setEmailMethodCall.getDestroy();
        when(setEmailMethodCall.getDestroy()).thenReturn(destroyString);

        String[] destroy = setEmailMethodCallAdapter.getDestroy();
        assertEquals(destroyString, destroy);
    }

    @Test
    public void testGetUpdate() {
        Map<String, Map<String, Object>> updateMap = Map.of("accountId", Map.of("key", "value"));
        when(setEmailMethodCall.getUpdate()).thenReturn(updateMap);

        Map<String, Map<String, Object>> update = setEmailMethodCallAdapter.getUpdate();
        assertEquals(updateMap, update);
    }

    @Test
    public void testIfInState() {
        when(setEmailMethodCall.getIfInState()).thenReturn("state");

        String state = setEmailMethodCallAdapter.ifInState();
        assertEquals("state", state);
    }
}
