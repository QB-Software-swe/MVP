package it.qbsoftware.application.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import it.qbsoftware.business.domain.methodcall.filter.EmailPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.filter.IdentityPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.filter.MailboxPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.filter.ThreadPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.filter.standard.StandardEmailPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.filter.standard.StandardIdentityPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.filter.standard.StandardMailboxPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.filter.standard.StandardThreadPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.process.get.JmapReferenceIdsResolver;
import it.qbsoftware.business.domain.methodcall.process.get.ReferenceIdsResolver;
import it.qbsoftware.business.domain.methodcall.process.set.create.CreateEmail;
import it.qbsoftware.business.domain.methodcall.process.set.create.CreateIdentity;
import it.qbsoftware.business.domain.methodcall.process.set.create.CreateMailbox;
import it.qbsoftware.business.domain.methodcall.process.set.create.StandardCreateEmail;
import it.qbsoftware.business.domain.methodcall.process.set.create.StandardCreateIdentity;
import it.qbsoftware.business.domain.methodcall.process.set.create.StandardCreateMailbox;
import it.qbsoftware.business.domain.methodcall.process.set.destroy.DestroyEmail;
import it.qbsoftware.business.domain.methodcall.process.set.destroy.DestroyMailbox;
import it.qbsoftware.business.domain.methodcall.process.set.destroy.StandardDestroyEmail;
import it.qbsoftware.business.domain.methodcall.process.set.destroy.StandardDestroyMailbox;
import it.qbsoftware.business.domain.methodcall.process.set.update.StandardUpdateEmail;
import it.qbsoftware.business.domain.methodcall.process.set.update.StandardUpdateMailbox;
import it.qbsoftware.business.domain.methodcall.process.set.update.UpdateEmail;
import it.qbsoftware.business.domain.methodcall.process.set.update.UpdateMailbox;
import it.qbsoftware.business.domain.methodcall.statematch.IfInStateMatch;
import it.qbsoftware.business.domain.methodcall.statematch.StandardIfInStateMatch;
import it.qbsoftware.business.domain.util.get.CreationIdResolverPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourceBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.changes.ChangesThreadMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetThreadMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.other.EchoMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.query.QueryEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailSubmissionMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.util.ResultReferenceResolverPort;
import it.qbsoftware.business.ports.in.usecase.EchoMethodCallUsecase;
import it.qbsoftware.business.ports.in.usecase.SessionUsecase;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesEmailMethodCallUsecase;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesIdentityMethodCallUsecase;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesMailboxMethodCallUsecase;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesThreadMethodCallUsecase;
import it.qbsoftware.business.ports.in.usecase.get.GetEmailMethodCallUsecase;
import it.qbsoftware.business.ports.in.usecase.get.GetIdentityMethodCallUsecase;
import it.qbsoftware.business.ports.in.usecase.get.GetMailboxMethodCallUsecase;
import it.qbsoftware.business.ports.in.usecase.get.GetThreadMethodCallUsecase;
import it.qbsoftware.business.ports.in.usecase.query.QueryEmailMethodCallUsecase;
import it.qbsoftware.business.ports.in.usecase.set.SetEmailMethodCallUsecase;
import it.qbsoftware.business.ports.in.usecase.set.SetEmailSubmissionMethodCallUsecase;
import it.qbsoftware.business.ports.in.usecase.set.SetIdentityMethodCallUsecase;
import it.qbsoftware.business.ports.in.usecase.set.SetMailboxMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.EmailChangesTrackerRepository;
import it.qbsoftware.business.ports.out.domain.IdentityChangesTrackerRepository;
import it.qbsoftware.business.ports.out.domain.MailboxChangesTrackerRepository;
import it.qbsoftware.business.ports.out.domain.ThreadChangesTrackerRepository;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;
import it.qbsoftware.business.ports.out.jmap.IdentityRepository;
import it.qbsoftware.business.ports.out.jmap.MailboxRepository;
import it.qbsoftware.business.ports.out.jmap.ThreadRepository;
import it.qbsoftware.business.ports.out.jmap.UserSessionResourceRepository;
import it.qbsoftware.business.services.EchoMethodCallSerivce;
import it.qbsoftware.business.services.SessionService;
import it.qbsoftware.business.services.changes.ChangesEmailMethodCallService;
import it.qbsoftware.business.services.changes.ChangesIdentityMethodCallService;
import it.qbsoftware.business.services.changes.ChangesMailboxMethodCallService;
import it.qbsoftware.business.services.changes.ChangesThreadMethodCallService;
import it.qbsoftware.business.services.get.GetEmailMethodCallService;
import it.qbsoftware.business.services.get.GetIdentityMethodCallService;
import it.qbsoftware.business.services.get.GetMailboxMethodCallService;
import it.qbsoftware.business.services.get.GetThreadMethodCallService;
import it.qbsoftware.business.services.query.QueryEmailMethodCallService;
import it.qbsoftware.business.services.set.SetEmailMethodCallService;
import it.qbsoftware.business.services.set.SetEmailSubmissionMethodCallService;
import it.qbsoftware.business.services.set.SetIdentityMethodCallService;
import it.qbsoftware.business.services.set.SetMailboxMethodCallService;

