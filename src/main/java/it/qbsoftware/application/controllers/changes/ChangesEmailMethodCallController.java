package it.qbsoftware.application.controllers.changes;

import java.util.ArrayList;

import it.qbsoftware.adapters.in.jmaplib.method.call.changes.ChangesEmailMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.AbstracMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesEmailMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.ChangesEmailMethodCall;

public class ChangesEmailMethodCallController extends ControllerHandlerBase {
    private final ChangesEmailMethodCallUsecase changesEmailMethodCallUsecase;

    public ChangesEmailMethodCallController(final ChangesEmailMethodCallUsecase changesEmailMethodCallUsecase) {
        this.changesEmailMethodCallUsecase = changesEmailMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof ChangesEmailMethodCall changesEmailMethodCall) {
            final ChangesEmailMethodCallAdapter ChangesEmailMethodCallAdapter = new ChangesEmailMethodCallAdapter(
                    changesEmailMethodCall);

            final AbstracMethodResponseAdapter[] methodResponseAdapters = (AbstracMethodResponseAdapter[]) changesEmailMethodCallUsecase
                    .call(ChangesEmailMethodCallAdapter, handlerRequest.previousResponses());

            ArrayList<MethodResponse> methodResponseList = new ArrayList<>();

            for (AbstracMethodResponseAdapter methodResponseAdapter : methodResponseAdapters) {
                methodResponseList.add(methodResponseAdapter.adaptee());
            }

            return methodResponseList.toArray(MethodResponse[]::new);
        }
        return super.handle(handlerRequest);
    }
}
