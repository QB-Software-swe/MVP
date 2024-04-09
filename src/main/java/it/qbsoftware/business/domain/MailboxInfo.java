package it.qbsoftware.business.domain;

import it.qbsoftware.business.ports.in.jmap.entity.RolePort;

public record MailboxInfo(String id, String name, RolePort role) {
}
