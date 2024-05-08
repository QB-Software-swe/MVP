package it.qbsoftware.adapters.in.jmaplib.method.response.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.ports.in.jmap.entity.AbstractIdentifiableEntityPort;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.entity.AbstractIdentifiableEntity;
import rs.ltt.jmap.common.entity.Mailbox;
import rs.ltt.jmap.common.method.response.mailbox.SetMailboxMethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SetMailboxMethodResponseAdapterTest {
    @Mock private SetMailboxMethodResponse setMailboxMethodResponse;

    @InjectMocks private SetMailboxMethodResponseAdapter setMailboxMethodResponseAdapter;

    @Test
    public void testAdaptee() {
        SetMailboxMethodResponseAdapter setMailboxMethodResponseAdapter =
                new SetMailboxMethodResponseAdapter(setMailboxMethodResponse);

        assertEquals(setMailboxMethodResponse, setMailboxMethodResponseAdapter.adaptee());
    }

    @Test
    public void testGetCreated() {
        Map<String, AbstractIdentifiableEntity> getCreatedMap = new HashMap<>();
        getCreatedMap.put("key1", mock(AbstractIdentifiableEntity.class));

        Mailbox mailbox = mock(Mailbox.class);
        Map<String, Mailbox> mailboxs = new HashMap<>();
        mailboxs.put("key1", mailbox);

        when(setMailboxMethodResponse.getCreated()).thenReturn(mailboxs);

        Map<String, AbstractIdentifiableEntityPort> result =
                setMailboxMethodResponseAdapter.getCreated();
        assertEquals(getCreatedMap.size(), result.size());
    }
}
