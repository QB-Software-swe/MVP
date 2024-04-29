package it.qbsoftware.business.domain.entity.changes;

public class AccountState {
    private final String id;
    private final String state;

    public AccountState(final String id, final String state) {
        this.id = id;
        this.state = state;
    }

    public AccountState(final String id) {
        this.id = id;
        this.state = "0";
    }

    public String id() {
        return this.id;
    }

    public String state() {
        return this.state;
    }

    public AccountState increaseState() {
        return new AccountState(this.id, increaseState(this.state));
    }

    private String increaseState(final String state) {
        return String.valueOf(Long.parseLong(state) + 1L);
    }
}
