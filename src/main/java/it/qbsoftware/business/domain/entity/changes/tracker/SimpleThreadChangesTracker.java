package it.qbsoftware.business.domain.entity.changes.tracker;

import java.util.HashMap;
import java.util.Map;

public class SimpleThreadChangesTracker implements ThreadChangesTracker {
    private final String id;
    private final Map<String, String> created;
    private final Map<String, String> updated;
    private final Map<String, String> destroyed;

    public SimpleThreadChangesTracker(final String id) {
        this.id = id;
        this.created = new HashMap<String, String>();
        this.updated = new HashMap<String, String>();
        this.destroyed = new HashMap<String, String>();
    }

    public SimpleThreadChangesTracker(
            final String id,
            final Map<String, String> created,
            final Map<String, String> updated,
            final Map<String, String> destroyed) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.destroyed = destroyed;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public Map<String, String> created() {
        return created;
    }

    @Override
    public Map<String, String> updated() {
        return updated;
    }

    @Override
    public Map<String, String> destroyed() {
        return destroyed;
    }

    @Override
    public SimpleThreadChangesTracker threadHasBeenCreated(
            final String newState, final String threadId) {
        final Map<String, String> copyCreated = this.created;
        copyCreated.put(newState, threadId);
        return new SimpleThreadChangesTracker(this.id, copyCreated, this.updated, this.destroyed);
    }

    @Override
    public SimpleThreadChangesTracker threadHasBeenUpdated(
            final String newState, final String threadId) {
        final Map<String, String> copyUpdated = this.updated;
        copyUpdated.put(newState, threadId);
        return new SimpleThreadChangesTracker(this.id, this.created, copyUpdated, this.destroyed);
    }

    @Override
    public SimpleThreadChangesTracker threadHasBeenDestroyed(
            final String newState, final String threadId) {
        final Map<String, String> copyDestroyed = this.destroyed;
        copyDestroyed.put(newState, threadId);
        return new SimpleThreadChangesTracker(this.id, this.created, this.updated, copyDestroyed);
    }
}
