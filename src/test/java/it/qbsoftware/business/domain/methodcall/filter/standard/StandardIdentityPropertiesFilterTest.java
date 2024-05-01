package it.qbsoftware.business.domain.methodcall.filter.standard;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.ports.in.jmap.entity.EmailAddressPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class StandardIdentityPropertiesFilterTest {

    @Mock
    private IdentityBuilderPort identityBuilderPort;

    @Mock
    private IdentityPort identityPort;

    @Mock
    private EmailAddressPort EmailAddressPort;

    @InjectMocks
    private StandardIdentityPropertiesFilter standardIdentityPropertiesFilter;


    @Test
    public void testFilterWithNullProperties() throws InvalidArgumentsException {
        IdentityPort[] identitys = new IdentityPort[] {identityPort};

        IdentityPort[] result = standardIdentityPropertiesFilter.filter(identitys, null);

        assertArrayEquals(identitys, result);
    }

    @Test
    public void testFilterWithValidProperties() throws InvalidArgumentsException {
        IdentityPort[] identitys = new IdentityPort[] {identityPort};
        EmailAddressPort[] replyTo = new EmailAddressPort[] {EmailAddressPort};
        EmailAddressPort[] bcc = new EmailAddressPort[] {EmailAddressPort};
        String[] properties = new String[] {"name", "email", "replyTo", "bcc", "textSignature", "htmlSignature", "mayDelete"};

        when(identityPort.getId()).thenReturn("id");
        when(identityPort.getName()).thenReturn("name");
        when(identityPort.getEmail()).thenReturn("email");
        when(identityPort.getReplyTo()).thenReturn(replyTo);
        when(identityPort.getBcc()).thenReturn(bcc);
        when(identityPort.getTextSignature()).thenReturn("textSignature");
        when(identityPort.getHtmlSignature()).thenReturn("htmlSignature");
        when(identityPort.getMayDelete()).thenReturn(true);
        when(identityBuilderPort.reset()).thenReturn(identityBuilderPort);
        when(identityBuilderPort.id(any())).thenReturn(identityBuilderPort);
        when(identityBuilderPort.name(any())).thenReturn(identityBuilderPort);
        when(identityBuilderPort.email(any())).thenReturn(identityBuilderPort);
        when(identityBuilderPort.replyTo(any())).thenReturn(identityBuilderPort);
        when(identityBuilderPort.bcc(any())).thenReturn(identityBuilderPort);
        when(identityBuilderPort.textSignature(any())).thenReturn(identityBuilderPort);
        when(identityBuilderPort.htmlSignature(any())).thenReturn(identityBuilderPort);
        when(identityBuilderPort.mayDelete(any())).thenReturn(identityBuilderPort);
        when(identityBuilderPort.build()).thenReturn(identityPort);

        StandardIdentityPropertiesFilter standardIdentityPropertiesFilter = new StandardIdentityPropertiesFilter(identityBuilderPort);
        IdentityPort[] result = standardIdentityPropertiesFilter.filter(identitys, properties);

        assertArrayEquals(identitys, result);
    }

    @Test
    public void testFilterWithInvalidArgumentsException() throws InvalidArgumentsException {
        IdentityPort[] identitys = new IdentityPort[] {identityPort};
        String[] properties = new String[] {"name", "invalidSignature", "replyTo", "bcc", "textSignature", "htmlSignature", "mayDelete"};

        when(identityPort.getId()).thenReturn("id");
        when(identityPort.getName()).thenReturn("name");
        when(identityBuilderPort.reset()).thenReturn(identityBuilderPort);
        when(identityBuilderPort.id(any())).thenReturn(identityBuilderPort);
        when(identityBuilderPort.name(any())).thenReturn(identityBuilderPort);

        StandardIdentityPropertiesFilter standardIdentityPropertiesFilter = new StandardIdentityPropertiesFilter(identityBuilderPort);

        assertThrows(InvalidArgumentsException.class, () -> standardIdentityPropertiesFilter.filter(identitys, properties));
    }

}