public class ControllerModule extends AbstractModule {
    @Provides
    SessionUsecase provideSessionService(
            final SessionResourceBuilderPort sessionResourceBuilderPort,
            final UserSessionResourceRepository userSessionResourceRepository) {
        return new SessionService(sessionResourceBuilderPort, userSessionResourceRepository);
    }

    @Provides
    EchoMethodCallUsecase provideEchoMethodCallService(
            final EchoMethodResponseBuilderPort echoMethodResponseBuilderPort) {
        return new EchoMethodCallSerivce(echoMethodResponseBuilderPort);
    }

    // Service>/Get
    @Provides
    GetEmailMethodCallUsecase provideGetEmailMethodCallService(
            final AccountStateRepository accountStateRepository,
            final EmailPropertiesFilter emailPropertiesFilter,
            final EmailRepository emailRepository,
            final GetEmailMethodResponseBuilderPort getEmailMethodResponseBuilderPort,
            final ReferenceIdsResolver getReferenceIdsResolver) {
        return new GetEmailMethodCallService(
                accountStateRepository,
                emailPropertiesFilter,
                emailRepository,
                getEmailMethodResponseBuilderPort,
                getReferenceIdsResolver);
    }

    @Provides
    GetIdentityMethodCallUsecase provideGetIdentityMethodCallService(
            final AccountStateRepository accountStateRepository,
            final GetIdentityMethodResponseBuilderPort getIdentityMethodResponseBuilderPort,
            final ReferenceIdsResolver getReferenceIdsResolver,
            final IdentityPropertiesFilter identityPropertiesFilter,
            final IdentityRepository identityRepository) {
        return new GetIdentityMethodCallService(
                accountStateRepository,
                getIdentityMethodResponseBuilderPort,
                getReferenceIdsResolver,
                identityPropertiesFilter,
                identityRepository);
    }

    @Provides
    GetMailboxMethodCallUsecase provideGetMailboxMethodCallService(
            final AccountStateRepository accountStateRepository,
            final GetMailboxMethodResponseBuilderPort getMailboxMethodResponseBuilderPort,
            final ReferenceIdsResolver getReferenceIdsResolver,
            final MailboxPropertiesFilter mailboxPropertiesFilter,
            final MailboxRepository mailboxRepository) {
        return new GetMailboxMethodCallService(
                accountStateRepository,
                getMailboxMethodResponseBuilderPort,
                getReferenceIdsResolver,
                mailboxPropertiesFilter,
                mailboxRepository);
    }

    @Provides
    GetThreadMethodCallUsecase provideGetThreadMethodCallService(
            final AccountStateRepository accountStateRepository,
            final ReferenceIdsResolver getReferenceIdsResolver,
            final GetThreadMethodResponseBuilderPort getThreadMethodResponseBuilderPort,
            final ThreadPropertiesFilter threadPropertiesFilter,
            final ThreadRepository threadRepository) {
        return new GetThreadMethodCallService(
                accountStateRepository,
                getReferenceIdsResolver,
                getThreadMethodResponseBuilderPort,
                threadPropertiesFilter,
                threadRepository);
    }

    // Service>/Set

    @Provides
    SetEmailMethodCallUsecase provideSetEmailMethodCallService(
            final AccountStateRepository accountStateRepository,
            final IfInStateMatch ifInStateMatch,
            final CreateEmail createEmail,
            final UpdateEmail updateEmail,
            final DestroyEmail destroyEmail,
            final SetEmailMethodResponseBuilderPort setEmailMethodResponseBuilderPort) {
        return new SetEmailMethodCallService(
                accountStateRepository,
                ifInStateMatch,
                createEmail,
                updateEmail,
                destroyEmail,
                setEmailMethodResponseBuilderPort);
    }

    @Provides
    SetMailboxMethodCallUsecase provideSetMailboxMethodCallService(
            final AccountStateRepository accountStateRepository,
            final IfInStateMatch ifInStateMatch,
            final SetMailboxMethodResponseBuilderPort setMailboxMethodResponseBuilderPort,
            final CreateMailbox createMailbox,
            final DestroyMailbox destroyMailbox,
            final UpdateMailbox updateMailbox) {
        return new SetMailboxMethodCallService(
                accountStateRepository,
                ifInStateMatch,
                setMailboxMethodResponseBuilderPort,
                createMailbox,
                destroyMailbox,
                updateMailbox);
    }

