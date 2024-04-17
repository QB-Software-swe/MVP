package it.qbsoftware.business.domain;

import java.util.List;
import java.util.Map;

import it.qbsoftware.business.ports.in.guava.CaseFormatPort;
import it.qbsoftware.business.ports.in.guava.SplitterPort;
import it.qbsoftware.business.ports.in.jmap.entity.RolePort;

public class MailboxPatcher {
    final SplitterPort splitterPort;
    final CaseFormatPort caseFormatPort;
    final RolePort rolePort;

    public MailboxPatcher(final SplitterPort splitterPort, final CaseFormatPort caseFormatPort,
            final RolePort rolePort) {
        this.splitterPort = splitterPort;
        this.caseFormatPort = caseFormatPort;
        this.rolePort = rolePort;
    }

    public MailboxInfo patch(final String accountId, final MailboxInfo oldMailboxInfo,
            final Map<String, Object> mailboxPatches) {
        for (final Map.Entry<String, Object> mailboxPatch : mailboxPatches.entrySet()) {
            final String fullPath = mailboxPatch.getKey();
            final Object modification = mailboxPatch.getValue();
            final List<String> pathParts = splitterPort.on('/').splitToList(fullPath);
            final String parameter = pathParts.get(0);

            if ("role".equals(parameter)) {
                final RolePort role = rolePort.valueOf(caseFormatPort.convert((String) modification));
                return new MailboxInfo(oldMailboxInfo.id(), oldMailboxInfo.name(), role);
            } else {
                throw new IllegalArgumentException("No path found " + fullPath);
            }
        }

        return oldMailboxInfo;
    }
}
