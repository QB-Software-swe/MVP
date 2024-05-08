package it.qbsoftware.adapters.in.jmaplib.method.response.changes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesThreadMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesThreadMethodResponsePort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import rs.ltt.jmap.common.method.response.thread.ChangesThreadMethodResponse;
import rs.ltt.jmap.common.method.response.thread.ChangesThreadMethodResponse.ChangesThreadMethodResponseBuilder;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ChangesThreadMethodResponseBuilderAdapterTest {

    @Mock private ChangesThreadMethodResponseBuilder changesThreadMethodResponseBuilder;

    @InjectMocks
    private ChangesThreadMethodResponseBuilderAdapter changesThreadMethodResponseBuilderAdapter;

    @Test
    public void testAccountId() {
        when(changesThreadMethodResponseBuilder.accountId("accountId"))
                .thenReturn(changesThreadMethodResponseBuilder);

        ChangesThreadMethodResponseBuilderPort result =
                changesThreadMethodResponseBuilderAdapter.accountId("accountId");

        assertTrue(result instanceof ChangesThreadMethodResponseBuilderAdapter);
        verify(changesThreadMethodResponseBuilder).accountId("accountId");
        assertNotNull(result);
    }

    @Test
    public void testBuild() {
        ChangesThreadMethodResponsePort result = changesThreadMethodResponseBuilderAdapter.build();

        assertNotNull(result);
    }

    @Test
    public void testCreated() {
        String[] ids = new String[] {"created"};
        when(changesThreadMethodResponseBuilder.created(ids))
                .thenReturn(changesThreadMethodResponseBuilder);

        ChangesThreadMethodResponseBuilderPort result =
                changesThreadMethodResponseBuilderAdapter.created(ids);

        assertTrue(result instanceof ChangesThreadMethodResponseBuilderAdapter);
        verify(changesThreadMethodResponseBuilder).created(ids);
        assertNotNull(result);
    }

    @Test
    public void testDestroyed() {
        String[] ids = new String[] {"destroyed"};
        when(changesThreadMethodResponseBuilder.destroyed(ids))
                .thenReturn(changesThreadMethodResponseBuilder);

        ChangesThreadMethodResponseBuilderPort result =
                changesThreadMethodResponseBuilderAdapter.destroyed(ids);

        assertTrue(result instanceof ChangesThreadMethodResponseBuilderAdapter);
        verify(changesThreadMethodResponseBuilder).destroyed(ids);
        assertNotNull(result);
    }

    @Test
    public void testHasMoreChanges() {
        when(changesThreadMethodResponseBuilder.hasMoreChanges(true))
                .thenReturn(changesThreadMethodResponseBuilder);

        ChangesThreadMethodResponseBuilderPort result =
                changesThreadMethodResponseBuilderAdapter.hasMoreChanges(true);

        assertTrue(result instanceof ChangesThreadMethodResponseBuilderAdapter);
        verify(changesThreadMethodResponseBuilder).hasMoreChanges(true);
        assertNotNull(result);
    }

    @Test
    public void testNewState() {
        when(changesThreadMethodResponseBuilder.newState("newState"))
                .thenReturn(changesThreadMethodResponseBuilder);

        ChangesThreadMethodResponseBuilderPort result =
                changesThreadMethodResponseBuilderAdapter.newState("newState");

        assertTrue(result instanceof ChangesThreadMethodResponseBuilderAdapter);
        verify(changesThreadMethodResponseBuilder).newState("newState");
        assertNotNull(result);
    }

    @Test
    public void testOldState() {
        when(changesThreadMethodResponseBuilder.oldState("oldState"))
                .thenReturn(changesThreadMethodResponseBuilder);

        ChangesThreadMethodResponseBuilderPort result =
                changesThreadMethodResponseBuilderAdapter.oldState("oldState");

        assertTrue(result instanceof ChangesThreadMethodResponseBuilderAdapter);
        verify(changesThreadMethodResponseBuilder).oldState("oldState");
        assertNotNull(result);
    }

    @Test
    public void testReset() {
        try (MockedStatic<ChangesThreadMethodResponse> getThreadMethodResponseStatic =
                Mockito.mockStatic(ChangesThreadMethodResponse.class)) {
            assertEquals(
                    changesThreadMethodResponseBuilderAdapter.reset(),
                    changesThreadMethodResponseBuilderAdapter);
            getThreadMethodResponseStatic.verify(ChangesThreadMethodResponse::builder);
        }
    }

    @Test
    public void testUpdated() {
        String[] ids = new String[] {"updated"};
        when(changesThreadMethodResponseBuilder.updated(ids))
                .thenReturn(changesThreadMethodResponseBuilder);

        ChangesThreadMethodResponseBuilderPort result =
                changesThreadMethodResponseBuilderAdapter.updated(ids);

        assertTrue(result instanceof ChangesThreadMethodResponseBuilderAdapter);
        verify(changesThreadMethodResponseBuilder).updated(ids);
        assertNotNull(result);
    }
}
