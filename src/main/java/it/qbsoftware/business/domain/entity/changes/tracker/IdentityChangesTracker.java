package it.qbsoftware.business.domain.entity.changes.tracker;

import java.util.Map;

public interface IdentityChangesTracker {

    String id();

    Map<String, String> created();

    Map<String, String> updated();

    Map<String, String> destroyed();

    IdentityChangesTracker identityHasBeenCreated(String newState, String identityId);

    IdentityChangesTracker identityHasBeenUpdated(String newState, String identityId);

    IdentityChangesTracker identityHasBeenDestroyed(String newState, String identityId);
}
