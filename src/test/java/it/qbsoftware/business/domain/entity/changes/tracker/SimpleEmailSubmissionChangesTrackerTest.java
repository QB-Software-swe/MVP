package it.qbsoftware.business.domain.entity.changes.tracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class SimpleEmailSubmissionChangesTrackerTest {
    @Test
    public void testConstructorWithIdOnly() {
        SimpleEmailSubmissionChangesTracker tracker = new SimpleEmailSubmissionChangesTracker("id");

        assertEquals("id", tracker.id());
        assertTrue(tracker.created().isEmpty());
        assertTrue(tracker.updated().isEmpty());
        assertTrue(tracker.destroyed().isEmpty());
    }

    @Test
    public void testConstructorWithAllParameters() {
        Map<String, String> created = new HashMap<>();
        created.put("createdState", "createdEmailSubmissionId");
        Map<String, String> updated = new HashMap<>();
        updated.put("updatedState", "updatedEmailSubmissionId");
        Map<String, String> destroyed = new HashMap<>();
        destroyed.put("destroyedState", "destroyedEmailSubmissionId");

        SimpleEmailSubmissionChangesTracker tracker = new SimpleEmailSubmissionChangesTracker("id", created, updated, destroyed);

        assertEquals("id", tracker.id());
        assertEquals(created, tracker.created());
        assertEquals(updated, tracker.updated());
        assertEquals(destroyed, tracker.destroyed());
    }

    @Test
    public void testEmailSubmissionHasBeenCreated() {
        SimpleEmailSubmissionChangesTracker tracker = new SimpleEmailSubmissionChangesTracker("id");

        EmailSubmissionChangesTracker updatedTracker = tracker.emailSubmissionHasBeenCreated("newState", "newEmailSubmissionId");

        assertEquals("newEmailSubmissionId", updatedTracker.id());
        assertEquals("newEmailSubmissionId", updatedTracker.created().get("newState"));
    }

    @Test
    public void testEmailSubmissionHasBeenUpdated() {
        SimpleEmailSubmissionChangesTracker tracker = new SimpleEmailSubmissionChangesTracker("id");

        EmailSubmissionChangesTracker updatedTracker = tracker.emailSubmissionHasBeenUpdated("newState", "newEmailSubmissionId");

        assertEquals("newEmailSubmissionId", updatedTracker.id());
        assertEquals("newEmailSubmissionId", updatedTracker.updated().get("newState"));
    }

    @Test
    public void testEmailSubmissionHasBeenDestroyed() {
        SimpleEmailSubmissionChangesTracker tracker = new SimpleEmailSubmissionChangesTracker("id");

        EmailSubmissionChangesTracker updatedTracker = tracker.emailSubmissionHasBeenDestroyed("newState", "newEmailSubmissionId");

        assertEquals("newEmailSubmissionId", updatedTracker.id());
        assertEquals("newEmailSubmissionId", updatedTracker.destroyed().get("newState"));
    }
}
