package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.GetMailboxMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResultReferencePort;
import rs.ltt.jmap.common.method.call.mailbox.GetMailboxMethodCall;

public class GetMailboxMethodCallAdapter implements GetMailboxMethodCallPort{
    GetMailboxMethodCall getMailboxMethodCall;

    public GetMailboxMethodCallAdapter(GetMailboxMethodCall getMailboxMethodCall){
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
    public ResultReferencePort getIdsReference() {
        return getMailboxMethodCall.getIdsReference() != null ?
            new ResultReferenceAdapter(getMailboxMethodCall.getIdsReference()) : null;
    }
    

}
