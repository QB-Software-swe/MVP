package it.qbsoftware.business.ports.out.domain;

import it.qbsoftware.business.domain.AccountState;

public interface AccountStateRepository {
    public AccountState retrive(String accountId);
}
