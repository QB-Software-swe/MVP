package it.qbsoftware.adapters.in.jmaplib.entity;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ch.qos.logback.core.filter.Filter;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.entity.filter.EmailFilterCondition;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class JmapFilterAdapterTest {

    @Mock private Filter<Email> filter;

    @InjectMocks private JmapFilterAdapter<Email> jmapFilterAdapter;

    @Test
    public void testApply() {
        @SuppressWarnings("unchecked")
        Stream<EmailPort> emails = mock(Stream.class);

        EmailFilterCondition emailFilterCondition = mock(EmailFilterCondition.class);
        String inbox = "inbox";

        when(emailFilterCondition.getInMailbox()).thenReturn(inbox);
        String inMailbox = emailFilterCondition.getInMailbox();
        assertNotNull(inMailbox);

        Stream<EmailPort> result = jmapFilterAdapter.apply(emails);
        assertNotNull(result);
    }
}
