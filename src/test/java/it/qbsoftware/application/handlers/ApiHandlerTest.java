package it.qbsoftware.application.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.google.gson.JsonSyntaxException;
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
public class ApiHandlerTest {

    @Mock Response response;

    @Mock ApiRequestDispatch apiRequestDispatch;

    @Mock Callback callback;

    @InjectMocks private ApiHandler apiHandler;

    @Test
    public void testHandleValid() throws Exception {
        try (MockedStatic<Request> theMock = Mockito.mockStatic(Request.class)) {
            try (MockedStatic<Content.Sink> sink = Mockito.mockStatic(Content.Sink.class)) {
                try (MockedStatic<Content.Source> conSource =
                        Mockito.mockStatic(Content.Source.class)) {
                    Request request = Mockito.mock(Request.class);

                    when(request.getMethod()).thenReturn("POST");
                    when(response.getHeaders()).thenReturn(Mockito.mock(HttpFields.Mutable.class));
                    theMock.when(() -> Request.getPathInContext(request)).thenReturn("/api");
                    sink.when(() -> Content.Sink.write(response, true, "responsePayload", callback))
                            .then(invocationOnMock -> null);

                    Boolean result = apiHandler.handle(request, response, callback);
                    assertEquals(true, result);
                }
            }
        }
    }

    @Test
    public void testHandleWithException() throws Exception {
        try (MockedStatic<Request> theMock = Mockito.mockStatic(Request.class)) {
            try (MockedStatic<Content.Sink> sink = Mockito.mockStatic(Content.Sink.class)) {
                try (MockedStatic<Content.Source> conSource =
                        Mockito.mockStatic(Content.Source.class)) {
                    Request request = Mockito.mock(Request.class);

                    when(request.getMethod()).thenReturn("POST");
                    when(apiRequestDispatch.dispatch("requestPayload"))
                            .thenThrow(JsonSyntaxException.class);
                    theMock.when(() -> Content.Source.asString(request))
                            .thenReturn("requestPayload");
                    theMock.when(() -> Request.getPathInContext(request)).thenReturn("/api");
                    sink.when(() -> Content.Sink.write(response, true, "responsePayload", callback))
                            .then(invocationOnMock -> null);

                    Boolean result = apiHandler.handle(request, response, callback);
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

                when(request.getMethod()).thenReturn("notPOST");
                theMock.when(() -> Request.getPathInContext(request)).thenReturn("/api");

                Boolean result = apiHandler.handle(request, response, callback);

                assertEquals(false, result);
            }
        }
    }
}
