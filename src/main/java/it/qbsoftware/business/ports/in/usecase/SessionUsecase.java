package it.qbsoftware.business.ports.in.usecase;

import it.qbsoftware.business.ports.in.jmap.SessionResourcePort;

public interface SessionUsecase {

    public SessionResourcePort call(String username);
}
