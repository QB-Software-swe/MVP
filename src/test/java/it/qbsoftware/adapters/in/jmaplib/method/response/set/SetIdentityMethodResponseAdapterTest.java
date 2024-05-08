package it.qbsoftware.adapters.in.jmaplib.method.response.set;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.response.identity.SetIdentityMethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SetIdentityMethodResponseAdapterTest {
    @Mock private SetIdentityMethodResponse setIdentityMethodResponse;

    @InjectMocks private SetIdentityMethodResponseAdapter setIdentityMethodResponseAdapter;

    @Test
    public void testAdaptee() {
        SetIdentityMethodResponseAdapter setIdentityMethodResponseAdapter =
                new SetIdentityMethodResponseAdapter(setIdentityMethodResponse);

        assertEquals(setIdentityMethodResponse, setIdentityMethodResponseAdapter.adaptee());
    }
}
