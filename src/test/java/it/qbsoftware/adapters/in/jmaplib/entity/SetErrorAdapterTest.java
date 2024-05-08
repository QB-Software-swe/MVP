package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import rs.ltt.jmap.common.entity.SetError;

public class SetErrorAdapterTest {
    @Test
    void testAdaptee() {
        SetError setError = mock(SetError.class);
        SetErrorAdapter adapter = new SetErrorAdapter(setError);

        assertEquals(setError, adapter.adaptee());
    }

    @Test
    void testInvalidPropertiesErorr() {
        String error = "error";
        SetError setError = mock(SetError.class);
        SetErrorAdapter adapter = new SetErrorAdapter(setError);

        adapter.invalidPropertiesErorr(error);
        assertEquals(error, adapter.adaptee().getDescription());
    }

    @Test
    void testNotFoundError() {
        String error = "error";
        SetError setError = mock(SetError.class);
        SetErrorAdapter adapter = new SetErrorAdapter(setError);

        adapter.notFoundError(error);
        assertEquals(error, adapter.adaptee().getDescription());
    }
}
