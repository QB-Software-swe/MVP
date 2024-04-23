package it.qbsoftware.application.module;

import com.google.inject.AbstractModule;

import it.qbsoftware.adapters.in.jmaplib.entity.AbstractIdentifiableEntityAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.AccountAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.AccountBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.AccountCapabilityAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.CapabilityAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.ClassAccountCapabilityAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.EmailAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.EmailAddressAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.EmailBodyPartAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.EmailBodyValueAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.EmailBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.EmailSubmissionAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.EmailSubmissionBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.IdentityAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.IdentityBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.InvocationResultReferenceAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.MailboxAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.MailboxBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.MailboxRightsAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.ResponseInvocationAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.RoleAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.SessionResourceAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.SessionResourceBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.SetErrorAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.SetErrorEnumAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.ThreadAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.ThreadBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.error.AccountNotFoundMethodErrorResponseAdapter;
import it.qbsoftware.adapters.in.jmaplib.error.InvalidArgumentsMethodErrorResponseAdapter;
import it.qbsoftware.adapters.in.jmaplib.error.InvalidResultReferenceMethodErrorResponseAdapter;
import it.qbsoftware.adapters.in.jmaplib.error.StateMismatchMethodErrorResponseAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.call.changes.ChangesEmailMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.call.changes.ChangesEmailSubmissionMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.call.changes.ChangesIdentityMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.call.changes.ChangesMailboxMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.call.changes.ChangesThreadMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.call.get.GetEmailMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.call.get.GetEmailSubmissionMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.call.get.GetIdentityMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.call.get.GetMailboxMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.call.get.GetThreadMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.call.other.EchoMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.call.set.SetEmailMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.call.set.SetIdentityMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.call.set.SetMailboxMethodCallAdapter;
import it.qbsoftware.business.ports.in.jmap.capability.AccountCapabilityPort;
import it.qbsoftware.business.ports.in.jmap.capability.CapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.AbstractIdentifiableEntityPort;
import it.qbsoftware.business.ports.in.jmap.entity.AccountBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.AccountPort;
import it.qbsoftware.business.ports.in.jmap.entity.ClassAccountCapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailAddressPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyPartPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBodyValuePort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxRightsPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.entity.RolePort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourceBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourcePort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorPort;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;
import it.qbsoftware.business.ports.in.jmap.error.AccountNotFoundMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidArgumentsMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.InvalidResultReferenceMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.error.StateMismatchMethodErrorResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesEmailSubmissionMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesIdentityMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesThreadMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetEmailSubmissionMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetIdentityMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetThreadMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.call.other.EchoMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetIdentityMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetMailboxMethodCallPort;

public class JmapLibAdapterModule extends AbstractModule {
    @Override
    protected void configure() {
        // Entity
        bind(AbstractIdentifiableEntityPort.class).to(AbstractIdentifiableEntityAdapter.class);
        bind(AccountPort.class).to(AccountAdapter.class);
        bind(AccountBuilderPort.class).to(AccountBuilderAdapter.class);
        bind(AccountCapabilityPort.class).to(AccountCapabilityAdapter.class);
        bind(CapabilityPort.class).to(CapabilityAdapter.class);
        bind(ClassAccountCapabilityPort.class).to(ClassAccountCapabilityAdapter.class);
        bind(EmailPort.class).to(EmailAdapter.class);
        bind(EmailAddressPort.class).to(EmailAddressAdapter.class);
        bind(EmailBodyPartPort.class).to(EmailBodyPartAdapter.class);
        bind(EmailBodyValuePort.class).to(EmailBodyValueAdapter.class);
        bind(EmailBuilderPort.class).to(EmailBuilderAdapter.class);
        bind(EmailSubmissionPort.class).to(EmailSubmissionAdapter.class);
        bind(EmailSubmissionBuilderPort.class).to(EmailSubmissionBuilderAdapter.class);
        bind(IdentityPort.class).to(IdentityAdapter.class);
        bind(IdentityBuilderPort.class).to(IdentityBuilderAdapter.class);
        bind(InvocationResultReferencePort.class).to(InvocationResultReferenceAdapter.class);
        bind(MailboxPort.class).to(MailboxAdapter.class);
        bind(MailboxBuilderPort.class).to(MailboxBuilderAdapter.class);
        bind(MailboxRightsPort.class).to(MailboxRightsAdapter.class);
        bind(ResponseInvocationPort.class).to(ResponseInvocationAdapter.class);
        bind(RolePort.class).to(RoleAdapter.class);
        bind(SessionResourcePort.class).to(SessionResourceAdapter.class);
        bind(SessionResourceBuilderPort.class).to(SessionResourceBuilderAdapter.class);
        bind(SetErrorPort.class).to(SetErrorAdapter.class);
        bind(SetErrorEnumPort.class).to(SetErrorEnumAdapter.class);
        bind(ThreadPort.class).to(ThreadAdapter.class);
        bind(ThreadBuilderPort.class).to(ThreadBuilderAdapter.class);

        // Error
        bind(AccountNotFoundMethodErrorResponsePort.class).to(AccountNotFoundMethodErrorResponseAdapter.class);
        bind(InvalidArgumentsMethodErrorResponsePort.class).to(InvalidArgumentsMethodErrorResponseAdapter.class);
        bind(InvalidResultReferenceMethodErrorResponsePort.class)
                .to(InvalidResultReferenceMethodErrorResponseAdapter.class);
        bind(StateMismatchMethodErrorResponsePort.class).to(StateMismatchMethodErrorResponseAdapter.class);

        // MethodCall
        bind(ChangesEmailMethodCallPort.class).to(ChangesEmailMethodCallAdapter.class);
        bind(ChangesEmailSubmissionMethodCallPort.class).to(ChangesEmailSubmissionMethodCallAdapter.class);
        bind(ChangesIdentityMethodCallPort.class).to(ChangesIdentityMethodCallAdapter.class);
        bind(ChangesMailboxMethodCallPort.class).to(ChangesMailboxMethodCallAdapter.class);
        bind(ChangesThreadMethodCallPort.class).to(ChangesThreadMethodCallAdapter.class);

        bind(GetEmailMethodCallPort.class).to(GetEmailMethodCallAdapter.class);
        bind(GetEmailSubmissionMethodCallPort.class).to(GetEmailSubmissionMethodCallAdapter.class);
        bind(GetIdentityMethodCallPort.class).to(GetIdentityMethodCallAdapter.class);
        bind(GetMailboxMethodCallPort.class).to(GetMailboxMethodCallAdapter.class);
        bind(GetThreadMethodCallPort.class).to(GetThreadMethodCallAdapter.class);

        bind(EchoMethodCallPort.class).to(EchoMethodCallAdapter.class);

        bind(SetEmailMethodCallPort.class).to(SetEmailMethodCallAdapter.class);
        bind(SetIdentityMethodCallPort.class).to(SetIdentityMethodCallAdapter.class);
        bind(SetMailboxMethodCallPort.class).to(SetMailboxMethodCallAdapter.class);

        // MethodResponse
    }
}
