package it.qbsoftware.business.ports.in.utils;

public interface ListMultimapPort<K, V> {
    public void put(K key, V value);

    public V[] values();
}
