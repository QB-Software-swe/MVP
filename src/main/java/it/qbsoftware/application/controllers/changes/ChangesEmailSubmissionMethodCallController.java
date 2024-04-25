package it.qbsoftware.application.controllers.changes;

import java.util.ArrayList;

import it.qbsoftware.adapters.in.jmaplib.method.call.changes.ChangesEmailSubmissionMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.AbstracMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesEmailSubmissionMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.submission.ChangesEmailSubmissionMethodCall;

public class ChangesEmailSubmissionMethodCallController extends ControllerHandlerBase {
    private final ChangesEmailSubmissionMethodCallUsecase changesEmailSubmissionMethodCallUsecase;

    public ChangesEmailSubmissionMethodCallController(
            final ChangesEmailSubmissionMethodCallUsecase changesEmailSubmissionMethodCallUsecase) {
        this.changesEmailSubmissionMethodCallUsecase = changesEmailSubmissionMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof ChangesEmailSubmissionMethodCall changesEmailSubmissionMethodCall) {
            final ChangesEmailSubmissionMethodCallAdapter changesEmailSubmissionMethodCallAdapter = new ChangesEmailSubmissionMethodCallAdapter(
                    changesEmailSubmissionMethodCall);

            final AbstracMethodResponseAdapter[] methodResponseAdapters = (AbstracMethodResponseAdapter[]) changesEmailSubmissionMethodCallUsecase
                    .call(changesEmailSubmissionMethodCallAdapter, handlerRequest.previousResponses());

            ArrayList<MethodResponse> methodResponseList = new ArrayList<>();

            for (AbstracMethodResponseAdapter methodResponseAdapter : methodResponseAdapters) {
                methodResponseList.add(methodResponseAdapter.adaptee());
            }

            return methodResponseList.toArray(MethodResponse[]::new);
        }
        return super.handle(handlerRequest);
    }
}
