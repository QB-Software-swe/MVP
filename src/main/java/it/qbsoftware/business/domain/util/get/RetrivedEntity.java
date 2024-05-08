package it.qbsoftware.business.domain.util.get;

public record RetrivedEntity<EntityType>(EntityType[] found, String[] notFound) {}
