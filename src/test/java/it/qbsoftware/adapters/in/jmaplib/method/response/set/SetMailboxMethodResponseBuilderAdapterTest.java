package it.qbsoftware.adapters.in.jmaplib.method.response.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.entity.MailboxAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.SetErrorAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetMailboxMethodResponsePort;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import rs.ltt.jmap.common.entity.Mailbox;
import rs.ltt.jmap.common.entity.SetError;
import rs.ltt.jmap.common.method.response.mailbox.SetMailboxMethodResponse;
import rs.ltt.jmap.common.method.response.mailbox.SetMailboxMethodResponse.SetMailboxMethodResponseBuilder;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SetMailboxMethodResponseBuilderAdapterTest {
    @Mock private SetMailboxMethodResponseBuilder setMailboxMethodResponseBuilder;

    @InjectMocks
    private SetMailboxMethodResponseBuilderAdapter setMailboxMethodResponseBuilderAdapter;

    @Test
    public void testAccountId() {
        when(setMailboxMethodResponseBuilder.accountId("accountId"))
                .thenReturn(setMailboxMethodResponseBuilder);

        SetMailboxMethodResponseBuilderPort result =
                setMailboxMethodResponseBuilderAdapter.accountId("accountId");
        assertTrue(result instanceof SetMailboxMethodResponseBuilderAdapter);
        verify(setMailboxMethodResponseBuilder).accountId("accountId");
        assertNotNull(result);
    }

    @Test
    public void testBuild() {
        SetMailboxMethodResponsePort result = setMailboxMethodResponseBuilderAdapter.build();

        assertNotNull(result);
    }

    @Test
    public void testCreated() {
        MailboxPort mailboxPort = mock(MailboxAdapter.class);
        Map<String, MailboxPort> mailboxList = new HashMap<>();
        mailboxList.put("key", mailboxPort);
        Mailbox mailbox = mock(Mailbox.class);

        when(((MailboxAdapter) mailboxPort).adaptee()).thenReturn(mailbox);

        setMailboxMethodResponseBuilderAdapter.created(mailboxList);

        verify(setMailboxMethodResponseBuilder).created(any());
    }

    @Test
    public void testDestroyed() {
        String[] idDestroyed = {"idDestroyed"};
        when(setMailboxMethodResponseBuilder.destroyed(idDestroyed))
                .thenReturn(setMailboxMethodResponseBuilder);

        SetMailboxMethodResponseBuilderPort result =
                setMailboxMethodResponseBuilderAdapter.destroyed(idDestroyed);
        assertTrue(result instanceof SetMailboxMethodResponseBuilderAdapter);
        verify(setMailboxMethodResponseBuilder).destroyed(idDestroyed);
        assertNotNull(result);
    }

    @Test
    public void testNewState() {
        when(setMailboxMethodResponseBuilder.newState("newState"))
                .thenReturn(setMailboxMethodResponseBuilder);

        SetMailboxMethodResponseBuilderPort result =
                setMailboxMethodResponseBuilderAdapter.newState("newState");
        assertTrue(result instanceof SetMailboxMethodResponseBuilderAdapter);
        verify(setMailboxMethodResponseBuilder).newState("newState");
        assertNotNull(result);
    }

    @Test
    public void testNotCreated() {
        SetErrorPort setErrorPort = mock(SetErrorAdapter.class);
        Map<String, SetErrorPort> mailboxList = new HashMap<>();
        mailboxList.put("key", setErrorPort);
        SetError setError = mock(SetError.class);

        when(((SetErrorAdapter) setErrorPort).adaptee()).thenReturn(setError);

        setMailboxMethodResponseBuilderAdapter.notCreated(mailboxList);

        verify(setMailboxMethodResponseBuilder).notCreated(any());
    }

    @Test
    public void testNotCreatedNull() {
        setMailboxMethodResponseBuilderAdapter.notCreated(null);

        verify(setMailboxMethodResponseBuilder).notCreated(null);
    }

    @Test
    public void testNotDestroyed() {
        SetErrorPort setErrorPort = mock(SetErrorAdapter.class);
        Map<String, SetErrorPort> setErrorList = new HashMap<>();
        setErrorList.put("key", setErrorPort);

        SetError setError = mock(SetError.class);

        when(((SetErrorAdapter) setErrorPort).adaptee()).thenReturn(setError);

        setMailboxMethodResponseBuilderAdapter.notDestroyed(setErrorList);

        verify(setMailboxMethodResponseBuilder).notDestroyed(any());
    }

    @Test
    public void testNotDestroyedNull() {
        setMailboxMethodResponseBuilderAdapter.notDestroyed(null);

        verify(setMailboxMethodResponseBuilder).notDestroyed(null);
    }

    @Test
    public void testNotUpdated() {
        SetErrorPort setErrorPort = mock(SetErrorAdapter.class);
        Map<String, SetErrorPort> setErrorList = new HashMap<>();
        setErrorList.put("key", setErrorPort);

        SetError setError = mock(SetError.class);

        when(((SetErrorAdapter) setErrorPort).adaptee()).thenReturn(setError);

        setMailboxMethodResponseBuilderAdapter.notUpdated(setErrorList);

        verify(setMailboxMethodResponseBuilder).notUpdated(any());
    }

    @Test
    public void testNotUpdatedNull() {
        setMailboxMethodResponseBuilderAdapter.notUpdated(null);

        verify(setMailboxMethodResponseBuilder).notUpdated(null);
    }

    @Test
    public void testOldState() {
        when(setMailboxMethodResponseBuilder.oldState("oldState"))
                .thenReturn(setMailboxMethodResponseBuilder);

        SetMailboxMethodResponseBuilderPort result =
                setMailboxMethodResponseBuilderAdapter.oldState("oldState");
        assertTrue(result instanceof SetMailboxMethodResponseBuilderAdapter);
        verify(setMailboxMethodResponseBuilder).oldState("oldState");
        assertNotNull(result);
    }

    @Test
    public void testReset() {
        try (MockedStatic<SetMailboxMethodResponse> setMailboxMethodResponseStatic =
                Mockito.mockStatic(SetMailboxMethodResponse.class)) {
            assertEquals(
                    setMailboxMethodResponseBuilderAdapter.reset(),
                    setMailboxMethodResponseBuilderAdapter);
            setMailboxMethodResponseStatic.verify(SetMailboxMethodResponse::builder);
        }
    }

    @Test
    public void testUpdated() {
        MailboxPort mailboxPort = mock(MailboxAdapter.class);
        Map<String, MailboxPort> mailboxList = new HashMap<>();
        mailboxList.put("key", mailboxPort);

        Mailbox mailbox = mock(Mailbox.class);

        when(((MailboxAdapter) mailboxPort).adaptee()).thenReturn(mailbox);

        setMailboxMethodResponseBuilderAdapter.updated(mailboxList);

        verify(setMailboxMethodResponseBuilder).updated(any());
    }
}
