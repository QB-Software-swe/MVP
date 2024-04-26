package it.qbsoftware.application;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.inject.Inject;

import it.qbsoftware.adapters.in.guava.ListMultimapAdapter;
import it.qbsoftware.adapters.in.jmaplib.entity.ResponseInvocationAdapter;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.application.controllers.changes.ChangesEmailMethodCallController;
import it.qbsoftware.application.controllers.changes.ChangesEmailSubmissionMethodCallController;
import it.qbsoftware.application.controllers.changes.ChangesIdentityMethodCallController;
import it.qbsoftware.application.controllers.changes.ChangesMailboxMethodCallController;
import it.qbsoftware.application.controllers.changes.ChangesThreadMethodCallController;
import it.qbsoftware.application.controllers.get.GetEmailMethodCallController;
import it.qbsoftware.application.controllers.get.GetEmailSubmissionMethodCallController;
import it.qbsoftware.application.controllers.get.GetIdentityMethodCallController;
import it.qbsoftware.application.controllers.get.GetMailboxMethodCallController;
import it.qbsoftware.application.controllers.get.GetThreadMethodCallController;
import it.qbsoftware.application.controllers.other.EchoMethodCallController;
import it.qbsoftware.application.controllers.set.SetEmailMethodCallController;
import it.qbsoftware.application.controllers.set.SetIdentityMethodCallController;
import it.qbsoftware.application.controllers.set.SetMailboxMethodCallController;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import rs.ltt.jmap.common.GenericResponse;
import rs.ltt.jmap.common.Request;
import rs.ltt.jmap.common.Response;
import rs.ltt.jmap.common.method.MethodResponse;

public class CorRequestDispatch implements ApiRequestDispatch {
    private final EchoMethodCallController echoMethodCallController;

    private final GetEmailMethodCallController getEmailMethodCallController;
    private final GetIdentityMethodCallController getIdentityMethodCallController;
    private final GetMailboxMethodCallController getMailboxMethodCallController;
    private final GetThreadMethodCallController getThreadMethodCallController;
    private final GetEmailSubmissionMethodCallController getEmailSubmissionMethodCallController;

    private final ChangesEmailMethodCallController changesEmailMethodCallController;
    private final ChangesEmailSubmissionMethodCallController changesEmailSubmissionMethodCallController;
    private final ChangesIdentityMethodCallController changesIdentityMethodCallController;
    private final ChangesMailboxMethodCallController changesMailboxMethodCallController;
    private final ChangesThreadMethodCallController changesThreadMethodCallController;

    private final SetEmailMethodCallController setEmailMethodCallController;
    private final SetMailboxMethodCallController setMailboxMethodCallController;
    private final SetIdentityMethodCallController setIdentityMethodCallController;

    private final Gson gson;

    @Inject
    public CorRequestDispatch(final EchoMethodCallController echoMethodCallController, final Gson gson,
            final GetEmailMethodCallController getEmailMethodCallController,
            final GetIdentityMethodCallController getIdentityMethodCallController,
            final GetMailboxMethodCallController getMailboxMethodCallController,
            final GetThreadMethodCallController getThreadMethodCallController,
            final GetEmailSubmissionMethodCallController getEmailSubmissionMethodCallController,
            final ChangesEmailMethodCallController changesEmailMethodCallController,
            final ChangesEmailSubmissionMethodCallController changesEmailSubmissionMethodCallController,
            final ChangesIdentityMethodCallController changesIdentityMethodCallController,
            final ChangesMailboxMethodCallController changesMailboxMethodCallController,
            final ChangesThreadMethodCallController changesThreadMethodCallController,
            final SetEmailMethodCallController setEmailMethodCallController,
            final SetIdentityMethodCallController setIdentityMethodCallController,
            final SetMailboxMethodCallController setMailboxMethodCallController) {

        this.echoMethodCallController = echoMethodCallController;

        this.getEmailMethodCallController = getEmailMethodCallController;
        this.getIdentityMethodCallController = getIdentityMethodCallController;
        this.getMailboxMethodCallController = getMailboxMethodCallController;
        this.getThreadMethodCallController = getThreadMethodCallController;
        this.getEmailSubmissionMethodCallController = getEmailSubmissionMethodCallController;

        this.changesEmailMethodCallController = changesEmailMethodCallController;
        this.changesEmailSubmissionMethodCallController = changesEmailSubmissionMethodCallController;
        this.changesIdentityMethodCallController = changesIdentityMethodCallController;
        this.changesMailboxMethodCallController = changesMailboxMethodCallController;
        this.changesThreadMethodCallController = changesThreadMethodCallController;
        this.setEmailMethodCallController = setEmailMethodCallController;
        this.setMailboxMethodCallController = setMailboxMethodCallController;
        this.setIdentityMethodCallController = setIdentityMethodCallController;

        this.gson = gson;

        setupCoR();
    }

    private void setupCoR() {
        echoMethodCallController.setNext(getEmailMethodCallController);

        getEmailMethodCallController.setNext(getIdentityMethodCallController);
        getIdentityMethodCallController.setNext(getMailboxMethodCallController);
        getMailboxMethodCallController.setNext(getThreadMethodCallController);
        getThreadMethodCallController.setNext(getEmailSubmissionMethodCallController);
        getEmailSubmissionMethodCallController.setNext(changesEmailMethodCallController);

        changesEmailMethodCallController.setNext(changesEmailSubmissionMethodCallController);
        changesEmailSubmissionMethodCallController.setNext(changesIdentityMethodCallController);
        changesIdentityMethodCallController.setNext(changesMailboxMethodCallController);
        changesMailboxMethodCallController.setNext(changesThreadMethodCallController);
        changesThreadMethodCallController.setNext(setEmailMethodCallController);

        setEmailMethodCallController.setNext(setMailboxMethodCallController);
        setMailboxMethodCallController.setNext(setIdentityMethodCallController);
    }

    @Override
    public String Dispatch(final String jmapRequest) throws JsonSyntaxException {
        final Request request = gson.fromJson(jmapRequest, Request.class);
        final Request.Invocation[] methodCalls = request.getMethodCalls();

        final ListMultimapAdapter<String, ResponseInvocationPort> previousResponses = new ListMultimapAdapter<String, ResponseInvocationPort>();

        for (final Request.Invocation invocation : methodCalls) {
            final String invocationId = invocation.getId();

            // Rename
            final MethodResponse methodResponses = echoMethodCallController.handle(
                    new HandlerRequest(invocation.getMethodCall(), previousResponses));

            previousResponses.put(invocationId,
                    new ResponseInvocationAdapter(new Response.Invocation(methodResponses, invocationId)));
        }

        final GenericResponse genericResponse = new Response(
                previousResponses.values().stream().map(ri -> ((ResponseInvocationAdapter) ri).adaptee())
                        .toArray(Response.Invocation[]::new),
                "0");

        return gson.toJson(genericResponse);
    }
}
