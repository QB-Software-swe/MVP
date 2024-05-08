package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.mockito.Mockito.mock;

import org.junit.Test;
import rs.ltt.jmap.common.entity.MailboxRights;

public class MailboxRightsAdapterTest {
    @Test
    public void testAdaptee() {
        MailboxRights mailboxRights = mock(MailboxRights.class);
        MailboxRightsAdapter mailboxRightsAdapter = new MailboxRightsAdapter(mailboxRights);
        assert (mailboxRightsAdapter.adaptee().equals(mailboxRights));
    }
}
