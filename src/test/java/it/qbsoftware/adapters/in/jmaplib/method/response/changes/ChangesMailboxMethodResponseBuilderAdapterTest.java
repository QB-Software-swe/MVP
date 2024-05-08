package it.qbsoftware.adapters.in.jmaplib.method.response.changes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesMailboxMethodResponsePort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import rs.ltt.jmap.common.method.response.mailbox.ChangesMailboxMethodResponse;
import rs.ltt.jmap.common.method.response.mailbox.ChangesMailboxMethodResponse.ChangesMailboxMethodResponseBuilder;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ChangesMailboxMethodResponseBuilderAdapterTest {

    @Mock private ChangesMailboxMethodResponseBuilder changesMailboxMethodResponseBuilder;

    @InjectMocks
    private ChangesMailboxMethodResponseBuilderAdapter changesMailboxMethodResponseBuilderAdapter;

    @Test
    public void testAccountId() {
        when(changesMailboxMethodResponseBuilder.accountId("accountId"))
                .thenReturn(changesMailboxMethodResponseBuilder);

        ChangesMailboxMethodResponseBuilderPort result =
                changesMailboxMethodResponseBuilderAdapter.accountId("accountId");

        assertTrue(result instanceof ChangesMailboxMethodResponseBuilderAdapter);
        verify(changesMailboxMethodResponseBuilder).accountId("accountId");
        assertNotNull(result);
    }

    @Test
    public void testBuild() {
        ChangesMailboxMethodResponsePort result =
                changesMailboxMethodResponseBuilderAdapter.build();

        assertNotNull(result);
    }

    @Test
    public void testCreated() {
        String[] ids = new String[] {"created"};
        when(changesMailboxMethodResponseBuilder.created(ids))
                .thenReturn(changesMailboxMethodResponseBuilder);

        ChangesMailboxMethodResponseBuilderPort result =
                changesMailboxMethodResponseBuilderAdapter.created(ids);

        assertTrue(result instanceof ChangesMailboxMethodResponseBuilderAdapter);
        verify(changesMailboxMethodResponseBuilder).created(ids);
        assertNotNull(result);
    }

    @Test
    public void testDestroyed() {
        String[] ids = new String[] {"destroyed"};
        when(changesMailboxMethodResponseBuilder.destroyed(ids))
                .thenReturn(changesMailboxMethodResponseBuilder);

        ChangesMailboxMethodResponseBuilderPort result =
                changesMailboxMethodResponseBuilderAdapter.destroyed(ids);

        assertTrue(result instanceof ChangesMailboxMethodResponseBuilderAdapter);
        verify(changesMailboxMethodResponseBuilder).destroyed(ids);
        assertNotNull(result);
    }

    @Test
    public void testUpdatedProperties() {
        String[] updStrings = new String[] {"updatedProperties"};

        ChangesMailboxMethodResponseBuilderPort result =
                changesMailboxMethodResponseBuilderAdapter.updatedProperties(updStrings);

        assertTrue(result instanceof ChangesMailboxMethodResponseBuilderAdapter);
        verify(changesMailboxMethodResponseBuilder).updated(updStrings);
        assertNotNull(result);
    }

    @Test
    public void testHasMoreChanges() {
        when(changesMailboxMethodResponseBuilder.hasMoreChanges(true))
                .thenReturn(changesMailboxMethodResponseBuilder);

        ChangesMailboxMethodResponseBuilderPort result =
                changesMailboxMethodResponseBuilderAdapter.hasMoreChanges(true);

        assertTrue(result instanceof ChangesMailboxMethodResponseBuilderAdapter);
        verify(changesMailboxMethodResponseBuilder).hasMoreChanges(true);
        assertNotNull(result);
    }

    @Test
    public void testNewState() {
        when(changesMailboxMethodResponseBuilder.newState("newState"))
                .thenReturn(changesMailboxMethodResponseBuilder);

        ChangesMailboxMethodResponseBuilderPort result =
                changesMailboxMethodResponseBuilderAdapter.newState("newState");

        assertTrue(result instanceof ChangesMailboxMethodResponseBuilderAdapter);
        verify(changesMailboxMethodResponseBuilder).newState("newState");
        assertNotNull(result);
    }

    @Test
    public void testOldState() {
        when(changesMailboxMethodResponseBuilder.oldState("oldState"))
                .thenReturn(changesMailboxMethodResponseBuilder);

        ChangesMailboxMethodResponseBuilderPort result =
                changesMailboxMethodResponseBuilderAdapter.oldState("oldState");

        assertTrue(result instanceof ChangesMailboxMethodResponseBuilderAdapter);
        verify(changesMailboxMethodResponseBuilder).oldState("oldState");
        assertNotNull(result);
    }

    @Test
    public void testReset() {
        try (MockedStatic<ChangesMailboxMethodResponse> getMailboxMethodResponseStatic =
                Mockito.mockStatic(ChangesMailboxMethodResponse.class)) {
            assertEquals(
                    changesMailboxMethodResponseBuilderAdapter.reset(),
                    changesMailboxMethodResponseBuilderAdapter);
            getMailboxMethodResponseStatic.verify(ChangesMailboxMethodResponse::builder);
        }
    }

    @Test
    public void testUpdated() {
        String[] ids = new String[] {"updated"};
        when(changesMailboxMethodResponseBuilder.updated(ids))
                .thenReturn(changesMailboxMethodResponseBuilder);

        ChangesMailboxMethodResponseBuilderPort result =
                changesMailboxMethodResponseBuilderAdapter.updated(ids);

        assertTrue(result instanceof ChangesMailboxMethodResponseBuilderAdapter);
        verify(changesMailboxMethodResponseBuilder).updated(ids);
        assertNotNull(result);
    }
}
