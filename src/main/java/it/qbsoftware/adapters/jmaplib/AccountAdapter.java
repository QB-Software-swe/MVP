package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.entity.AccountPort;
import rs.ltt.jmap.common.entity.Account;

public class AccountAdapter implements AccountPort {
    Account account;

    public AccountAdapter(Account account){
        this.account = account;
    }
}
