package it.qbsoftware.core;

import com.google.common.collect.ImmutableMap;
import it.qbsoftware.boot.handlers.ApiHandler;
import it.qbsoftware.boot.handlers.DownloadHandler;
import it.qbsoftware.boot.handlers.UploadHandler;
import it.qbsoftware.core.utils.MemoryUnit;
import rs.ltt.jmap.common.SessionResource;
import rs.ltt.jmap.common.SessionResource.SessionResourceBuilder;
import rs.ltt.jmap.common.entity.Account;
import rs.ltt.jmap.common.entity.Capability;
import rs.ltt.jmap.common.entity.capability.CoreCapability;
import rs.ltt.jmap.common.entity.capability.MailAccountCapability;
import rs.ltt.jmap.common.entity.capability.MailCapability;

@SuppressWarnings("null")
public class JmapSession {
  static final String API_ENDPOINT = ApiHandler.HANDLER_ENDPOINT_NAME;
  ;
  static final String UPLOAD_ENDPOINT = UploadHandler.HANDLER_ENDPOINT_NAME;
  static final String DOWNLOAD_ENDPOINT = DownloadHandler.HANDLER_ENDPOINT_NAME;

  static final long maxServerSizeUpload = 100 * MemoryUnit.MB;
  static final long maxServerObjectInGet = 4096;

  long maxSizeAttachmentsPerEmail;
  String state;

  static ImmutableMap.Builder<Class<? extends Capability>, Capability> serverCapability;

  static {
    serverCapability = ImmutableMap.builder();
    serverCapability.put(
        CoreCapability.class,
        CoreCapability.builder()
            .maxSizeUpload(maxServerSizeUpload)
            .maxObjectsInGet(maxServerObjectInGet)
            .build());
    serverCapability.put(MailCapability.class, MailCapability.builder().build());
  }

  public JmapSession() {
    maxSizeAttachmentsPerEmail = 0;
    state = "0";
  }

  public SessionResource sessionResources() {
    SessionResourceBuilder sessionResourceBuilder = SessionResource.builder();

    sessionResourceBuilder
        .apiUrl(API_ENDPOINT)
        .uploadUrl(UPLOAD_ENDPOINT)
        .downloadUrl(DOWNLOAD_ENDPOINT)
        .state(state);

    // Example
    sessionResourceBuilder
        .account(
            "0",
            Account.builder()
                .name("AccountExample")
                .accountCapabilities(
                    ImmutableMap.of(
                        MailAccountCapability.class,
                        MailAccountCapability.builder()
                            .maxSizeAttachmentsPerEmail(maxSizeAttachmentsPerEmail)
                            .build()))
                .build())
        .capabilities(serverCapability.build())
        .primaryAccounts(ImmutableMap.of(MailAccountCapability.class, "0"))
        .build();

    return sessionResourceBuilder.build();
  }
}
