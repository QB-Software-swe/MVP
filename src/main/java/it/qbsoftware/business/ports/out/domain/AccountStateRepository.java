package it.qbsoftware.business.ports.out.domain;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;

public interface AccountStateRepository {
    public AccountState retrive(final String accountId) throws AccountNotFoundException;

    public void save(final AccountState accountState);
}
