package it.qbsoftware.business.domain.methodcall.filter.standard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.methodcall.filter.EmailFilterBodyPartSettings;
import it.qbsoftware.business.domain.methodcall.filter.EmailPropertiesFilter;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;

public class StandardEmailPropertiesFilter implements EmailPropertiesFilter {
    @Override
    public EmailPort[] filter(final EmailPort[] emails, final String[] properties,
            EmailFilterBodyPartSettings emailFilterBodyPartSettings) throws InvalidArgumentsException {
        if (properties == null) {
            return emails;
        }

        final List<EmailPort> filtredEmail = new ArrayList<EmailPort>();
        for (final EmailPort emailPort : emails) {
            filtredEmail.add(emailFilter(emailPort, properties));
        }

        return filtredEmail.toArray(EmailPort[]::new);
    }

    private EmailPort emailFilter(final EmailPort emailPort, final String[] properties)
            throws InvalidArgumentsException {
        EmailBuilderPort emailBuilder = emailPort.toBuilder().reset().id(emailPort.getId());

        for (final String property : properties) {
            emailBuilder = switch (Arrays.asList(property.split(":")).get(0)) {
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

                case "messageId":
                    yield emailPort.getMessageId() != null ? emailBuilder.messageId(emailPort.getMessageId())
                            : emailBuilder;

                case "inReplyTo":
                    yield emailPort.getInReplyTo() != null ? emailBuilder.inReplyTo(emailPort.getInReplyTo())
                            : emailBuilder;

                case "references":
                    yield emailPort.getReferences() != null ? emailBuilder.references(emailPort.getReferences())
                            : emailBuilder;

                case "sender":
                    yield emailPort.getSender() != null ? emailBuilder.sender(emailPort.getSender()) : emailBuilder;

                case "from":
                    yield emailPort.getFrom() != null ? emailBuilder.from(emailPort.getFrom()) : emailBuilder;

                case "to":
                    yield emailPort.getTo() != null ? emailBuilder.to(emailPort.getTo()) : emailBuilder;

                case "cc":
                    yield emailPort.getCc() != null ? emailBuilder.cc(emailPort.getCc()) : emailBuilder;

                case "bcc":
                    yield emailPort.getBcc() != null ? emailBuilder.bcc(emailPort.getBcc()) : emailBuilder;

                case "replyTo":
                    yield emailPort.getReplyTo() != null ? emailBuilder.replyTo(emailPort.getReplyTo()) : emailBuilder;

                case "subject":
                    yield emailBuilder.subject(emailPort.getSubject());

                case "bodyValues":
                    yield emailPort.getBodyValues() != null ? emailBuilder.bodyValues(emailPort.getBodyValues())
                            : emailBuilder;

                case "textBody":
                    yield emailPort.getTextBody() != null ? emailBuilder.textBody(emailPort.getTextBody())
                            : emailBuilder;

                case "htmlBody":
                    yield emailPort.getHtmlBody() != null ? emailBuilder.htmlBody(emailPort.getHtmlBody())
                            : emailBuilder;

                case "attachments":
                    yield emailPort.getAttachments() != null ? emailBuilder.attachments(emailPort.getAttachments())
                            : emailBuilder;

                case "sentAt":
                    yield emailPort.getSentAt() != null ? emailBuilder.sentAt(emailPort.getSentAt()) : emailBuilder;

                case "bodyStructure":
                    yield emailPort.getBodyStructure() != null
                            ? emailBuilder.bodyStructure(emailPort.getBodyStructure())
                            : emailBuilder;

                case "header":
                    yield emailPort.getHeaders() != null ? emailBuilder.headers(emailPort.getHeaders()) : emailBuilder;

                case "id":
                    yield emailBuilder;

                default:
                    throw new InvalidArgumentsException();
            };
        }

        return emailBuilder.build();
    }

}
