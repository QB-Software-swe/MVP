package it.qbsoftware.adapters.in.jmaplib.method.response.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.entity.EmailAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.SetErrorAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponsePort;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.entity.SetError;
import rs.ltt.jmap.common.method.response.email.SetEmailMethodResponse;
import rs.ltt.jmap.common.method.response.email.SetEmailMethodResponse.SetEmailMethodResponseBuilder;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SetEmailMethodResponseBuilderAdapterTest {
    @Mock private SetEmailMethodResponseBuilder setEmailMethodResponseBuilder;

    @InjectMocks private SetEmailMethodResponseBuilderAdapter setEmailMethodResponseBuilderAdapter;

    @Test
    public void testAccountId() {
        when(setEmailMethodResponseBuilder.accountId("accountId"))
                .thenReturn(setEmailMethodResponseBuilder);

        SetEmailMethodResponseBuilderPort result =
                setEmailMethodResponseBuilderAdapter.accountId("accountId");
        assertTrue(result instanceof SetEmailMethodResponseBuilderAdapter);
        verify(setEmailMethodResponseBuilder).accountId("accountId");
        assertNotNull(result);
    }

    @Test
    public void testBuild() {
        SetEmailMethodResponsePort result = setEmailMethodResponseBuilderAdapter.build();

        assertNotNull(result);
    }

    @Test
    public void testCreated() {
        EmailPort emailPort = mock(EmailAdapter.class);
        Map<String, EmailPort> emailList = new HashMap<>();
        emailList.put("key", emailPort);
        Email email = mock(Email.class);

        when(((EmailAdapter) emailPort).adaptee()).thenReturn(email);

        setEmailMethodResponseBuilderAdapter.created(emailList);

        verify(setEmailMethodResponseBuilder).created(any());
    }

    @Test
    public void testCreatedNull() {
        setEmailMethodResponseBuilderAdapter.created(null);

        verify(setEmailMethodResponseBuilder).created(null);
    }

    @Test
    public void testDestroyed() {
        String[] idDestroyed = {"idDestroyed"};
        when(setEmailMethodResponseBuilder.destroyed(idDestroyed))
                .thenReturn(setEmailMethodResponseBuilder);

        SetEmailMethodResponseBuilderPort result =
                setEmailMethodResponseBuilderAdapter.destroyed(idDestroyed);
        assertTrue(result instanceof SetEmailMethodResponseBuilderAdapter);
        verify(setEmailMethodResponseBuilder).destroyed(idDestroyed);
        assertNotNull(result);
    }

    @Test
    public void testNewState() {
        when(setEmailMethodResponseBuilder.newState("newState"))
                .thenReturn(setEmailMethodResponseBuilder);

        SetEmailMethodResponseBuilderPort result =
                setEmailMethodResponseBuilderAdapter.newState("newState");
        assertTrue(result instanceof SetEmailMethodResponseBuilderAdapter);
        verify(setEmailMethodResponseBuilder).newState("newState");
        assertNotNull(result);
    }

    @Test
    public void testNotCreated() {
        SetErrorPort setErrorPort = mock(SetErrorAdapter.class);
        Map<String, SetErrorPort> emailList = new HashMap<>();
        emailList.put("key", setErrorPort);
        SetError setError = mock(SetError.class);

        when(((SetErrorAdapter) setErrorPort).adaptee()).thenReturn(setError);

        setEmailMethodResponseBuilderAdapter.notCreated(emailList);

        verify(setEmailMethodResponseBuilder).notCreated(any());
    }

    @Test
    public void testNotCreatedNull() {
        setEmailMethodResponseBuilderAdapter.notCreated(null);

        verify(setEmailMethodResponseBuilder).notCreated(null);
    }

    @Test
    public void testNotDestroyed() {
        SetErrorPort setErrorPort = mock(SetErrorAdapter.class);
        Map<String, SetErrorPort> setErrorList = new HashMap<>();
        setErrorList.put("key", setErrorPort);

        SetError setError = mock(SetError.class);

        when(((SetErrorAdapter) setErrorPort).adaptee()).thenReturn(setError);

        setEmailMethodResponseBuilderAdapter.notDestroyed(setErrorList);

        verify(setEmailMethodResponseBuilder).notDestroyed(any());
    }

    @Test
    public void testNotDestroyedNull() {
        setEmailMethodResponseBuilderAdapter.notDestroyed(null);

        verify(setEmailMethodResponseBuilder).notDestroyed(null);
    }

    @Test
    public void testNotUpdated() {
        SetErrorPort setErrorPort = mock(SetErrorAdapter.class);
        Map<String, SetErrorPort> setErrorList = new HashMap<>();
        setErrorList.put("key", setErrorPort);

        SetError setError = mock(SetError.class);

        when(((SetErrorAdapter) setErrorPort).adaptee()).thenReturn(setError);

        setEmailMethodResponseBuilderAdapter.notUpdated(setErrorList);

        verify(setEmailMethodResponseBuilder).notUpdated(any());
    }

    @Test
    public void testNotUpdatedNull() {
        setEmailMethodResponseBuilderAdapter.notUpdated(null);

        verify(setEmailMethodResponseBuilder).notUpdated(null);
    }

    @Test
    public void testOldState() {
        when(setEmailMethodResponseBuilder.oldState("oldState"))
                .thenReturn(setEmailMethodResponseBuilder);

        SetEmailMethodResponseBuilderPort result =
                setEmailMethodResponseBuilderAdapter.oldState("oldState");
        assertTrue(result instanceof SetEmailMethodResponseBuilderAdapter);
        verify(setEmailMethodResponseBuilder).oldState("oldState");
        assertNotNull(result);
    }

    @Test
    public void testReset() {
        try (MockedStatic<SetEmailMethodResponse> setEmailMethodResponseStatic =
                Mockito.mockStatic(SetEmailMethodResponse.class)) {
            assertEquals(
                    setEmailMethodResponseBuilderAdapter.reset(),
                    setEmailMethodResponseBuilderAdapter);
            setEmailMethodResponseStatic.verify(SetEmailMethodResponse::builder);
        }
    }

    @Test
    public void testUpdated() {
        EmailPort emailPort = mock(EmailAdapter.class);
        Map<String, EmailPort> emailList = new HashMap<>();
        emailList.put("key", emailPort);

        Email email = mock(Email.class);

        when(((EmailAdapter) emailPort).adaptee()).thenReturn(email);

        setEmailMethodResponseBuilderAdapter.updated(emailList);

        verify(setEmailMethodResponseBuilder).updated(any());
    }

    @Test
    public void testUpdatedNull() {
        setEmailMethodResponseBuilderAdapter.updated(null);

        verify(setEmailMethodResponseBuilder).updated(null);
    }
}
