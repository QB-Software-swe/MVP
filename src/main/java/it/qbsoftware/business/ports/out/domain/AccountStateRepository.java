package it.qbsoftware.business.ports.out.domain;

import java.util.Optional;
import it.qbsoftware.business.domain.AccountState;

public interface AccountStateRepository {
    public Optional<AccountState> retrive(String accountId);
}
