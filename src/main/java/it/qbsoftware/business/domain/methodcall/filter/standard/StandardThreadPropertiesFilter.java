package it.qbsoftware.business.domain.methodcall.filter.standard;

import java.util.ArrayList;
import java.util.List;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.methodcall.filter.ThreadPropertiesFilter;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;

public class StandardThreadPropertiesFilter implements ThreadPropertiesFilter {
    final ThreadBuilderPort threadBuilderPort;

    public StandardThreadPropertiesFilter(final ThreadBuilderPort threadBuilderPort) {
        this.threadBuilderPort = threadBuilderPort;
    }

    @Override
    public ThreadPort[] filter(final ThreadPort[] threadPorts, final String[] properties)
            throws InvalidArgumentsException {
        if (properties == null) {
            return threadPorts;
        }

        final List<ThreadPort> filtredMailboxes = new ArrayList<ThreadPort>();
        for (final ThreadPort threadPort : threadPorts) {
            filtredMailboxes.add(threadFilter(threadPort, properties));
        }

        return filtredMailboxes.toArray(ThreadPort[]::new);
    }

    private ThreadPort threadFilter(final ThreadPort threadPort, final String[] properties) {
        return threadPort; // FIXME: implementarlo (molto veloce e facile da fare)
    }
}
