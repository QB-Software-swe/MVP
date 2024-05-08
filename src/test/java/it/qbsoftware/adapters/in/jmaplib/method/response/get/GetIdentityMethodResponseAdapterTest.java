package it.qbsoftware.adapters.in.jmaplib.method.response.get;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.response.identity.GetIdentityMethodResponse;

public class GetIdentityMethodResponseAdapterTest {

    @Mock private GetIdentityMethodResponse getIdentityMethodResponse;

    @InjectMocks private GetIdentityMethodResponseAdapter getIdentityMethodResponseAdapter;

    @Test
    public void testAdaptee() {
        GetIdentityMethodResponseAdapter getIdentityMethodResponseAdapter =
                new GetIdentityMethodResponseAdapter(getIdentityMethodResponse);

        assertEquals(getIdentityMethodResponse, getIdentityMethodResponseAdapter.adaptee());
    }
}
