package it.qbsoftware.core;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ListMultimap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.internal.client.model.FindOptions;

import it.qbsoftware.core.util.JmapSession;
import it.qbsoftware.core.util.MailboxInfo;
import it.qbsoftware.core.util.RequestResponse;
import it.qbsoftware.persistence.EmailDao;
import it.qbsoftware.persistence.EmailImp;
import it.qbsoftware.persistence.IdentityDao;
import it.qbsoftware.persistence.IdentityImp;
import it.qbsoftware.persistence.MailboxInfoDao;
import it.qbsoftware.persistence.MailboxInfoImp;
import it.qbsoftware.persistence.MongoConnectionSingleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.joda.time.DateTime;
import rs.ltt.jmap.common.GenericResponse;
import rs.ltt.jmap.common.Request;
import rs.ltt.jmap.common.Response;
import rs.ltt.jmap.common.SessionResource;
import rs.ltt.jmap.common.SessionResource.SessionResourceBuilder;
import rs.ltt.jmap.common.Response.Invocation;
import rs.ltt.jmap.common.entity.Capability;
import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.entity.Identity;
import rs.ltt.jmap.common.entity.Keyword;
import rs.ltt.jmap.common.entity.Mailbox;
import rs.ltt.jmap.common.entity.filter.EmailFilterCondition;
import rs.ltt.jmap.common.entity.filter.Filter;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.core.EchoMethodCall;
import rs.ltt.jmap.common.method.call.email.ChangesEmailMethodCall;
import rs.ltt.jmap.common.method.call.email.GetEmailMethodCall;
import rs.ltt.jmap.common.method.call.email.QueryChangesEmailMethodCall;
import rs.ltt.jmap.common.method.call.email.QueryEmailMethodCall;
import rs.ltt.jmap.common.method.call.identity.GetIdentityMethodCall;
import rs.ltt.jmap.common.method.call.mailbox.ChangesMailboxMethodCall;
import rs.ltt.jmap.common.method.call.mailbox.GetMailboxMethodCall;
import rs.ltt.jmap.common.method.call.thread.*;
import rs.ltt.jmap.common.method.error.AnchorNotFoundMethodErrorResponse;
import rs.ltt.jmap.common.method.error.CannotCalculateChangesMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidResultReferenceMethodErrorResponse;
import rs.ltt.jmap.common.method.error.UnknownMethodMethodErrorResponse;
import rs.ltt.jmap.common.method.response.core.EchoMethodResponse;
import rs.ltt.jmap.common.method.response.email.ChangesEmailMethodResponse;
import rs.ltt.jmap.common.method.response.email.GetEmailMethodResponse;
import rs.ltt.jmap.common.method.response.email.QueryChangesEmailMethodResponse;
import rs.ltt.jmap.common.method.response.email.QueryEmailMethodResponse;
import rs.ltt.jmap.common.method.response.identity.GetIdentityMethodResponse;
import rs.ltt.jmap.common.method.response.mailbox.ChangesMailboxMethodResponse;
import rs.ltt.jmap.common.method.response.mailbox.GetMailboxMethodResponse;
import rs.ltt.jmap.common.method.response.thread.ChangesThreadMethodResponse;
import rs.ltt.jmap.common.method.response.thread.GetThreadMethodResponse;
import rs.ltt.jmap.gson.JmapAdapters;
import rs.ltt.jmap.mock.server.Changes;
import rs.ltt.jmap.mock.server.ResultReferenceResolver;
import rs.ltt.jmap.mock.server.Update;
import rs.ltt.jmap.common.entity.Thread;
import rs.ltt.jmap.common.entity.capability.CoreCapability;
import rs.ltt.jmap.common.entity.capability.MailAccountCapability;
import rs.ltt.jmap.common.entity.capability.MailCapability;

public class Jmap {
  static final Gson GSON;
  final LinkedHashMap<String, Update> updates = new LinkedHashMap<>();

  static {
    GsonBuilder gsonBuilder = new GsonBuilder();// .setPrettyPrinting();
    JmapAdapters.register(gsonBuilder);
    GSON = gsonBuilder.create();
  }

  public RequestResponse requestSession(String authentication) {
    // increaseSessionState();
    return new RequestResponse(GSON.toJson(new JmapSession().sessionResources()), 200);
  }

