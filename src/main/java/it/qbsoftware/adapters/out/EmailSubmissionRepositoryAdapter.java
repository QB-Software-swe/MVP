package it.qbsoftware.adapters.out;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.mongodb.MongoException;
import it.qbsoftware.adapters.in.jmaplib.entity.EmailSubmissionAdapter;
import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import it.qbsoftware.business.ports.out.jmap.EmailSubmissionRepository;
import it.qbsoftware.persistance.MongoConnection;
import org.bson.Document;

public class EmailSubmissionRepositoryAdapter implements EmailSubmissionRepository {
    private static final String COLLECTION = "submission";
    private final MongoConnection connection;
    private final Gson gson;

    @Inject
    public EmailSubmissionRepositoryAdapter(final MongoConnection connection, final Gson gson) {
        this.connection = connection;
        this.gson = gson;
    }

    @Override
    public void save(final EmailSubmissionPort emailSubmissionPort) throws SetNotFoundException {

        final Document emailDoc =
                Document.parse(
                        gson.toJson(((EmailSubmissionAdapter) emailSubmissionPort).adaptee()));
        emailDoc.put("_id", emailSubmissionPort.getId());

        try {
            connection.getDatabase().getCollection(COLLECTION).insertOne(emailDoc);
        } catch (final MongoException mongoException) {
            throw new SetNotFoundException();
        }
    }
}
