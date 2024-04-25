package it.qbsoftware.application;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.inject.Inject;

import it.qbsoftware.adapters.in.guava.ListMultimapAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.ResponseInvocationAdapter;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.application.controllers.get.GetEmailMethodCallController;
import it.qbsoftware.application.controllers.get.GetIdentityMethodCallController;
import it.qbsoftware.application.controllers.get.GetMailboxMethodCallController;
import it.qbsoftware.application.controllers.get.GetThreadMethodCallController;
import it.qbsoftware.application.controllers.other.EchoMethodCallController;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import rs.ltt.jmap.common.GenericResponse;
import rs.ltt.jmap.common.Request;
import rs.ltt.jmap.common.Response;
import rs.ltt.jmap.common.method.MethodResponse;

import java.util.Arrays;

public class CorRequestDispatch implements ApiRequestDispatch {
    private final EchoMethodCallController echoMethodCallController;

    private final GetEmailMethodCallController getEmailMethodCallController;
    private final GetIdentityMethodCallController getIdentityMethodCallController;
    private final GetMailboxMethodCallController getMailboxMethodCallController;
    private final GetThreadMethodCallController getThreadMethodCallController;

    private final Gson gson;

    @Inject
    public CorRequestDispatch(final EchoMethodCallController echoMethodCallController, final Gson gson,
            final GetEmailMethodCallController getEmailMethodCallController,
            final GetIdentityMethodCallController getIdentityMethodCallController,
            final GetMailboxMethodCallController getMailboxMethodCallController,
            final GetThreadMethodCallController getThreadMethodCallController) {

        this.echoMethodCallController = echoMethodCallController;

        this.getEmailMethodCallController = getEmailMethodCallController;
        this.getIdentityMethodCallController = getIdentityMethodCallController;
        this.getMailboxMethodCallController = getMailboxMethodCallController;
        this.getThreadMethodCallController = getThreadMethodCallController;

        this.gson = gson;

        setupCoR();
    }

    private void setupCoR() {
        echoMethodCallController.setNext(getEmailMethodCallController);

        getEmailMethodCallController.setNext(getIdentityMethodCallController);
        getIdentityMethodCallController.setNext(getMailboxMethodCallController);
        getMailboxMethodCallController.setNext(getThreadMethodCallController);
    }

    @Override
    public String Dispatch(final String jmapRequest) throws JsonSyntaxException {
        Request request = gson.fromJson(jmapRequest, Request.class);
        Request.Invocation[] methodCalls = request.getMethodCalls();

        ListMultimapAdapter<String, ResponseInvocationPort> previousResponses = new ListMultimapAdapter<String, ResponseInvocationPort>();

        for (final Request.Invocation invocation : methodCalls) {
            final String invocationId = invocation.getId();

            // Rename
            final MethodResponse methodResponses = echoMethodCallController.handle(
                    new HandlerRequest(invocation.getMethodCall(), previousResponses));

            previousResponses.put(invocationId,
                    new ResponseInvocationAdapter(new Response.Invocation(methodResponses, invocationId)));
        }

        GenericResponse genericResponse = new Response(
                previousResponses.values().stream().map(ri -> ((ResponseInvocationAdapter) ri).adaptee())
                        .toArray(Response.Invocation[]::new),
                "0");

        return gson.toJson(genericResponse);
    }
}
