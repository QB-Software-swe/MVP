package it.qbsoftware.adapters.in.jmaplib.method.response.set;

import it.qbsoftware.adapters.in.jmaplib.entity.AbstractIdentifiableEntityAdapter;
import it.qbsoftware.business.ports.in.jmap.entity.AbstractIdentifiableEntityPort;
import it.qbsoftware.business.ports.in.jmap.method.response.set.SetEmailMethodResponsePort;
import java.util.Map;
import java.util.stream.Collectors;
import rs.ltt.jmap.common.method.response.email.SetEmailMethodResponse;

public class SetEmailMethodResponseAdapter implements SetEmailMethodResponsePort {
    private final SetEmailMethodResponse setEmailMethodResponse;

    public SetEmailMethodResponseAdapter(final SetEmailMethodResponse setEmailMethodResponse) {
        this.setEmailMethodResponse = setEmailMethodResponse;
    }

    @Override
    public Map<String, AbstractIdentifiableEntityPort> getCreated() {
        return setEmailMethodResponse.getCreated().entrySet().stream()
                .collect(
                        Collectors.toMap(
                                e -> e.getKey(),
                                e -> new AbstractIdentifiableEntityAdapter(e.getValue())));
    }

    public SetEmailMethodResponse adaptee() {
        return setEmailMethodResponse;
    }
}
