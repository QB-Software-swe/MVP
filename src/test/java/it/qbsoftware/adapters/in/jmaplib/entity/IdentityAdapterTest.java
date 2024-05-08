package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.ports.in.jmap.entity.EmailAddressPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityBuilderPort;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import rs.ltt.jmap.common.entity.EmailAddress;
import rs.ltt.jmap.common.entity.Identity;

public class IdentityAdapterTest {
    @Test
    public void testAdaptee() {
        Identity identity = mock(Identity.class);
        IdentityAdapter identityAdapter = new IdentityAdapter(identity);

        assertEquals(identity, identityAdapter.adaptee());
    }

    @Test
    public void testGetBccNotNull() {
        Identity identity = mock(Identity.class);

        List<EmailAddress> replyToList = new ArrayList<>();
        IdentityAdapter identityAdapter = new IdentityAdapter(identity);

        when(identity.getBcc()).thenReturn(replyToList);

        EmailAddressPort[] result = identityAdapter.getBcc();

        assertNotNull(result);
        assertEquals(replyToList.size(), result.length);
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testGetBccNull() {
        Identity identity = mock(Identity.class);
        IdentityAdapter identityAdapter = new IdentityAdapter(identity);
        when(identity.getBcc()).thenReturn(null);

        assertEquals(null, identityAdapter.getBcc());
    }

    @Test
    public void testGetBuilder() {
        Identity identity = mock(Identity.class);
        List<EmailAddress> replyToList = new ArrayList<>();
        List<EmailAddress> bccList = new ArrayList<>();

        when(identity.getId()).thenReturn("testId");
        when(identity.getName()).thenReturn("testName");
        when(identity.getEmail()).thenReturn("testEmail");
        when(identity.getReplyTo()).thenReturn(replyToList);
        when(identity.getBcc()).thenReturn(bccList);
        when(identity.getTextSignature()).thenReturn("testTextSignature");
        when(identity.getHtmlSignature()).thenReturn("testHtmlSignature");
        when(identity.getMayDelete()).thenReturn(true);

        IdentityAdapter identityAdapter = new IdentityAdapter(identity);
        IdentityBuilderPort builder = identityAdapter.getBuilder();

        verify(identity).getId();
        verify(identity).getName();
        verify(identity).getEmail();
        verify(identity).getReplyTo();
        verify(identity).getBcc();
        verify(identity).getTextSignature();
        verify(identity).getHtmlSignature();
        verify(identity).getMayDelete();

        assertNotNull(builder);
    }

    @Test
    public void testGetEmail() {
        Identity identity = mock(Identity.class);
        IdentityAdapter identityAdapter = new IdentityAdapter(identity);
        when(identity.getEmail()).thenReturn("testEmail");

        assertEquals("testEmail", identityAdapter.getEmail());
        verify(identity).getEmail();
    }

    @Test
    public void testGetHtmlSignature() {
        Identity identity = mock(Identity.class);
        IdentityAdapter identityAdapter = new IdentityAdapter(identity);
        when(identity.getHtmlSignature()).thenReturn("testHtmlSignature");

        assertEquals("testHtmlSignature", identityAdapter.getHtmlSignature());
        verify(identity).getHtmlSignature();
    }

    @Test
    public void testGetId() {
        Identity identity = mock(Identity.class);
        IdentityAdapter identityAdapter = new IdentityAdapter(identity);
        when(identity.getId()).thenReturn("testId");

        assertEquals("testId", identityAdapter.getId());
        verify(identity).getId();
    }

    @Test
    public void testGetMayDelete() {
        Identity identity = mock(Identity.class);
        IdentityAdapter identityAdapter = new IdentityAdapter(identity);
        when(identity.getMayDelete()).thenReturn(true);

        assertEquals(true, identityAdapter.getMayDelete());
        verify(identity).getMayDelete();
    }

    @Test
    public void testGetName() {
        Identity identity = mock(Identity.class);
        IdentityAdapter identityAdapter = new IdentityAdapter(identity);
        when(identity.getName()).thenReturn("testName");

        assertEquals("testName", identityAdapter.getName());
        verify(identity).getName();
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testGetReplyToNull() {
        Identity identity = mock(Identity.class);
        IdentityAdapter identityAdapter = new IdentityAdapter(identity);
        when(identity.getReplyTo()).thenReturn(null);

        assertEquals(null, identityAdapter.getReplyTo());
    }

    @Test
    public void testGetReplyToNotNull() {
        Identity identity = mock(Identity.class);
        List<EmailAddress> replyToList = new ArrayList<>();
        IdentityAdapter identityAdapter = new IdentityAdapter(identity);

        when(identity.getReplyTo()).thenReturn(replyToList);

        EmailAddressPort[] result = identityAdapter.getReplyTo();

        assertNotNull(result);
        assertEquals(replyToList.size(), result.length);
    }

    @Test
    public void testGetTextSignature() {
        Identity identity = mock(Identity.class);
        IdentityAdapter identityAdapter = new IdentityAdapter(identity);
        when(identity.getTextSignature()).thenReturn("testTextSignature");

        assertEquals("testTextSignature", identityAdapter.getTextSignature());
        verify(identity).getTextSignature();
    }
}
