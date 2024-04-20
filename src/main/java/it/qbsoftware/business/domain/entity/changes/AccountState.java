package it.qbsoftware.business.domain.entity.changes;

//TODO: implementare la classe
//FIXME: trovare un modo più efficiente per incrementare lo stato
public class AccountState {
    private final String id;
    private String mailboxState;
    private String emailState;
    private String identityState;
    private String emailSubmissionState;
    private String threadState;

    public AccountState(final String id, final String mailboxState, final String emailState, final String identityState,
            final String emailSubmissionState, final String threadState) {
        this.id = id;
        this.mailboxState = mailboxState;
        this.emailState = emailState;
        this.identityState = identityState;
        this.emailSubmissionState = emailSubmissionState;
    }

    public AccountState(final String id) {
        this.id = id;
        this.mailboxState = "0";
        this.emailState = "0";
        this.identityState = "0";
        this.emailSubmissionState = "0";
    }

    public String id() {
        return this.id;
    }

    public String mailboxState() {
        return this.mailboxState;
    }

    public String emailState() {
        return this.emailState;
    }

    public String identityState() {
        return this.identityState;
    }

    public String threadState() {
        return this.threadState;
    }

    public String emailSubmissionState() {
        return this.emailSubmissionState;
    }

    public void increaseMailboxState() {
        this.mailboxState = increaseState(this.mailboxState);
    }

    public void increaseEmailState() {
        this.emailState = increaseState(this.emailState);
    }

    public void increaseIdentityState() {
        this.identityState = increaseState(this.identityState);
    }

    public void increaseThreadState() {
        this.threadState = increaseState(this.threadState);
    }

    private String increaseState(final String state) {
        return state + "A";// FIXME: implementare una tecnica migliore
    }
}