  public RequestResponse request(String JsonRequestJmap) {
    System.out.println(
        "\n---------\n" + DateTime.now().toString() + " - Richiesta:\n" + JsonRequestJmap);
    Request request = null;

    try {
      request = GSON.fromJson(JsonRequestJmap, Request.class);
    } catch (Exception e) {
      System.out.println("\n\n" + DateTime.now().toString() + " - Risposta ERRORE!!!");
      request = null;
    }

    if (request != null) {
      String responseJson = GSON.toJson(computeResponse(request));
      System.out.println("\n\n" + DateTime.now().toString() + " - Risposta:\n" + responseJson);
      return new RequestResponse(responseJson, 200);
    } else {
      System.out.println("\n\n" + DateTime.now().toString() + " - Risposta ERRORE500!!!");
      return new RequestResponse("", 500);
    }
  }

  @SuppressWarnings("null")
  private GenericResponse computeResponse(Request jmapRequest) {
    final Request.Invocation[] methodCalls = jmapRequest.getMethodCalls();

    final ArrayListMultimap<String, rs.ltt.jmap.common.Response.Invocation> response = ArrayListMultimap.create();

    for (final Request.Invocation invocation : methodCalls) {
      final MethodCall methodCall = invocation.getMethodCall();
      final String id = invocation.getId();

      MethodResponse[] methodResponses = dispatch(methodCall, ImmutableListMultimap.copyOf(response));

      Arrays.stream(methodResponses)
          .sequential()
          .forEach(r -> response.put(id, new Response.Invocation(r, id)));
    }

    return new Response(response.values().toArray(new Response.Invocation[0]), "0");
  }

  private MethodResponse[] dispatch(
      final MethodCall methodCall,
      final ListMultimap<String, Response.Invocation> previousResponses) {
    return switch (methodCall) {
      case EchoMethodCall echoCall -> {
        yield execute(echoCall, previousResponses);
      }

      case GetIdentityMethodCall getIdentityMethodCall -> {
        yield execute(getIdentityMethodCall, previousResponses);
      }

      case GetMailboxMethodCall getMailboxMethodCall -> {
        yield execute(getMailboxMethodCall, previousResponses);
      }

      case ChangesMailboxMethodCall changesMailboxMethodCall -> {
        yield execute(changesMailboxMethodCall, previousResponses);
      }

      case QueryChangesEmailMethodCall queryChangesEmailMethodCall -> {
        yield execute(queryChangesEmailMethodCall, previousResponses);
      }

      case GetThreadMethodCall getThreadMethodCall -> {
        yield execute(getThreadMethodCall, previousResponses);
      }

      case GetEmailMethodCall getEmailMethodCall -> {
        yield execute(getEmailMethodCall, previousResponses);
      }

      case ChangesEmailMethodCall changesEmailMethodCall -> {
        yield execute(changesEmailMethodCall, previousResponses);
      }

      case QueryEmailMethodCall queryEmailMethodCall -> {
        yield execute(queryEmailMethodCall, previousResponses);
      }

      case ChangesThreadMethodCall changesThreadMethodCall -> {
        yield execute(changesThreadMethodCall, previousResponses);
      }

      default -> {
        System.out.println(
            "................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................................");
        yield new MethodResponse[] { new UnknownMethodMethodErrorResponse() };
      }
    };
  }

  protected MethodResponse[] execute(
      ChangesEmailMethodCall methodCall,
      ListMultimap<String, Response.Invocation> previousResponses) {
    final String since = methodCall.getSinceState();
    if (since != null && since.equals("0")) {
      return new MethodResponse[] {
          ChangesEmailMethodResponse.builder()
              .oldState(getState())
              .newState(getState())
              .updated(new String[0])
              .created(new String[0])
              .destroyed(new String[0])
              .build()
      };
    } else {
      final Update update = getAccumulatedUpdateSince(since);
      if (update == null) {
        return new MethodResponse[] { new CannotCalculateChangesMethodErrorResponse() };
      } else {
        final Changes changes = update.getChangesFor(Email.class);
        return new MethodResponse[] {
            ChangesEmailMethodResponse.builder()
                .oldState(since)
                .newState(update.getNewVersion())
                .updated(changes == null ? new String[0] : changes.updated)
                .created(changes == null ? new String[0] : changes.created)
                .destroyed(new String[0])
                .hasMoreChanges(!update.getNewVersion().equals(getState()))
                .build()
        };
      }
    }
  }

