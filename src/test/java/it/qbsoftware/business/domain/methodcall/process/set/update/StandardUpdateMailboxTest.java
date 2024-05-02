package it.qbsoftware.business.domain.methodcall.process.set.update;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetMailboxMethodCallPort;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class StandardUpdateMailboxTest {
    @Mock
    SetMailboxMethodCallPort setMailboxMethodCallPort;

    @InjectMocks
    private StandardUpdateMailbox standardUpdateMailbox;

    @Test
    public void testUpdate() {
        UpdatedResult<MailboxPort> result = standardUpdateMailbox.update(setMailboxMethodCallPort);

        assertTrue(result.updated().isEmpty());
        assertTrue(result.notUpdated().isEmpty());
        assertNotNull(result);
    }

}
