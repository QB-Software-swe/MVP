package it.qbsoftware.application.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import it.qbsoftware.business.domain.methodcall.filter.EmailPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.filter.EmailSubmissionPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.filter.IdentityPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.filter.MailboxPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.filter.ThreadPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.filter.standard.StandardEmailPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.filter.standard.StandardEmailSubmissionPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.filter.standard.StandardIdentityPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.filter.standard.StandardMailboxPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.filter.standard.StandardThreadPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.process.get.GetReferenceIdsResolver;
import it.qbsoftware.business.domain.methodcall.process.get.JmapReferenceIdsResolver;
import it.qbsoftware.business.domain.methodcall.process.set.create.CreateEmail;
import it.qbsoftware.business.domain.methodcall.process.set.create.StandardCreateEmail;
import it.qbsoftware.business.domain.methodcall.process.set.destroy.DestroyEmail;
import it.qbsoftware.business.domain.methodcall.process.set.destroy.StandardDestroyEmail;
import it.qbsoftware.business.domain.methodcall.process.set.update.StandardUpdateEmail;
import it.qbsoftware.business.domain.methodcall.process.set.update.UpdateEmail;
import it.qbsoftware.business.domain.methodcall.statematch.IfInStateMatch;
import it.qbsoftware.business.domain.methodcall.statematch.StandardIfInStateMatch;
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
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetThreadMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.util.ResultReferenceResolverPort;
import it.qbsoftware.business.ports.in.usecase.SessionUsecase;
import it.qbsoftware.business.ports.in.usecase.get.GetEmailMethodCallUsecase;
import it.qbsoftware.business.ports.in.usecase.get.GetIdentityMethodCallUsecase;
import it.qbsoftware.business.ports.in.usecase.get.GetMailboxMethodCallUsecase;
import it.qbsoftware.business.ports.in.usecase.get.GetThreadMethodCallUsecase;
import it.qbsoftware.business.ports.in.usecase.set.SetEmailMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.EmailChangesTrackerRepository;
import it.qbsoftware.business.ports.out.domain.MailboxChangesTrackerRepository;
import it.qbsoftware.business.ports.out.domain.ThreadChangesTrackerRepository;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;
import it.qbsoftware.business.ports.out.jmap.IdentityRepository;
import it.qbsoftware.business.ports.out.jmap.MailboxRepository;
import it.qbsoftware.business.ports.out.jmap.ThreadRepository;
import it.qbsoftware.business.ports.out.jmap.UserSessionResourceRepository;
import it.qbsoftware.business.services.SessionService;
import it.qbsoftware.business.services.get.GetEmailMethodCallService;
import it.qbsoftware.business.services.get.GetIdentityMethodCallService;
import it.qbsoftware.business.services.get.GetMailboxMethodCallService;
import it.qbsoftware.business.services.get.GetThreadMethodCallService;
import it.qbsoftware.business.services.set.SetEmailMethodCallService;

public class ControllerModule extends AbstractModule {
        @Override
        protected void configure() {
        }

        @Provides
        SessionUsecase provideSessionService(final SessionResourceBuilderPort sessionResourceBuilderPort,
                        final UserSessionResourceRepository userSessionResourceRepository) {
                return new SessionService(sessionResourceBuilderPort, userSessionResourceRepository);
        }

        // Service /Get
        @Provides
        GetEmailMethodCallUsecase provideGetEmailMethodCallService(final AccountStateRepository accountStateRepository,
                        final EmailPropertiesFilter emailPropertiesFilter,
                        final EmailRepository emailRepository,
                        final GetEmailMethodResponseBuilderPort getEmailMethodResponseBuilderPort,
                        final GetReferenceIdsResolver getReferenceIdsResolver) {
                return new GetEmailMethodCallService(accountStateRepository, emailPropertiesFilter, emailRepository,
                                getEmailMethodResponseBuilderPort, getReferenceIdsResolver);
        }

        @Provides
        GetIdentityMethodCallUsecase provideGetIdentityMethodCallService(
                        final AccountStateRepository accountStateRepository,
                        final GetIdentityMethodResponseBuilderPort getIdentityMethodResponseBuilderPort,
                        final GetReferenceIdsResolver getReferenceIdsResolver,
                        final IdentityPropertiesFilter identityPropertiesFilter,
                        final IdentityRepository identityRepository) {
                return new GetIdentityMethodCallService(accountStateRepository, getIdentityMethodResponseBuilderPort,
                                getReferenceIdsResolver, identityPropertiesFilter, identityRepository);
        }

        @Provides
        GetMailboxMethodCallUsecase provideGetMailboxMethodCallService(
                        final AccountStateRepository accountStateRepository,
                        final GetMailboxMethodResponseBuilderPort getMailboxMethodResponseBuilderPort,
                        final GetReferenceIdsResolver getReferenceIdsResolver,
                        final MailboxPropertiesFilter mailboxPropertiesFilter,
                        final MailboxRepository mailboxRepository) {
                return new GetMailboxMethodCallService(accountStateRepository, getMailboxMethodResponseBuilderPort,
                                getReferenceIdsResolver, mailboxPropertiesFilter, mailboxRepository);
        }

