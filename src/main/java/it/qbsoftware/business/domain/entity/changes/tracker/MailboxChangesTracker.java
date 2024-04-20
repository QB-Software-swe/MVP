package it.qbsoftware.business.domain.entity.changes.tracker;

import java.util.Map;

public interface MailboxChangesTracker {

    String id();

    Map<String, String> created();

    Map<String, String> updated();

    Map<String, String> destroyed();

    void mailboxHasBeenCreated(String newState, String emailId);

    void mailboxHasBeenUpdated(String newState, String emailId);

    void mailboxHasBeenDestroyed(String newState, String emailId);

}