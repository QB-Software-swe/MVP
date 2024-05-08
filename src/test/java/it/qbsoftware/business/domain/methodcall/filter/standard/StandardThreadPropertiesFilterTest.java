package it.qbsoftware.business.domain.methodcall.filter.standard;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class StandardThreadPropertiesFilterTest {

    @Mock private ThreadBuilderPort threadBuilderPort;

    @Mock private ThreadPort threadPort;

    @InjectMocks private StandardThreadPropertiesFilter standardThreadPropertiesFilter;

    @Test
    public void testFilterWithNullProperties() throws InvalidArgumentsException {
        ThreadPort[] threadPorts = new ThreadPort[] {threadPort};

        ThreadPort[] result = standardThreadPropertiesFilter.filter(threadPorts, null);

        assertArrayEquals(threadPorts, result);
    }

    @Test
    public void testFilterWithValidProperties() throws InvalidArgumentsException {
        ThreadPort[] threadPorts = new ThreadPort[] {threadPort};
        String[] properties = new String[] {"emailIds", "id"};
        List<String> emailIds = new ArrayList<>();

        when(threadPort.getId()).thenReturn("id");
        when(threadPort.getEmailIds()).thenReturn(emailIds);
        when(threadPort.toBuilder()).thenReturn(threadBuilderPort);

        when(threadBuilderPort.id(any())).thenReturn(threadBuilderPort);
        when(threadBuilderPort.emailIds(any())).thenReturn(threadBuilderPort);
        when(threadBuilderPort.build()).thenReturn(threadPort);
        when(threadBuilderPort.reset()).thenReturn(threadBuilderPort);

        ThreadPort[] result = standardThreadPropertiesFilter.filter(threadPorts, properties);

        assertArrayEquals(threadPorts, result);
    }

    @Test
    public void testFilterWithInvalidArgumentsException() throws InvalidArgumentsException {
        ThreadPort[] threadPorts = new ThreadPort[] {threadPort};
        String[] properties = new String[] {"emailIds", "invalidProperties"};
        List<String> emailIds = new ArrayList<>();

        when(threadPort.getId()).thenReturn("id");
        when(threadPort.getEmailIds()).thenReturn(emailIds);
        when(threadPort.toBuilder()).thenReturn(threadBuilderPort);

        when(threadBuilderPort.id(any())).thenReturn(threadBuilderPort);
        when(threadBuilderPort.emailIds(any())).thenReturn(threadBuilderPort);
        when(threadBuilderPort.reset()).thenReturn(threadBuilderPort);

        assertThrows(
                InvalidArgumentsException.class,
                () -> standardThreadPropertiesFilter.filter(threadPorts, properties));
    }
}
