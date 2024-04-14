package it.qbsoftware.business.domain.util.get;

public interface GetEntityRetrived<EntityType> {
    public EntityType[] found();

    public String[] notFound();
}