  private MethodResponse[] execute(
      GetThreadMethodCall methodCall,
      ListMultimap<String, Response.Invocation> previousResponses) {
    final Request.Invocation.ResultReference idsReference = methodCall.getIdsReference();
    final List<String> ids;
    if (idsReference != null) {
      try {
        ids = Arrays.asList(
            ResultReferenceResolver.resolve(idsReference, previousResponses));
      } catch (final IllegalArgumentException e) {
        return new MethodResponse[] { new InvalidResultReferenceMethodErrorResponse() };
      }
    } else {
      ids = Arrays.asList(methodCall.getIds());
    }
    Map<String, Email> emails = new HashMap<String, Email>();
    EmailDao emailDao = new EmailImp();
    for (Email email : emailDao.getAllEmails()) {
      emails.put(email.getId(), email);
    }
    final Thread[] threads = ids.stream()
        .map(
            threadId -> Thread.builder()
                .id(threadId)
                .emailIds(
                    emails.values().stream()
                        .filter(
                            email -> email.getThreadId()
                                .equals(
                                    threadId))
                        .sorted(
                            Comparator.comparing(
                                Email::getReceivedAt))
                        .map(Email::getId)
                        .collect(Collectors.toList()))
                .build())
        .toArray(Thread[]::new);
    return new MethodResponse[] {
        GetThreadMethodResponse.builder().list(threads).state(getState()).build()
    };
  }

  private MethodResponse[] execute(ChangesThreadMethodCall changesThreadMethodCall,
      ListMultimap<String, Invocation> previousResponses) {
    final String since = changesThreadMethodCall.getSinceState();
    if (since != null && since.equals(getState())) {
      return new MethodResponse[] {
          ChangesThreadMethodResponse.builder()
              .oldState(getState())
              .newState(getState())
              .updated(new String[0])
              .created(new String[0])
              .destroyed(new String[0])
              .build()
      };
    } else {
      final Update update = getAccumulatedUpdateSince(since);
      if (update == null) {
        return new MethodResponse[] { new CannotCalculateChangesMethodErrorResponse() };
      } else {
        final Changes changes = update.getChangesFor(Thread.class);
        return new MethodResponse[] {
            ChangesThreadMethodResponse.builder()
                .oldState(since)
                .newState(update.getNewVersion())
                .updated(changes == null ? new String[0] : changes.updated)
                .created(changes == null ? new String[0] : changes.created)
                .destroyed(new String[0])
                .hasMoreChanges(!update.getNewVersion().equals(getState()))
                .build()
        };
      }
    }
  }

  private MethodResponse[] execute(
      QueryEmailMethodCall queryEmailMethodCall,
      ListMultimap<String, Invocation> previousResponses) {
    final Filter<Email> filter = queryEmailMethodCall.getFilter();
    EmailDao emailDao = new EmailImp();
    Map<String, Email> emails = new HashMap<String, Email>();
    for (Email email : emailDao.getAllEmails()) {
      emails.put(email.getId(), email);
    }
    Stream<Email> emailStream = emails.values().stream();
    emailStream = applyFilter(filter, emailStream);
    emailStream = emailStream.sorted(Comparator.comparing(Email::getReceivedAt).reversed());

    if (Boolean.TRUE.equals(queryEmailMethodCall.getCollapseThreads())) {
      emailStream = emailStream.filter(distinctByKey(Email::getThreadId));
    }

    final List<String> ids = emailStream.map(Email::getId).collect(Collectors.toList());
    final String anchor = queryEmailMethodCall.getAnchor();
    final int position;
    if (anchor != null) {
      final Long anchorOffset = queryEmailMethodCall.getAnchorOffset();
      final int anchorPosition = ids.indexOf(anchor);
      if (anchorPosition == -1) {
        return new MethodResponse[] { new AnchorNotFoundMethodErrorResponse() };
      }
      position = Math.toIntExact(anchorPosition + (anchorOffset == null ? 0 : anchorOffset));
    } else {
      position = Math.toIntExact(
          queryEmailMethodCall.getPosition() == null ? 0 : queryEmailMethodCall.getPosition());
    }
    final int limit = Math.toIntExact(
        queryEmailMethodCall.getLimit() == null ? 40 : queryEmailMethodCall.getLimit());
    final int endPosition = Math.min(position + limit, ids.size());
    final String[] page = ids.subList(position, endPosition).toArray(new String[0]);
    final Long total = Boolean.TRUE.equals(queryEmailMethodCall.getCalculateTotal()) ? (long) ids.size() : null;

    return new MethodResponse[] {
        QueryEmailMethodResponse.builder()
            .canCalculateChanges(false) // Non implementato
            .queryState(getState())
            .total(total)
            .ids(page)
            .position((long) position)
            .build()
    };
  }

