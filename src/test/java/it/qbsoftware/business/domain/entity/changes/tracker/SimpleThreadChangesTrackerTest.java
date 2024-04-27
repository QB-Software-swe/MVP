package it.qbsoftware.business.domain.entity.changes.tracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class SimpleThreadChangesTrackerTest {
        @Test
    public void testConstructorWithIdOnly() {
        SimpleThreadChangesTracker tracker = new SimpleThreadChangesTracker("id");

        assertEquals("id", tracker.id());
        assertTrue(tracker.created().isEmpty());
        assertTrue(tracker.updated().isEmpty());
        assertTrue(tracker.destroyed().isEmpty());
    }

    @Test
    public void testConstructorWithAllParameters() {
        Map<String, String> created = new HashMap<>();
        created.put("createdState", "createdThreadId");
        Map<String, String> updated = new HashMap<>();
        updated.put("updatedState", "updatedThreadId");
        Map<String, String> destroyed = new HashMap<>();
        destroyed.put("destroyedState", "destroyedThreadId");

        SimpleThreadChangesTracker tracker = new SimpleThreadChangesTracker("id", created, updated, destroyed);

        assertEquals("id", tracker.id());
        assertEquals(created, tracker.created());
        assertEquals(updated, tracker.updated());
        assertEquals(destroyed, tracker.destroyed());
    }

    @Test
    public void testThreadHasBeenCreated() {
        SimpleThreadChangesTracker tracker = new SimpleThreadChangesTracker("id");

        ThreadChangesTracker updatedTracker = tracker.threadHasBeenCreated("newState", "newThreadId");

        assertEquals("newThreadId", updatedTracker.id());
        assertEquals("newThreadId", updatedTracker.created().get("newState"));
    }

    @Test
    public void testThreadHasBeenUpdated() {
        SimpleThreadChangesTracker tracker = new SimpleThreadChangesTracker("id");

        ThreadChangesTracker updatedTracker = tracker.threadHasBeenUpdated("newState", "newThreadId");

        assertEquals("newThreadId", updatedTracker.id());
        assertEquals("newThreadId", updatedTracker.updated().get("newState"));
    }

    @Test
    public void testThreadHasBeenDestroyed() {
        SimpleThreadChangesTracker tracker = new SimpleThreadChangesTracker("id");

        ThreadChangesTracker updatedTracker = tracker.threadHasBeenDestroyed("newState", "newThreadId");

        assertEquals("newThreadId", updatedTracker.id());
        assertEquals("newThreadId", updatedTracker.destroyed().get("newState"));
    }
}
