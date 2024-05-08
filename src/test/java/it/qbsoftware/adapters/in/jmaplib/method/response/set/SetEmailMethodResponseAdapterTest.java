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
import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.method.response.email.SetEmailMethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SetEmailMethodResponseAdapterTest {
    @Mock private SetEmailMethodResponse setEmailMethodResponse;

    @InjectMocks private SetEmailMethodResponseAdapter setEmailMethodResponseAdapter;

    @Test
    public void testAdaptee() {
        SetEmailMethodResponseAdapter setEmailMethodResponseAdapter =
                new SetEmailMethodResponseAdapter(setEmailMethodResponse);

        assertEquals(setEmailMethodResponse, setEmailMethodResponseAdapter.adaptee());
    }

    @Test
    public void testGetCreated() {
        Map<String, AbstractIdentifiableEntity> getCreatedMap = new HashMap<>();
        getCreatedMap.put("key1", mock(AbstractIdentifiableEntity.class));

        Email email = mock(Email.class);
        Map<String, Email> emails = new HashMap<>();
        emails.put("key1", email);

        when(setEmailMethodResponse.getCreated()).thenReturn(emails);

        Map<String, AbstractIdentifiableEntityPort> result =
                setEmailMethodResponseAdapter.getCreated();
        assertEquals(getCreatedMap.size(), result.size());
    }
}
