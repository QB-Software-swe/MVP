package it.qbsoftware.business.domain.entity.changes.tracker;

import java.util.HashMap;
import java.util.Map;

public class SimpleIdentityChangesTracker implements IdentityChangesTracker {
    private final String id;
    private final Map<String, String> created;
    private final Map<String, String> updated;
    private final Map<String, String> destroyed;

    public SimpleIdentityChangesTracker(final String id) {
        this.id = id;
        this.created = new HashMap<String, String>();
        this.updated = new HashMap<String, String>();
        this.destroyed = new HashMap<String, String>();
    }

    public SimpleIdentityChangesTracker(
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
    public IdentityChangesTracker identityHasBeenCreated(
            final String newState, final String identityId) {
        final Map<String, String> copyCreated = this.created;
        copyCreated.put(newState, identityId);
        return new SimpleIdentityChangesTracker(
                identityId, copyCreated, this.updated, this.destroyed);
    }

    @Override
    public IdentityChangesTracker identityHasBeenUpdated(
            final String newState, final String identityId) {
        final Map<String, String> copyUpdated = this.updated;
        copyUpdated.put(newState, identityId);
        return new SimpleIdentityChangesTracker(
                identityId, this.created, copyUpdated, this.destroyed);
    }

    @Override
    public IdentityChangesTracker identityHasBeenDestroyed(
            final String newState, final String identityId) {
        final Map<String, String> copyDestroyed = this.destroyed;
        copyDestroyed.put(newState, identityId);
        return new SimpleIdentityChangesTracker(
                identityId, this.created, this.updated, copyDestroyed);
    }
}
