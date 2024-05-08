package it.qbsoftware.adapters.in.jmaplib.method.response.query;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import rs.ltt.jmap.common.method.response.email.QueryEmailMethodResponse;

public class QueryEmailMethodResponseAdapterTest {
    @Test
    void testAdaptee() {
        QueryEmailMethodResponse queryEmailMethodResponse = mock(QueryEmailMethodResponse.class);
        QueryEmailMethodResponseAdapter queryEmailMethodResponseAdapter =
                new QueryEmailMethodResponseAdapter(queryEmailMethodResponse);

        assertEquals(queryEmailMethodResponse, queryEmailMethodResponseAdapter.adaptee());
    }
}
