package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import it.qbsoftware.business.ports.in.jmap.entity.AccountPort;
import rs.ltt.jmap.common.entity.Account;
import rs.ltt.jmap.common.entity.Account.AccountBuilder;

import org.mockito.junit.MockitoJUnitRunner;



@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class AccountBuilderAdapterTest {

    @Mock
    private AccountBuilder accountBuilderMock;

    @Test
    public void testBuild() {
        AccountBuilderAdapter adapter = new AccountBuilderAdapter();

        Account accountMock = mock(Account.class);
        when(accountBuilderMock.build()).thenReturn(accountMock);

        AccountPort result = adapter.build();

        assertTrue(result instanceof AccountAdapter);
        verify(accountBuilderMock).build();
    }

}