  private MethodResponse[] execute(
      GetEmailMethodCall getEmailMethodCall, ListMultimap<String, Invocation> previousResponses) {
    final Request.Invocation.ResultReference idsReference = getEmailMethodCall.getIdsReference();
    final List<String> ids;
    if (idsReference != null) {
      try {
        ids = Arrays.asList(ResultReferenceResolver.resolve(idsReference, previousResponses));
      } catch (final IllegalArgumentException e) {
        return new MethodResponse[] { new InvalidResultReferenceMethodErrorResponse() };
      }
    } else {
      ids = Arrays.asList(getEmailMethodCall.getIds());
    }
    EmailDao emailDao = new EmailImp();
    Map<String, Email> emails = new HashMap<String, Email>();
    for (Email email : emailDao.getAllEmails()) {
      emails.put(email.getId(), email);
    }
    final String[] properties = getEmailMethodCall.getProperties();
    Stream<Email> emailStream = emails.values().stream();
    if (Arrays.equals(properties, Email.Properties.THREAD_ID)) {
      emailStream = emailStream.map(
          email -> Email.builder().id(email.getId()).threadId(email.getThreadId()).build());
    } else if (Arrays.equals(properties, Email.Properties.MUTABLE)) {
      emailStream = emailStream.map(
          email -> Email.builder()
              .id(email.getId())
              .keywords(email.getKeywords())
              .mailboxIds(email.getMailboxIds())
              .build());
    }
    return new MethodResponse[] {
        GetEmailMethodResponse.builder().list(emailStream.toArray(Email[]::new)).state(getState()).build()
    };
  }

  private MethodResponse[] execute(
      EchoMethodCall methodCall, ListMultimap<String, Response.Invocation> previousResponses) {
    return new MethodResponse[] {
        EchoMethodResponse.builder().libraryName(methodCall.getLibraryName()).build()
    };
  }

  private MethodResponse[] execute(
      GetIdentityMethodCall getIdentityMethodCall,
      ListMultimap<String, Response.Invocation> previousResponses) {

    IdentityDao identityDao = new IdentityImp();

    return new MethodResponse[] {
        GetIdentityMethodResponse.builder()
            .list(
                new Identity[] { identityDao.getIdentity(getIdentityMethodCall.getAccountId()).get() })
            .build()
    };
  }

  private MethodResponse[] execute(
      GetMailboxMethodCall getMailboxMethodCall,
      ListMultimap<String, Response.Invocation> previousResponses) {

    final Request.Invocation.ResultReference idsReference = getMailboxMethodCall.getIdsReference();
    final List<String> ids;

    if (idsReference != null) {
      try {
        ids = Arrays.asList(ResultReferenceResolver.resolve(idsReference, previousResponses));
      } catch (final IllegalArgumentException e) {
        return new MethodResponse[] { new InvalidResultReferenceMethodErrorResponse() };
      }
    } else {
      final String[] idsParameter = getMailboxMethodCall.getIds();
      ids = idsParameter == null ? null : Arrays.asList(idsParameter);
    }

    MailboxInfoDao mailboxInfoDao = new MailboxInfoImp();
    ArrayList<MailboxInfo> mailboxs = mailboxInfoDao.getMailboxsInfo();

    return new MethodResponse[] {
        GetMailboxMethodResponse.builder()
            .list(
                mailboxs.stream()
                    .map(this::toMailbox)
                    .filter(m -> ids == null || ids.contains(m.getId()))
                    .toArray(Mailbox[]::new))
            .state(getState())
            .build()
    };
  }

