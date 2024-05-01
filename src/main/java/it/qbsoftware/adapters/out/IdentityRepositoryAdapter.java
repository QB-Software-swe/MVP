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

import it.qbsoftware.adapters.in.jmaplib.entity.IdentityAdapter;
import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetSingletonException;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.out.jmap.IdentityRepository;
import it.qbsoftware.persistance.MongoConnection;
import rs.ltt.jmap.common.entity.Identity;

public class IdentityRepositoryAdapter implements IdentityRepository {
    private static final String COLLECTION = "identity";
    private final MongoConnection connection;
    private final Gson gson;

    @Inject
    public IdentityRepositoryAdapter(final MongoConnection connection, final Gson gson) {
        this.connection = connection;
        this.gson = gson;
    }

    @Override
    public RetrivedEntity<IdentityPort> retriveAll(final String accountId) {
        final List<IdentityPort> found = new ArrayList<>();

        final Bson filter = Filters.regex("_id", "^" + accountId + "\\/.*$");
        final FindIterable<Document> findIterable = connection.getDatabase().getCollection(COLLECTION).find(filter);

        for (final Document document : findIterable) {
            found.add(new IdentityAdapter(gson.fromJson(document.toJson(), Identity.class)));
        }

        return new RetrivedEntity<>(found.toArray(IdentityPort[]::new), new String[] {});
    }

    @Override
    public RetrivedEntity<IdentityPort> retrive(final String[] ids) {
        final List<String> notFound = new ArrayList<>();

        final Bson filter = Filters.in("_id", Arrays.asList(ids));
        final FindIterable<Document> findIterable = connection.getDatabase().getCollection(COLLECTION).find(filter);

        final HashMap<String, IdentityPort> findIdentityPort = new HashMap<>();
        for (final Document document : findIterable) {
            final Identity retrivedIdentity = gson.fromJson(document.toJson(), Identity.class);
            findIdentityPort.put(retrivedIdentity.getId(), new IdentityAdapter(retrivedIdentity));
        }

        for (final String id : ids) {
            if (!findIdentityPort.containsKey(id)) {
                notFound.add(id);
            }
        }

        return new RetrivedEntity<>(findIdentityPort.values().toArray(IdentityPort[]::new),
                notFound.toArray(String[]::new));
    }

    @Override
    public void destroy(final String id) throws SetNotFoundException {
        final Bson filter = Filters.eq("_id", id);
        try {
            connection.getDatabase().getCollection(COLLECTION).find(filter);
        } catch (final MongoException mongoException) {
            throw new SetNotFoundException();
        }
    }

    @Override
    public void save(final IdentityPort identityPort) throws SetSingletonException {
        final Document emailDoc = Document.parse(gson.toJson(((IdentityAdapter) identityPort).adaptee()));
        emailDoc.put("_id", identityPort.getId());
        try {
            connection.getDatabase().getCollection(COLLECTION).insertOne(emailDoc);
        } catch (final MongoException mongoException) {
            throw new SetSingletonException();
        }
    }

}
