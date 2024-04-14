package it.qbsoftware.business.domain.util.get;

public interface GetRetrivedEntity<EntityType> {
    public EntityType[] found();

    public String[] notFound();
}
