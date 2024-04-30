package it.qbsoftware.adapters.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;

import it.qbsoftware.adapters.in.jmaplib.entity.MailboxAdapter;
import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetSingletonException;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.out.jmap.MailboxRepository;
import it.qbsoftware.persistance.MongoConnection;
import rs.ltt.jmap.common.entity.Mailbox;

public class MailboxRepositoryAdapter implements MailboxRepository {
    private final static String COLLECTION = "mailbox";
    private final MongoConnection connection;
    private final Gson gson;

    @Inject
    public MailboxRepositoryAdapter(final MongoConnection connection, final Gson gson) {
        this.connection = connection;
        this.gson = gson;
    }

    @Override
    public RetrivedEntity<MailboxPort> retriveAll(final String accountId) {
        final List<MailboxPort> found = new ArrayList<>();

        final Bson filter = Filters.regex("_id", "^" + accountId + "\\/.*$");
        final FindIterable<Document> findIterable = connection.getDatabase().getCollection(COLLECTION).find(filter);

        for (final Document document : findIterable) {
            found.add(new MailboxAdapter(gson.fromJson(document.toJson(), Mailbox.class)));
        }

        return new RetrivedEntity<>(found.toArray(MailboxPort[]::new), new String[] {});
    }

    @Override
    public RetrivedEntity<MailboxPort> retrive(final String[] ids) {
        final List<String> notFound = new ArrayList<>();

        final Bson filter = Filters.in("_id", Arrays.asList(ids));
        final FindIterable<Document> findIterable = connection.getDatabase().getCollection(COLLECTION).find(filter);

        final HashMap<String, MailboxPort> findMailboxPort = new HashMap<>();
        for (final Document document : findIterable) {
            final Mailbox retrivedMailbox = gson.fromJson(document.toJson(), Mailbox.class);
            findMailboxPort.put(retrivedMailbox.getId(), new MailboxAdapter(retrivedMailbox));
        }

        for (final String id : ids) {
            if (!findMailboxPort.containsKey(id)) {
                notFound.add(id);
            }
        }

        return new RetrivedEntity<>(findMailboxPort.values().toArray(MailboxPort[]::new),
                notFound.toArray(String[]::new));
    }

    @Override
    public void destroy(final String id) throws SetNotFoundException {
        final Bson filter = Filters.eq("_id", id);
        try {
            connection.getDatabase().getCollection(COLLECTION).deleteOne(filter);
        } catch (final MongoException mongoException) {
            throw new SetNotFoundException();
        }
    }

    @Override
    public void save(final MailboxPort mailboxPort) throws SetSingletonException {
        final Document mailboxDoc = Document.parse(gson.toJson(((MailboxAdapter) mailboxPort).adaptee()));
        mailboxDoc.put("_id", mailboxPort.getId());
        try {
            connection.getDatabase().getCollection(COLLECTION).insertOne(mailboxDoc);
        } catch (final MongoException mongoException) {
            throw new SetSingletonException();
        }
    }

}
