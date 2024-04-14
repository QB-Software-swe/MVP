package it.qbsoftware.business.domain.util.get;

public class GetEntityRetrivedImp<EntityType> implements GetRetrivedEntity<EntityType> {
    final EntityType[] found;
    final String[] notFound;

    public GetEntityRetrivedImp(final EntityType[] found, final String[] notFound) {
        this.found = found;
        this.notFound = notFound;
    }

    @Override
    public EntityType[] found() {
        return this.found;
    }

    @Override
    public String[] notFound() {
        return this.notFound;
    }

}
