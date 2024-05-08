package it.qbsoftware.adapters.in.jmaplib.method.response.get;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.entity.IdentityAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetIdentityMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetIdentityMethodResponsePort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import rs.ltt.jmap.common.method.response.identity.GetIdentityMethodResponse;
import rs.ltt.jmap.common.method.response.identity.GetIdentityMethodResponse.GetIdentityMethodResponseBuilder;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GetIdentityMethodResponseBuilderAdapterTest {

    @Mock private GetIdentityMethodResponseBuilder getIdentityMethodResponseBuilder;

    @InjectMocks
    private GetIdentityMethodResponseBuilderAdapter getIdentityMethodResponseBuilderAdapter;

    @Test
    public void testBuild() {
        GetIdentityMethodResponsePort result = getIdentityMethodResponseBuilderAdapter.build();

        assertNotNull(result);
    }

    @Test
    public void testList() {
        IdentityPort identityPort = mock(IdentityAdapter.class);
        IdentityPort[] identityList = new IdentityPort[] {identityPort};

        getIdentityMethodResponseBuilderAdapter.list(identityList);

        verify(getIdentityMethodResponseBuilder).list(any());
    }

    @Test
    public void testNotFound() {
        String[] notFoundString = {"n1", "n2"};
        when(getIdentityMethodResponseBuilder.notFound(notFoundString))
                .thenReturn(getIdentityMethodResponseBuilder);

        GetIdentityMethodResponseBuilderPort result =
                getIdentityMethodResponseBuilderAdapter.notFound(notFoundString);
        assertTrue(result instanceof GetIdentityMethodResponseBuilderAdapter);
        verify(getIdentityMethodResponseBuilder).notFound(notFoundString);
        assertNotNull(result);
    }

    @Test
    public void testReset() {

        try (MockedStatic<GetIdentityMethodResponse> getIdentityMethodResponseStatic =
                Mockito.mockStatic(GetIdentityMethodResponse.class)) {
            assertEquals(
                    getIdentityMethodResponseBuilderAdapter.reset(),
                    getIdentityMethodResponseBuilderAdapter);
            getIdentityMethodResponseStatic.verify(GetIdentityMethodResponse::builder);
        }
    }

    @Test
    public void testState() {

        when(getIdentityMethodResponseBuilder.state("stato"))
                .thenReturn(getIdentityMethodResponseBuilder);

        GetIdentityMethodResponseBuilderPort result =
                getIdentityMethodResponseBuilderAdapter.state("stato");
        assertTrue(result instanceof GetIdentityMethodResponseBuilderAdapter);
        verify(getIdentityMethodResponseBuilder).state("stato");
        assertNotNull(result);
    }
}