    @Provides
    SetIdentityMethodCallUsecase provideSetIdentityMethodCallServide(
            final AccountStateRepository accountStateRepository,
            final CreateIdentity createIndetity,
            final IfInStateMatch ifInStateMatch,
            final SetIdentityMethodResponseBuilderPort setIdentityMethodResponseBuilderPort) {
        return new SetIdentityMethodCallService(
                accountStateRepository,
                createIndetity,
                ifInStateMatch,
                setIdentityMethodResponseBuilderPort);
    }

    @Provides
    SetEmailSubmissionMethodCallUsecase provideSetEmailSubmissionMethodCallService(
            final AccountStateRepository accountStateRepository,
            final EmailSubmissionBuilderPort emailSubmissionBuilderPort,
            final SetEmailMethodResponseBuilderPort setEmailMethodResponseBuilderPort,
            final SetEmailSubmissionMethodResponseBuilderPort
                    setEmailSubmissionMethodResponseBuilderPort,
            final UpdateEmail updateEmail) {
        return new SetEmailSubmissionMethodCallService(
                accountStateRepository,
                emailSubmissionBuilderPort,
                setEmailMethodResponseBuilderPort,
                setEmailSubmissionMethodResponseBuilderPort,
                updateEmail);
    }

    // Service>/Changes
    @Provides
    ChangesEmailMethodCallUsecase provideChangesEmailMethodCallService(
            final EmailChangesTrackerRepository emailChangesTrackerRepository,
            final ChangesEmailMethodResponseBuilderPort changesEmailMethodResponseBuilderPort,
            final AccountStateRepository accountStateRepository) {
        return new ChangesEmailMethodCallService(
                emailChangesTrackerRepository,
                changesEmailMethodResponseBuilderPort,
                accountStateRepository);
    }

    @Provides
    ChangesIdentityMethodCallUsecase provideChangesIdentityMethodCallService(
            final AccountStateRepository accountStateRepository,
            final ChangesIdentityMethodResponseBuilderPort changesIdentityMethodResponseBuilderPort,
            final IdentityChangesTrackerRepository identityChangesTrackerRepository) {
        return new ChangesIdentityMethodCallService(
                accountStateRepository,
                changesIdentityMethodResponseBuilderPort,
                identityChangesTrackerRepository);
    }

    @Provides
    ChangesMailboxMethodCallUsecase provideChangesMailboxMethodCallService(
            final AccountStateRepository accountStateRepository,
            final ChangesMailboxMethodResponseBuilderPort changesMailboxMethodResponseBuilderPort,
            final MailboxChangesTrackerRepository mailboxChangesTrackerRepository) {
        return new ChangesMailboxMethodCallService(
                accountStateRepository,
                changesMailboxMethodResponseBuilderPort,
                mailboxChangesTrackerRepository);
    }

    @Provides
    ChangesThreadMethodCallUsecase provideChangesThreadMethodCallService(
            final AccountStateRepository accountStateRepository,
            final ChangesThreadMethodResponseBuilderPort changesThreadMethodResponseBuilderPort,
            final ThreadChangesTrackerRepository threadChangesTrackerRepository) {
        return new ChangesThreadMethodCallService(
                accountStateRepository,
                changesThreadMethodResponseBuilderPort,
                threadChangesTrackerRepository);
    }

    // Service>/Query
    @Provides
    QueryEmailMethodCallUsecase provideQueryEmailMethodCallService(
            final AccountStateRepository accountStateRepository,
            final EmailRepository emailRepository,
            final QueryEmailMethodResponseBuilderPort queryEmailMethodResponseBuilderPort) {
        return new QueryEmailMethodCallService(
                accountStateRepository, emailRepository, queryEmailMethodResponseBuilderPort);
    }

    // Domain>util
    @Provides
    ReferenceIdsResolver provideJmapReferenceIdsResolver(
            final ResultReferenceResolverPort referenceResolverPort) {
        return new JmapReferenceIdsResolver(referenceResolverPort);
    }

    // Domain>filter
    @Provides
    EmailPropertiesFilter provideStandardEmailPropertiesFilter() {
        return new StandardEmailPropertiesFilter();
    }

    @Provides
    IdentityPropertiesFilter provideStandardIdentityPropertiesFilter(
            final IdentityBuilderPort identityBuilderPort) {
        return new StandardIdentityPropertiesFilter(identityBuilderPort);
    }

    @Provides
    MailboxPropertiesFilter provideStandardMailboxPropertiesFilter(
            final MailboxBuilderPort mailboxBuilderPort) {
        return new StandardMailboxPropertiesFilter(mailboxBuilderPort);
    }

