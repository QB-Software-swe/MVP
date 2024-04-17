package it.qbsoftware.adapters.in.jmaplib.entity;

import it.qbsoftware.business.ports.in.jmap.capability.AccountCapabilityPort;
import rs.ltt.jmap.common.entity.AccountCapability;

public class AccountCapabilityAdapter implements AccountCapabilityPort{
    AccountCapability accountCapability;

    public AccountCapabilityAdapter(AccountCapability accountCapability){
        this.accountCapability = accountCapability;
    }

}
