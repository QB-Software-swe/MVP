package it.qbsoftware.business.domain.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AccountStateTest {

    private final String id = "id";
    private String mailboxState = "mailboxState";
    private String emailState = "emailState";
    private String identityState = "identityState";
    private String emailSubmissionState = "emailSubmissionState";

    AccountState accountState = new AccountState(id, mailboxState, emailState, identityState, emailSubmissionState);

    @Test
    public void testEmailState() {
        assertEquals(emailState, accountState.emailState());
    }

    @Test
    public void testEmailSubmissionState() {
        assertEquals(emailSubmissionState, accountState.emailSubmissionState());
    }

    @Test
    public void testId() {
        assertEquals(id, accountState.id());
    }

    @Test
    public void testIdentityState() {
        assertEquals(identityState, accountState.identityState());
    }

    @Test  //fixme: implementare il test
    public void testIncreaseEmailState() {
       
    }

    @Test  //fixme: implementare il test
    public void testIncreaseIdentityState() {

    }

    @Test  //fixme: implementare il test
    public void testIncreaseMailboxState() {

    }

    @Test  //fixme: implementare il test
    public void testMailboxState() {

    }
}