  private MethodResponse[] execute(
      QueryChangesEmailMethodCall queryChangesEmailMethodCall,
      ListMultimap<String, Response.Invocation> previousResponses) {
    final String since = queryChangesEmailMethodCall.getSinceQueryState();
    if (since != null && since.equals(getState())) {
      return new MethodResponse[] {
          QueryChangesEmailMethodResponse.builder()
              .oldQueryState(getState())
              .newQueryState(getState())
              .added(Collections.emptyList())
              .removed(new String[0])
              .build()
      };
    } else {
      return new MethodResponse[] { new CannotCalculateChangesMethodErrorResponse() };
    }
  }

  private MethodResponse[] execute(
      ChangesMailboxMethodCall methodCall,
      ListMultimap<String, Response.Invocation> previousResponses) {
    final String since = methodCall.getSinceState();
    if (since != null && since.equals(getState())) {
      return new MethodResponse[] {
          ChangesMailboxMethodResponse.builder()
              .oldState(getState())
              .newState(getState())
              .updated(new String[0])
              .created(new String[0])
              .destroyed(new String[0])
              .updatedProperties(new String[0])
              .build()
      };
    } else {
      final Update update = getAccumulatedUpdateSince(since);
      if (update == null) {
        return new MethodResponse[] { new CannotCalculateChangesMethodErrorResponse() };
      } else {
        final Changes changes = update.getChangesFor(Mailbox.class);
        return new MethodResponse[] {
            ChangesMailboxMethodResponse.builder()
                .oldState(since)
                .newState(update.getNewVersion())
                .updated(changes.updated)
                .created(changes.created)
                .destroyed(new String[0])
                .hasMoreChanges(!update.getNewVersion().equals("0"))
                .build()
        };
      }
    }
  }

  private Mailbox toMailbox(MailboxInfo mailboxInfo) {
    // TODO: implementare conteggio e-mail
    return Mailbox.builder()
        .id(mailboxInfo.getId())
        .name(mailboxInfo.getName())
        .role(mailboxInfo.getRole())
        .totalEmails(null)
        .totalThreads(null)
        .unreadEmails(null)
        .unreadThreads(null)
        .build();
  }

  private Update getAccumulatedUpdateSince(final String oldVersion) {
    final ArrayList<Update> updates = new ArrayList<>();
    for (Map.Entry<String, Update> updateEntry : this.updates.entrySet()) {
      if (updateEntry.getKey().equals(oldVersion) || updates.size() > 0) {
        updates.add(updateEntry.getValue());
      }
    }
    if (updates.isEmpty()) {
      return null;
    }
    return Update.merge(updates);
  }

  private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
    final Set<Object> seen = ConcurrentHashMap.newKeySet();
    return t -> seen.add(keyExtractor.apply(t));
  }

  private static Stream<Email> applyFilter(final Filter<Email> filter, Stream<Email> emailStream) {
    if (filter instanceof EmailFilterCondition) {
      final EmailFilterCondition emailFilterCondition = (EmailFilterCondition) filter;
      final String inMailbox = emailFilterCondition.getInMailbox();
      if (inMailbox != null) {
        emailStream = emailStream.filter(email -> email.getMailboxIds().containsKey(inMailbox));
      }
      final String[] header = emailFilterCondition.getHeader();
      if (header != null && header.length == 2 && header[0].equals("Autocrypt-Setup-Message")) {
        emailStream = emailStream.filter(email -> header[1].equals(email.getAutocryptSetupMessage()));
      }
    }
    return emailStream;
  }

  // Session
  public String getState() {
    return MongoConnectionSingleton.INSTANCE.getConnection().getDatabase().getCollection("Account")
        .find(Filters.eq("_id", "0")).first().getString("state");
  }

  public void increaseState() {
    MongoConnectionSingleton.INSTANCE
        .getConnection().getDatabase().getCollection(
            "Account")
        .updateOne(Filters.eq("_id", "0"), Updates.set("state", String.valueOf(Integer.valueOf(getState()) + 1)));
  }
}
