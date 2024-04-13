package it.qbsoftware.business.domain;

//TODO: implementare la classe
//FIXME: trovare un modo più efficiente per incrementare lo stato
public class AccountState {
    String mailboxState;
    String emailState;
    String identityState;

    public AccountState(final String mailboxState, final String emailState, final String identityState) {
        this.mailboxState = mailboxState;
        this.emailState = emailState;
        this.identityState = identityState;
    }

    public String mailboxState() {
        return mailboxState;
    }

    public String emailState() {
        return emailState;
    }

    public String identityState() {
        return identityState;
    }

    public AccountState increaseMailboxState() {
        AccountState newAccountState = this;
        newAccountState.mailboxState = increaseState(newAccountState.mailboxState);
        return newAccountState;
    }

    public AccountState increaseEmailState() {
        AccountState newAccountState = this;
        newAccountState.emailState = increaseState(emailState);
        return newAccountState;
    }

    public void increaseIdentityState() {
        identityState = increaseState(identityState);
    }

    private String increaseState(final String state) {
        return state + "A";// FIXME
    }
}
