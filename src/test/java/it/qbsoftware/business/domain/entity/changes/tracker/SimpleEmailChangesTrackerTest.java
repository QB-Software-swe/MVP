package it.qbsoftware.business.domain.entity.changes.tracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SimpleEmailChangesTrackerTest {
    @Test
    public void testConstructorWithIdOnly() {
        SimpleEmailChangesTracker tracker = new SimpleEmailChangesTracker("id");

        assertEquals("id", tracker.id());
        assertTrue(tracker.created().isEmpty());
        assertTrue(tracker.updated().isEmpty());
        assertTrue(tracker.destroyed().isEmpty());
    }

    @Test
    public void testConstructorWithAllParameters() {
        Map<String, String> created = new HashMap<>();
        created.put("createdState", "createdEmailId");
        Map<String, String> updated = new HashMap<>();
        updated.put("updatedState", "updatedEmailId");
        Map<String, String> destroyed = new HashMap<>();
        destroyed.put("destroyedState", "destroyedEmailId");

        SimpleEmailChangesTracker tracker = new SimpleEmailChangesTracker("id", created, updated, destroyed);

        assertEquals("id", tracker.id());
        assertEquals(created, tracker.created());
        assertEquals(updated, tracker.updated());
        assertEquals(destroyed, tracker.destroyed());
    }

    @Test
    public void testEmailHasBeenCreated() {
        SimpleEmailChangesTracker tracker = new SimpleEmailChangesTracker("id");

        SimpleEmailChangesTracker updatedTracker = tracker.emailHasBeenCreated("newState", "newEmailId");

        assertEquals("newEmailId", updatedTracker.id());
        assertEquals("newEmailId", updatedTracker.created().get("newState"));
    }

    @Test
    public void testEmailHasBeenUpdated() {
        SimpleEmailChangesTracker tracker = new SimpleEmailChangesTracker("id");

        SimpleEmailChangesTracker updatedTracker = tracker.emailHasBeenUpdated("newState", "newEmailId");

        assertEquals("newEmailId", updatedTracker.id());
        assertEquals("newEmailId", updatedTracker.updated().get("newState"));
    }

    @Test
    public void testEmailHasBeenDestroyed() {
        SimpleEmailChangesTracker tracker = new SimpleEmailChangesTracker("id");

        SimpleEmailChangesTracker updatedTracker = tracker.emailHasBeenDestroyed("newState", "newEmailId");

        assertEquals("newEmailId", updatedTracker.id());
        assertEquals("newEmailId", updatedTracker.destroyed().get("newState"));
    }
}
