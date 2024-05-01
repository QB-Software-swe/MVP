package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import rs.ltt.jmap.common.Request.Invocation;

public class InvocationResultReferenceAdapterTest {
    @Test
    public void testInvocationResultReference() {
        Invocation.ResultReference invocationResultReference = mock(Invocation.ResultReference.class);
        InvocationResultReferenceAdapter invocationResultReferenceAdapter = new InvocationResultReferenceAdapter(
                invocationResultReference);

        assertEquals(invocationResultReference, invocationResultReferenceAdapter.invocationResultReference());
    }
}
