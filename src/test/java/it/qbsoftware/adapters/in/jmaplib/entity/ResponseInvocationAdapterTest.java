package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import rs.ltt.jmap.common.Response;

public class ResponseInvocationAdapterTest {
    @Test
    public void testAdaptee() {
        Response.Invocation invocation = mock(Response.Invocation.class);
        ResponseInvocationAdapter responseInvocationAdapter = new ResponseInvocationAdapter(invocation);

        assertEquals(invocation, responseInvocationAdapter.adaptee());
    }
}
