package it.qbsoftware.application.module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import it.qbsoftware.adapters.in.jmaplib.entity.AccountBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.SessionResourceBuilderAdapter;
import it.qbsoftware.application.ApiRequestDispatch;
import it.qbsoftware.application.CorRequestDispatch;
import it.qbsoftware.application.config.CommonJmapUrlConfiguration;
import it.qbsoftware.application.config.JmapConfig;
import it.qbsoftware.business.ports.in.jmap.JmapUrlConfiguration;
import it.qbsoftware.business.ports.in.jmap.capability.CapabilityPort;
import it.qbsoftware.business.ports.in.jmap.entity.AccountBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.SessionResourceBuilderPort;

public class JettyHandlerModule extends AbstractModule {
    @Override
    protected void configure() {
        // Config
        bind(SessionResourceBuilderPort.class).to(SessionResourceBuilderAdapter.class);
        bind(AccountBuilderPort.class).to(AccountBuilderAdapter.class);
        bind(JmapUrlConfiguration.class).to(CommonJmapUrlConfiguration.class);
        bind(CapabilityPort[].class)
                .annotatedWith(Names.named("serverCapabilities"))
                .toInstance(JmapConfig.serverCapabilities());

        bind(ApiRequestDispatch.class).to(CorRequestDispatch.class);
    }
}
