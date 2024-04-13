package it.qbsoftware.adapters.jmaplib;

import it.qbsoftware.business.ports.in.jmap.capabilities.AccountCapabilityPort;
import rs.ltt.jmap.common.entity.AccountCapability;

public class AccountCapabilityAdapter implements AccountCapabilityPort{
    AccountCapability accountCapability;

    public AccountCapabilityAdapter(AccountCapability accountCapability){
        this.accountCapability = accountCapability;
    }

}
