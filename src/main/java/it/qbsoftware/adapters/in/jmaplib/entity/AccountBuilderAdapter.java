package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.AccountBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.AccountPort;
import rs.ltt.jmap.common.entity.Account;
import rs.ltt.jmap.common.entity.Account.AccountBuilder;

public class AccountBuilderAdapter implements AccountBuilderPort {
    AccountBuilder accountBuilder;

    public AccountBuilderAdapter() {
        this.accountBuilder = Account.builder();
    }

    @Override
    public AccountPort build() {
        return new AccountAdapter(accountBuilder.build());
    }
}
