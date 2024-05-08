package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxRightsPort;
import it.qbsoftware.business.ports.in.jmap.entity.RolePort;
import org.junit.Test;
import rs.ltt.jmap.common.entity.Mailbox;
import rs.ltt.jmap.common.entity.MailboxRights;
import rs.ltt.jmap.common.entity.Role;

public class MailboxAdapterTest {
    @Test
    public void testAdaptee() {
        Mailbox mailbox = mock(Mailbox.class);
        MailboxAdapter mailboxAdapter = new MailboxAdapter(mailbox);

        assertNotNull(mailboxAdapter.adaptee());
    }

    @Test
    public void testGetBuilder() {
        Mailbox mailbox = mock(Mailbox.class);
        Role role = mock(Role.class);

        when(mailbox.getId()).thenReturn("testId");
        when(mailbox.getIsSubscribed()).thenReturn(true);
        when(mailbox.getMyRights()).thenReturn(new MailboxRights());
        when(mailbox.getName()).thenReturn("testName");
        when(mailbox.getParentId()).thenReturn("testParentId");
        when(mailbox.getSortOrder()).thenReturn(1L);
        when(mailbox.getTotalEmails()).thenReturn(2L);
        when(mailbox.getUnreadEmails()).thenReturn(3L);
        when(mailbox.getTotalThreads()).thenReturn(4L);
        when(mailbox.getUnreadEmails()).thenReturn(5L);
        when(mailbox.getRole()).thenReturn(role);

        MailboxAdapter mailboxAdapter = new MailboxAdapter(mailbox);
        MailboxBuilderPort builder = mailboxAdapter.toBuilder();

        verify(mailbox).getId();
        verify(mailbox).getIsSubscribed();
        verify(mailbox).getMyRights();
        verify(mailbox).getName();
        verify(mailbox).getParentId();
        verify(mailbox).getSortOrder();
        verify(mailbox).getTotalEmails();
        verify(mailbox).getUnreadEmails();
        verify(mailbox).getTotalThreads();
        verify(mailbox).getUnreadEmails();
        verify(mailbox).getRole();

        assertNotNull(builder);
    }

    @Test
    public void testGetId() {
        Mailbox mailbox = mock(Mailbox.class);
        MailboxAdapter mailboxAdapter = new MailboxAdapter(mailbox);

        mailboxAdapter.getId();
        verify(mailbox).getId();
    }

    @Test
    public void testGetIsSubscribed() {
        Mailbox mailbox = mock(Mailbox.class);
        MailboxAdapter mailboxAdapter = new MailboxAdapter(mailbox);

        mailboxAdapter.getIsSubscribed();
        verify(mailbox).getIsSubscribed();
    }

    @Test
    public void testGetMyRights() {
        Mailbox mailbox = mock(Mailbox.class);
        MailboxRights mailboxRights = mock(MailboxRights.class);
        MailboxAdapter mailboxAdapter = new MailboxAdapter(mailbox);

        when(mailbox.getMyRights()).thenReturn(mailboxRights);
        MailboxRightsPort result = mailboxAdapter.getMyRights();

        assertNotNull(result);
    }

    @Test
    public void testGetName() {
        Mailbox mailbox = mock(Mailbox.class);
        MailboxAdapter mailboxAdapter = new MailboxAdapter(mailbox);

        mailboxAdapter.getName();
        verify(mailbox).getName();
    }

    @Test
    public void testGetParentId() {
        Mailbox mailbox = mock(Mailbox.class);
        MailboxAdapter mailboxAdapter = new MailboxAdapter(mailbox);

        mailboxAdapter.getParentId();
        verify(mailbox).getParentId();
    }

    @Test
    public void testGetRoleNotNull() {
        Role role = mock(Role.class);
        Mailbox mailbox = mock(Mailbox.class);
        MailboxAdapter mailboxAdapter = new MailboxAdapter(mailbox);

        when(mailbox.getRole()).thenReturn(role);
        mailboxAdapter.getRole();
        verify(mailbox).getRole();
        assertNotNull(mailboxAdapter.getRole());
    }

    @Test
    public void testGetRoleNull() {
        Role role = null;
        Mailbox mailbox = mock(Mailbox.class);
        MailboxAdapter mailboxAdapter = new MailboxAdapter(mailbox);

        when(mailbox.getRole()).thenReturn(role);
        RolePort result = mailboxAdapter.getRole();
        assertEquals(role, result);
    }

    @Test
    public void testGetSortOrder() {
        Mailbox mailbox = mock(Mailbox.class);
        MailboxAdapter mailboxAdapter = new MailboxAdapter(mailbox);

        mailboxAdapter.getSortOrder();
        verify(mailbox).getSortOrder();
    }

    @Test
    public void testGetTotalEmails() {
        Mailbox mailbox = mock(Mailbox.class);
        MailboxAdapter mailboxAdapter = new MailboxAdapter(mailbox);

        mailboxAdapter.getTotalEmails();
        verify(mailbox).getTotalEmails();
    }

    @Test
    public void testGetTotalThreads() {
        Mailbox mailbox = mock(Mailbox.class);
        MailboxAdapter mailboxAdapter = new MailboxAdapter(mailbox);

        mailboxAdapter.getTotalThreads();
        verify(mailbox).getTotalThreads();
    }

    @Test
    public void testGetUnreadEmails() {
        Mailbox mailbox = mock(Mailbox.class);
        MailboxAdapter mailboxAdapter = new MailboxAdapter(mailbox);

        mailboxAdapter.getUnreadEmails();
        verify(mailbox).getUnreadEmails();
    }

    @Test
    public void testGetUnreadThreads() {
        Mailbox mailbox = mock(Mailbox.class);
        MailboxAdapter mailboxAdapter = new MailboxAdapter(mailbox);

        mailboxAdapter.getUnreadThreads();
        verify(mailbox).getUnreadEmails();
    }
}