    @Provides
    ThreadPropertiesFilter provideStandardThreadPropertiesFilter(
            final ThreadBuilderPort threadBuilderPort) {
        return new StandardThreadPropertiesFilter(threadBuilderPort);
    }

    // Domain>methodcall>process>[Create, Update, Destroy]
    @Provides
    CreateEmail provideStandardCreateEmail(
            final EmailBuilderPort emailBuilderPort,
            final EmailRepository emailRepository,
            final AccountStateRepository accountStateRepository,
            final EmailChangesTrackerRepository emailChangesTrackerRepository,
            final MailboxChangesTrackerRepository mailboxChangesTrackerRepository,
            final ThreadChangesTrackerRepository threadChangesTrackerRepository,
            final SetErrorEnumPort setErrorEnumPort,
            final ThreadRepository threadRepository,
            final CreationIdResolverPort creationIdResolverPort) {
        return new StandardCreateEmail(
                emailBuilderPort,
                emailRepository,
                accountStateRepository,
                emailChangesTrackerRepository,
                mailboxChangesTrackerRepository,
                threadChangesTrackerRepository,
                setErrorEnumPort,
                threadRepository,
                creationIdResolverPort);
    }

    @Provides
    UpdateEmail provideStandardUpdateEmail(
            final EmailRepository emailRepository,
            final SetErrorEnumPort setErrorEnumPort,
            final AccountStateRepository accountStateRepository,
            final EmailChangesTrackerRepository emailChangesTrackerRepository,
            final MailboxChangesTrackerRepository mailboxChangesTrackerRepository,
            final CreationIdResolverPort creationIdResolverPort) {
        return new StandardUpdateEmail(
                emailRepository,
                setErrorEnumPort,
                accountStateRepository,
                emailChangesTrackerRepository,
                mailboxChangesTrackerRepository,
                creationIdResolverPort);
    }

    @Provides
    DestroyEmail provideStandardDestroyEmail(
            final EmailRepository emailRepository,
            final EmailChangesTrackerRepository emailChangesTrackerRepository,
            final MailboxChangesTrackerRepository mailboxChangesTrackerRepository,
            final SetErrorEnumPort setErrorEnumPort,
            final AccountStateRepository accountStateRepository,
            final ThreadChangesTrackerRepository threadChangesTrackerRepository) {
        return new StandardDestroyEmail(
                emailRepository,
                emailChangesTrackerRepository,
                mailboxChangesTrackerRepository,
                setErrorEnumPort,
                accountStateRepository,
                threadChangesTrackerRepository);
    }

    @Provides
    CreateMailbox provideStandardCreateMailbox(
            final AccountStateRepository accountStateRepository,
            final MailboxChangesTrackerRepository mailboxChangesTrackerRepository,
            final MailboxRepository mailboxRepository,
            final SetErrorEnumPort setErrorEnumPort) {
        return new StandardCreateMailbox(
                accountStateRepository,
                mailboxChangesTrackerRepository,
                mailboxRepository,
                setErrorEnumPort);
    }

    @Provides
    UpdateMailbox provideStandardUpdateMailbox(
            final SetErrorEnumPort setErrorEnumPort,
            final MailboxRepository mailboxRepository,
            final CreationIdResolverPort creationIdResolverPort,
            final AccountStateRepository accountStateRepository,
            final MailboxChangesTrackerRepository mailboxChangesTrackerRepository) {
        return new StandardUpdateMailbox(
                setErrorEnumPort,
                mailboxRepository,
                creationIdResolverPort,
                accountStateRepository,
                mailboxChangesTrackerRepository);
    }

    @Provides
    DestroyMailbox provideStandardDestroyMailbox(
            final AccountStateRepository accountStateRepository,
            final MailboxChangesTrackerRepository mailboxChangesTrackerRepository,
            final MailboxRepository mailboxRepository,
            final SetErrorEnumPort setErrorEnumPort) {
        return new StandardDestroyMailbox(
                accountStateRepository,
                mailboxChangesTrackerRepository,
                mailboxRepository,
                setErrorEnumPort);
    }

    @Provides
    CreateIdentity provideStandardCreateIdentity(
            final AccountStateRepository accountStateRepository,
            final IdentityChangesTrackerRepository identityChangesTrackerRepository,
            final IdentityRepository identityRepository,
            final SetErrorEnumPort setErrorEnumPort) {
        return new StandardCreateIdentity(
                accountStateRepository,
                identityChangesTrackerRepository,
                identityRepository,
                setErrorEnumPort);
    }

    // Domain>Other
    @Provides
    IfInStateMatch provideInStateMatch() {
        return new StandardIfInStateMatch();
    }
}
