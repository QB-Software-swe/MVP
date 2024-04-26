package it.qbsoftware.business.domain.entity.changes.tracker;

import java.util.Map;

public interface EmailSubmissionChangesTracker {

    String id();

    Map<String, String> created();

    Map<String, String> updated();

    Map<String, String> destroyed();

    EmailSubmissionChangesTracker emailSubmissionHasBeenCreated(String newState,
            String emailSubmissionId);

    EmailSubmissionChangesTracker emailSubmissionHasBeenUpdated(String newState,
            String emailSubmissionId);

    EmailSubmissionChangesTracker emailSubmissionHasBeenDestroyed(String newState,
            String emailSubmissionId);

}