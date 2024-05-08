package it.qbsoftware.adapters.in.jmaplib.method.response.changes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesIdentityMethodResponsePort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import rs.ltt.jmap.common.method.response.identity.ChangesIdentityMethodResponse;
import rs.ltt.jmap.common.method.response.identity.ChangesIdentityMethodResponse.ChangesIdentityMethodResponseBuilder;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ChangesIdentityMethodResponseBuilderAdapterTest {

    @Mock private ChangesIdentityMethodResponseBuilder changesIdentityMethodResponseBuilder;

    @InjectMocks
    private ChangesIdentityMethodResponseBuilderAdapter changesIdentityMethodResponseBuilderAdapter;

    @Test
    public void testAccountId() {
        when(changesIdentityMethodResponseBuilder.accountId("accountId"))
                .thenReturn(changesIdentityMethodResponseBuilder);

        ChangesIdentityMethodResponseBuilderPort result =
                changesIdentityMethodResponseBuilderAdapter.accountId("accountId");

        assertTrue(result instanceof ChangesIdentityMethodResponseBuilderAdapter);
        verify(changesIdentityMethodResponseBuilder).accountId("accountId");
        assertNotNull(result);
    }

    @Test
    public void testBuild() {
        ChangesIdentityMethodResponsePort result =
                changesIdentityMethodResponseBuilderAdapter.build();

        assertNotNull(result);
    }

    @Test
    public void testCreated() {
        String[] ids = new String[] {"created"};
        when(changesIdentityMethodResponseBuilder.created(ids))
                .thenReturn(changesIdentityMethodResponseBuilder);

        ChangesIdentityMethodResponseBuilderPort result =
                changesIdentityMethodResponseBuilderAdapter.created(ids);

        assertTrue(result instanceof ChangesIdentityMethodResponseBuilderAdapter);
        verify(changesIdentityMethodResponseBuilder).created(ids);
        assertNotNull(result);
    }

    @Test
    public void testDestroyed() {
        String[] ids = new String[] {"destroyed"};
        when(changesIdentityMethodResponseBuilder.destroyed(ids))
                .thenReturn(changesIdentityMethodResponseBuilder);

        ChangesIdentityMethodResponseBuilderPort result =
                changesIdentityMethodResponseBuilderAdapter.destroyed(ids);

        assertTrue(result instanceof ChangesIdentityMethodResponseBuilderAdapter);
        verify(changesIdentityMethodResponseBuilder).destroyed(ids);
        assertNotNull(result);
    }

    @Test
    public void testHasMoreChanges() {
        when(changesIdentityMethodResponseBuilder.hasMoreChanges(true))
                .thenReturn(changesIdentityMethodResponseBuilder);

        ChangesIdentityMethodResponseBuilderPort result =
                changesIdentityMethodResponseBuilderAdapter.hasMoreChanges(true);

        assertTrue(result instanceof ChangesIdentityMethodResponseBuilderAdapter);
        verify(changesIdentityMethodResponseBuilder).hasMoreChanges(true);
        assertNotNull(result);
    }

    @Test
    public void testNewState() {
        when(changesIdentityMethodResponseBuilder.newState("newState"))
                .thenReturn(changesIdentityMethodResponseBuilder);

        ChangesIdentityMethodResponseBuilderPort result =
                changesIdentityMethodResponseBuilderAdapter.newState("newState");

        assertTrue(result instanceof ChangesIdentityMethodResponseBuilderAdapter);
        verify(changesIdentityMethodResponseBuilder).newState("newState");
        assertNotNull(result);
    }

    @Test
    public void testOldState() {
        when(changesIdentityMethodResponseBuilder.oldState("oldState"))
                .thenReturn(changesIdentityMethodResponseBuilder);

        ChangesIdentityMethodResponseBuilderPort result =
                changesIdentityMethodResponseBuilderAdapter.oldState("oldState");

        assertTrue(result instanceof ChangesIdentityMethodResponseBuilderAdapter);
        verify(changesIdentityMethodResponseBuilder).oldState("oldState");
        assertNotNull(result);
    }

    @Test
    public void testReset() {
        try (MockedStatic<ChangesIdentityMethodResponse> getIdentityMethodResponseStatic =
                Mockito.mockStatic(ChangesIdentityMethodResponse.class)) {
            assertEquals(
                    changesIdentityMethodResponseBuilderAdapter.reset(),
                    changesIdentityMethodResponseBuilderAdapter);
            getIdentityMethodResponseStatic.verify(ChangesIdentityMethodResponse::builder);
        }
    }

    @Test
    public void testUpdated() {
        String[] ids = new String[] {"updated"};
        when(changesIdentityMethodResponseBuilder.updated(ids))
                .thenReturn(changesIdentityMethodResponseBuilder);

        ChangesIdentityMethodResponseBuilderPort result =
                changesIdentityMethodResponseBuilderAdapter.updated(ids);

        assertTrue(result instanceof ChangesIdentityMethodResponseBuilderAdapter);
        verify(changesIdentityMethodResponseBuilder).updated(ids);
        assertNotNull(result);
    }
}
