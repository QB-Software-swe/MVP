package it.qbsoftware.business.domain.methodcall.filter.standard;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.ports.in.jmap.entity.EmailAddressPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.RolePort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class StandardMailboxPropertiesFilterTest {

    @Mock private MailboxBuilderPort mailboxBuilderPort;

    @Mock private MailboxPort mailboxPort;

    @Mock private EmailAddressPort EmailAddressPort;

    @InjectMocks private StandardMailboxPropertiesFilter standardMailboxPropertiesFilter;

    @Test
    public void testFilterWithNullProperties() throws InvalidArgumentsException {
        MailboxPort[] mailboxs = new MailboxPort[] {mailboxPort};

        MailboxPort[] result = standardMailboxPropertiesFilter.filter(mailboxs, null);

        assertArrayEquals(mailboxs, result);
    }

    @Test
    public void testFilterWithValidProperties() throws InvalidArgumentsException {
        MailboxPort[] mailboxs = new MailboxPort[] {mailboxPort};
        RolePort rolePort = mock(RolePort.class);
        String[] properties =
                new String[] {
                    "name",
                    "parentId",
                    "role",
                    "sortOrder",
                    "totalEmails",
                    "unreadEmails",
                    "totalThreads",
                    "unreadThreads",
                    "myRights",
                    "isSubscribed"
                };

        when(mailboxPort.getId()).thenReturn("id");
        when(mailboxPort.getName()).thenReturn("name");
        when(mailboxPort.getParentId()).thenReturn("parentId");
        when(mailboxPort.getRole()).thenReturn(rolePort);
        when(mailboxPort.getSortOrder()).thenReturn(1L);
        when(mailboxPort.getTotalEmails()).thenReturn(1L);
        when(mailboxPort.getUnreadEmails()).thenReturn(1L);
        when(mailboxPort.getTotalThreads()).thenReturn(1L);
        when(mailboxPort.getUnreadThreads()).thenReturn(1L);
        when(mailboxPort.getMyRights()).thenReturn(null);
        when(mailboxPort.getIsSubscribed()).thenReturn(true);
        when(mailboxBuilderPort.reset()).thenReturn(mailboxBuilderPort);
        when(mailboxBuilderPort.id(any())).thenReturn(mailboxBuilderPort);
        when(mailboxBuilderPort.name(any())).thenReturn(mailboxBuilderPort);
        when(mailboxBuilderPort.parentId(any())).thenReturn(mailboxBuilderPort);
        when(mailboxBuilderPort.role(any())).thenReturn(mailboxBuilderPort);
        when(mailboxBuilderPort.sortOrder(any())).thenReturn(mailboxBuilderPort);
        when(mailboxBuilderPort.totalEmails(any())).thenReturn(mailboxBuilderPort);
        when(mailboxBuilderPort.unreadEmails(any())).thenReturn(mailboxBuilderPort);
        when(mailboxBuilderPort.totalThreads(any())).thenReturn(mailboxBuilderPort);
        when(mailboxBuilderPort.unreadThreads(any())).thenReturn(mailboxBuilderPort);
        when(mailboxBuilderPort.myRights(any())).thenReturn(mailboxBuilderPort);
        when(mailboxBuilderPort.isSubscribed(any())).thenReturn(mailboxBuilderPort);
        when(mailboxBuilderPort.build()).thenReturn(mailboxPort);

        StandardMailboxPropertiesFilter standardMailboxPropertiesFilter =
                new StandardMailboxPropertiesFilter(mailboxBuilderPort);
        MailboxPort[] result = standardMailboxPropertiesFilter.filter(mailboxs, properties);

        assertArrayEquals(mailboxs, result);
    }

    @Test
    public void testFilterWithInvalidArgumentsException() throws InvalidArgumentsException {
        MailboxPort[] mailboxs = new MailboxPort[] {mailboxPort};
        String[] properties =
                new String[] {
                    "name",
                    "invalidSignature",
                    "replyTo",
                    "bcc",
                    "textSignature",
                    "htmlSignature",
                    "mayDelete"
                };

        when(mailboxPort.getId()).thenReturn("id");
        when(mailboxPort.getName()).thenReturn("name");
        when(mailboxBuilderPort.reset()).thenReturn(mailboxBuilderPort);
        when(mailboxBuilderPort.id(any())).thenReturn(mailboxBuilderPort);
        when(mailboxBuilderPort.name(any())).thenReturn(mailboxBuilderPort);

        StandardMailboxPropertiesFilter standardMailboxPropertiesFilter =
                new StandardMailboxPropertiesFilter(mailboxBuilderPort);

        assertThrows(
                InvalidArgumentsException.class,
                () -> standardMailboxPropertiesFilter.filter(mailboxs, properties));
    }
}
