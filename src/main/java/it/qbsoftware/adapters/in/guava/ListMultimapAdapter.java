package it.qbsoftware.adapters.in.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import java.util.Collection;

import it.qbsoftware.business.ports.in.utils.ListMultimapPort;

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
