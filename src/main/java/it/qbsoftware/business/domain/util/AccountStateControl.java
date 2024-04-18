package it.qbsoftware.business.domain.util;

import it.qbsoftware.business.domain.entity.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;

public interface AccountStateControl {
    public AccountState accountState(final String id) throws AccountNotFoundException;
}
