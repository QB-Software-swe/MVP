package it.qbsoftware.persistence;

import rs.ltt.jmap.common.entity.Identity;
import java.util.Optional;

public interface IdentityDao {
    Optional<Identity> getIdentity(String id);
    void saveIdentity(Identity identity);
}
