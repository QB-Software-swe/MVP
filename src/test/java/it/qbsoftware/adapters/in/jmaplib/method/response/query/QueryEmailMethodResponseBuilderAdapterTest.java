package it.qbsoftware.adapters.in.jmaplib.method.response.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.ports.in.jmap.method.response.query.QueryEmailMethodResponsePort;
import java.lang.reflect.Field;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import rs.ltt.jmap.common.method.response.email.QueryEmailMethodResponse;
import rs.ltt.jmap.common.method.response.email.QueryEmailMethodResponse.QueryEmailMethodResponseBuilder;

public class QueryEmailMethodResponseBuilderAdapterTest {
    @Test
    void testAccountId()
            throws NoSuchFieldException,
                    SecurityException,
                    IllegalArgumentException,
                    IllegalAccessException {
        QueryEmailMethodResponseBuilderAdapter queryEmailMethodResponseBuilderAdapter =
                new QueryEmailMethodResponseBuilderAdapter();
        QueryEmailMethodResponseBuilder queryEmailMethodResponseBuilder =
                mock(QueryEmailMethodResponseBuilder.class);

        Field field =
                QueryEmailMethodResponseBuilderAdapter.class.getDeclaredField(
                        "queryEmailMethodResponseBuilder");
        field.setAccessible(true);
        field.set(queryEmailMethodResponseBuilderAdapter, queryEmailMethodResponseBuilder);

        queryEmailMethodResponseBuilderAdapter.accountId("accountId");
        verify(queryEmailMethodResponseBuilder).accountId("accountId");
    }

    @Test
    void testBuild() {
        QueryEmailMethodResponseBuilder queryEmailMethodResponseBuilder =
                mock(QueryEmailMethodResponseBuilder.class);
        QueryEmailMethodResponseBuilderAdapter queryEmailMethodResponseBuilderAdapter =
                new QueryEmailMethodResponseBuilderAdapter();

        QueryEmailMethodResponse queryEmailMethodResponse = mock(QueryEmailMethodResponse.class);

        when(queryEmailMethodResponseBuilder.build()).thenReturn(queryEmailMethodResponse);

        QueryEmailMethodResponsePort queryEmailMethodResponsePort =
                queryEmailMethodResponseBuilderAdapter.build();

        assertEquals(
                QueryEmailMethodResponseAdapter.class, queryEmailMethodResponsePort.getClass());
    }

    @Test
    void testCanCalculateChanges()
            throws NoSuchFieldException,
                    SecurityException,
                    IllegalArgumentException,
                    IllegalAccessException {
        QueryEmailMethodResponseBuilderAdapter queryEmailMethodResponseBuilderAdapter =
                new QueryEmailMethodResponseBuilderAdapter();
        QueryEmailMethodResponseBuilder queryEmailMethodResponseBuilder =
                mock(QueryEmailMethodResponseBuilder.class);

        Field field =
                QueryEmailMethodResponseBuilderAdapter.class.getDeclaredField(
                        "queryEmailMethodResponseBuilder");
        field.setAccessible(true);
        field.set(queryEmailMethodResponseBuilderAdapter, queryEmailMethodResponseBuilder);

        queryEmailMethodResponseBuilderAdapter.canCalculateChanges(true);
        verify(queryEmailMethodResponseBuilder).canCalculateChanges(true);
    }

    @Test
    void testIds()
            throws NoSuchFieldException,
                    SecurityException,
                    IllegalArgumentException,
                    IllegalAccessException {
        QueryEmailMethodResponseBuilderAdapter queryEmailMethodResponseBuilderAdapter =
                new QueryEmailMethodResponseBuilderAdapter();
        QueryEmailMethodResponseBuilder queryEmailMethodResponseBuilder =
                mock(QueryEmailMethodResponseBuilder.class);

        Field field =
                QueryEmailMethodResponseBuilderAdapter.class.getDeclaredField(
                        "queryEmailMethodResponseBuilder");
        field.setAccessible(true);
        field.set(queryEmailMethodResponseBuilderAdapter, queryEmailMethodResponseBuilder);

        queryEmailMethodResponseBuilderAdapter.ids(new String[] {"id1", "id2"});
        verify(queryEmailMethodResponseBuilder).ids(new String[] {"id1", "id2"});
    }

