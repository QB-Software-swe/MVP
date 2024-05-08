package it.qbsoftware.adapters.in.jmaplib.method.response.get;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.entity.EmailSubmissionAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailSubmissionMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetEmailSubmissionMethodResponsePort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import rs.ltt.jmap.common.method.response.submission.GetEmailSubmissionMethodResponse;
import rs.ltt.jmap.common.method.response.submission.GetEmailSubmissionMethodResponse.GetEmailSubmissionMethodResponseBuilder;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GetEmailSubmissionMethodResponseBuilderAdapterTest {

    @Mock private GetEmailSubmissionMethodResponseBuilder getEmailSubmissionMethodResponseBuilder;

    @InjectMocks
    private GetEmailSubmissionMethodResponseBuilderAdapter
            getEmailSubmissionMethodResponseBuilderAdapter;

    @Test
    public void testBuild() {
        GetEmailSubmissionMethodResponsePort result =
                getEmailSubmissionMethodResponseBuilderAdapter.build();

        assertNotNull(result);
    }

    @Test
    public void testList() {
        EmailSubmissionPort emailsubmissionPort = mock(EmailSubmissionAdapter.class);
        EmailSubmissionPort[] emailsubmissionList = new EmailSubmissionPort[] {emailsubmissionPort};

        getEmailSubmissionMethodResponseBuilderAdapter.list(emailsubmissionList);

        verify(getEmailSubmissionMethodResponseBuilder).list(any());
    }

    @Test
    public void testListNull() {
        getEmailSubmissionMethodResponseBuilderAdapter.list(null);

        verify(getEmailSubmissionMethodResponseBuilder).list(null);
    }

    @Test
    public void testNotFound() {
        String[] notFoundString = {"n1", "n2"};
        when(getEmailSubmissionMethodResponseBuilder.notFound(notFoundString))
                .thenReturn(getEmailSubmissionMethodResponseBuilder);

        GetEmailSubmissionMethodResponseBuilderPort result =
                getEmailSubmissionMethodResponseBuilderAdapter.notFound(notFoundString);
        assertTrue(result instanceof GetEmailSubmissionMethodResponseBuilderAdapter);
        verify(getEmailSubmissionMethodResponseBuilder).notFound(notFoundString);
        assertNotNull(result);
    }

    @Test
    public void testReset() {

        try (MockedStatic<GetEmailSubmissionMethodResponse> getEmailSubmissionMethodResponseStatic =
                Mockito.mockStatic(GetEmailSubmissionMethodResponse.class)) {
            assertEquals(
                    getEmailSubmissionMethodResponseBuilderAdapter.reset(),
                    getEmailSubmissionMethodResponseBuilderAdapter);
            getEmailSubmissionMethodResponseStatic.verify(
                    GetEmailSubmissionMethodResponse::builder);
        }
    }

    @Test
    public void testState() {

        when(getEmailSubmissionMethodResponseBuilder.state("stato"))
                .thenReturn(getEmailSubmissionMethodResponseBuilder);

        GetEmailSubmissionMethodResponseBuilderPort result =
                getEmailSubmissionMethodResponseBuilderAdapter.state("stato");
        assertTrue(result instanceof GetEmailSubmissionMethodResponseBuilderAdapter);
        verify(getEmailSubmissionMethodResponseBuilder).state("stato");
        assertNotNull(result);
    }
}
