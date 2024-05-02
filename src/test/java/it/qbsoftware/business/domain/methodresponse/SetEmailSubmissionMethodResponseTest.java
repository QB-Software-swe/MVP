package it.qbsoftware.business.domain.methodresponse;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailSubmissionMethodResponsePort;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SetEmailSubmissionMethodResponseTest {
    @Mock
    SetEmailSubmissionMethodResponsePort setEmailSubmissionMethodResponsePort;

    @InjectMocks
    private SetEmailSubmissionMethodResponse setEmailSubmissionMethodResponse;

    @Test
    public void testSetEmailSubmissionMethodResponse() {

        assertEquals(setEmailSubmissionMethodResponsePort, setEmailSubmissionMethodResponse.setEmailSubmissionMethodResponsePort());

    }
}
