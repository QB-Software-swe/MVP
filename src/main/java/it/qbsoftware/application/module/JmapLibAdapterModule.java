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
import it.qbsoftware.adapters.in.jmaplib.error.AccountNotFoundMethodErrorResponseAdapter;
import it.qbsoftware.adapters.in.jmaplib.error.InvalidArgumentsMethodErrorResponseAdapter;
import it.qbsoftware.adapters.in.jmaplib.error.InvalidResultReferenceMethodErrorResponseAdapter;
import it.qbsoftware.adapters.in.jmaplib.error.StateMismatchMethodErrorResponseAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetEmailMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetIdentityMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetMailboxMethodResponseAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetMailboxMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.set.SetEmailMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.util.ResultReferenceResolverAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.AccountBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourceBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadBuilderPort;
import it.qbsoftware.business.ports.in.jmap.error.AccountNotFoundMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidArgumentsMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.StateMismatchMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetIdentityMethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.util.ResultReferenceResolverPort;
import rs.ltt.jmap.common.method.response.identity.GetIdentityMethodResponse.GetIdentityMethodResponseBuilder;
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

        // Error
        bind(AccountNotFoundMethodErrorResponsePort.class).to(AccountNotFoundMethodErrorResponseAdapter.class);
        bind(InvalidArgumentsMethodErrorResponsePort.class).to(InvalidArgumentsMethodErrorResponseAdapter.class);
        bind(InvalidResultReferenceMethodErrorResponsePort.class)
                .to(InvalidResultReferenceMethodErrorResponseAdapter.class);
        bind(StateMismatchMethodErrorResponsePort.class).to(StateMismatchMethodErrorResponseAdapter.class);

        // MethodResponse
        bind(GetEmailMethodResponseBuilderPort.class).to(GetEmailMethodResponseBuilderAdapter.class);
        bind(GetIdentityMethodResponseBuilderPort.class).to(GetIdentityMethodResponseBuilderAdapter.class);
        bind(GetMailboxMethodResponseBuilderPort.class).to(GetMailboxMethodResponseBuilderAdapter.class);

        bind(SetEmailMethodResponseBuilderPort.class).to(SetEmailMethodResponseBuilderAdapter.class);

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
