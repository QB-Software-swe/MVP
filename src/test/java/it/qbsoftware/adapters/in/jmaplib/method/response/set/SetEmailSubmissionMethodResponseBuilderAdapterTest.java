package it.qbsoftware.adapters.in.jmaplib.method.response.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.entity.EmailSubmissionAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.SetErrorAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailSubmissionMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailSubmissionMethodResponsePort;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import rs.ltt.jmap.common.entity.EmailSubmission;
import rs.ltt.jmap.common.entity.SetError;
import rs.ltt.jmap.common.method.response.submission.SetEmailSubmissionMethodResponse;
import rs.ltt.jmap.common.method.response.submission.SetEmailSubmissionMethodResponse.SetEmailSubmissionMethodResponseBuilder;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SetEmailSubmissionMethodResponseBuilderAdapterTest {
    @Mock private SetEmailSubmissionMethodResponseBuilder setEmailSubmissionMethodResponseBuilder;

    @InjectMocks
    private SetEmailSubmissionMethodResponseBuilderAdapter
            setEmailSubmissionMethodResponseBuilderAdapter;

    @Test
    public void testAccountId() {
        when(setEmailSubmissionMethodResponseBuilder.accountId("accountId"))
                .thenReturn(setEmailSubmissionMethodResponseBuilder);

        SetEmailSubmissionMethodResponseBuilderPort result =
                setEmailSubmissionMethodResponseBuilderAdapter.accountId("accountId");
        assertTrue(result instanceof SetEmailSubmissionMethodResponseBuilderAdapter);
        verify(setEmailSubmissionMethodResponseBuilder).accountId("accountId");
        assertNotNull(result);
    }

    @Test
    public void testBuild() {
        SetEmailSubmissionMethodResponsePort result =
                setEmailSubmissionMethodResponseBuilderAdapter.build();

        assertNotNull(result);
    }

    @Test
    public void testCreated() {
        EmailSubmissionPort emailSubmissionPort = mock(EmailSubmissionAdapter.class);
        Map<String, EmailSubmissionPort> emailSubmissionList = new HashMap<>();
        emailSubmissionList.put("key", emailSubmissionPort);
        EmailSubmission emailSubmission = mock(EmailSubmission.class);

        when(((EmailSubmissionAdapter) emailSubmissionPort).adaptee()).thenReturn(emailSubmission);

        setEmailSubmissionMethodResponseBuilderAdapter.created(emailSubmissionList);

        verify(setEmailSubmissionMethodResponseBuilder).created(any());
    }

    @Test
    public void testDestroyed() {
        String[] idDestroyed = {"idDestroyed"};
        when(setEmailSubmissionMethodResponseBuilder.destroyed(idDestroyed))
                .thenReturn(setEmailSubmissionMethodResponseBuilder);

        SetEmailSubmissionMethodResponseBuilderPort result =
                setEmailSubmissionMethodResponseBuilderAdapter.destroyed(idDestroyed);
        assertTrue(result instanceof SetEmailSubmissionMethodResponseBuilderAdapter);
        verify(setEmailSubmissionMethodResponseBuilder).destroyed(idDestroyed);
        assertNotNull(result);
    }

    @Test
    public void testNewState() {
        when(setEmailSubmissionMethodResponseBuilder.newState("newState"))
                .thenReturn(setEmailSubmissionMethodResponseBuilder);

        SetEmailSubmissionMethodResponseBuilderPort result =
                setEmailSubmissionMethodResponseBuilderAdapter.newState("newState");
        assertTrue(result instanceof SetEmailSubmissionMethodResponseBuilderAdapter);
        verify(setEmailSubmissionMethodResponseBuilder).newState("newState");
        assertNotNull(result);
    }

    @Test
    public void testNotCreated() {
        SetErrorPort setErrorPort = mock(SetErrorAdapter.class);
        Map<String, SetErrorPort> emailSubmissionList = new HashMap<>();
        emailSubmissionList.put("key", setErrorPort);
        SetError setError = mock(SetError.class);

        when(((SetErrorAdapter) setErrorPort).adaptee()).thenReturn(setError);

        setEmailSubmissionMethodResponseBuilderAdapter.notCreated(emailSubmissionList);

        verify(setEmailSubmissionMethodResponseBuilder).notCreated(any());
    }

    @Test
    public void testNotDestroyed() {
        SetErrorPort setErrorPort = mock(SetErrorAdapter.class);
        Map<String, SetErrorPort> setErrorList = new HashMap<>();
        setErrorList.put("key", setErrorPort);

        SetError setError = mock(SetError.class);

        when(((SetErrorAdapter) setErrorPort).adaptee()).thenReturn(setError);

        setEmailSubmissionMethodResponseBuilderAdapter.notDestroyed(setErrorList);

        verify(setEmailSubmissionMethodResponseBuilder).notDestroyed(any());
    }

    @Test
    public void testNotUpdated() {
        SetErrorPort setErrorPort = mock(SetErrorAdapter.class);
        Map<String, SetErrorPort> setErrorList = new HashMap<>();
        setErrorList.put("key", setErrorPort);

        SetError setError = mock(SetError.class);

        when(((SetErrorAdapter) setErrorPort).adaptee()).thenReturn(setError);

        setEmailSubmissionMethodResponseBuilderAdapter.notUpdated(setErrorList);

        verify(setEmailSubmissionMethodResponseBuilder).notUpdated(any());
    }

    @Test
    public void testOldState() {
        when(setEmailSubmissionMethodResponseBuilder.oldState("oldState"))
                .thenReturn(setEmailSubmissionMethodResponseBuilder);

        SetEmailSubmissionMethodResponseBuilderPort result =
                setEmailSubmissionMethodResponseBuilderAdapter.oldState("oldState");
        assertTrue(result instanceof SetEmailSubmissionMethodResponseBuilderAdapter);
        verify(setEmailSubmissionMethodResponseBuilder).oldState("oldState");
        assertNotNull(result);
    }

    @Test
    public void testReset() {
        try (MockedStatic<SetEmailSubmissionMethodResponse> setEmailSubmissionMethodResponseStatic =
                Mockito.mockStatic(SetEmailSubmissionMethodResponse.class)) {
            assertEquals(
                    setEmailSubmissionMethodResponseBuilderAdapter.reset(),
                    setEmailSubmissionMethodResponseBuilderAdapter);
            setEmailSubmissionMethodResponseStatic.verify(
                    SetEmailSubmissionMethodResponse::builder);
        }
    }

    @Test
    public void testUpdated() {
        EmailSubmissionPort emailSubmissionPort = mock(EmailSubmissionAdapter.class);
        Map<String, EmailSubmissionPort> emailSubmissionList = new HashMap<>();
        emailSubmissionList.put("key", emailSubmissionPort);

        EmailSubmission emailSubmission = mock(EmailSubmission.class);

        when(((EmailSubmissionAdapter) emailSubmissionPort).adaptee()).thenReturn(emailSubmission);

        setEmailSubmissionMethodResponseBuilderAdapter.updated(emailSubmissionList);

        verify(setEmailSubmissionMethodResponseBuilder).updated(any());
    }
}
