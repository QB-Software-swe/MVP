package it.qbsoftware.adapters.out;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.persistance.MongoConnection;
import org.bson.Document;

public class AccountStateRepositoryAdapter implements AccountStateRepository {
    private static final String COLLECTION = "account_state";

    private final MongoConnection connection;
    private final Gson gson;

    @Inject
    public AccountStateRepositoryAdapter(final MongoConnection connection, final Gson gson) {
        this.connection = connection;
        this.gson = gson;
    }

    @Override
    public AccountState retrive(final String accountId) throws AccountNotFoundException {
        FindIterable<Document> accountStateDocuments =
                connection
                        .getDatabase()
                        .getCollection(COLLECTION)
                        .find(Filters.eq("_id", accountId));

        Document accountStateDoc = accountStateDocuments.first();

        if (accountStateDoc != null) {
            return gson.fromJson(accountStateDoc.toJson().replace("_id", "id"), AccountState.class);
        }

        throw new AccountNotFoundException();
    }

    @Override
    public void save(final AccountState accountState) {
        Document docAccountState =
                Document.parse(gson.toJson(accountState).replace("\"id\"", "\"_id\""));
        connection
                .getDatabase()
                .getCollection(COLLECTION)
                .replaceOne(
                        Filters.eq("_id", accountState.id()),
                        docAccountState,
                        new ReplaceOptions().upsert(true));
    }
}
