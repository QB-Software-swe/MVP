package it.qbsoftware.business.domain.util;

import java.util.Optional;

import it.qbsoftware.business.domain.entity.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;

public class AccountStateManager implements AccountStateControl {
    private final AccountStateRepository accountStateRepository;

    public AccountStateManager(final AccountStateRepository accountStateRepository) {
        this.accountStateRepository = accountStateRepository;
    }

    @Override
    public AccountState accountState(final String id) throws AccountNotFoundException {

        throw new AccountNotFoundException();
    }
}
