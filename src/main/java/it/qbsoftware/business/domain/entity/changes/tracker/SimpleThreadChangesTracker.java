package it.qbsoftware.business.domain.entity.changes.tracker;

import java.util.Map;
import java.util.HashMap;

public class SimpleThreadChangesTracker implements ThreadChangesTracker {
    private final String id;
    private Map<String, String> created;
    private Map<String, String> updated;
    private Map<String, String> destroyed;

    public SimpleThreadChangesTracker(final String id) {
        this.id = id;
        this.created = new HashMap<String, String>();
        this.updated = new HashMap<String, String>();
        this.destroyed = new HashMap<String, String>();
    }

    public SimpleThreadChangesTracker(final String id, final Map<String, String> created,
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
    public void threadHasBeenCreated(final String newState, final String emailId) {
        this.created.put(newState, emailId);
    }

    @Override
    public void threadHasBeenUpdated(final String newState, final String emailId) {
        this.updated.put(newState, emailId);
    }

    @Override
    public void threadHasBeenDestroyed(final String newState, final String emailId) {
        this.destroyed.put(newState, emailId);
    }
}
