package it.qbsoftware.adapters.in.jmaplib.method.response.get;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.entity.EmailAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailMethodResponsePort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import rs.ltt.jmap.common.method.response.email.GetEmailMethodResponse;
import rs.ltt.jmap.common.method.response.email.GetEmailMethodResponse.GetEmailMethodResponseBuilder;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GetEmailMethodResponseBuilderAdapterTest {

    @Mock private GetEmailMethodResponseBuilder getEmailMethodResponseBuilder;

    @InjectMocks private GetEmailMethodResponseBuilderAdapter getEmailMethodResponseBuilderAdapter;

    @Test
    public void testBuild() {
        GetEmailMethodResponsePort result = getEmailMethodResponseBuilderAdapter.build();

        assertNotNull(result);
    }

    @Test
    public void testList() {
        EmailPort emailPort = mock(EmailAdapter.class);
        EmailPort[] emailList = new EmailPort[] {emailPort};

        getEmailMethodResponseBuilderAdapter.list(emailList);

        verify(getEmailMethodResponseBuilder).list(any());
    }

    @Test
    public void testListNull() {
        getEmailMethodResponseBuilderAdapter.list(null);

        verify(getEmailMethodResponseBuilder).list(null);
    }

    @Test
    public void testNotFound() {
        String[] notFoundString = {"n1", "n2"};
        when(getEmailMethodResponseBuilder.notFound(notFoundString))
                .thenReturn(getEmailMethodResponseBuilder);

        GetEmailMethodResponseBuilderPort result =
                getEmailMethodResponseBuilderAdapter.notFound(notFoundString);
        assertTrue(result instanceof GetEmailMethodResponseBuilderAdapter);
        verify(getEmailMethodResponseBuilder).notFound(notFoundString);
        assertNotNull(result);
    }

    @Test
    public void testReset() {

        try (MockedStatic<GetEmailMethodResponse> getEmailMethodResponseStatic =
                Mockito.mockStatic(GetEmailMethodResponse.class)) {
            assertEquals(
                    getEmailMethodResponseBuilderAdapter.reset(),
                    getEmailMethodResponseBuilderAdapter);
            getEmailMethodResponseStatic.verify(GetEmailMethodResponse::builder);
        }
    }

    @Test
    public void testState() {

        when(getEmailMethodResponseBuilder.state("stato"))
                .thenReturn(getEmailMethodResponseBuilder);

        GetEmailMethodResponseBuilderPort result =
                getEmailMethodResponseBuilderAdapter.state("stato");
        assertTrue(result instanceof GetEmailMethodResponseBuilderAdapter);
        verify(getEmailMethodResponseBuilder).state("stato");
        assertNotNull(result);
    }
}
