package it.qbsoftware.persistence;

import java.util.Optional;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import it.qbsoftware.core.util.MailboxInfo;
import rs.ltt.jmap.gson.JmapAdapters;

public class MailboxInfoImp implements MailboxInfoDao {
    static final String COLLECTION = "MailboxInfo";
    static final Gson GSON;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        JmapAdapters.register(gsonBuilder);
        GSON = gsonBuilder.create();
    }

    @Override
    public ArrayList<MailboxInfo> getMailboxsInfo() {
        MongoCollection<Document> identityCollection = MongoConnectionSingleton.INSTANCE.getConnection().mongoDatabase
                .getCollection(COLLECTION);
        FindIterable<Document> documentResults = identityCollection.find();

        ArrayList<MailboxInfo> mailboxsInfo = new ArrayList<MailboxInfo>();

        for (Document documentResult : documentResults) {
            mailboxsInfo.add(GSON.fromJson(documentResult.toJson().replace("_id", "id"), MailboxInfo.class));
        }

        return mailboxsInfo;
    }

    @Override
    public Optional<MailboxInfo> getMailboxInfo(String id) {
        Bson filterById = Filters.eq("_id", id);

        MongoCollection<Document> identityCollection = MongoConnectionSingleton.INSTANCE.getConnection().mongoDatabase
                .getCollection(COLLECTION);
        FindIterable<Document> documentResults = identityCollection.find(filterById);

        Document doc = documentResults.first();

        if (doc == null) {
            return Optional.empty();
        } else {
            return Optional.of(GSON.fromJson(doc.toJson().replace("_id", "id"), MailboxInfo.class));
        }
    }

    @Override
    public void saveMailboxInfo(MailboxInfo mailboxInfo) {
        Document doc = Document.parse(GSON.toJson(mailboxInfo, MailboxInfo.class).replace("id", "_id"));
        MongoCollection<Document> identityCollection = MongoConnectionSingleton.INSTANCE.getConnection().mongoDatabase
                .getCollection(COLLECTION);

        identityCollection.insertOne(doc);
    }
}
