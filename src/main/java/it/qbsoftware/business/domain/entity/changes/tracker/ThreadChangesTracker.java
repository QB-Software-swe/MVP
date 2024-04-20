package it.qbsoftware.business.domain.entity.changes.tracker;

import java.util.Map;

public interface ThreadChangesTracker {
    String id();

    Map<String, String> created();

    Map<String, String> updated();

    Map<String, String> destroyed();

    void threadHasBeenCreated(String newState, String emailId);

    void threadHasBeenUpdated(String newState, String emailId);

    void threadHasBeenDestroyed(String newState, String emailId);
}
