package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;

import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import org.junit.jupiter.api.Test;

public class SetErrorEnumAdapterTest {
    @Test
    void testInvalidPatch() {
        SetErrorEnumAdapter adapter = new SetErrorEnumAdapter();
        SetErrorPort error = adapter.invalidPatch();

        assertEquals(SetErrorAdapter.class, error.getClass());
    }

    @Test
    void testInvalidProperties() {
        SetErrorEnumAdapter adapter = new SetErrorEnumAdapter();
        SetErrorPort error = adapter.invalidProperties();

        assertEquals(SetErrorAdapter.class, error.getClass());
    }

    @Test
    void testNotFound() {
        SetErrorEnumAdapter adapter = new SetErrorEnumAdapter();
        SetErrorPort error = adapter.notFound();

        assertEquals(SetErrorAdapter.class, error.getClass());
    }

    @Test
    void testSingleton() {
        SetErrorEnumAdapter adapter = new SetErrorEnumAdapter();
        SetErrorPort error = adapter.singleton();

        assertEquals(SetErrorAdapter.class, error.getClass());
    }
}
