package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.domain.AccountState;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;

public class AccountStateRepositoryAdapter implements AccountStateRepository{

    AccountState accountState;

    //TODO COSTRUTTORE

    @Override
    public AccountState retrive(String accountId) {
        //TODO DB
        return null;
    }

}
