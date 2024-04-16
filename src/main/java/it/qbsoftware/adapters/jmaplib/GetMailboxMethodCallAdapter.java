package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.GetMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import rs.ltt.jmap.common.method.call.mailbox.GetMailboxMethodCall;

public class GetMailboxMethodCallAdapter implements GetMailboxMethodCallPort {
    GetMailboxMethodCall getMailboxMethodCall;

    public GetMailboxMethodCallAdapter(GetMailboxMethodCall getMailboxMethodCall) {
        this.getMailboxMethodCall = getMailboxMethodCall;
    }

    @Override
    public String accountId() {
        return getMailboxMethodCall.getAccountId();
    }

    @Override
    public String[] getIds() {
        return getMailboxMethodCall.getIds();
    }

    @Override
    public InvocationResultReferencePort getIdsReference() {
        return new InvocationResultReferenceAdapter(getMailboxMethodCall.getIdsReference());
    }

    @Override
    public String[] getProperties() {
        return this.getMailboxMethodCall.getProperties();
    }
}
