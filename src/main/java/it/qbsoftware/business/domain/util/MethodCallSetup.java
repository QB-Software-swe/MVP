package it.qbsoftware.business.domain.util;

import java.util.Optional;

import it.qbsoftware.business.domain.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;

public class MethodCallSetup implements CommonMethodCallSetup {
    final AccountStateRepository accountStateRepository;

    public MethodCallSetup(final AccountStateRepository accountStateRepository) {
        this.accountStateRepository = accountStateRepository;
    }

    @Override
    public AccountState accountState(final String id) throws AccountNotFoundException {
        Optional<AccountState> optionalAccountState = accountStateRepository.retrive(id);
        if (optionalAccountState.isPresent()) {
            return optionalAccountState.get();
        }

        throw new AccountNotFoundException();
    }

}
