package it.qbsoftware.adapters.in.jmaplib.method.response.changes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailSubmissionMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailSubmissionMethodResponsePort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import rs.ltt.jmap.common.method.response.submission.ChangesEmailSubmissionMethodResponse;
import rs.ltt.jmap.common.method.response.submission.ChangesEmailSubmissionMethodResponse.ChangesEmailSubmissionMethodResponseBuilder;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ChangesEmailSubmissionMethodResponseBuilderAdapterTest {

    @Mock
    private ChangesEmailSubmissionMethodResponseBuilder changesEmailSubmissionMethodResponseBuilder;

    @InjectMocks
    private ChangesEmailSubmissionMethodResponseBuilderAdapter
            changesEmailSubmissionMethodResponseBuilderAdapter;

    @Test
    public void testAccountId() {
        when(changesEmailSubmissionMethodResponseBuilder.accountId("accountId"))
                .thenReturn(changesEmailSubmissionMethodResponseBuilder);

        ChangesEmailSubmissionMethodResponseBuilderPort result =
                changesEmailSubmissionMethodResponseBuilderAdapter.accountId("accountId");

        assertTrue(result instanceof ChangesEmailSubmissionMethodResponseBuilderAdapter);
        verify(changesEmailSubmissionMethodResponseBuilder).accountId("accountId");
        assertNotNull(result);
    }

    @Test
    public void testBuild() {
        ChangesEmailSubmissionMethodResponsePort result =
                changesEmailSubmissionMethodResponseBuilderAdapter.build();

        assertNotNull(result);
    }

    @Test
    public void testCreated() {
        String[] ids = new String[] {"created"};
        when(changesEmailSubmissionMethodResponseBuilder.created(ids))
                .thenReturn(changesEmailSubmissionMethodResponseBuilder);

        ChangesEmailSubmissionMethodResponseBuilderPort result =
                changesEmailSubmissionMethodResponseBuilderAdapter.created(ids);

        assertTrue(result instanceof ChangesEmailSubmissionMethodResponseBuilderAdapter);
        verify(changesEmailSubmissionMethodResponseBuilder).created(ids);
        assertNotNull(result);
    }

    @Test
    public void testDestroyed() {
        String[] ids = new String[] {"destroyed"};
        when(changesEmailSubmissionMethodResponseBuilder.destroyed(ids))
                .thenReturn(changesEmailSubmissionMethodResponseBuilder);

        ChangesEmailSubmissionMethodResponseBuilderPort result =
                changesEmailSubmissionMethodResponseBuilderAdapter.destroyed(ids);

        assertTrue(result instanceof ChangesEmailSubmissionMethodResponseBuilderAdapter);
        verify(changesEmailSubmissionMethodResponseBuilder).destroyed(ids);
        assertNotNull(result);
    }

    @Test
    public void testHasMoreChanges() {
        when(changesEmailSubmissionMethodResponseBuilder.hasMoreChanges(true))
                .thenReturn(changesEmailSubmissionMethodResponseBuilder);

        ChangesEmailSubmissionMethodResponseBuilderPort result =
                changesEmailSubmissionMethodResponseBuilderAdapter.hasMoreChanges(true);

        assertTrue(result instanceof ChangesEmailSubmissionMethodResponseBuilderAdapter);
        verify(changesEmailSubmissionMethodResponseBuilder).hasMoreChanges(true);
        assertNotNull(result);
    }

    @Test
    public void testNewState() {
        when(changesEmailSubmissionMethodResponseBuilder.newState("newState"))
                .thenReturn(changesEmailSubmissionMethodResponseBuilder);

        ChangesEmailSubmissionMethodResponseBuilderPort result =
                changesEmailSubmissionMethodResponseBuilderAdapter.newState("newState");

        assertTrue(result instanceof ChangesEmailSubmissionMethodResponseBuilderAdapter);
        verify(changesEmailSubmissionMethodResponseBuilder).newState("newState");
        assertNotNull(result);
    }

    @Test
    public void testOldState() {
        when(changesEmailSubmissionMethodResponseBuilder.oldState("oldState"))
                .thenReturn(changesEmailSubmissionMethodResponseBuilder);

        ChangesEmailSubmissionMethodResponseBuilderPort result =
                changesEmailSubmissionMethodResponseBuilderAdapter.oldState("oldState");

        assertTrue(result instanceof ChangesEmailSubmissionMethodResponseBuilderAdapter);
        verify(changesEmailSubmissionMethodResponseBuilder).oldState("oldState");
        assertNotNull(result);
    }

    @Test
    public void testReset() {
        try (MockedStatic<ChangesEmailSubmissionMethodResponse>
                getEmailSubmissionMethodResponseStatic =
                        Mockito.mockStatic(ChangesEmailSubmissionMethodResponse.class)) {
            assertEquals(
                    changesEmailSubmissionMethodResponseBuilderAdapter.reset(),
                    changesEmailSubmissionMethodResponseBuilderAdapter);
            getEmailSubmissionMethodResponseStatic.verify(
                    ChangesEmailSubmissionMethodResponse::builder);
        }
    }

    @Test
    public void testUpdated() {
        String[] ids = new String[] {"updated"};
        when(changesEmailSubmissionMethodResponseBuilder.updated(ids))
                .thenReturn(changesEmailSubmissionMethodResponseBuilder);

        ChangesEmailSubmissionMethodResponseBuilderPort result =
                changesEmailSubmissionMethodResponseBuilderAdapter.updated(ids);

        assertTrue(result instanceof ChangesEmailSubmissionMethodResponseBuilderAdapter);
        verify(changesEmailSubmissionMethodResponseBuilder).updated(ids);
        assertNotNull(result);
    }
}