    @Test
    void testLimit()
            throws NoSuchFieldException,
                    SecurityException,
                    IllegalArgumentException,
                    IllegalAccessException {
        QueryEmailMethodResponseBuilderAdapter queryEmailMethodResponseBuilderAdapter =
                new QueryEmailMethodResponseBuilderAdapter();
        QueryEmailMethodResponseBuilder queryEmailMethodResponseBuilder =
                mock(QueryEmailMethodResponseBuilder.class);

        Field field =
                QueryEmailMethodResponseBuilderAdapter.class.getDeclaredField(
                        "queryEmailMethodResponseBuilder");
        field.setAccessible(true);
        field.set(queryEmailMethodResponseBuilderAdapter, queryEmailMethodResponseBuilder);

        queryEmailMethodResponseBuilderAdapter.limit(10L);
        verify(queryEmailMethodResponseBuilder).limit(10L);
    }

    @Test
    void testPosition()
            throws NoSuchFieldException,
                    SecurityException,
                    IllegalArgumentException,
                    IllegalAccessException {
        QueryEmailMethodResponseBuilderAdapter queryEmailMethodResponseBuilderAdapter =
                new QueryEmailMethodResponseBuilderAdapter();
        QueryEmailMethodResponseBuilder queryEmailMethodResponseBuilder =
                mock(QueryEmailMethodResponseBuilder.class);

        Field field =
                QueryEmailMethodResponseBuilderAdapter.class.getDeclaredField(
                        "queryEmailMethodResponseBuilder");
        field.setAccessible(true);
        field.set(queryEmailMethodResponseBuilderAdapter, queryEmailMethodResponseBuilder);

        queryEmailMethodResponseBuilderAdapter.position(10L);
        verify(queryEmailMethodResponseBuilder).position(10L);
    }

    @Test
    void testQueryState()
            throws NoSuchFieldException,
                    SecurityException,
                    IllegalArgumentException,
                    IllegalAccessException {
        QueryEmailMethodResponseBuilderAdapter queryEmailMethodResponseBuilderAdapter =
                new QueryEmailMethodResponseBuilderAdapter();
        QueryEmailMethodResponseBuilder queryEmailMethodResponseBuilder =
                mock(QueryEmailMethodResponseBuilder.class);

        Field field =
                QueryEmailMethodResponseBuilderAdapter.class.getDeclaredField(
                        "queryEmailMethodResponseBuilder");
        field.setAccessible(true);
        field.set(queryEmailMethodResponseBuilderAdapter, queryEmailMethodResponseBuilder);

        queryEmailMethodResponseBuilderAdapter.queryState("queryState");
        verify(queryEmailMethodResponseBuilder).queryState("queryState");
    }

    @Test
    void testReset()
            throws NoSuchFieldException,
                    SecurityException,
                    IllegalArgumentException,
                    IllegalAccessException {
        QueryEmailMethodResponseBuilderAdapter queryEmailMethodResponseBuilderAdapter =
                new QueryEmailMethodResponseBuilderAdapter();
        QueryEmailMethodResponseBuilder queryEmailMethodResponseBuilder =
                mock(QueryEmailMethodResponseBuilder.class);

        Field field =
                QueryEmailMethodResponseBuilderAdapter.class.getDeclaredField(
                        "queryEmailMethodResponseBuilder");
        field.setAccessible(true);
        field.set(queryEmailMethodResponseBuilderAdapter, queryEmailMethodResponseBuilder);

        try (MockedStatic<QueryEmailMethodResponse> mailboxSatic =
                Mockito.mockStatic(QueryEmailMethodResponse.class)) {
            mailboxSatic
                    .when(QueryEmailMethodResponse::builder)
                    .thenReturn(queryEmailMethodResponseBuilder);
            assertEquals(
                    queryEmailMethodResponseBuilderAdapter.reset(),
                    queryEmailMethodResponseBuilderAdapter);
            mailboxSatic.verify(QueryEmailMethodResponse::builder);
        }
    }

    @Test
    void testTotal()
            throws NoSuchFieldException,
                    SecurityException,
                    IllegalArgumentException,
                    IllegalAccessException {
        QueryEmailMethodResponseBuilderAdapter queryEmailMethodResponseBuilderAdapter =
                new QueryEmailMethodResponseBuilderAdapter();
        QueryEmailMethodResponseBuilder queryEmailMethodResponseBuilder =
                mock(QueryEmailMethodResponseBuilder.class);

        Field field =
                QueryEmailMethodResponseBuilderAdapter.class.getDeclaredField(
                        "queryEmailMethodResponseBuilder");
        field.setAccessible(true);
        field.set(queryEmailMethodResponseBuilderAdapter, queryEmailMethodResponseBuilder);

        queryEmailMethodResponseBuilderAdapter.total(10L);
        verify(queryEmailMethodResponseBuilder).total(10L);
    }
}
