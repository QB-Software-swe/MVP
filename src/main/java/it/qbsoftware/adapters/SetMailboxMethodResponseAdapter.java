package it.qbsoftware.adapters;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import it.qbsoftware.adapters.in.jmaplib.AbstractIdentifiableEntityAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.AbstractIdentifiableEntityPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetMailboxMethodResponsePort;
import rs.ltt.jmap.common.method.response.mailbox.SetMailboxMethodResponse;

public class SetMailboxMethodResponseAdapter implements SetMailboxMethodResponsePort{
    SetMailboxMethodResponse setMailboxMethodResponse;

    public SetMailboxMethodResponseAdapter(final SetMailboxMethodResponse setMailboxMethodResponse){
        this.setMailboxMethodResponse = setMailboxMethodResponse;
    }

    @Override
    public Map<String, AbstractIdentifiableEntityPort> getCreated() {
        return setMailboxMethodResponse.getCreated().entrySet().stream().collect(Collectors.toMap(Entry::getKey, e -> new AbstractIdentifiableEntityAdapter(e.getValue())));
    }

}
