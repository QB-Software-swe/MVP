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

import it.qbsoftware.adapters.in.jmaplib.entity.MailboxAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.ThreadAdapter;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;
import it.qbsoftware.business.ports.out.jmap.ThreadRepository;
import it.qbsoftware.persistance.MongoConnection;
import rs.ltt.jmap.common.entity.Mailbox;
import rs.ltt.jmap.common.entity.Thread;

public class ThreadRepositoryAdapter implements ThreadRepository {
    private final String COLLECTION = "thread";
    private final MongoConnection connection;
    private final Gson gson;

    @Inject
    public ThreadRepositoryAdapter(final MongoConnection connection, final Gson gson) {
        this.connection = connection;
        this.gson = gson;
    }

    @Override
    public RetrivedEntity<ThreadPort> retriveAll(final String accountId) {
        final List<ThreadPort> found = new ArrayList<>();

        final Bson filter = Filters.regex("_id", "^" + accountId + "\\/.*$");
        final FindIterable<Document> findIterable = connection.getDatabase().getCollection(COLLECTION).find(filter);

        for (final Document document : findIterable) {
            found.add(new ThreadAdapter(gson.fromJson(document.toJson(), Thread.class)));
        }

        return new RetrivedEntity<>(found.toArray(ThreadPort[]::new), new String[] {});
    }

    @Override
    public RetrivedEntity<ThreadPort> retrive(final String[] ids) {
        final List<String> notFound = new ArrayList<>();

        final Bson filter = Filters.in("_id", Arrays.asList(ids));
        final FindIterable<Document> findIterable = connection.getDatabase().getCollection(COLLECTION).find(filter);

        final HashMap<String, ThreadPort> findThreadPort = new HashMap<>();
        for (final Document document : findIterable) {
            final Thread retrivedThread = gson.fromJson(document.toJson(), Thread.class);
            findThreadPort.put(retrivedThread.getId(), new ThreadAdapter(retrivedThread));
        }

        for (final String id : ids) {
            if (!findThreadPort.containsKey(id)) {
                notFound.add(id);
            }
        }

        return new RetrivedEntity<>(findThreadPort.values().toArray(ThreadPort[]::new),
                notFound.toArray(String[]::new));
    }

}
