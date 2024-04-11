package it.qbsoftware.business.domain;

//TODO: implementare la classe
//FIXME: trovare un modo più efficiente per incrementare lo stato
public class AccountState {
    String mailboxState;

    public AccountState(final String mailboxState) {
        this.mailboxState = mailboxState;
    }

    public String mailboxState() {
        return mailboxState;
    }

    public AccountState increaseState() {
        AccountState newAccountState = this;
        newAccountState.mailboxState = newAccountState.mailboxState + "a"; // FIXME
        return newAccountState;
    }
}
