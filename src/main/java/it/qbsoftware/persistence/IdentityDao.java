package it.qbsoftware.persistence;

import java.util.Optional;
import rs.ltt.jmap.common.entity.Identity;

public interface IdentityDao {
  Optional<Identity> getIdentity(String id);

  void saveIdentity(Identity identity);
}
