package it.qbsoftware.application.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import it.qbsoftware.application.ApiRequestDispatch;
import org.eclipse.jetty.http.HttpFields;
import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class WellKnownHandlerTest {

    @Mock Response response;

    @Mock ApiRequestDispatch wellKnowRequestDispatch;

    @Mock Callback callback;

    @InjectMocks private WellKnownHandler wellKnowHandler;

    @Test
    public void testHandleValid() throws Exception {
        try (MockedStatic<Request> theMock = Mockito.mockStatic(Request.class)) {
            try (MockedStatic<Content.Sink> sink = Mockito.mockStatic(Content.Sink.class)) {
                try (MockedStatic<Content.Source> conSource =
                        Mockito.mockStatic(Content.Source.class)) {
                    Request request = Mockito.mock(Request.class);
                    when(request.getMethod()).thenReturn("GET");
                    when(request.getHeaders()).thenReturn(Mockito.mock(HttpFields.class));
                    theMock.when(() -> Request.getPathInContext(request)).thenReturn("/jmap");
                    sink.when(() -> Content.Sink.write(response, true, "responsePayload", callback))
                            .then(invocationOnMock -> null);

                    Boolean result = wellKnowHandler.handle(request, response, callback);
                    assertEquals(true, result);
                }
            }
        }
    }

    @Test
    public void testHandleWithNotPost() throws Exception {

        try (MockedStatic<Request> theMock = Mockito.mockStatic(Request.class)) {
            try (MockedStatic<Content.Sink> sink = Mockito.mockStatic(Content.Sink.class)) {
                Request request = Mockito.mock(Request.class);

                when(request.getMethod()).thenReturn("notGET");
                theMock.when(() -> Request.getPathInContext(request)).thenReturn("/jmap");

                Boolean result = wellKnowHandler.handle(request, response, callback);

                assertEquals(false, result);
            }
        }
    }
}
