package it.qbsoftware.adapters.in.jmaplib.method.call.changes;

import it.qbsoftware.business.ports.in.jmap.method.call.changes.ChangesMethodCallPort;
import rs.ltt.jmap.common.method.call.standard.ChangesMethodCall;

public class ChangesMethodCallAdapter implements ChangesMethodCallPort{
    ChangesMethodCall changesMethodCall;
    
    @Override
    public String accountId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accountId'");
    }

    @Override
    public String getSinceState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSinceState'");
    }

    @Override
    public Long getMaxChanges() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMaxChanges'");
    }

}
