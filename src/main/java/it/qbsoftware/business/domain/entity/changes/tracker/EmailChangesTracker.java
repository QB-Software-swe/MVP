package it.qbsoftware.business.domain.entity.changes.tracker;

import java.util.Map;

public interface EmailChangesTracker {

    String id();

    Map<String, String> created();

    Map<String, String> updated();

    Map<String, String> destroyed();

    void emailHasBeenCreated(String newState, String emailId);

    void emailHasBeenUpdated(String newState, String emailId);

    void emailHasBeenDestroyed(String newState, String emailId);

}