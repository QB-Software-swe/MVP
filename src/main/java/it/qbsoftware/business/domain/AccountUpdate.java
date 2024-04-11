package it.qbsoftware.business.domain;

import java.util.LinkedHashMap;

//TODO: implementare la classe
public class AccountUpdate {
    final String accountId;
    LinkedHashMap<String, Update> updatesMap;

    public AccountUpdate(final String accountId) {
        this.accountId = accountId;
        this.updatesMap = new LinkedHashMap<>();
    }

    public void put(final String oldState, final Update update) {
        updatesMap.put(oldState, update);
    }
}
