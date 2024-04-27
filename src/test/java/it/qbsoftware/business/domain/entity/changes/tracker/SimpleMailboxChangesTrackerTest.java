package it.qbsoftware.business.domain.entity.changes.tracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class SimpleMailboxChangesTrackerTest {

        @Test
    public void testConstructorWithIdOnly() {
        SimpleMailboxChangesTracker tracker = new SimpleMailboxChangesTracker("id");

        assertEquals("id", tracker.id());
        assertTrue(tracker.created().isEmpty());
        assertTrue(tracker.updated().isEmpty());
        assertTrue(tracker.destroyed().isEmpty());
    }

    @Test
    public void testConstructorWithAllParameters() {
        Map<String, String> created = new HashMap<>();
        created.put("createdState", "createdMailboxId");
        Map<String, String> updated = new HashMap<>();
        updated.put("updatedState", "updatedMailboxId");
        Map<String, String> destroyed = new HashMap<>();
        destroyed.put("destroyedState", "destroyedMailboxId");

        SimpleMailboxChangesTracker tracker = new SimpleMailboxChangesTracker("id", created, updated, destroyed);

        assertEquals("id", tracker.id());
        assertEquals(created, tracker.created());
        assertEquals(updated, tracker.updated());
        assertEquals(destroyed, tracker.destroyed());
    }

    @Test
    public void testMailboxHasBeenCreated() {
        SimpleMailboxChangesTracker tracker = new SimpleMailboxChangesTracker("id");

        MailboxChangesTracker updatedTracker = tracker.mailboxHasBeenCreated("newState", "newMailboxId");

        assertEquals("newMailboxId", updatedTracker.id());
        assertEquals("newMailboxId", updatedTracker.created().get("newState"));
    }

    @Test
    public void testMailboxHasBeenUpdated() {
        SimpleMailboxChangesTracker tracker = new SimpleMailboxChangesTracker("id");

        MailboxChangesTracker updatedTracker = tracker.mailboxHasBeenUpdated("newState", "newMailboxId");

        assertEquals("newMailboxId", updatedTracker.id());
        assertEquals("newMailboxId", updatedTracker.updated().get("newState"));
    }

    @Test
    public void testMailboxHasBeenDestroyed() {
        SimpleMailboxChangesTracker tracker = new SimpleMailboxChangesTracker("id");

        MailboxChangesTracker updatedTracker = tracker.mailboxHasBeenDestroyed("newState", "newMailboxId");

        assertEquals("newMailboxId", updatedTracker.id());
        assertEquals("newMailboxId", updatedTracker.destroyed().get("newState"));
    }
}
