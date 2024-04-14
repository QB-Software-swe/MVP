package it.qbsoftware.business.domain.util.get;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;

public interface GetEntityPropertiesFilter<EntityType> {
    public EntityType[] filter(final EntityType[] entities, final String[] properties) throws InvalidArgumentsException;
}