        @Provides
        GetThreadMethodCallUsecase provideGetThreadMethodCallService(
                        final AccountStateRepository accountStateRepository,
                        final GetReferenceIdsResolver getReferenceIdsResolver,
                        final GetThreadMethodResponseBuilderPort getThreadMethodResponseBuilderPort,
                        final ThreadPropertiesFilter threadPropertiesFilter,
                        final ThreadRepository threadRepository) {
                return new GetThreadMethodCallService(accountStateRepository, getReferenceIdsResolver,
                                getThreadMethodResponseBuilderPort, threadPropertiesFilter, threadRepository);
        }

        // Service /Set
        /* 
        @Provides
        SetEmailMethodCallUsecase provideSetEmailMethodCallService(final AccountStateRepository accountStateRepository,
                        final IfInStateMatch ifInStateMatch,
                        final StateMismatchMethodErrorResponsePort stateMismatchMethodErrorResponsePort,
                        final CreateEmail createEmail,
                        final UpdateEmail updateEmail,
                        final DestroyEmail destroyEmail,
                        final SetEmailMethodResponseBuilderPort setEmailMethodResponseBuilderPort,
                        final AccountNotFoundMethodErrorResponsePort accountNotFoundMethodErrorResponsePort) {
                return new SetEmailMethodCallService(accountStateRepository, ifInStateMatch,
                                stateMismatchMethodErrorResponsePort, createEmail, updateEmail, destroyEmail,
                                setEmailMethodResponseBuilderPort, accountNotFoundMethodErrorResponsePort);
        }
        */

        // Domain>util
        @Provides
        GetReferenceIdsResolver provideJmapReferenceIdsResolver(
                        final ResultReferenceResolverPort referenceResolverPort) {
                return new JmapReferenceIdsResolver(referenceResolverPort);
        }

        // Domain>filter
        @Provides
        EmailPropertiesFilter provideStandardEmailPropertiesFilter(final EmailBuilderPort emailBuilderPort) {
                return new StandardEmailPropertiesFilter(emailBuilderPort);
        }

        @Provides
        IdentityPropertiesFilter provideStandardIdentityPropertiesFilter(
                        final IdentityBuilderPort identityBuilderPort) {
                return new StandardIdentityPropertiesFilter(identityBuilderPort);
        }

        @Provides
        MailboxPropertiesFilter provideStandardMailboxPropertiesFilter(final MailboxBuilderPort mailboxBuilderPort) {
                return new StandardMailboxPropertiesFilter(mailboxBuilderPort);
        }

        @Provides
        ThreadPropertiesFilter provideStandardThreadPropertiesFilter(final ThreadBuilderPort threadBuilderPort) {
                return new StandardThreadPropertiesFilter(threadBuilderPort);
        }

        @Provides
        EmailSubmissionPropertiesFilter provideStandardEmailSubmissionPropertiesFilter(
                        final EmailSubmissionBuilderPort emailSubmissionBuilderPort) {
                return new StandardEmailSubmissionPropertiesFilter(emailSubmissionBuilderPort);
        }

        // Domain>methodcall>process>[Create, Update, Destroy]
        @Provides
        CreateEmail provideStandardCreateEmail(final EmailBuilderPort emailBuilderPort,
                        final EmailRepository emailRepository,
                        final AccountStateRepository accountStateRepository,
                        final EmailChangesTrackerRepository emailChangesTrackerRepository,
                        final MailboxChangesTrackerRepository mailboxChangesTrackerRepository,
                        final ThreadChangesTrackerRepository threadChangesTrackerRepository,
                        final SetErrorEnumPort setErrorEnumPort) {
                return new StandardCreateEmail(emailBuilderPort, emailRepository, accountStateRepository,
                                emailChangesTrackerRepository, mailboxChangesTrackerRepository,
                                threadChangesTrackerRepository, setErrorEnumPort);
        }

        @Provides
        UpdateEmail provideStandardUpdateEmail() {
                return new StandardUpdateEmail();
        }

        @Provides
        DestroyEmail provideDestroyEmail(final EmailRepository emailRepository,
                        final EmailChangesTrackerRepository emailChangesTrackerRepository,
                        final MailboxChangesTrackerRepository mailboxChangesTrackerRepository,
                        final SetErrorEnumPort setErrorEnumPort,
                        final AccountStateRepository accountStateRepository,
                        final ThreadChangesTrackerRepository threadChangesTrackerRepository) {
                return new StandardDestroyEmail(emailRepository, emailChangesTrackerRepository,
                                mailboxChangesTrackerRepository, setErrorEnumPort, accountStateRepository,
                                threadChangesTrackerRepository);
        }

        // Domain>Other
        @Provides
        IfInStateMatch provideInStateMatch() {
                return new StandardIfInStateMatch();
        }
}
