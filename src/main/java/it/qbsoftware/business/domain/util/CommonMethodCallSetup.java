package it.qbsoftware.business.domain.util;

import it.qbsoftware.business.domain.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;

//FIXME: rinominare
public interface CommonMethodCallSetup {
    public AccountState accountState(final String id) throws AccountNotFoundException;
}
