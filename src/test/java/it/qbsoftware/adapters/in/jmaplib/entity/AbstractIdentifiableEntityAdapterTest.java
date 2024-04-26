package it.qbsoftware.adapters.in.jmaplib.entity;

import org.junit.Test;
import rs.ltt.jmap.common.entity.AbstractIdentifiableEntity;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AbstractIdentifiableEntityAdapterTest {

    @Test
    public void testGetId() {
        AbstractIdentifiableEntity abstractIdentifiableEntity = mock(AbstractIdentifiableEntity.class);
        when(abstractIdentifiableEntity.getId()).thenReturn("id");

        assertEquals("id", new AbstractIdentifiableEntityAdapter(abstractIdentifiableEntity).getId());
    }
}
