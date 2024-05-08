package it.qbsoftware.adapters.in.guava;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import java.lang.reflect.Field;
import org.junit.Test;

public class ListMultimapAdapterTest {

    @Test
    public void testPut()
            throws IllegalArgumentException,
                    IllegalAccessException,
                    NoSuchFieldException,
                    SecurityException {
        @SuppressWarnings("unchecked")
        ListMultimap<String, String> listMultimap = mock(ListMultimap.class);
        ListMultimapAdapter<String, String> listMultimapAdapter = new ListMultimapAdapter<>();

        Field listMultimapField = ListMultimapAdapter.class.getDeclaredField("listMultimap");
        listMultimapField.setAccessible(true);
        listMultimapField.set(listMultimapAdapter, listMultimap);

        listMultimapAdapter.put("key", "value");
        verify(listMultimap).put("key", "value");
    }

    @Test
    public void testValues()
            throws NoSuchFieldException,
                    SecurityException,
                    IllegalArgumentException,
                    IllegalAccessException {
        @SuppressWarnings("unchecked")
        ListMultimap<String, String> listMultimap = mock(ListMultimap.class);
        ListMultimapAdapter<String, String> listMultimapAdapter = new ListMultimapAdapter<>();

        Field listMultimapField = ListMultimapAdapter.class.getDeclaredField("listMultimap");
        listMultimapField.setAccessible(true);
        listMultimapField.set(listMultimapAdapter, listMultimap);

        listMultimapAdapter.values();
        verify(listMultimap).values();
    }

    @Test
    public void testAdaptee()
            throws NoSuchFieldException,
                    SecurityException,
                    IllegalArgumentException,
                    IllegalAccessException {
        ListMultimap<String, String> listMultimap = ArrayListMultimap.create();
        ListMultimapAdapter<String, String> listMultimapAdapter = new ListMultimapAdapter<>();

        Field listMultimapField = ListMultimapAdapter.class.getDeclaredField("listMultimap");
        listMultimapField.setAccessible(true);
        listMultimapField.set(listMultimapAdapter, listMultimap);

        assertEquals(listMultimap, listMultimapAdapter.adaptee());
    }
}
