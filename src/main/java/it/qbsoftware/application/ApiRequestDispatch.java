package it.qbsoftware.application;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.inject.Inject;

import it.qbsoftware.adapters.in.guava.ListMultimapAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.ResponseInvocationAdapter;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.application.controllers.get.GetMailboxMethodCallController;
import it.qbsoftware.application.controllers.other.EchoMethodCallController;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import rs.ltt.jmap.common.GenericResponse;
import rs.ltt.jmap.common.Request;
import rs.ltt.jmap.common.Response;
import rs.ltt.jmap.common.method.MethodResponse;
import java.util.Arrays;

public class ApiRequestDispatch {
    final EchoMethodCallController echoMethodCallController;

    final GetMailboxMethodCallController mailboxMethodCallController;

    final Gson GSON;

    @Inject
    public ApiRequestDispatch(final GetMailboxMethodCallController mailboxMethodCallController, final Gson gson,
            final EchoMethodCallController echoMethodCallController) {
        this.echoMethodCallController = echoMethodCallController;
        this.mailboxMethodCallController = mailboxMethodCallController;
        this.GSON = gson;
    }

    public String Dispatch(final String jmapRequest) throws JsonSyntaxException {
        Request request = GSON.fromJson(jmapRequest, Request.class);
        Request.Invocation[] methodCalls = request.getMethodCalls();

        var previousResponses = new ListMultimapAdapter<String, ResponseInvocationPort>();

        for (final Request.Invocation invocation : methodCalls) {
            final String invocationId = invocation.getId();

            MethodResponse[] methodResponses = echoMethodCallController.handle(
                    new HandlerRequest(invocation.getMethodCall(), previousResponses));

            Arrays.stream(methodResponses).sequential().forEach(
                    response -> previousResponses.put(invocationId,
                            new ResponseInvocationAdapter(new Response.Invocation(response, invocationId))));
        }

        GenericResponse genericResponse = new Response(previousResponses.values().toArray(new Response.Invocation[0]),
                "0");

        return GSON.toJson(genericResponse);
    }
}
