package it.qbsoftware.business.ports.in.guava;

import java.util.Collection;

public interface ListMultimapPort<K, V> {
    public void put(K key, V value);

    public Collection<V> values();
}
