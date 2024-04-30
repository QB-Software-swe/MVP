package it.qbsoftware.business.services.query;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.util.get.RetrivedEntity;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.JmapFilterPort;
import it.qbsoftware.business.ports.in.jmap.method.call.query.QueryEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.query.QueryEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.query.QueryEmailMethodResponsePort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class QueryEmailMethodCallServiceTest {

    @Mock
    AccountStateRepository accountStateRepository;

    @Mock
    EmailRepository emailRepository;

    @Mock
    QueryEmailMethodResponseBuilderPort queryEmailMethodResponseBuilderPort;

    @Mock
    QueryEmailMethodCallPort queryEmailMethodCallPort;

    @Mock
    AccountState accountState;

    @Mock
    JmapFilterPort<EmailPort> queryFilter;

    @Mock
    EmailPort emailPort;

    @Mock
    QueryEmailMethodResponsePort queryEmailMethodResponsePort;

    @InjectMocks
    QueryEmailMethodCallService queryEmailMethodCallService;

    @Test
    public void testCallWithAnchorNotNullAndPresentInIds() throws Exception{
        EmailPort[] emails = new EmailPort[] {emailPort};

        when(queryEmailMethodCallPort.getAccountId()).thenReturn("accountId");
        when(queryEmailMethodCallPort.getFilter()).thenReturn(queryFilter);
        when(queryEmailMethodCallPort.getCollapseThreads()).thenReturn(true);
        when(queryEmailMethodCallPort.getAnchor()).thenReturn("id");
        when(queryEmailMethodCallPort.getLimit()).thenReturn(40L);
        when(queryEmailMethodCallPort.getCalculateTotal()).thenReturn(true);
        when(accountStateRepository.retrive(anyString())).thenReturn(accountState);
        when(emailRepository.retriveAll(anyString())).thenReturn(new RetrivedEntity<>(emails, new String[] {}));
        when(queryFilter.apply(any())).thenReturn(Stream.of(emailPort));
        when(emailPort.getThreadId()).thenReturn("threadId");
        when(emailPort.getId()).thenReturn("id");
        when(accountState.state()).thenReturn("state");
        when(queryEmailMethodResponseBuilderPort.reset()).thenReturn(queryEmailMethodResponseBuilderPort);
        when(queryEmailMethodResponseBuilderPort.canCalculateChanges(anyBoolean())).thenReturn(queryEmailMethodResponseBuilderPort);
        when(queryEmailMethodResponseBuilderPort.queryState(anyString())).thenReturn(queryEmailMethodResponseBuilderPort);
        when(queryEmailMethodResponseBuilderPort.total(anyLong())).thenReturn(queryEmailMethodResponseBuilderPort);
        when(queryEmailMethodResponseBuilderPort.ids(any())).thenReturn(queryEmailMethodResponseBuilderPort);
        when(queryEmailMethodResponseBuilderPort.position(anyLong())).thenReturn(queryEmailMethodResponseBuilderPort);
        when(queryEmailMethodResponseBuilderPort.build()).thenReturn(queryEmailMethodResponsePort);

        QueryEmailMethodResponsePort result = queryEmailMethodCallService.call(queryEmailMethodCallPort, null);
        assertNotNull(result);
    }

    @Test
    public void testCallWithAnchorsetNull() throws Exception{
        EmailPort[] emails = new EmailPort[] {emailPort};

        when(queryEmailMethodCallPort.getAccountId()).thenReturn("accountId");
        when(queryEmailMethodCallPort.getFilter()).thenReturn(queryFilter);
        when(queryEmailMethodCallPort.getCollapseThreads()).thenReturn(true);
        when(queryEmailMethodCallPort.getAnchor()).thenReturn("id");
        when(queryEmailMethodCallPort.getLimit()).thenReturn(40L);
        when(queryEmailMethodCallPort.getCalculateTotal()).thenReturn(true);
        when(accountStateRepository.retrive(anyString())).thenReturn(accountState);
        when(emailRepository.retriveAll(anyString())).thenReturn(new RetrivedEntity<>(emails, new String[] {}));
        when(queryFilter.apply(any())).thenReturn(Stream.of(emailPort));
        when(emailPort.getThreadId()).thenReturn("threadId");
        when(emailPort.getId()).thenReturn("id");
        when(accountState.state()).thenReturn("state");
        when(queryEmailMethodResponseBuilderPort.reset()).thenReturn(queryEmailMethodResponseBuilderPort);
        when(queryEmailMethodResponseBuilderPort.canCalculateChanges(anyBoolean())).thenReturn(queryEmailMethodResponseBuilderPort);
        when(queryEmailMethodResponseBuilderPort.queryState(anyString())).thenReturn(queryEmailMethodResponseBuilderPort);
        when(queryEmailMethodResponseBuilderPort.total(any())).thenReturn(queryEmailMethodResponseBuilderPort);
        when(queryEmailMethodResponseBuilderPort.ids(any())).thenReturn(queryEmailMethodResponseBuilderPort);
        when(queryEmailMethodResponseBuilderPort.position(anyLong())).thenReturn(queryEmailMethodResponseBuilderPort);
        when(queryEmailMethodResponseBuilderPort.build()).thenReturn(queryEmailMethodResponsePort);
        when(queryEmailMethodCallPort.getAnchorOffset()).thenReturn(null);
        when(queryEmailMethodCallPort.getCalculateTotal()).thenReturn(false);

        QueryEmailMethodResponsePort result = queryEmailMethodCallService.call(queryEmailMethodCallPort, null);
        assertNotNull(result);
    }

    @Test
    public void testCallWithLimitPositionNull() throws Exception{
        EmailPort[] emails = new EmailPort[] {emailPort};

        when(queryEmailMethodCallPort.getAccountId()).thenReturn("accountId");
        when(queryEmailMethodCallPort.getFilter()).thenReturn(queryFilter);
        when(queryEmailMethodCallPort.getCollapseThreads()).thenReturn(true);
        when(queryEmailMethodCallPort.getAnchor()).thenReturn(null);
        when(queryEmailMethodCallPort.getPosition()).thenReturn(null);
        when(queryEmailMethodCallPort.getLimit()).thenReturn(null);
        when(queryEmailMethodCallPort.getCalculateTotal()).thenReturn(true);
        when(accountStateRepository.retrive(anyString())).thenReturn(accountState);
        when(emailRepository.retriveAll(anyString())).thenReturn(new RetrivedEntity<>(emails, new String[] {}));
        when(queryFilter.apply(any())).thenReturn(Stream.of(emailPort));
        when(emailPort.getThreadId()).thenReturn("threadId");
        when(emailPort.getId()).thenReturn("id");
        when(accountState.state()).thenReturn("state");
        when(queryEmailMethodResponseBuilderPort.reset()).thenReturn(queryEmailMethodResponseBuilderPort);
        when(queryEmailMethodResponseBuilderPort.canCalculateChanges(anyBoolean())).thenReturn(queryEmailMethodResponseBuilderPort);
        when(queryEmailMethodResponseBuilderPort.queryState(anyString())).thenReturn(queryEmailMethodResponseBuilderPort);
        when(queryEmailMethodResponseBuilderPort.total(anyLong())).thenReturn(queryEmailMethodResponseBuilderPort);
        when(queryEmailMethodResponseBuilderPort.ids(any())).thenReturn(queryEmailMethodResponseBuilderPort);
        when(queryEmailMethodResponseBuilderPort.position(anyLong())).thenReturn(queryEmailMethodResponseBuilderPort);
        when(queryEmailMethodResponseBuilderPort.build()).thenReturn(queryEmailMethodResponsePort);

        QueryEmailMethodResponsePort result = queryEmailMethodCallService.call(queryEmailMethodCallPort, null);
        assertNotNull(result);

    }


}
