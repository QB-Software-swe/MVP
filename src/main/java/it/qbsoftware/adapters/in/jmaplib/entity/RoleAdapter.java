package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.entity.RolePort;
import rs.ltt.jmap.common.entity.Role;

public class RoleAdapter implements RolePort{
    Role role;

    public RoleAdapter(final Role role) {
        this.role = role;
    }
    
    @Override
    public RolePort valueOf(final String name) {
        return new RoleAdapter(Role.valueOf(name));
    }

    public Role getRole() {
        return role;
    }

}
