package it.qbsoftware.business.domain.entity.changes.tracker;

import java.util.Map;

public interface ThreadChangesTracker {
    String id();

    Map<String, String> created();

    Map<String, String> updated();

    Map<String, String> destroyed();

    ThreadChangesTracker threadHasBeenCreated(String newState, String threadId);

    ThreadChangesTracker threadHasBeenUpdated(String newState, String threadId);

    ThreadChangesTracker threadHasBeenDestroyed(String newState, String threadId);
}
