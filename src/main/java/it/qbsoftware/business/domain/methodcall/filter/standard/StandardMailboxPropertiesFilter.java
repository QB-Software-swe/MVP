package it.qbsoftware.business.domain.methodcall.filter.standard;

import java.util.ArrayList;
import java.util.List;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.methodcall.filter.MailboxPropertiesFilter;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;

public class StandardMailboxPropertiesFilter implements MailboxPropertiesFilter {
    final MailboxBuilderPort mailboxBuilderPort;

    public StandardMailboxPropertiesFilter(final MailboxBuilderPort mailboxBuilderPort) {
        this.mailboxBuilderPort = mailboxBuilderPort.reset();
    }

    @Override
    public MailboxPort[] filter(MailboxPort[] mailboxPorts, String[] properties) throws InvalidArgumentsException {
        if (properties == null) {
            return mailboxPorts;
        }

        final List<MailboxPort> filtredMailboxes = new ArrayList<MailboxPort>();
        for (final MailboxPort mailboxPort : mailboxPorts) {
            filtredMailboxes.add(mailboxFilter(mailboxPort, properties));
        }

        return filtredMailboxes.toArray(MailboxPort[]::new);
    }

    private MailboxPort mailboxFilter(final MailboxPort mailboxPort, final String[] properties)
            throws InvalidArgumentsException {
        MailboxBuilderPort mailboxBuilder = mailboxBuilderPort.reset().id(mailboxPort.getId());

        for (final String property : properties) { // FIXME: check della aderenza allo standard, nel caso terminare con
                                                   // quello che manca
            mailboxBuilder = switch (property) {
                case "name":
                    yield mailboxBuilder.name(mailboxPort.getName());

                case "parentId":
                    yield mailboxBuilder.parentId(mailboxPort.getParentId());

                case "role":
                    yield mailboxBuilder.role(mailboxPort.getRole());

                case "sortOrder":
                    yield mailboxBuilder.sortOrder(mailboxPort.getSortOrder());

                case "totalEmails":
                    yield mailboxBuilder.totalEmails(mailboxPort.getTotalEmails());

                case "unreadEmails":
                    yield mailboxBuilder.unreadEmails(mailboxPort.getUnreadEmails());

                case "totalThreads":
                    yield mailboxBuilder.totalThreads(mailboxPort.getTotalThreads());

                case "unreadThreads":
                    yield mailboxBuilder.unreadThreads(mailboxPort.getUnreadThreads());

                case "myRights":
                    yield mailboxBuilder.myRights(mailboxPort.getMyRights());

                case "isSubscribed":
                    yield mailboxBuilder.isSubscribed(mailboxPort.getIsSubscribed());

                default:
                    throw new InvalidArgumentsException();
            };
        }

        return mailboxBuilder.build();
    }

}
