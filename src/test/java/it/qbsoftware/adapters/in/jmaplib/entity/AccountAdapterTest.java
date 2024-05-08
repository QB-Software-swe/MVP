package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mock;
import rs.ltt.jmap.common.entity.Account;

public class AccountAdapterTest {

    @Mock Account account;

    @Test
    public void testAccountAdapter() {

        AccountAdapter accountAdapter = new AccountAdapter(account);

        assertEquals(account, accountAdapter.account);
    }
}
