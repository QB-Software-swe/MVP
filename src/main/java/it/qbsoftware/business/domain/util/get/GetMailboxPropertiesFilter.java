package it.qbsoftware.business.domain.util.get;

import java.util.ArrayList;
import java.util.List;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;

public class GetMailboxPropertiesFilter implements GetEntityPropertiesFilter<MailboxPort> {
    final MailboxBuilderPort mailboxBuilderPort;

    public GetMailboxPropertiesFilter(final MailboxBuilderPort mailboxBuilderPort) {
        this.mailboxBuilderPort = mailboxBuilderPort;
    }

    @Override
    public MailboxPort[] filter(MailboxPort[] entities, String[] properties) throws InvalidArgumentsException {
        if (properties == null) {
            return entities;
        }

        List<MailboxPort> filtredMailboxes = new ArrayList<MailboxPort>();
        for (final MailboxPort mailboxPort : entities) {
            filtredMailboxes.add(mailboxFilter(mailboxPort, properties));
        }

        return filtredMailboxes.toArray(MailboxPort[]::new);
    }

    private MailboxPort mailboxFilter(final MailboxPort mailboxPort, final String[] properties)
            throws InvalidArgumentsException {
        MailboxBuilderPort mailboxBuilder = mailboxBuilderPort.reset().id(mailboxPort.getId());

        for (final String property : properties) {
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

                // TODO: finire la lista, seguire lo standard

                default:
                    throw new InvalidArgumentsException();
            };
        }

        return mailboxBuilder.build();
    }

}
