package it.qbsoftware.application.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import it.qbsoftware.adapters.in.jmaplib.entity.AccountBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.EmailBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.EmailSubmissionBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.IdentityBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.MailboxBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.SessionResourceBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.SetErrorEnumAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.ThreadBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.changes.ChangesEmailMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.changes.ChangesEmailSubmissionMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.changes.ChangesIdentityMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.changes.ChangesMailboxMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.changes.ChangesThreadMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetEmailMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetEmailSubmissionMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetIdentityMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetMailboxMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetThreadMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.query.QueryEmailMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.set.SetEmailMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.set.SetIdentityMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.set.SetMailboxMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.util.ResultReferenceResolverAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.AccountBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourceBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailSubmissionMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesThreadMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailSubmissionMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetThreadMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.query.QueryEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.util.ResultReferenceResolverPort;
import rs.ltt.jmap.gson.JmapAdapters;

public class JmapLibAdapterModule extends AbstractModule {
    @Override
    protected void configure() {
        // Entity
        bind(AccountBuilderPort.class).to(AccountBuilderAdapter.class);
        bind(EmailBuilderPort.class).to(EmailBuilderAdapter.class);
        bind(EmailSubmissionBuilderPort.class).to(EmailSubmissionBuilderAdapter.class);
        bind(IdentityBuilderPort.class).to(IdentityBuilderAdapter.class);
        bind(MailboxBuilderPort.class).to(MailboxBuilderAdapter.class);
        bind(SessionResourceBuilderPort.class).to(SessionResourceBuilderAdapter.class);
        bind(SetErrorEnumPort.class).to(SetErrorEnumAdapter.class);
        bind(ThreadBuilderPort.class).to(ThreadBuilderAdapter.class);
        bind(GetEmailSubmissionMethodResponseBuilderPort.class)
                .to(GetEmailSubmissionMethodResponseBuilderAdapter.class);

        // MethodResponse
        bind(GetEmailMethodResponseBuilderPort.class).to(GetEmailMethodResponseBuilderAdapter.class);
        bind(GetIdentityMethodResponseBuilderPort.class).to(GetIdentityMethodResponseBuilderAdapter.class);
        bind(GetMailboxMethodResponseBuilderPort.class).to(GetMailboxMethodResponseBuilderAdapter.class);
        bind(GetThreadMethodResponseBuilderPort.class).to(GetThreadMethodResponseBuilderAdapter.class);

        bind(SetEmailMethodResponseBuilderPort.class).to(SetEmailMethodResponseBuilderAdapter.class);
        bind(SetMailboxMethodResponseBuilderPort.class).to(SetMailboxMethodResponseBuilderAdapter.class);
        bind(SetIdentityMethodResponseBuilderPort.class).to(SetIdentityMethodResponseBuilderAdapter.class);

        bind(ChangesEmailMethodResponseBuilderPort.class).to(ChangesEmailMethodResponseBuilderAdapter.class);
        bind(ChangesEmailSubmissionMethodResponseBuilderPort.class)
                .to(ChangesEmailSubmissionMethodResponseBuilderAdapter.class);
        bind(ChangesIdentityMethodResponseBuilderPort.class).to(ChangesIdentityMethodResponseBuilderAdapter.class);
        bind(ChangesMailboxMethodResponseBuilderPort.class).to(ChangesMailboxMethodResponseBuilderAdapter.class);
        bind(ChangesThreadMethodResponseBuilderPort.class).to(ChangesThreadMethodResponseBuilderAdapter.class);
        bind(QueryEmailMethodResponseBuilderPort.class).to(QueryEmailMethodResponseBuilderAdapter.class);

        // Util
        bind(ResultReferenceResolverPort.class).to(ResultReferenceResolverAdapter.class);

        // GSON
        bind(Gson.class).toInstance(jmapGsonConfig());
    }

    @Singleton
    private Gson jmapGsonConfig() {
        Gson gson = new Gson();
        GsonBuilder gsonBuilder = new GsonBuilder();
        JmapAdapters.register(gsonBuilder);
        gson = gsonBuilder.create();

        return gson;
    }
}
