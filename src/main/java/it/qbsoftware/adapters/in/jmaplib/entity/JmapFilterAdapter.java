package it.qbsoftware.adapters.in.jmaplib.entity;

import java.util.stream.Stream;

import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.JmapFilterPort;
import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.entity.filter.EmailFilterCondition;
import rs.ltt.jmap.common.entity.filter.Filter;

public class JmapFilterAdapter<EntityType> implements JmapFilterPort<EntityType> {
    private final Filter<Email> filter;

    public JmapFilterAdapter(final Filter<Email> filter) {
        this.filter = filter;
    }

    // FIXME: rimuovere se non utilizzato
    public Filter<Email> adapee() {
        return filter;
    }

    @Override
    public Stream<EmailPort> apply(final Stream<EmailPort> emails) {
        {
            var emailStream = emails.map(e -> ((EmailAdapter) e).adaptee());
            if (filter instanceof EmailFilterCondition) {
                final EmailFilterCondition emailFilterCondition = (EmailFilterCondition) filter;
                final String inMailbox = emailFilterCondition.getInMailbox();
                if (inMailbox != null) {
                    emailStream = emailStream.filter(email -> email.getMailboxIds().containsKey(inMailbox));
                }
                final String[] header = emailFilterCondition.getHeader();
                if (header != null
                        && header.length == 2
                        && header[0].equals("Autocrypt-Setup-Message")) {
                    emailStream = emailStream.filter(
                            email -> header[1].equals(email.getAutocryptSetupMessage()));
                }
            }
            return emailStream.map(e -> new EmailAdapter(e));
        }
    }
}
