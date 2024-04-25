package it.qbsoftware.application.module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import it.qbsoftware.adapters.in.jmaplib.entity.AccountBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.SessionResourceBuilderAdapter;
import it.qbsoftware.application.ApiRequestDispatch;
import it.qbsoftware.application.CorRequestDispatch;
import it.qbsoftware.application.config.JmapConfig;
import it.qbsoftware.application.config.JmapEndpoint;
import it.qbsoftware.business.ports.in.jmap.EndPointConfiguration;
import it.qbsoftware.business.ports.in.jmap.capability.CapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.AccountBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourceBuilderPort;

//TODO: check
public class JettyHandlerModule extends AbstractModule {
    @Override
    protected void configure() {
        // Config
        bind(SessionResourceBuilderPort.class).to(SessionResourceBuilderAdapter.class);
        bind(AccountBuilderPort.class).to(AccountBuilderAdapter.class);
        bind(EndPointConfiguration.class).to(JmapEndpoint.class);
        bind(CapabilityPort[].class).annotatedWith(Names.named("serverCapabilities"))
                .toInstance(JmapConfig.serverCapabilities());

        bind(ApiRequestDispatch.class).to(CorRequestDispatch.class);
    }
}
