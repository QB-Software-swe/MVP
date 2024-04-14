package it.qbsoftware.business.domain.util.get;

import java.util.ArrayList;
import java.util.List;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;

//FIXME: finirlo
public class GetEmailPropertiesFilter implements GetEntityPropertiesFilter<EmailPort> {
    final EmailBuilderPort emailBuilderPort;
    final String[] bodyProperties;
    final Boolean fetchTextBodyValues;
    final Boolean fetchHTMLBodyValues;
    final Boolean fetchAllBodyValues;
    final Long maxBodyValueByte;

    public GetEmailPropertiesFilter(final EmailBuilderPort emailBuilderPort, final String[] bodyProperties,
            final Boolean fetchTextBodyValues, final Boolean fetchHTMLBodyValues, final Boolean fetchAllBodyValues,
            final Long maxBodyValueByte) {
        this.emailBuilderPort = emailBuilderPort;
        this.bodyProperties = bodyProperties;
        this.fetchTextBodyValues = fetchTextBodyValues == true ? true : false;
        this.fetchHTMLBodyValues = fetchHTMLBodyValues == true ? true : false;
        this.fetchAllBodyValues = fetchAllBodyValues == true ? true : false;
        this.maxBodyValueByte = maxBodyValueByte >= 0 ? maxBodyValueByte : 0;
    }

    @Override
    public EmailPort[] filter(EmailPort[] entities, String[] properties) throws InvalidArgumentsException {
        if (properties == null) {
            return entities;
        }

        List<EmailPort> filtredEmail = new ArrayList<EmailPort>();
        for (EmailPort emailPort : entities) {
            filtredEmail.add(emailFilter(emailPort, properties));
        }

        return filtredEmail.toArray(EmailPort[]::new);
    }

    private EmailPort emailFilter(final EmailPort emailPort, final String[] properties)
            throws InvalidArgumentsException {
        EmailBuilderPort emailBuilder = emailBuilderPort.reset().id(emailPort.getId());

        for (final String property : properties) {
            emailBuilder = switch (property) { // FIXME: è JMAP case-sensitive?
                case "blobId":
                    yield emailBuilder.blobId(emailPort.getBlobId());

                case "threadId":
                    yield emailBuilder.threadId(emailPort.getThreadId());

                case "mailboxIds":
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
