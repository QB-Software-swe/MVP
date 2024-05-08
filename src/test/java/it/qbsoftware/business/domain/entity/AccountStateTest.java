package it.qbsoftware.business.domain.entity;

import static org.junit.Assert.assertEquals;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class AccountStateTest {
    @Test
    public void testId() {
        AccountState accountState = new AccountState("testId");
        assertEquals("testId", accountState.id());
    }

    @Test
    public void testState_withDefaultState() {
        AccountState accountState = new AccountState("testId");
        assertEquals("0", accountState.state());
    }

    @Test
    public void testState_withCustomState() {
        AccountState accountState = new AccountState("testId", "5");
        assertEquals("5", accountState.state());
    }

    @Test
    public void testIncreaseState() {
        AccountState accountState = new AccountState("testId", "5");
        AccountState increasedAccountState = accountState.increaseState();
        assertEquals("6", increasedAccountState.state());
    }
}
