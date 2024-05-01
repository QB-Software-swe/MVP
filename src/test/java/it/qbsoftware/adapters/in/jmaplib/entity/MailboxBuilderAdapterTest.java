package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import rs.ltt.jmap.common.entity.Mailbox;
import rs.ltt.jmap.common.entity.Mailbox.MailboxBuilder;

public class MailboxBuilderAdapterTest {

    @Test
    public void testBuild() {
        MailboxBuilder mailboxBuilder = mock(MailboxBuilder.class);
        MailboxBuilderAdapter mailboxBuilderAdapter = new MailboxBuilderAdapter(mailboxBuilder);

        Mailbox mailbox = mock(Mailbox.class);

        when(mailboxBuilder.build()).thenReturn(mailbox);

        MailboxPort mailboxPort = mailboxBuilderAdapter.build();

        assertEquals(MailboxAdapter.class, mailboxPort.getClass());
    }

    @Test
    public void testConstructor() {
        MailboxBuilder mailboxBuilder = mock(MailboxBuilder.class);
        MailboxBuilderAdapter MailboxBuilderAdapter = new MailboxBuilderAdapter();

        try(MockedStatic<Mailbox> mailboxSatic = Mockito.mockStatic(Mailbox.class)){
            mailboxSatic.when(Mailbox::builder).thenReturn(mailboxBuilder);
            assertEquals(MailboxBuilderAdapter.reset(), MailboxBuilderAdapter);
            mailboxSatic.verify(Mailbox::builder);
        }
    }

    @Test
    public void testId() {
        final String id = "testId";
        MailboxBuilder mailboxBuilder = mock(MailboxBuilder.class);
        MailboxBuilderAdapter mailboxBuilderAdapter = new MailboxBuilderAdapter(mailboxBuilder);

        mailboxBuilderAdapter.id(id);
        verify(mailboxBuilder).id(id);
    }

    @Test
    public void testIsSubscribed() {
        Boolean isSubscribed = true;
        MailboxBuilder mailboxBuilder = mock(MailboxBuilder.class);
        MailboxBuilderAdapter mailboxBuilderAdapter = new MailboxBuilderAdapter(mailboxBuilder);

        mailboxBuilderAdapter.isSubscribed(isSubscribed);
        verify(mailboxBuilder).isSubscribed(isSubscribed);
    }

    @Test
    public void testMyRights() {
        MailboxRightsAdapter myRights = mock(MailboxRightsAdapter.class);
        MailboxBuilder mailboxBuilder = mock(MailboxBuilder.class);
        MailboxBuilderAdapter mailboxBuilderAdapter = new MailboxBuilderAdapter(mailboxBuilder);

        mailboxBuilderAdapter.myRights(myRights);
        verify(myRights).adaptee();
    }

    @Test
    public void testName() {
        final String name = "testName";
        MailboxBuilder mailboxBuilder = mock(MailboxBuilder.class);
        MailboxBuilderAdapter mailboxBuilderAdapter = new MailboxBuilderAdapter(mailboxBuilder);

        mailboxBuilderAdapter.name(name);
        verify(mailboxBuilder).name(name);
    }

    @Test
    public void testParentId() {
        final String parentId = "testParentId";
        MailboxBuilder mailboxBuilder = mock(MailboxBuilder.class);
        MailboxBuilderAdapter mailboxBuilderAdapter = new MailboxBuilderAdapter(mailboxBuilder);

        mailboxBuilderAdapter.parentId(parentId);
        verify(mailboxBuilder).parentId(parentId);
    }

    @Test
    public void testReset() {
        MailboxBuilder mailboxBuilder = mock(MailboxBuilder.class);
        MailboxBuilderAdapter MailboxBuilderAdapter = new MailboxBuilderAdapter(mailboxBuilder);

        try(MockedStatic<Mailbox> mailboxSatic = Mockito.mockStatic(Mailbox.class)){
            mailboxSatic.when(Mailbox::builder).thenReturn(mailboxBuilder);
            assertEquals(MailboxBuilderAdapter.reset(), MailboxBuilderAdapter);
            mailboxSatic.verify(Mailbox::builder);
        }
    }

    

    @Test
    public void testRole() {
        RoleAdapter role = mock(RoleAdapter.class);
        MailboxBuilder mailboxBuilder = mock(MailboxBuilder.class);
        MailboxBuilderAdapter mailboxBuilderAdapter = new MailboxBuilderAdapter(mailboxBuilder);

        mailboxBuilderAdapter.role(role);
        verify(mailboxBuilder).role(role.getRole());
    }

    @Test
    public void testSortOrder() {
        final Long sortOrder = 1L;
        MailboxBuilder mailboxBuilder = mock(MailboxBuilder.class);
        MailboxBuilderAdapter mailboxBuilderAdapter = new MailboxBuilderAdapter(mailboxBuilder);

        mailboxBuilderAdapter.sortOrder(sortOrder);
        verify(mailboxBuilder).sortOrder(sortOrder);
    }

    @Test
    public void testTotalEmails() {
        final Long totalEmails = 2L;
        MailboxBuilder mailboxBuilder = mock(MailboxBuilder.class);
        MailboxBuilderAdapter mailboxBuilderAdapter = new MailboxBuilderAdapter(mailboxBuilder);

        mailboxBuilderAdapter.totalEmails(totalEmails);
        verify(mailboxBuilder).totalEmails(totalEmails);
    }

    @Test
    public void testTotalThreads() {
        final Long totalThreads = 4L;
        MailboxBuilder mailboxBuilder = mock(MailboxBuilder.class);
        MailboxBuilderAdapter mailboxBuilderAdapter = new MailboxBuilderAdapter(mailboxBuilder);

        mailboxBuilderAdapter.totalThreads(totalThreads);
        verify(mailboxBuilder).totalThreads(totalThreads);
    }

    @Test
    public void testUnreadEmails() {
        final Long unreadEmails = 5L;
        MailboxBuilder mailboxBuilder = mock(MailboxBuilder.class);
        MailboxBuilderAdapter mailboxBuilderAdapter = new MailboxBuilderAdapter(mailboxBuilder);

        mailboxBuilderAdapter.unreadEmails(unreadEmails);
        verify(mailboxBuilder).unreadEmails(unreadEmails);
    }

    @Test
    public void testUnreadThreads() {
        final Long unreadThreads = 6L;
        MailboxBuilder mailboxBuilder = mock(MailboxBuilder.class);
        MailboxBuilderAdapter mailboxBuilderAdapter = new MailboxBuilderAdapter(mailboxBuilder);

        mailboxBuilderAdapter.unreadThreads(unreadThreads);
        verify(mailboxBuilder).unreadThreads(unreadThreads);
    }
}
