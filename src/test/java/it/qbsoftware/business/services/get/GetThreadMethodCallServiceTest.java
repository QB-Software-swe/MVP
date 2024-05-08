package it.qbsoftware.business.services.get;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.entity.ThreadAdapter;
import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.domain.methodcall.filter.ThreadPropertiesFilter;
import it.qbsoftware.business.domain.methodcall.process.get.ReferenceIdsResolver;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;
import it.qbsoftware.business.ports.in.jmap.method.call.get.GetThreadMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetThreadMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetThreadMethodResponsePort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.ThreadRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GetThreadMethodCallServiceTest {

    @Mock private AccountStateRepository accountStateRepository;

    @Mock private ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @Mock private ThreadRepository threadRepository;

    @Mock private GetThreadMethodCallPort getThreadMethodCallPort;

    @Mock private ReferenceIdsResolver getReferenceIdsResolver;

    @Mock private ThreadPropertiesFilter threadPropertiesFilter;

    @Mock private GetThreadMethodResponsePort getThreadMethodResponsePort;

    @Mock private GetThreadMethodResponseBuilderPort getThreadMethodResponseBuilderPort;

    @InjectMocks private GetThreadMethodCallService getThreadMethodCallService;

    @Test
    public void testCallWithRetrive()
            throws AccountNotFoundException,
                    InvalidResultReferenceExecption,
                    InvalidArgumentsException {

        String accountId = "testAccountId";
        String[] threadIds = new String[] {"threadId1", "threadId2"};
        AccountState accountState = new AccountState(accountId);
        ThreadPort[] threads = new ThreadPort[] {new ThreadAdapter(null), new ThreadAdapter(null)};

        when(getReferenceIdsResolver.resolve(any(), any())).thenReturn(threadIds);
        when(threadRepository.retrive(any()))
                .thenReturn(new RetrivedEntity<>(threads, new String[] {}));
        when(getThreadMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(threadPropertiesFilter.filter(any(), any())).thenReturn(threads);
        when(getThreadMethodResponseBuilderPort.reset())
                .thenReturn(getThreadMethodResponseBuilderPort);

        when(getThreadMethodResponseBuilderPort.list(any()))
                .thenReturn(getThreadMethodResponseBuilderPort);

        when(getThreadMethodResponseBuilderPort.notFound(any()))
                .thenReturn(getThreadMethodResponseBuilderPort);

        when(getThreadMethodResponseBuilderPort.state(any()))
                .thenReturn(getThreadMethodResponseBuilderPort);
        when(getThreadMethodResponseBuilderPort.build()).thenReturn(getThreadMethodResponsePort);

        GetThreadMethodResponsePort result =
                getThreadMethodCallService.call(getThreadMethodCallPort, previousResponses);

        verify(getThreadMethodResponseBuilderPort).reset();
        verify(getThreadMethodResponseBuilderPort).list(threads);
        verify(getThreadMethodResponseBuilderPort).notFound(any());
        verify(getThreadMethodResponseBuilderPort).state(accountState.state());
        verify(getThreadMethodResponseBuilderPort).build();
        verify(threadRepository).retrive(threadIds);
        assertEquals(result, getThreadMethodResponsePort);
    }

    @Test
    public void testCallWithRetriveAll()
            throws AccountNotFoundException,
                    InvalidResultReferenceExecption,
                    InvalidArgumentsException {
        String accountId = "testAccountId";
        String[] threadIds = null;
        AccountState accountState = new AccountState(accountId);
        ThreadPort[] threads = new ThreadPort[] {new ThreadAdapter(null), new ThreadAdapter(null)};

        when(getReferenceIdsResolver.resolve(any(), any())).thenReturn(threadIds);
        when(threadRepository.retriveAll(any()))
                .thenReturn(new RetrivedEntity<>(threads, new String[] {}));
        when(getThreadMethodCallPort.accountId()).thenReturn(accountId);
        when(accountStateRepository.retrive(accountId)).thenReturn(accountState);
        when(threadPropertiesFilter.filter(any(), any())).thenReturn(threads);
        when(getThreadMethodResponseBuilderPort.reset())
                .thenReturn(getThreadMethodResponseBuilderPort);

        when(getThreadMethodResponseBuilderPort.list(any()))
                .thenReturn(getThreadMethodResponseBuilderPort);

        when(getThreadMethodResponseBuilderPort.notFound(any()))
                .thenReturn(getThreadMethodResponseBuilderPort);

        when(getThreadMethodResponseBuilderPort.state(any()))
                .thenReturn(getThreadMethodResponseBuilderPort);
        when(getThreadMethodResponseBuilderPort.build()).thenReturn(getThreadMethodResponsePort);

        GetThreadMethodResponsePort result =
                getThreadMethodCallService.call(getThreadMethodCallPort, previousResponses);

        verify(getThreadMethodResponseBuilderPort).reset();
        verify(getThreadMethodResponseBuilderPort).list(threads);
        verify(getThreadMethodResponseBuilderPort).notFound(any());
        verify(getThreadMethodResponseBuilderPort).state(accountState.state());
        verify(getThreadMethodResponseBuilderPort).build();
        verify(threadRepository).retriveAll(accountId);
        assertEquals(result, getThreadMethodResponsePort);
    }
}
