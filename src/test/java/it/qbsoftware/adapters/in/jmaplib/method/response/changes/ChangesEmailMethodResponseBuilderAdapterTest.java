package it.qbsoftware.adapters.in.jmaplib.method.response.changes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailMethodResponsePort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import rs.ltt.jmap.common.method.response.email.ChangesEmailMethodResponse;
import rs.ltt.jmap.common.method.response.email.ChangesEmailMethodResponse.ChangesEmailMethodResponseBuilder;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ChangesEmailMethodResponseBuilderAdapterTest {

    @Mock private ChangesEmailMethodResponseBuilder changesEmailMethodResponseBuilder;

    @InjectMocks
    private ChangesEmailMethodResponseBuilderAdapter changesEmailMethodResponseBuilderAdapter;

    @Test
    public void testAccountId() {
        when(changesEmailMethodResponseBuilder.accountId("accountId"))
                .thenReturn(changesEmailMethodResponseBuilder);

        ChangesEmailMethodResponseBuilderPort result =
                changesEmailMethodResponseBuilderAdapter.accountId("accountId");

        assertTrue(result instanceof ChangesEmailMethodResponseBuilderAdapter);
        verify(changesEmailMethodResponseBuilder).accountId("accountId");
        assertNotNull(result);
    }

    @Test
    public void testBuild() {
        ChangesEmailMethodResponsePort result = changesEmailMethodResponseBuilderAdapter.build();

        assertNotNull(result);
    }

    @Test
    public void testCreated() {
        String[] ids = new String[] {"created"};
        when(changesEmailMethodResponseBuilder.created(ids))
                .thenReturn(changesEmailMethodResponseBuilder);

        ChangesEmailMethodResponseBuilderPort result =
                changesEmailMethodResponseBuilderAdapter.created(ids);

        assertTrue(result instanceof ChangesEmailMethodResponseBuilderAdapter);
        verify(changesEmailMethodResponseBuilder).created(ids);
        assertNotNull(result);
    }

    @Test
    public void testDestroyed() {
        String[] ids = new String[] {"destroyed"};
        when(changesEmailMethodResponseBuilder.destroyed(ids))
                .thenReturn(changesEmailMethodResponseBuilder);

        ChangesEmailMethodResponseBuilderPort result =
                changesEmailMethodResponseBuilderAdapter.destroyed(ids);

        assertTrue(result instanceof ChangesEmailMethodResponseBuilderAdapter);
        verify(changesEmailMethodResponseBuilder).destroyed(ids);
        assertNotNull(result);
    }

    @Test
    public void testHasMoreChanges() {
        when(changesEmailMethodResponseBuilder.hasMoreChanges(true))
                .thenReturn(changesEmailMethodResponseBuilder);

        ChangesEmailMethodResponseBuilderPort result =
                changesEmailMethodResponseBuilderAdapter.hasMoreChanges(true);

        assertTrue(result instanceof ChangesEmailMethodResponseBuilderAdapter);
        verify(changesEmailMethodResponseBuilder).hasMoreChanges(true);
        assertNotNull(result);
    }

    @Test
    public void testNewState() {
        when(changesEmailMethodResponseBuilder.newState("newState"))
                .thenReturn(changesEmailMethodResponseBuilder);

        ChangesEmailMethodResponseBuilderPort result =
                changesEmailMethodResponseBuilderAdapter.newState("newState");

        assertTrue(result instanceof ChangesEmailMethodResponseBuilderAdapter);
        verify(changesEmailMethodResponseBuilder).newState("newState");
        assertNotNull(result);
    }

    @Test
    public void testOldState() {
        when(changesEmailMethodResponseBuilder.oldState("oldState"))
                .thenReturn(changesEmailMethodResponseBuilder);

        ChangesEmailMethodResponseBuilderPort result =
                changesEmailMethodResponseBuilderAdapter.oldState("oldState");

        assertTrue(result instanceof ChangesEmailMethodResponseBuilderAdapter);
        verify(changesEmailMethodResponseBuilder).oldState("oldState");
        assertNotNull(result);
    }

    @Test
    public void testReset() {
        try (MockedStatic<ChangesEmailMethodResponse> getEmailMethodResponseStatic =
                Mockito.mockStatic(ChangesEmailMethodResponse.class)) {
            assertEquals(
                    changesEmailMethodResponseBuilderAdapter.reset(),
                    changesEmailMethodResponseBuilderAdapter);
            getEmailMethodResponseStatic.verify(ChangesEmailMethodResponse::builder);
        }
    }

    @Test
    public void testUpdated() {
        String[] ids = new String[] {"updated"};
        when(changesEmailMethodResponseBuilder.updated(ids))
                .thenReturn(changesEmailMethodResponseBuilder);

        ChangesEmailMethodResponseBuilderPort result =
                changesEmailMethodResponseBuilderAdapter.updated(ids);

        assertTrue(result instanceof ChangesEmailMethodResponseBuilderAdapter);
        verify(changesEmailMethodResponseBuilder).updated(ids);
        assertNotNull(result);
    }
}
