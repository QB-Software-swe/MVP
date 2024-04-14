package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.entity.RolePort;
import rs.ltt.jmap.common.entity.Role;

public class RoleAdapter implements RolePort{
    Role role;

    public RoleAdapter(Role role) {
        this.role = role;
    }

    public RoleAdapter() {
        this.role = new Role();
    }
    
    @Override
    public RolePort valueOf(String name) {
        return new RoleAdapter(Role.valueOf(name));
    }

    public Role getRole() {
        return role;
    }

}
