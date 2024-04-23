package it.qbsoftware.business.domain.entity.changes.tracker;

import java.util.Map;
import java.util.HashMap;

public class SimpleEmailSubmissionChangesTracker implements EmailSubmissionChangesTracker {
    final private String id;
    final private Map<String, String> created;
    final private Map<String, String> updated;
    final private Map<String, String> destroyed;

    public SimpleEmailSubmissionChangesTracker(final String id) {
        this.id = id;
        this.created = new HashMap<String, String>();
        this.updated = new HashMap<String, String>();
        this.destroyed = new HashMap<String, String>();
    }

    public SimpleEmailSubmissionChangesTracker(final String id, final Map<String, String> created,
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
    public EmailSubmissionChangesTracker emailSubmissionHasBeenCreated(final String newState,
            final String emailSubmissionId) {
        final Map<String, String> copyCreated = this.created;
        copyCreated.put(newState, emailSubmissionId);
        return new SimpleEmailSubmissionChangesTracker(emailSubmissionId, copyCreated, this.updated, this.destroyed);
    }

    @Override
    public EmailSubmissionChangesTracker emailSubmissionHasBeenUpdated(final String newState,
            final String emailSubmissionId) {
        final Map<String, String> copyUpdated = this.updated;
        copyUpdated.put(newState, emailSubmissionId);
        return new SimpleEmailSubmissionChangesTracker(emailSubmissionId, this.created, copyUpdated, this.destroyed);
    }

    @Override
    public EmailSubmissionChangesTracker emailSubmissionHasBeenDestroyed(final String newState,
            final String emailSubmissionId) {
        final Map<String, String> copyDestroyed = this.destroyed;
        copyDestroyed.put(newState, emailSubmissionId);
        return new SimpleEmailSubmissionChangesTracker(emailSubmissionId, this.created, this.updated, copyDestroyed);
    }
}
