package it.qbsoftware.adapters.in.jmaplib.method.response.get;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.adapters.in.jmaplib.entity.MailboxAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.get.GetMailboxMethodResponsePort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import rs.ltt.jmap.common.method.response.mailbox.GetMailboxMethodResponse;
import rs.ltt.jmap.common.method.response.mailbox.GetMailboxMethodResponse.GetMailboxMethodResponseBuilder;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class GetMailboxMethodResponseBuilderAdapterTest {

    @Mock private GetMailboxMethodResponseBuilder getMailboxMethodResponseBuilder;

    @InjectMocks
    private GetMailboxMethodResponseBuilderAdapter getMailboxMethodResponseBuilderAdapter;

    @Test
    public void testBuild() {
        GetMailboxMethodResponsePort result = getMailboxMethodResponseBuilderAdapter.build();

        assertNotNull(result);
    }

    @Test
    public void testList() {
        MailboxPort mailboxPort = mock(MailboxAdapter.class);
        MailboxPort[] mailboxList = new MailboxPort[] {mailboxPort};

        getMailboxMethodResponseBuilderAdapter.list(mailboxList);

        verify(getMailboxMethodResponseBuilder).list(any());
    }

    @Test
    public void testListNull() {
        getMailboxMethodResponseBuilderAdapter.list(null);

        verify(getMailboxMethodResponseBuilder).list(null);
    }

    @Test
    public void testNotFound() {
        String[] notFoundString = {"n1", "n2"};
        when(getMailboxMethodResponseBuilder.notFound(notFoundString))
                .thenReturn(getMailboxMethodResponseBuilder);

        GetMailboxMethodResponseBuilderPort result =
                getMailboxMethodResponseBuilderAdapter.notFound(notFoundString);
        assertTrue(result instanceof GetMailboxMethodResponseBuilderAdapter);
        verify(getMailboxMethodResponseBuilder).notFound(notFoundString);
        assertNotNull(result);
    }

    @Test
    public void testReset() {

        try (MockedStatic<GetMailboxMethodResponse> getMailboxMethodResponseStatic =
                Mockito.mockStatic(GetMailboxMethodResponse.class)) {
            assertEquals(
                    getMailboxMethodResponseBuilderAdapter.reset(),
                    getMailboxMethodResponseBuilderAdapter);
            getMailboxMethodResponseStatic.verify(GetMailboxMethodResponse::builder);
        }
    }

    @Test
    public void testState() {

        when(getMailboxMethodResponseBuilder.state("stato"))
                .thenReturn(getMailboxMethodResponseBuilder);

        GetMailboxMethodResponseBuilderPort result =
                getMailboxMethodResponseBuilderAdapter.state("stato");
        assertTrue(result instanceof GetMailboxMethodResponseBuilderAdapter);
        verify(getMailboxMethodResponseBuilder).state("stato");
        assertNotNull(result);
    }
}
