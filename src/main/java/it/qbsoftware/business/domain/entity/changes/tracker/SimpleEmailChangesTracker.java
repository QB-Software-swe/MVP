package it.qbsoftware.business.domain.entity.changes.tracker;

import java.util.HashMap;
import java.util.Map;

public class SimpleEmailChangesTracker implements EmailChangesTracker {
    private final String id;
    private final Map<String, String> created;
    private final Map<String, String> updated;
    private final Map<String, String> destroyed;

    public SimpleEmailChangesTracker(final String id) {
        this.id = id;
        this.created = new HashMap<String, String>();
        this.updated = new HashMap<String, String>();
        this.destroyed = new HashMap<String, String>();
    }

    public SimpleEmailChangesTracker(
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
        return this.created;
    }

    @Override
    public Map<String, String> updated() {
        return this.updated;
    }

    @Override
    public Map<String, String> destroyed() {
        return this.destroyed;
    }

    @Override
    public SimpleEmailChangesTracker emailHasBeenCreated(
            final String newState, final String emailId) {
        final Map<String, String> copyCreated = this.created;
        copyCreated.put(newState, emailId);
        return new SimpleEmailChangesTracker(emailId, copyCreated, this.updated, this.destroyed);
    }

    @Override
    public SimpleEmailChangesTracker emailHasBeenUpdated(
            final String newState, final String emailId) {
        final Map<String, String> copyUpdated = this.updated;
        copyUpdated.put(newState, emailId);
        return new SimpleEmailChangesTracker(emailId, this.created, copyUpdated, this.destroyed);
    }

    @Override
    public SimpleEmailChangesTracker emailHasBeenDestroyed(
            final String newState, final String emailId) {
        final Map<String, String> copyDestroyed = this.destroyed;
        copyDestroyed.put(newState, emailId);
        return new SimpleEmailChangesTracker(emailId, this.created, this.updated, copyDestroyed);
    }
}
