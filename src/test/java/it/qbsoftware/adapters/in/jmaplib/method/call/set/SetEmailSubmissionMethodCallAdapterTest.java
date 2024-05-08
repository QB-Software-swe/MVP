package it.qbsoftware.adapters.in.jmaplib.method.call.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.entity.EmailSubmission;
import rs.ltt.jmap.common.method.call.submission.SetEmailSubmissionMethodCall;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SetEmailSubmissionMethodCallAdapterTest {

    @Mock private SetEmailSubmissionMethodCall setEmailSubmissionMethodCall;

    @InjectMocks private SetEmailSubmissionMethodCallAdapter setEmailSubmissionMethodCallAdapter;

    @Test
    public void testAccountId() {
        when(setEmailSubmissionMethodCall.getAccountId()).thenReturn("accountId");

        String accountId = setEmailSubmissionMethodCallAdapter.accountId();
        assertEquals("accountId", accountId);
    }

    @Test
    public void testGetCreate() {
        EmailSubmission emailSubmission = mock(EmailSubmission.class);
        Map<String, EmailSubmission> emailSubmissionMap = new HashMap<>();
        emailSubmissionMap.put("key", emailSubmission);
        when(setEmailSubmissionMethodCall.getCreate()).thenReturn(emailSubmissionMap);

        Map<String, EmailSubmissionPort> create = setEmailSubmissionMethodCallAdapter.getCreate();
        assertEquals(emailSubmissionMap.size(), create.size());
    }

    @Test
    public void testGetCreateNull() {
        when(setEmailSubmissionMethodCall.getCreate()).thenReturn(null);

        Map<String, EmailSubmissionPort> create = setEmailSubmissionMethodCallAdapter.getCreate();
        assertEquals(null, create);
    }

    @Test
    public void testGetDestroy() {
        String[] destroyString = setEmailSubmissionMethodCall.getDestroy();
        when(setEmailSubmissionMethodCall.getDestroy()).thenReturn(destroyString);

        String[] destroy = setEmailSubmissionMethodCallAdapter.getDestroy();
        assertEquals(destroyString, destroy);
    }

    @Test
    public void testGetUpdate() {
        Map<String, Map<String, Object>> updateMap = Map.of("accountId", Map.of("key", "value"));
        when(setEmailSubmissionMethodCall.getUpdate()).thenReturn(updateMap);

        Map<String, Map<String, Object>> update = setEmailSubmissionMethodCallAdapter.getUpdate();
        assertEquals(updateMap, update);
    }

    @Test
    public void testIfInState() {
        when(setEmailSubmissionMethodCall.getIfInState()).thenReturn("state");

        String state = setEmailSubmissionMethodCallAdapter.getIfInState();
        assertEquals("state", state);
    }

    @Test
    public void testGetOnSuccessUpdateEmail() {
        Map<String, Map<String, Object>> updateMap = Map.of("accountId", Map.of("key", "value"));
        when(setEmailSubmissionMethodCall.getOnSuccessUpdateEmail()).thenReturn(updateMap);

        Map<String, Map<String, Object>> update =
                setEmailSubmissionMethodCallAdapter.getOnSuccessUpdateEmail();
        assertEquals(updateMap, update);
    }
}
