package it.qbsoftware.business.domain.entity.changes.tracker;

import java.util.Map;

public interface MailboxChangesTracker {

    String id();

    Map<String, String> created();

    Map<String, String> updated();

    Map<String, String> destroyed();

    MailboxChangesTracker mailboxHasBeenCreated(String newState, String mailboxId);

    MailboxChangesTracker mailboxHasBeenUpdated(String newState, String mailboxId);

    MailboxChangesTracker mailboxHasBeenDestroyed(String newState, String mailboxId);
}
