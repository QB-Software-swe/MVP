package it.qbsoftware.business.domain.methodcall.filter.standard;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.methodcall.filter.EmailFilterBodyPartSettings;
import it.qbsoftware.business.domain.methodcall.filter.EmailPropertiesFilter;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;

public class StandardEmailPropertiesFilter implements EmailPropertiesFilter {
    final EmailBuilderPort emailBuilderPort;

    public StandardEmailPropertiesFilter(final EmailBuilderPort emailBuilderPort) {
        this.emailBuilderPort = emailBuilderPort.reset();
    }

    @Override
    public EmailPort[] filter(final EmailPort[] emails, final String[] properties,
            EmailFilterBodyPartSettings emailFilterBodyPartSettings) throws InvalidArgumentsException {
        //if (properties == null) {
            return emails;
        //}

        /* 
        final List<EmailPort> filtredEmail = new ArrayList<EmailPort>();
        for (final EmailPort emailPort : emails) {
            filtredEmail.add(emailFilter(emailPort, properties));
        }

        return filtredEmail.toArray(EmailPort[]::new);
        */
    }

    private EmailPort emailFilter(final EmailPort emailPort, final String[] properties)
            throws InvalidArgumentsException {
        EmailBuilderPort emailBuilder = emailBuilderPort.reset().id(emailPort.getId());

        for (final String property : properties) { // FIXME: completarlo
            emailBuilder = switch (property) {
                case "blobId":
                    yield emailBuilder.blobId(emailPort.getBlobId());

                case "threadId":
                    yield emailBuilder.threadId(emailPort.getThreadId());

                case "mailboxIds":
                    yield emailBuilder.mailboxIds(emailPort.getMailboxIds());

                case "keywords":
                    yield emailBuilder.keywords(emailPort.getKeywords());

                case "size":
                    yield emailBuilder.size(emailPort.getSize());

                case "receivedAt":
                    yield emailBuilder.receivedAt(emailPort.getReceivedAt());

                default:
                    throw new InvalidArgumentsException();
            };
        }

        return emailBuilder.build();
    }

}
