package it.qbsoftware.business.domain.entity.changes;

public class AccountState {
    private final String id;
    final private String mailboxState;
    final private String emailState;
    final private String identityState;
    final private String emailSubmissionState;
    final private String threadState;

    public AccountState(final String id, final String mailboxState, final String emailState, final String identityState,
            final String emailSubmissionState, final String threadState) {
        this.id = id;
        this.mailboxState = mailboxState;
        this.emailState = emailState;
        this.identityState = identityState;
        this.emailSubmissionState = emailSubmissionState;
        this.threadState = threadState;
    }

    public AccountState(final String id) {
        this.id = id;
        this.mailboxState = "0";
        this.emailState = "0";
        this.identityState = "0";
        this.emailSubmissionState = "0";
        this.threadState = "0";
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

    public AccountState increaseMailboxState() {
        return new AccountState(this.id, increaseState(this.mailboxState), this.emailState, this.identityState,
                this.emailSubmissionState, this.threadState);
    }

    public AccountState increaseEmailState() {
        return new AccountState(this.id, this.mailboxState, increaseState(this.emailState), this.identityState,
                this.emailSubmissionState, this.threadState);
    }

    public AccountState increaseIdentityState() {
        return new AccountState(this.id, this.mailboxState, this.emailState, increaseState(this.identityState),
                this.emailSubmissionState, this.threadState);
    }

    public AccountState increaseEmailSubmissionState() {
        return new AccountState(this.id, this.mailboxState, this.emailState, this.identityState,
                increaseState(this.emailSubmissionState), this.threadState);
    }

    public AccountState increaseThreadState() {
        return new AccountState(this.id, this.mailboxState, this.emailState, this.identityState,
                this.emailSubmissionState, increaseState(this.threadState));
    }

    private String increaseState(final String state) {
        return String.valueOf(Long.parseLong(state) + 1L);
    }
}
