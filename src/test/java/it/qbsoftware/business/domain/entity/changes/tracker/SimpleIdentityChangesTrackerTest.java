package it.qbsoftware.business.domain.entity.changes.tracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SimpleIdentityChangesTrackerTest {
    @Test
    public void testConstructorWithIdOnly() {
        SimpleIdentityChangesTracker tracker = new SimpleIdentityChangesTracker("id");

        assertEquals("id", tracker.id());
        assertTrue(tracker.created().isEmpty());
        assertTrue(tracker.updated().isEmpty());
        assertTrue(tracker.destroyed().isEmpty());
    }

    @Test
    public void testConstructorWithAllParameters() {
        Map<String, String> created = new HashMap<>();
        created.put("createdState", "createdIdentityId");
        Map<String, String> updated = new HashMap<>();
        updated.put("updatedState", "updatedIdentityId");
        Map<String, String> destroyed = new HashMap<>();
        destroyed.put("destroyedState", "destroyedIdentityId");

        SimpleIdentityChangesTracker tracker =
                new SimpleIdentityChangesTracker("id", created, updated, destroyed);

        assertEquals("id", tracker.id());
        assertEquals(created, tracker.created());
        assertEquals(updated, tracker.updated());
        assertEquals(destroyed, tracker.destroyed());
    }

    @Test
    public void testIdentityHasBeenCreated() {
        SimpleIdentityChangesTracker tracker = new SimpleIdentityChangesTracker("id");

        IdentityChangesTracker updatedTracker =
                tracker.identityHasBeenCreated("newState", "newIdentityId");

        assertEquals("newIdentityId", updatedTracker.id());
        assertEquals("newIdentityId", updatedTracker.created().get("newState"));
    }

    @Test
    public void testIdentityHasBeenUpdated() {
        SimpleIdentityChangesTracker tracker = new SimpleIdentityChangesTracker("id");

        IdentityChangesTracker updatedTracker =
                tracker.identityHasBeenUpdated("newState", "newIdentityId");

        assertEquals("newIdentityId", updatedTracker.id());
        assertEquals("newIdentityId", updatedTracker.updated().get("newState"));
    }

    @Test
    public void testIdentityHasBeenDestroyed() {
        SimpleIdentityChangesTracker tracker = new SimpleIdentityChangesTracker("id");

        IdentityChangesTracker updatedTracker =
                tracker.identityHasBeenDestroyed("newState", "newIdentityId");

        assertEquals("newIdentityId", updatedTracker.id());
        assertEquals("newIdentityId", updatedTracker.destroyed().get("newState"));
    }
}
