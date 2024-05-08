package it.qbsoftware.adapters.in.jmaplib.method.response.changes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.method.response.identity.ChangesIdentityMethodResponse;

public class ChangesIdentityMethodResponseAdapterTest {
    @Mock private ChangesIdentityMethodResponse changesIdentityMethodResponse;

    @InjectMocks private ChangesIdentityMethodResponseAdapter changesIdentityMethodResponseAdapter;

    @Test
    public void testAdaptee() {
        ChangesIdentityMethodResponseAdapter changesIdentityMethodResponseAdapter =
                new ChangesIdentityMethodResponseAdapter(changesIdentityMethodResponse);

        assertEquals(changesIdentityMethodResponse, changesIdentityMethodResponseAdapter.adaptee());
    }
}
