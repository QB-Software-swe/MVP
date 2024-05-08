package it.qbsoftware.business.services.set;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.methodcall.process.set.update.UpdateEmail;
import it.qbsoftware.business.domain.methodcall.process.set.update.UpdatedResult;
import it.qbsoftware.business.domain.methodresponse.SetEmailSubmissionMethodResponse;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailSubmissionPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailSubmissionMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponsePort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailSubmissionMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailSubmissionMethodResponsePort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SetEmailSubmissionMethodCallServiceTest {

    @Mock AccountStateRepository accountStateRepository;

    @Mock EmailSubmissionBuilderPort emailSubmissionBuilderPort;

    @Mock SetEmailMethodResponseBuilderPort setEmailMethodResponseBuilderPort;

    @Mock SetEmailSubmissionMethodResponseBuilderPort setEmailSubmissionMethodResponseBuilderPort;

    @Mock UpdateEmail updateEmail;

    @Mock UpdatedResult<EmailPort> updatedResult;

    @Mock private SetEmailSubmissionMethodResponsePort setEmailSubmissionMethodResponsePort;

    @Mock private SetEmailSubmissionMethodCallPort setEmailSubmissionMethodCallPort;

    @Mock private SetEmailMethodResponsePort setEmailMethodResponsePort;

    @Mock private EmailSubmissionPort emailSubmissionPort;

    @Mock private ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock AccountState accountState;

    @InjectMocks SetEmailSubmissionMethodCallService setEmailSubmissionMethodCallService;

    @Test
    public void testCall() throws Exception {
        String accountId = "accountId";
        Map<String, EmailSubmissionPort> createMap = new HashMap<>();
        createMap.put("key", emailSubmissionPort);

        when(setEmailSubmissionMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState, accountState);
        when(updateEmail.update(any(), any(), any())).thenReturn(updatedResult);
        when(setEmailSubmissionMethodResponseBuilderPort.reset())
                .thenReturn(setEmailSubmissionMethodResponseBuilderPort);
        when(setEmailSubmissionMethodResponseBuilderPort.oldState(any()))
                .thenReturn(setEmailSubmissionMethodResponseBuilderPort);
        when(setEmailSubmissionMethodResponseBuilderPort.newState(any()))
                .thenReturn(setEmailSubmissionMethodResponseBuilderPort);
        when(setEmailSubmissionMethodResponseBuilderPort.created(any()))
                .thenReturn(setEmailSubmissionMethodResponseBuilderPort);
        when(setEmailSubmissionMethodResponseBuilderPort.build())
                .thenReturn(setEmailSubmissionMethodResponsePort);
        when(setEmailSubmissionMethodCallPort.getCreate()).thenReturn(createMap);

        when(setEmailMethodResponseBuilderPort.reset())
                .thenReturn(setEmailMethodResponseBuilderPort);
        when(setEmailMethodResponseBuilderPort.accountId(accountId))
                .thenReturn(setEmailMethodResponseBuilderPort);
        when(setEmailMethodResponseBuilderPort.oldState(any()))
                .thenReturn(setEmailMethodResponseBuilderPort);
        when(setEmailMethodResponseBuilderPort.newState(any()))
                .thenReturn(setEmailMethodResponseBuilderPort);
        when(setEmailMethodResponseBuilderPort.updated(any()))
                .thenReturn(setEmailMethodResponseBuilderPort);
        when(setEmailMethodResponseBuilderPort.build()).thenReturn(setEmailMethodResponsePort);

        when(emailSubmissionBuilderPort.reset()).thenReturn(emailSubmissionBuilderPort);
        when(emailSubmissionBuilderPort.id(any())).thenReturn(emailSubmissionBuilderPort);
        when(emailSubmissionBuilderPort.build()).thenReturn(emailSubmissionPort);

        SetEmailSubmissionMethodResponse result =
                setEmailSubmissionMethodCallService.call(
                        setEmailSubmissionMethodCallPort, previousResponses);

        verify(setEmailSubmissionMethodResponseBuilderPort).reset();
        verify(setEmailSubmissionMethodResponseBuilderPort).oldState(accountState.state());
        verify(setEmailSubmissionMethodResponseBuilderPort).newState(accountState.state());

        assertTrue(result instanceof SetEmailSubmissionMethodResponse);
    }
}
