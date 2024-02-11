package it.qbsoftware.persistence;

import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.entity.Keyword;
import rs.ltt.jmap.gson.JmapAdapters;

public class EmailImp implements EmailDao {
    static final String COLLECTION = "Email";
    static final Gson GSON;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        JmapAdapters.register(gsonBuilder);
        GSON = gsonBuilder.create();
    }

    @Override
    public ArrayList<Email> getEmailsInMailboxs(String mailboxsId) {
        MongoCollection<Document> identityCollection = MongoConnectionSingleton.INSTANCE.getConnection().mongoDatabase
                .getCollection(COLLECTION);
        FindIterable<Document> documentResults = identityCollection.find();

        ArrayList<Email> emails = new ArrayList<Email>();

        for (Document document : documentResults) {
            emails.add(GSON.fromJson(document.toJson(), Email.class));
        }

        return emails.stream().filter(e -> e.getMailboxIds().containsKey(mailboxsId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void saveEmail(Email email) {
        MongoConnectionSingleton.INSTANCE.getConnection().mongoDatabase.getCollection(COLLECTION)
                .insertOne(Document.parse(GSON.toJson(email, Email.class).replace("id", "_id")));
    }

}
