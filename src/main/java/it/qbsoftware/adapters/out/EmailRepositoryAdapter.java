package it.qbsoftware.adapters.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;

import it.qbsoftware.adapters.in.jmaplib.entity.EmailAdapter;
import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetSingletonException;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;
import it.qbsoftware.persistance.MongoConnection;
import rs.ltt.jmap.common.entity.Email;

public class EmailRepositoryAdapter implements EmailRepository {
    private final static String COLLECTION = "email";

    private final MongoConnection connection;
    private final Gson gson;

    @Inject
    public EmailRepositoryAdapter(final MongoConnection connection, final Gson gson) {
        this.connection = connection;
        this.gson = gson;
    }

    @Override
    public RetrivedEntity<EmailPort> retriveAll(final String accountId) {
        final List<EmailPort> found = new ArrayList<>();

        final Bson filter = Filters.regex("_id", "^" + accountId + "\\/.*$");
        final FindIterable<Document> findIterable = connection.getDatabase().getCollection(COLLECTION).find(filter);

        for (final Document document : findIterable) {
            found.add(new EmailAdapter(gson.fromJson(document.toJson(), Email.class)));
        }

        return new RetrivedEntity<>(found.toArray(EmailPort[]::new), new String[] {});
    }

    @Override
    public RetrivedEntity<EmailPort> retrive(final String[] ids) {
        final List<String> notFound = new ArrayList<>();

        final Bson filter = Filters.in("_id", Arrays.asList(ids));
        final FindIterable<Document> findIterable = connection.getDatabase().getCollection(COLLECTION).find(filter);

        final HashMap<String, EmailPort> findEmailPort = new HashMap<>();
        for (final Document document : findIterable) {
            final Email retrivedEmail = gson.fromJson(document.toJson(), Email.class);
            findEmailPort.put(retrivedEmail.getId(), new EmailAdapter(retrivedEmail));
        }

        for (final String id : ids) {
            if (!findEmailPort.containsKey(id)) {
                notFound.add(id);
            }
        }

        return new RetrivedEntity<>(findEmailPort.values().toArray(EmailPort[]::new), notFound.toArray(String[]::new));
    }

    @Override
    public EmailPort retriveOne(final String emailId) throws SetNotFoundException {
        final Bson filter = Filters.eq("_id", emailId);
        final FindIterable<Document> findIterable = connection.getDatabase().getCollection(COLLECTION).find(filter);

        final Document emailDocRetrived = findIterable.first();
        if (emailDocRetrived == null) {
            throw new SetNotFoundException();
        }

        return new EmailAdapter(gson.fromJson(emailDocRetrived.toJson(), Email.class));
    }

    @Override
    public EmailPort destroy(final String emailId) throws SetNotFoundException {
        final Bson filter = Filters.eq("_id", emailId);
        final Document emailDeleteResult = connection.getDatabase().getCollection(COLLECTION)
                .findOneAndDelete(filter);

        if (emailDeleteResult == null) {
            throw new SetNotFoundException();
        }

        return new EmailAdapter(gson.fromJson(emailDeleteResult.toJson(), Email.class));
    }

    @Override
    public void save(final EmailPort emailPort) throws SetSingletonException {
        // FIXME: singleton
        final Document emailDoc = Document.parse(gson.toJson(((EmailAdapter) emailPort).adaptee()));
        emailDoc.put("_id", emailPort.getId());
        var i = connection.getDatabase().getCollection(COLLECTION).insertOne(emailDoc);
    }

    @Override
    public void overwrite(final EmailPort emailPort) throws SetNotFoundException {
        final Bson filter = Filters.eq("_id", emailPort.getId());
        final Document emailDoc = Document.parse(gson.toJson(emailPort));
        emailDoc.put("_id", emailPort.getId());

        final var i = connection.getDatabase().getCollection(COLLECTION).findOneAndReplace(filter,
                emailDoc);
    }
}
