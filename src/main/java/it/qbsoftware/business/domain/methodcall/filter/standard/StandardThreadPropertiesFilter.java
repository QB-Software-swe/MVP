package it.qbsoftware.business.domain.methodcall.filter.standard;

import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.methodcall.filter.ThreadPropertiesFilter;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.ThreadPort;
import java.util.ArrayList;
import java.util.List;

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
            filtredMailboxes.add(filterThread(threadPort, properties));
        }

        return filtredMailboxes.toArray(ThreadPort[]::new);
    }

    private ThreadPort filterThread(final ThreadPort threadPort, final String[] properties)
            throws InvalidArgumentsException {
        final ThreadBuilderPort threadBuilderPort =
                threadPort.toBuilder().reset().id(threadPort.getId());

        for (final String property : properties) {
            switch (property) {
                case "emailIds":
                    threadBuilderPort.emailIds(threadPort.getEmailIds());
                    break;

                case "id":
                    break;

                default:
                    throw new InvalidArgumentsException();
            }
            ;
        }

        return threadBuilderPort.build();
    }
}
