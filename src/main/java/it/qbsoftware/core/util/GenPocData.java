package it.qbsoftware.core.util;

import it.qbsoftware.persistence.IdentityImp;
import it.qbsoftware.persistence.MongoConnectionSingleton;
import rs.ltt.jmap.common.entity.Identity;

public class GenPocData {

    public static void generate() {
        MongoConnectionSingleton.INSTANCE.getConnection().getDatabase().drop();
        generateIdentity();
    }

    static void generateIdentity() {
        new IdentityImp()
                .saveIdentity(
                        Identity
                                .builder()
                                .id("0")
                                .email("team@qbsoftware.it")
                                .name("QB Software")
                                .build()
                            );
    }
}
