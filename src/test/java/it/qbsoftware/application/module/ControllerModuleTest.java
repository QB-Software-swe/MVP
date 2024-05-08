package it.qbsoftware.application.module;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ControllerModuleTest {

    @InjectMocks private ControllerModule controllerModule;

    @Test
    public void testProvideChangesEmailMethodCallService() {
        AccountStateRepository accountStateRepository = mock(AccountStateRepository.class);
        EmailChangesTrackerRepository emailChangesTrackerRepository =
                mock(EmailChangesTrackerRepository.class);
        ChangesEmailMethodResponseBuilderPort changesEmailMethodResponseBuilderPort =
                mock(ChangesEmailMethodResponseBuilderPort.class);

        ChangesEmailMethodCallUsecase result =
                controllerModule.provideChangesEmailMethodCallService(
                        emailChangesTrackerRepository,
                        changesEmailMethodResponseBuilderPort,
                        accountStateRepository);

        assertTrue(result instanceof ChangesEmailMethodCallService);
    }

    @Test
    public void testProvideChangesIdentityMethodCallService() {
        AccountStateRepository accountStateRepository = mock(AccountStateRepository.class);
        ChangesIdentityMethodResponseBuilderPort changesIdentityMethodResponseBuilderPort =
                mock(ChangesIdentityMethodResponseBuilderPort.class);
        IdentityChangesTrackerRepository identityChangesTrackerRepository =
                mock(IdentityChangesTrackerRepository.class);

        ChangesIdentityMethodCallUsecase result =
                controllerModule.provideChangesIdentityMethodCallService(
                        accountStateRepository,
                        changesIdentityMethodResponseBuilderPort,
                        identityChangesTrackerRepository);

        assertTrue(result instanceof ChangesIdentityMethodCallService);
    }

    @Test
    public void testProvideChangesMailboxMethodCallService() {
        AccountStateRepository accountStateRepository = mock(AccountStateRepository.class);
        ChangesMailboxMethodResponseBuilderPort changesMailboxMethodResponseBuilderPort =
                mock(ChangesMailboxMethodResponseBuilderPort.class);
        MailboxChangesTrackerRepository mailboxChangesTrackerRepository =
                mock(MailboxChangesTrackerRepository.class);

        ChangesMailboxMethodCallUsecase result =
                controllerModule.provideChangesMailboxMethodCallService(
                        accountStateRepository,
                        changesMailboxMethodResponseBuilderPort,
                        mailboxChangesTrackerRepository);

        assertTrue(result instanceof ChangesMailboxMethodCallService);
    }

    @Test
    public void testProvideChangesThreadMethodCallService() {
        AccountStateRepository accountStateRepository = mock(AccountStateRepository.class);
        ChangesThreadMethodResponseBuilderPort changesThreadMethodResponseBuilderPort =
                mock(ChangesThreadMethodResponseBuilderPort.class);
        ThreadChangesTrackerRepository threadChangesTrackerRepository =
                mock(ThreadChangesTrackerRepository.class);

        ChangesThreadMethodCallUsecase result =
                controllerModule.provideChangesThreadMethodCallService(
                        accountStateRepository,
                        changesThreadMethodResponseBuilderPort,
                        threadChangesTrackerRepository);

        assertTrue(result instanceof ChangesThreadMethodCallService);
    }

    @Test
    public void testProvideEchoMethodCallService() {
        EchoMethodResponseBuilderPort echoMethodResponseBuilderPort =
                mock(EchoMethodResponseBuilderPort.class);

        EchoMethodCallUsecase result =
                controllerModule.provideEchoMethodCallService(echoMethodResponseBuilderPort);

        assertTrue(result instanceof EchoMethodCallSerivce);
    }

    @Test
    public void testProvideGetEmailMethodCallService() {
        AccountStateRepository accountStateRepository = mock(AccountStateRepository.class);
        EmailPropertiesFilter emailPropertiesFilter = mock(EmailPropertiesFilter.class);
        EmailRepository emailRepository = mock(EmailRepository.class);
        GetEmailMethodResponseBuilderPort getEmailMethodResponseBuilderPort =
                mock(GetEmailMethodResponseBuilderPort.class);
        ReferenceIdsResolver getReferenceIdsResolver = mock(ReferenceIdsResolver.class);

        GetEmailMethodCallUsecase result =
                controllerModule.provideGetEmailMethodCallService(
                        accountStateRepository,
                        emailPropertiesFilter,
                        emailRepository,
                        getEmailMethodResponseBuilderPort,
                        getReferenceIdsResolver);

        assertTrue(result instanceof GetEmailMethodCallService);
    }

    @Test
    public void testProvideGetIdentityMethodCallService() {
        AccountStateRepository accountStateRepository = mock(AccountStateRepository.class);
        GetIdentityMethodResponseBuilderPort getIdentityMethodResponseBuilderPort =
                mock(GetIdentityMethodResponseBuilderPort.class);
        ReferenceIdsResolver getReferenceIdsResolver = mock(ReferenceIdsResolver.class);
        IdentityPropertiesFilter identityPropertiesFilter = mock(IdentityPropertiesFilter.class);
        IdentityRepository identityRepository = mock(IdentityRepository.class);

        GetIdentityMethodCallUsecase result =
                controllerModule.provideGetIdentityMethodCallService(
                        accountStateRepository,
                        getIdentityMethodResponseBuilderPort,
                        getReferenceIdsResolver,
                        identityPropertiesFilter,
                        identityRepository);

        assertTrue(result instanceof GetIdentityMethodCallService);
    }

    @Test
    public void testProvideGetMailboxMethodCallService() {
        AccountStateRepository accountStateRepository = mock(AccountStateRepository.class);
        GetMailboxMethodResponseBuilderPort getMailboxMethodResponseBuilderPort =
                mock(GetMailboxMethodResponseBuilderPort.class);
        ReferenceIdsResolver getReferenceIdsResolver = mock(ReferenceIdsResolver.class);
        MailboxPropertiesFilter mailboxPropertiesFilter = mock(MailboxPropertiesFilter.class);
        MailboxRepository mailboxRepository = mock(MailboxRepository.class);

        GetMailboxMethodCallUsecase result =
                controllerModule.provideGetMailboxMethodCallService(
                        accountStateRepository,
                        getMailboxMethodResponseBuilderPort,
                        getReferenceIdsResolver,
                        mailboxPropertiesFilter,
                        mailboxRepository);

        assertTrue(result instanceof GetMailboxMethodCallService);
    }

    @Test
    public void testProvideGetThreadMethodCallService() {
        AccountStateRepository accountStateRepository = mock(AccountStateRepository.class);
        ReferenceIdsResolver getReferenceIdsResolver = mock(ReferenceIdsResolver.class);
        GetThreadMethodResponseBuilderPort getThreadMethodResponseBuilderPort =
                mock(GetThreadMethodResponseBuilderPort.class);
        ThreadPropertiesFilter threadPropertiesFilter = mock(ThreadPropertiesFilter.class);
        ThreadRepository threadRepository = mock(ThreadRepository.class);

        GetThreadMethodCallUsecase result =
                controllerModule.provideGetThreadMethodCallService(
                        accountStateRepository,
                        getReferenceIdsResolver,
                        getThreadMethodResponseBuilderPort,
                        threadPropertiesFilter,
                        threadRepository);

        assertTrue(result instanceof GetThreadMethodCallService);
    }

    @Test
    public void testProvideInStateMatch() {
        IfInStateMatch result = controllerModule.provideInStateMatch();

        assertTrue(result instanceof StandardIfInStateMatch);
    }

    @Test
    public void testProvideJmapReferenceIdsResolver() {
        ResultReferenceResolverPort referenceResolverPort = mock(ResultReferenceResolverPort.class);

        ReferenceIdsResolver result =
                controllerModule.provideJmapReferenceIdsResolver(referenceResolverPort);

        assertTrue(result instanceof JmapReferenceIdsResolver);
    }

    @Test
    public void testProvideQueryEmailMethodCallService() {
        AccountStateRepository accountStateRepository = mock(AccountStateRepository.class);
        EmailRepository emailRepository = mock(EmailRepository.class);
        QueryEmailMethodResponseBuilderPort queryEmailMethodResponseBuilderPort =
                mock(QueryEmailMethodResponseBuilderPort.class);

        QueryEmailMethodCallUsecase result =
                controllerModule.provideQueryEmailMethodCallService(
                        accountStateRepository,
                        emailRepository,
                        queryEmailMethodResponseBuilderPort);

        assertTrue(result instanceof QueryEmailMethodCallService);
    }

    @Test
    public void testProvideSessionService() {
        SessionResourceBuilderPort sessionResourceBuilderPort =
                mock(SessionResourceBuilderPort.class);
        UserSessionResourceRepository userSessionResourceRepository =
                mock(UserSessionResourceRepository.class);

        SessionUsecase result =
                controllerModule.provideSessionService(
                        sessionResourceBuilderPort, userSessionResourceRepository);

        assertTrue(result instanceof SessionService);
    }

    @Test
    public void testProvideSetEmailMethodCallService() {
        AccountStateRepository accountStateRepository = mock(AccountStateRepository.class);
        IfInStateMatch ifInStateMatch = mock(IfInStateMatch.class);
        CreateEmail createEmail = mock(CreateEmail.class);
        UpdateEmail updateEmail = mock(UpdateEmail.class);
        DestroyEmail destroyEmail = mock(DestroyEmail.class);
        SetEmailMethodResponseBuilderPort setEmailMethodResponseBuilderPort =
                mock(SetEmailMethodResponseBuilderPort.class);

        SetEmailMethodCallUsecase result =
                controllerModule.provideSetEmailMethodCallService(
                        accountStateRepository,
                        ifInStateMatch,
                        createEmail,
                        updateEmail,
                        destroyEmail,
                        setEmailMethodResponseBuilderPort);

        assertTrue(result instanceof SetEmailMethodCallService);
    }

    @Test
    public void testProvideSetEmailSubmissionMethodCallService() {
        AccountStateRepository accountStateRepository = mock(AccountStateRepository.class);
        EmailSubmissionBuilderPort emailSubmissionBuilderPort =
                mock(EmailSubmissionBuilderPort.class);
        SetEmailMethodResponseBuilderPort setEmailMethodResponseBuilderPort =
                mock(SetEmailMethodResponseBuilderPort.class);
        SetEmailSubmissionMethodResponseBuilderPort setEmailSubmissionMethodResponseBuilderPort =
                mock(SetEmailSubmissionMethodResponseBuilderPort.class);
        UpdateEmail updateEmail = mock(UpdateEmail.class);

        SetEmailSubmissionMethodCallUsecase result =
                controllerModule.provideSetEmailSubmissionMethodCallService(
                        accountStateRepository,
                        emailSubmissionBuilderPort,
                        setEmailMethodResponseBuilderPort,
                        setEmailSubmissionMethodResponseBuilderPort,
                        updateEmail);

        assertTrue(result instanceof SetEmailSubmissionMethodCallService);
    }

    @Test
    public void testProvideSetIdentityMethodCallServide() {
        AccountStateRepository accountStateRepository = mock(AccountStateRepository.class);
        CreateIdentity createIndetity = mock(CreateIdentity.class);
        IfInStateMatch ifInStateMatch = mock(IfInStateMatch.class);
        SetIdentityMethodResponseBuilderPort setIdentityMethodResponseBuilderPort =
                mock(SetIdentityMethodResponseBuilderPort.class);

        SetIdentityMethodCallUsecase result =
                controllerModule.provideSetIdentityMethodCallServide(
                        accountStateRepository,
                        createIndetity,
                        ifInStateMatch,
                        setIdentityMethodResponseBuilderPort);

        assertTrue(result instanceof SetIdentityMethodCallService);
    }

    @Test
    public void testProvideSetMailboxMethodCallService() {
        AccountStateRepository accountStateRepository = mock(AccountStateRepository.class);
        IfInStateMatch ifInStateMatch = mock(IfInStateMatch.class);
        SetMailboxMethodResponseBuilderPort setMailboxMethodResponseBuilderPort =
                mock(SetMailboxMethodResponseBuilderPort.class);
        CreateMailbox createMailbox = mock(CreateMailbox.class);
        DestroyMailbox destroyMailbox = mock(DestroyMailbox.class);
        UpdateMailbox updateMailbox = mock(UpdateMailbox.class);

        SetMailboxMethodCallUsecase result =
                controllerModule.provideSetMailboxMethodCallService(
                        accountStateRepository,
                        ifInStateMatch,
                        setMailboxMethodResponseBuilderPort,
                        createMailbox,
                        destroyMailbox,
                        updateMailbox);

        assertTrue(result instanceof SetMailboxMethodCallService);
    }

    @Test
    public void testProvideStandardCreateEmail() {
        EmailBuilderPort emailBuilderPort = mock(EmailBuilderPort.class);
        EmailRepository emailRepository = mock(EmailRepository.class);
        AccountStateRepository accountStateRepository = mock(AccountStateRepository.class);
        EmailChangesTrackerRepository emailChangesTrackerRepository =
                mock(EmailChangesTrackerRepository.class);
        MailboxChangesTrackerRepository mailboxChangesTrackerRepository =
                mock(MailboxChangesTrackerRepository.class);
        ThreadChangesTrackerRepository threadChangesTrackerRepository =
                mock(ThreadChangesTrackerRepository.class);
        SetErrorEnumPort setErrorEnumPort = mock(SetErrorEnumPort.class);
        ThreadRepository threadRepository = mock(ThreadRepository.class);
        CreationIdResolverPort creationIdResolverPort = mock(CreationIdResolverPort.class);

        CreateEmail result =
                controllerModule.provideStandardCreateEmail(
                        emailBuilderPort,
                        emailRepository,
                        accountStateRepository,
                        emailChangesTrackerRepository,
                        mailboxChangesTrackerRepository,
                        threadChangesTrackerRepository,
                        setErrorEnumPort,
                        threadRepository,
                        creationIdResolverPort);

        assertTrue(result instanceof StandardCreateEmail);
    }

    @Test
    public void testProvideStandardCreateIdentity() {
        AccountStateRepository accountStateRepository = mock(AccountStateRepository.class);
        IdentityChangesTrackerRepository identityChangesTrackerRepository =
                mock(IdentityChangesTrackerRepository.class);
        IdentityRepository identityRepository = mock(IdentityRepository.class);
        SetErrorEnumPort setErrorEnumPort = mock(SetErrorEnumPort.class);

        CreateIdentity result =
                controllerModule.provideStandardCreateIdentity(
                        accountStateRepository,
                        identityChangesTrackerRepository,
                        identityRepository,
                        setErrorEnumPort);

        assertTrue(result instanceof StandardCreateIdentity);
    }

    @Test
    public void testProvideStandardCreateMailbox() {
        AccountStateRepository accountStateRepository = mock(AccountStateRepository.class);
        MailboxChangesTrackerRepository mailboxChangesTrackerRepository =
                mock(MailboxChangesTrackerRepository.class);
        MailboxRepository mailboxRepository = mock(MailboxRepository.class);
        SetErrorEnumPort setErrorEnumPort = mock(SetErrorEnumPort.class);

        CreateMailbox result =
                controllerModule.provideStandardCreateMailbox(
                        accountStateRepository,
                        mailboxChangesTrackerRepository,
                        mailboxRepository,
                        setErrorEnumPort);

        assertTrue(result instanceof StandardCreateMailbox);
    }

    @Test
    public void testProvideStandardDestroyEmail() {
        EmailRepository emailRepository = mock(EmailRepository.class);
        EmailChangesTrackerRepository emailChangesTrackerRepository =
                mock(EmailChangesTrackerRepository.class);
        MailboxChangesTrackerRepository mailboxChangesTrackerRepository =
                mock(MailboxChangesTrackerRepository.class);
        SetErrorEnumPort setErrorEnumPort = mock(SetErrorEnumPort.class);
        AccountStateRepository accountStateRepository = mock(AccountStateRepository.class);
        ThreadChangesTrackerRepository threadChangesTrackerRepository =
                mock(ThreadChangesTrackerRepository.class);

        DestroyEmail result =
                controllerModule.provideStandardDestroyEmail(
                        emailRepository,
                        emailChangesTrackerRepository,
                        mailboxChangesTrackerRepository,
                        setErrorEnumPort,
                        accountStateRepository,
                        threadChangesTrackerRepository);

        assertTrue(result instanceof StandardDestroyEmail);
    }

    @Test
    public void testProvideStandardDestroyMailbox() {
        AccountStateRepository accountStateRepository = mock(AccountStateRepository.class);
        MailboxChangesTrackerRepository mailboxChangesTrackerRepository =
                mock(MailboxChangesTrackerRepository.class);
        MailboxRepository mailboxRepository = mock(MailboxRepository.class);
        SetErrorEnumPort setErrorEnumPort = mock(SetErrorEnumPort.class);

        DestroyMailbox result =
                controllerModule.provideStandardDestroyMailbox(
                        accountStateRepository,
                        mailboxChangesTrackerRepository,
                        mailboxRepository,
                        setErrorEnumPort);

        assertTrue(result instanceof StandardDestroyMailbox);
    }

    @Test
    public void testProvideStandardEmailPropertiesFilter() {
        EmailPropertiesFilter result = controllerModule.provideStandardEmailPropertiesFilter();

        assertTrue(result instanceof StandardEmailPropertiesFilter);
    }

    @Test
    public void testProvideStandardIdentityPropertiesFilter() {
        IdentityBuilderPort identityBuilderPort = mock(IdentityBuilderPort.class);

        IdentityPropertiesFilter result =
                controllerModule.provideStandardIdentityPropertiesFilter(identityBuilderPort);

        assertTrue(result instanceof StandardIdentityPropertiesFilter);
    }

    @Test
    public void testProvideStandardMailboxPropertiesFilter() {
        MailboxBuilderPort mailboxBuilderPort = mock(MailboxBuilderPort.class);

        MailboxPropertiesFilter result =
                controllerModule.provideStandardMailboxPropertiesFilter(mailboxBuilderPort);

        assertTrue(result instanceof StandardMailboxPropertiesFilter);
    }

    @Test
    public void testProvideStandardThreadPropertiesFilter() {
        ThreadBuilderPort threadBuilderPort = mock(ThreadBuilderPort.class);

        ThreadPropertiesFilter result =
                controllerModule.provideStandardThreadPropertiesFilter(threadBuilderPort);

        assertTrue(result instanceof StandardThreadPropertiesFilter);
    }

    @Test
    public void testProvideStandardUpdateEmail() {
        EmailRepository emailRepository = mock(EmailRepository.class);
        SetErrorEnumPort setErrorEnumPort = mock(SetErrorEnumPort.class);
        AccountStateRepository accountStateRepository = mock(AccountStateRepository.class);
        EmailChangesTrackerRepository emailChangesTrackerRepository =
                mock(EmailChangesTrackerRepository.class);
        MailboxChangesTrackerRepository mailboxChangesTrackerRepository =
                mock(MailboxChangesTrackerRepository.class);
        CreationIdResolverPort creationIdResolverPort = mock(CreationIdResolverPort.class);

        UpdateEmail result =
                controllerModule.provideStandardUpdateEmail(
                        emailRepository,
                        setErrorEnumPort,
                        accountStateRepository,
                        emailChangesTrackerRepository,
                        mailboxChangesTrackerRepository,
                        creationIdResolverPort);

        assertTrue(result instanceof StandardUpdateEmail);
    }
}
