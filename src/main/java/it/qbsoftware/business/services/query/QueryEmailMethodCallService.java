package it.qbsoftware.business.services.query;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.query.QueryAnchorNotFoundException;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.JmapFilterPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.method.call.query.QueryEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.response.query.QueryEmailMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.method.response.query.QueryEmailMethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.query.QueryEmailMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QueryEmailMethodCallService implements QueryEmailMethodCallUsecase {
    private final AccountStateRepository accountStateRepository;
    private final EmailRepository emailRepository;
    private final QueryEmailMethodResponseBuilderPort queryEmailMethodResponseBuilderPort;

    public QueryEmailMethodCallService(
            final AccountStateRepository accountStateRepository,
            final EmailRepository emailRepository,
            final QueryEmailMethodResponseBuilderPort queryEmailMethodResponseBuilderPort) {
        this.accountStateRepository = accountStateRepository;
        this.emailRepository = emailRepository;
        this.queryEmailMethodResponseBuilderPort = queryEmailMethodResponseBuilderPort;
    }

    @Override
    public QueryEmailMethodResponsePort call(
            final QueryEmailMethodCallPort queryEmailMethodCallPort,
            final ListMultimapPort<String, ResponseInvocationPort> previousResponses)
            throws QueryAnchorNotFoundException, AccountNotFoundException {
        final AccountState accountState =
                accountStateRepository.retrive(queryEmailMethodCallPort.getAccountId());

        final JmapFilterPort<EmailPort> queryFilter = queryEmailMethodCallPort.getFilter();
        Stream<EmailPort> emails =
                Arrays.asList(
                        emailRepository.retriveAll(queryEmailMethodCallPort.getAccountId()).found())
                        .stream();

        emails = queryFilter.apply(emails);
        emails = emails.sorted(Comparator.comparing(EmailPort::getReceivedAt).reversed());

        if (Boolean.TRUE.equals(queryEmailMethodCallPort.getCollapseThreads())) {
            emails = emails.filter(distinctByKey(EmailPort::getThreadId));
        }

        final List<String> ids = emails.map(EmailPort::getId).collect(Collectors.toList());

        final String anchor = queryEmailMethodCallPort.getAnchor();
        final int position;

        if (anchor != null) {
            final Long anchorOffset = queryEmailMethodCallPort.getAnchorOffset();
            final int anchorPosition = ids.indexOf(anchor);
            if (anchorPosition == -1) {
                throw new QueryAnchorNotFoundException();
            }
            position = Math.toIntExact(anchorPosition + (anchorOffset == null ? 0 : anchorOffset));
        } else {
            position =
                    Math.toIntExact(
                            queryEmailMethodCallPort.getPosition() == null
                                    ? 0
                                    : queryEmailMethodCallPort.getPosition());
        }
        final int limit =
                Math.toIntExact(
                        queryEmailMethodCallPort.getLimit() == null
                                ? 40
                                : queryEmailMethodCallPort.getLimit());
        final int endPosition = Math.min(position + limit, ids.size());
        final String[] page = ids.subList(position, endPosition).toArray(new String[0]);
        final Long total =
                Boolean.TRUE.equals(queryEmailMethodCallPort.getCalculateTotal())
                        ? (long) ids.size()
                        : null;

        return queryEmailMethodResponseBuilderPort
                .reset()
                .canCalculateChanges(true)
                .queryState(accountState.state())
                .total(total)
                .ids(page)
                .position((long) position)
                .build();
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        final Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
