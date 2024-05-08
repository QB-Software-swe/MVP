package it.qbsoftware.adapters.in.jmaplib.method.response.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.entity.IdentityAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.SetErrorAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetIdentityMethodResponsePort;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import rs.ltt.jmap.common.entity.Identity;
import rs.ltt.jmap.common.entity.SetError;
import rs.ltt.jmap.common.method.response.identity.SetIdentityMethodResponse;
import rs.ltt.jmap.common.method.response.identity.SetIdentityMethodResponse.SetIdentityMethodResponseBuilder;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SetIdentityMethodResponseBuilderAdapterTest {
    @Mock private SetIdentityMethodResponseBuilder setIdentityMethodResponseBuilder;

    @InjectMocks
    private SetIdentityMethodResponseBuilderAdapter setIdentityMethodResponseBuilderAdapter;

    @Test
    public void testAccountId() {
        when(setIdentityMethodResponseBuilder.accountId("accountId"))
                .thenReturn(setIdentityMethodResponseBuilder);

        SetIdentityMethodResponseBuilderPort result =
                setIdentityMethodResponseBuilderAdapter.accountId("accountId");
        assertTrue(result instanceof SetIdentityMethodResponseBuilderAdapter);
        verify(setIdentityMethodResponseBuilder).accountId("accountId");
        assertNotNull(result);
    }

    @Test
    public void testBuild() {
        SetIdentityMethodResponsePort result = setIdentityMethodResponseBuilderAdapter.build();

        assertNotNull(result);
    }

    @Test
    public void testCreated() {
        IdentityPort identityPort = mock(IdentityAdapter.class);
        Map<String, IdentityPort> identityList = new HashMap<>();
        identityList.put("key", identityPort);
        Identity identity = mock(Identity.class);

        when(((IdentityAdapter) identityPort).adaptee()).thenReturn(identity);

        setIdentityMethodResponseBuilderAdapter.created(identityList);

        verify(setIdentityMethodResponseBuilder).created(any());
    }

    @Test
    public void testCreatedNull() {
        setIdentityMethodResponseBuilderAdapter.created(null);

        verify(setIdentityMethodResponseBuilder).created(null);
    }

    @Test
    public void testDestroyed() {
        String[] idDestroyed = {"idDestroyed"};
        when(setIdentityMethodResponseBuilder.destroyed(idDestroyed))
                .thenReturn(setIdentityMethodResponseBuilder);

        SetIdentityMethodResponseBuilderPort result =
                setIdentityMethodResponseBuilderAdapter.destroyed(idDestroyed);
        assertTrue(result instanceof SetIdentityMethodResponseBuilderAdapter);
        verify(setIdentityMethodResponseBuilder).destroyed(idDestroyed);
        assertNotNull(result);
    }

    @Test
    public void testNewState() {
        when(setIdentityMethodResponseBuilder.newState("newState"))
                .thenReturn(setIdentityMethodResponseBuilder);

        SetIdentityMethodResponseBuilderPort result =
                setIdentityMethodResponseBuilderAdapter.newState("newState");
        assertTrue(result instanceof SetIdentityMethodResponseBuilderAdapter);
        verify(setIdentityMethodResponseBuilder).newState("newState");
        assertNotNull(result);
    }

    @Test
    public void testNotCreated() {
        SetErrorPort setErrorPort = mock(SetErrorAdapter.class);
        Map<String, SetErrorPort> identityList = new HashMap<>();
        identityList.put("key", setErrorPort);
        SetError setError = mock(SetError.class);

        when(((SetErrorAdapter) setErrorPort).adaptee()).thenReturn(setError);

        setIdentityMethodResponseBuilderAdapter.notCreated(identityList);

        verify(setIdentityMethodResponseBuilder).notCreated(any());
    }

    @Test
    public void testNotCreatedNull() {
        setIdentityMethodResponseBuilderAdapter.notCreated(null);

        verify(setIdentityMethodResponseBuilder).notCreated(null);
    }

    @Test
    public void testNotDestroyed() {
        SetErrorPort setErrorPort = mock(SetErrorAdapter.class);
        Map<String, SetErrorPort> setErrorList = new HashMap<>();
        setErrorList.put("key", setErrorPort);

        SetError setError = mock(SetError.class);

        when(((SetErrorAdapter) setErrorPort).adaptee()).thenReturn(setError);

        setIdentityMethodResponseBuilderAdapter.notDestroyed(setErrorList);

        verify(setIdentityMethodResponseBuilder).notDestroyed(any());
    }

    @Test
    public void testNotDestroyedNull() {
        setIdentityMethodResponseBuilderAdapter.notDestroyed(null);

        verify(setIdentityMethodResponseBuilder).notDestroyed(null);
    }

    @Test
    public void testNotUpdated() {
        SetErrorPort setErrorPort = mock(SetErrorAdapter.class);
        Map<String, SetErrorPort> setErrorList = new HashMap<>();
        setErrorList.put("key", setErrorPort);

        SetError setError = mock(SetError.class);

        when(((SetErrorAdapter) setErrorPort).adaptee()).thenReturn(setError);

        setIdentityMethodResponseBuilderAdapter.notUpdated(setErrorList);

        verify(setIdentityMethodResponseBuilder).notUpdated(any());
    }

    @Test
    public void testNotUpdatedNull() {
        setIdentityMethodResponseBuilderAdapter.notUpdated(null);

        verify(setIdentityMethodResponseBuilder).notUpdated(null);
    }

    @Test
    public void testOldState() {
        when(setIdentityMethodResponseBuilder.oldState("oldState"))
                .thenReturn(setIdentityMethodResponseBuilder);

        SetIdentityMethodResponseBuilderPort result =
                setIdentityMethodResponseBuilderAdapter.oldState("oldState");
        assertTrue(result instanceof SetIdentityMethodResponseBuilderAdapter);
        verify(setIdentityMethodResponseBuilder).oldState("oldState");
        assertNotNull(result);
    }

    @Test
    public void testReset() {
        try (MockedStatic<SetIdentityMethodResponse> setIdentityMethodResponseStatic =
                Mockito.mockStatic(SetIdentityMethodResponse.class)) {
            assertEquals(
                    setIdentityMethodResponseBuilderAdapter.reset(),
                    setIdentityMethodResponseBuilderAdapter);
            setIdentityMethodResponseStatic.verify(SetIdentityMethodResponse::builder);
        }
    }

    @Test
    public void testUpdated() {
        IdentityPort identityPort = mock(IdentityAdapter.class);
        Map<String, IdentityPort> identityList = new HashMap<>();
        identityList.put("key", identityPort);

        Identity identity = mock(Identity.class);

        when(((IdentityAdapter) identityPort).adaptee()).thenReturn(identity);

        setIdentityMethodResponseBuilderAdapter.updated(identityList);

        verify(setIdentityMethodResponseBuilder).updated(any());
    }

    @Test
    public void testUpdatedNull() {
        setIdentityMethodResponseBuilderAdapter.updated(null);

        verify(setIdentityMethodResponseBuilder).updated(null);
    }
}
