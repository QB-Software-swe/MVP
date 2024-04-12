package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.entity.AccountBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.AccountPort;
import rs.ltt.jmap.common.entity.Account.AccountBuilder;

public class AccountBuilderAdapter implements AccountBuilderPort{
    AccountBuilder accountBuilder;

    @Override
    public AccountPort build() {
        return new AccountAdapter(accountBuilder.build());
    }
    
}
