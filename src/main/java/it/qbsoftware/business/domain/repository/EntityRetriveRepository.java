package it.qbsoftware.business.domain.repository;

import java.util.Map;

//FIXME: name
public interface EntityRetriveRepository<EntityType> {
    public EntityType[] retriveAll(final String accountId);

    public Map<String, EntityType> retrive(final String[] id);
}
