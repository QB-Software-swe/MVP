package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import it.qbsoftware.business.ports.in.jmap.entity.EmailAddressPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityBuilderPort;
import rs.ltt.jmap.common.entity.Identity;
import rs.ltt.jmap.common.entity.Identity.IdentityBuilder;

public class IdentityBuilderAdapterTest {
    @Test
    public void testBccNotNull() {
        // TODO: fix this test
        IdentityBuilder identityBuilder = mock(IdentityBuilder.class);
        EmailAddressPort[] emailAddressPorts = new EmailAddressPort[1];
        IdentityBuilderAdapter identityBuilderAdapter = new IdentityBuilderAdapter(identityBuilder);
        IdentityBuilderPort result = identityBuilderAdapter.bcc(emailAddressPorts);

        verify(identityBuilder).bcc(anyList());
        assertSame(identityBuilderAdapter, result);
    }

    @Test
    public void testBccNull() {
        EmailAddressPort[] emailAddressPort = null;
        IdentityBuilder identityBuilder = mock(IdentityBuilder.class);
        IdentityBuilderAdapter identityBuilderAdapter = new IdentityBuilderAdapter(identityBuilder);

        identityBuilderAdapter.bcc(emailAddressPort);
        verify(identityBuilder).bcc(null);
    }

    @Test
    public void testConstructorNoParam() {
        IdentityBuilder identityBuilder = mock(IdentityBuilder.class);
        IdentityBuilderAdapter identityBuilderAdapter = new IdentityBuilderAdapter(identityBuilder);

        try(MockedStatic<Identity> identityStatic = Mockito.mockStatic(Identity.class)){
            identityStatic.when(Identity::builder).thenReturn(identityBuilder);
            assertEquals(identityBuilderAdapter.reset(), identityBuilderAdapter);
            identityStatic.verify(Identity::builder);
        }
        assertNotNull(identityBuilderAdapter);
    }

    @Test
    public void testBuild() {
        IdentityBuilder identityBuilder = mock(IdentityBuilder.class);
        IdentityBuilderAdapter identityBuilderAdapter = new IdentityBuilderAdapter(identityBuilder);

        identityBuilderAdapter.build();
        verify(identityBuilder).build();
    }

    @Test
    public void testConstructor() {
        IdentityBuilder identityBuilder = mock(IdentityBuilder.class);
        IdentityBuilderAdapter identityBuilderAdapter = new IdentityBuilderAdapter();

         try(MockedStatic<Identity> identityStatic = Mockito.mockStatic(Identity.class)){
            identityStatic.when(Identity::builder).thenReturn(identityBuilder);
            assertEquals(identityBuilderAdapter.reset(), identityBuilderAdapter);
            identityStatic.verify(Identity::builder);
        }
    }

    @Test
    public void testEmail() {
        final String email = "testEmail";
        IdentityBuilder identityBuilder = mock(IdentityBuilder.class);
        IdentityBuilderAdapter identityBuilderAdapter = new IdentityBuilderAdapter(identityBuilder);

        identityBuilderAdapter.email(email);
        verify(identityBuilder).email(email);
    }

    @Test
    public void testHtmlSignature() {
        final String htmlSignature = "testHtmlSignature";
        IdentityBuilder identityBuilder = mock(IdentityBuilder.class);
        IdentityBuilderAdapter identityBuilderAdapter = new IdentityBuilderAdapter(identityBuilder);

        identityBuilderAdapter.htmlSignature(htmlSignature);
        verify(identityBuilder).htmlSignature(htmlSignature);
    }

    @Test
    public void testId() {
        final String id = "testId";
        IdentityBuilder identityBuilder = mock(IdentityBuilder.class);
        IdentityBuilderAdapter identityBuilderAdapter = new IdentityBuilderAdapter(identityBuilder);

        identityBuilderAdapter.id(id);
        verify(identityBuilder).id(id);
    }

    @Test
    public void testMayDelete() {
        Boolean mayDelete = true;
        IdentityBuilder identityBuilder = mock(IdentityBuilder.class);
        IdentityBuilderAdapter identityBuilderAdapter = new IdentityBuilderAdapter(identityBuilder);

        identityBuilderAdapter.mayDelete(mayDelete);
        verify(identityBuilder).mayDelete(mayDelete);
    }

    @Test
    public void testName() {
        final String name = "testName";
        IdentityBuilder identityBuilder = mock(IdentityBuilder.class);
        IdentityBuilderAdapter identityBuilderAdapter = new IdentityBuilderAdapter(identityBuilder);

        identityBuilderAdapter.name(name);
        verify(identityBuilder).name(name);
    }

    @Test
    public void testReplyToNotNull() {
        // TODO: fix this test
        IdentityBuilder identityBuilder = mock(IdentityBuilder.class);
        EmailAddressPort[] emailAddressPorts = new EmailAddressPort[1];
        IdentityBuilderAdapter identityBuilderAdapter = new IdentityBuilderAdapter(identityBuilder);
        IdentityBuilderPort result = identityBuilderAdapter.replyTo(emailAddressPorts);

        verify(identityBuilder).replyTo(anyList());
        assertSame(identityBuilderAdapter, result);
    }


    @Test
    public void testReplyToNull(){
        EmailAddressPort[] emailAddressPort = null;
        IdentityBuilder identityBuilder = mock(IdentityBuilder.class);
        IdentityBuilderAdapter identityBuilderAdapter = new IdentityBuilderAdapter(identityBuilder);

        identityBuilderAdapter.replyTo(emailAddressPort);
        verify(identityBuilder).replyTo(null);
    }

    @Test
    public void testReset() {
        IdentityBuilder identityBuilder = mock(IdentityBuilder.class);
        IdentityBuilderAdapter identityBuilderAdapter = new IdentityBuilderAdapter(identityBuilder);

         try(MockedStatic<Identity> identityStatic = Mockito.mockStatic(Identity.class)){
            identityStatic.when(Identity::builder).thenReturn(identityBuilder);
            assertEquals(identityBuilderAdapter.reset(), identityBuilderAdapter);
            identityStatic.verify(Identity::builder);
        }
    }



    @Test
    public void testTextSignature() {
        final String textSignature = "testTextSignature";
        IdentityBuilder identityBuilder = mock(IdentityBuilder.class);
        IdentityBuilderAdapter identityBuilderAdapter = new IdentityBuilderAdapter(identityBuilder);

        identityBuilderAdapter.textSignature(textSignature);
        verify(identityBuilder).textSignature(textSignature);
    }
}
