package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import rs.ltt.jmap.common.entity.Identity;

public class IdentityAdapter implements IdentityPort{
    Identity identity;

    public IdentityAdapter(Identity identity){
        this.identity = identity;
    }

}
