package it.qbsoftware.adapters.in.guava;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.google.common.base.Splitter;
import java.lang.reflect.Field;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class SplitterAdapterTest {
    @Test
    void testOn() {
        Splitter splitter = mock(Splitter.class);
        SplitterAdapter splitterAdapter = new SplitterAdapter();

        try (MockedStatic<Splitter> theMock = Mockito.mockStatic(Splitter.class)) {
            theMock.when(() -> Splitter.on(' ')).thenReturn(splitter);
            assertEquals(splitterAdapter, splitterAdapter.on(' '));
        }
    }

    @Test
    void testSplitToList()
            throws NoSuchFieldException,
                    SecurityException,
                    IllegalArgumentException,
                    IllegalAccessException {
        Splitter splitter = mock(Splitter.class);
        SplitterAdapter splitterAdapter = new SplitterAdapter();

        Field splitterField = SplitterAdapter.class.getDeclaredField("splitter");
        splitterField.setAccessible(true);
        splitterField.set(splitterAdapter, splitter);

        CharSequence sequence = "test";
        splitterAdapter.splitToList(sequence);
        verify(splitter).splitToList(sequence);
    }
}
