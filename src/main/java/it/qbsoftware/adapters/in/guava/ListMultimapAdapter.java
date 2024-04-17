package it.qbsoftware.adapters.in.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import it.qbsoftware.business.ports.in.guava.ListMultimapPort;

import java.util.Collection;

public class ListMultimapAdapter<K, V> implements ListMultimapPort<K, V> {
    ListMultimap<K, V> listMultimap = ArrayListMultimap.create();

    @Override
    public void put(K key, V value) {
        listMultimap.put(key, value);
    }

    public Collection<V> values() {
        return listMultimap.values();
    }

    public ListMultimap<K, V> listMultimap() {
        return listMultimap;
    }
}
