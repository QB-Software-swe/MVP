package it.qbsoftware.business.services;

import java.util.HashMap;

import it.qbsoftware.business.ports.in.jmap.SessionResourceBuilderPort;
import it.qbsoftware.business.ports.in.jmap.SessionResourcePort;
import it.qbsoftware.business.ports.in.jmap.capabilities.CapabilityPort;
import it.qbsoftware.business.ports.in.jmap.capabilities.CoreCapabilityBuilderPort;
import it.qbsoftware.business.ports.in.jmap.capabilities.CoreCapabilityPort;
import it.qbsoftware.business.ports.in.jmap.capabilities.MailAccountCapabilityPort;
import it.qbsoftware.business.ports.in.jmap.capabilities.MailCapabilityBuilderPort;
import it.qbsoftware.business.ports.in.jmap.capabilities.MailCapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.AccountBuilderPort;
import it.qbsoftware.business.ports.in.jmap.capabilities.AccountCapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.AccountPort;
import it.qbsoftware.business.ports.in.usecase.SessionUsecase;

public class SessionService implements SessionUsecase {
    SessionResourceBuilderPort sessionResourceBuilderPort;
    AccountBuilderPort accountBuilderPort;
    CoreCapabilityBuilderPort coreCapabilityBuilderPort;
    MailCapabilityBuilderPort mailCapabilityBuilderPort;

    public SessionService(SessionResourceBuilderPort sessionResourceBuilderPort,
            AccountBuilderPort accountBuilderPort, CoreCapabilityBuilderPort coreCapabilityBuilderPort,
            MailCapabilityBuilderPort mailCapabilityBuilderPort) {
        this.sessionResourceBuilderPort = sessionResourceBuilderPort;
        this.accountBuilderPort = accountBuilderPort;
        this.mailCapabilityBuilderPort = mailCapabilityBuilderPort;
    }

    // FIXME
    @Override
    public SessionResourcePort call(String username) {

        // HTTP port
        sessionResourceBuilderPort
                .apiUrl("/api")
                .uploadUrl("/upload")
                .downloadUrl("/dowload")
                .eventSourceUrl("/event");

        // Accounts
        var userAccounts = new HashMap<String, AccountPort>();
        userAccounts.put("0", accountBuilderPort.build());

        // Server capabilities
        var serverCapabilities = new HashMap<Class<? extends CapabilityPort>, CapabilityPort>();
        serverCapabilities.put(
                CoreCapabilityPort.class,
                coreCapabilityBuilderPort
                        .build());
        serverCapabilities.put(
                MailCapabilityPort.class,
                mailCapabilityBuilderPort.build());

        var primaryAccounts = new HashMap<Class<? extends AccountCapabilityPort>, String>();
        primaryAccounts.put(
                MailAccountCapabilityPort.class,
                "0");

        // FIXME refactoring
        sessionResourceBuilderPort
                .username("Example")
                .accounts(userAccounts)
                .capabilities(serverCapabilities)
                .primaryAccounts(primaryAccounts);

        return sessionResourceBuilderPort.build();
    }

}
