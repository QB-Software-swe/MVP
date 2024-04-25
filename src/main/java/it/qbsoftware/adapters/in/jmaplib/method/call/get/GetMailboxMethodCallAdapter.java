package it.qbsoftware.adapters.in.jmaplib.method.call.get;

import it.qbsoftware.adapters.in.jmaplib.entity.InvocationResultReferenceAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetMailboxMethodCallPort;
import rs.ltt.jmap.common.method.call.mailbox.GetMailboxMethodCall;

public class GetMailboxMethodCallAdapter implements GetMailboxMethodCallPort {
    private final GetMailboxMethodCall getMailboxMethodCall;

    public GetMailboxMethodCallAdapter(final GetMailboxMethodCall getMailboxMethodCall) {
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
        return getMailboxMethodCall.getIdsReference() != null
                ? new InvocationResultReferenceAdapter(getMailboxMethodCall.getIdsReference())
                : null;
    }

    @Override
    public String[] getProperties() {
        return this.getMailboxMethodCall.getProperties();
    }
}
