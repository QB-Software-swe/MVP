package it.qbsoftware.adapters.in.jmaplib.method.call.get;

import it.qbsoftware.adapters.in.jmaplib.entity.InvocationResultReferenceAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.InvocationResultReferencePort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetEmailMethodCallPort;
import rs.ltt.jmap.common.method.call.email.GetEmailMethodCall;

public class GetEmailMethodCallAdapter implements GetEmailMethodCallPort {
    GetEmailMethodCall getEmailMethodCall;

    public GetEmailMethodCallAdapter(GetEmailMethodCall getEmailMethodCall) {
        this.getEmailMethodCall = getEmailMethodCall;
    }

    @Override
    public String accountId() {
        return getEmailMethodCall.getAccountId();
    }

    @Override
    public String[] getIds() {
        return getEmailMethodCall.getIds();
    }

    @Override
    public String[] getProperties() {
        return getEmailMethodCall.getProperties();
    }

    @Override
    public InvocationResultReferencePort getIdsReference() {
        return getEmailMethodCall.getIdsReference() != null
                ? new InvocationResultReferenceAdapter(getEmailMethodCall.getIdsReference())
                : null;
    }

    @Override
    public String[] getBodyProperties() {
        return this.getEmailMethodCall.getBodyProperties();
    }

    @Override
    public Boolean getFetchTextBodyValues() {
        return this.getEmailMethodCall.getFetchTextBodyValues();
    }

    @Override
    public Boolean getFetchHtmlBodyValues() {
        return this.getEmailMethodCall.getFetchHTMLBodyValues();
    }

    @Override
    public Boolean getFetchAllBodyValues() {
        return this.getEmailMethodCall.getFetchAllBodyValues();
    }

    @Override
    public Long getMaxBodyValueBytes() {
        return this.getEmailMethodCall.getMaxBodyValueBytes();
    }
}
