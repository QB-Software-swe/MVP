package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.*;

import it.qbsoftware.business.ports.in.jmap.entity.AccountPort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import rs.ltt.jmap.common.entity.Account.AccountBuilder;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class AccountBuilderAdapterTest {

    @Mock private AccountBuilder accountBuilderMock;

    @Test
    public void testBuild() {
        AccountBuilderAdapter adapter = new AccountBuilderAdapter();

        AccountPort result = adapter.build();

        assertTrue(result instanceof AccountAdapter);
        assertNotNull(result);
    }
}
