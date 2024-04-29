package it.qbsoftware.business.ports.in.jmap.entity;

import java.util.stream.Stream;

public interface JmapFilterPort<EntityType> {

    Stream<EmailPort> apply(Stream<EmailPort> emails);
}
