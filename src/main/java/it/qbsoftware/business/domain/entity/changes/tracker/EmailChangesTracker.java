package it.qbsoftware.business.domain.entity.changes.tracker;

import java.util.Map;

public interface EmailChangesTracker {

    String id();

    Map<String, String> created();

    Map<String, String> updated();

    Map<String, String> destroyed();

    EmailChangesTracker emailHasBeenCreated(String newState, String emailId);

    EmailChangesTracker emailHasBeenUpdated(String newState, String emailId);

    EmailChangesTracker emailHasBeenDestroyed(String newState, String emailId);
}
