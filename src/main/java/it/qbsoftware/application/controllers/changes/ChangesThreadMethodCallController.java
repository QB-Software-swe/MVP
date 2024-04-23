package it.qbsoftware.application.controllers.changes;

import it.qbsoftware.adapters.in.jmaplib.method.call.changes.ChangesThreadMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.MethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesThreadMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.thread.ChangesThreadMethodCall;

import java.util.ArrayList;

public class ChangesThreadMethodCallController extends ControllerHandlerBase {
    private final ChangesThreadMethodCallUsecase changesThreadMethodCallUsecase;

    public ChangesThreadMethodCallController(final ChangesThreadMethodCallUsecase changesThreadMethodCallUsecase) {
        this.changesThreadMethodCallUsecase = changesThreadMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof ChangesThreadMethodCall changesThreadMethodCall) {
            final ChangesThreadMethodCallAdapter changesThreadMethodCallAdapter = new ChangesThreadMethodCallAdapter(
                    changesThreadMethodCall);

            final MethodResponseAdapter[] methodResponseAdapters = (MethodResponseAdapter[]) changesThreadMethodCallUsecase
                    .call(changesThreadMethodCallAdapter, handlerRequest.previousResponses());

            ArrayList<MethodResponse> methodResponseList = new ArrayList<>();

            for (MethodResponseAdapter methodResponseAdapter : methodResponseAdapters) {
                methodResponseList.add(methodResponseAdapter.adaptee());
            }

            return methodResponseList.toArray(MethodResponse[]::new);
        }
        return super.handle(handlerRequest);
    }
}
