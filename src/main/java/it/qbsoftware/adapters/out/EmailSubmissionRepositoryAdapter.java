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

import it.qbsoftware.adapters.in.jmaplib.entity.EmailSubmissionAdapter;
import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import it.qbsoftware.business.ports.out.jmap.EmailSubmissionRepository;
import it.qbsoftware.persistance.MongoConnection;
import rs.ltt.jmap.common.entity.EmailSubmission;

public class EmailSubmissionRepositoryAdapter implements EmailSubmissionRepository {
    private final static String COLLECTION = "submission";
    private final MongoConnection connection;
    private final Gson gson;

    @Inject
    public EmailSubmissionRepositoryAdapter(final MongoConnection connection, final Gson gson) {
        this.connection = connection;
        this.gson = gson;
    }

    @Override
    public RetrivedEntity<EmailSubmissionPort> retriveAll(final String accountId) {
        final List<EmailSubmissionPort> found = new ArrayList<>();

        final Bson filter = Filters.regex("_id", "^" + accountId + "\\/.*$");
        final FindIterable<Document> findIterable = connection.getDatabase().getCollection(COLLECTION).find(filter);

        for (final Document document : findIterable) {
            found.add(new EmailSubmissionAdapter(gson.fromJson(document.toJson(), EmailSubmission.class)));
        }

        return new RetrivedEntity<>(found.toArray(EmailSubmissionPort[]::new), new String[] {});
    }

    @Override
    public RetrivedEntity<EmailSubmissionPort> retrive(final String[] ids) {
        final List<String> notFound = new ArrayList<>();

        final Bson filter = Filters.in("_id", Arrays.asList(ids));
        final FindIterable<Document> findIterable = connection.getDatabase().getCollection(COLLECTION).find(filter);

        final HashMap<String, EmailSubmissionPort> findEmailSubmissionPort = new HashMap<>();
        for (final Document document : findIterable) {
            final EmailSubmission retrivedEmailSubmission = gson.fromJson(document.toJson(), EmailSubmission.class);
            findEmailSubmissionPort.put(retrivedEmailSubmission.getId(),
                    new EmailSubmissionAdapter(retrivedEmailSubmission));
        }

        for (final String id : ids) {
            if (!findEmailSubmissionPort.containsKey(id)) {
                notFound.add(id);
            }
        }

        return new RetrivedEntity<>(findEmailSubmissionPort.values().toArray(EmailSubmissionPort[]::new),
                notFound.toArray(String[]::new));
    }

    @Override
    public EmailSubmissionPort destroy(final String emailId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'destroy'");
    }

    @Override
    public void save(final EmailSubmissionPort emailSubmissionPort) throws SetNotFoundException {

        final Document emailDoc = Document.parse(gson.toJson(((EmailSubmissionAdapter) emailSubmissionPort).adaptee()));
        emailDoc.put("_id", emailSubmissionPort.getId());

        try {
            connection.getDatabase().getCollection(COLLECTION).insertOne(emailDoc);
        } catch (final MongoException mongoException) {
            throw new SetNotFoundException();
        }
    }

}
