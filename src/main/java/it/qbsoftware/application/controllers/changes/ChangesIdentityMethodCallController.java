package it.qbsoftware.application.controllers.changes;

import it.qbsoftware.adapters.in.jmaplib.method.call.changes.ChangesIdentityMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.AbstracMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesIdentityMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.identity.ChangesIdentityMethodCall;

import java.util.ArrayList;

public class ChangesIdentityMethodCallController extends ControllerHandlerBase {
    private final ChangesIdentityMethodCallUsecase changesIdentityMethodCallUsecase;

    public ChangesIdentityMethodCallController(
            final ChangesIdentityMethodCallUsecase changesIdentityMethodCallUsecase) {
        this.changesIdentityMethodCallUsecase = changesIdentityMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof ChangesIdentityMethodCall changesIdentityMethodCall) {
            final ChangesIdentityMethodCallAdapter changesIdentityMethodCallAdapter = new ChangesIdentityMethodCallAdapter(
                    changesIdentityMethodCall);

            final AbstracMethodResponseAdapter[] methodResponseAdapters = (AbstracMethodResponseAdapter[]) changesIdentityMethodCallUsecase
                    .call(changesIdentityMethodCallAdapter, handlerRequest.previousResponses());

            ArrayList<MethodResponse> methodResponseList = new ArrayList<>();

            for (AbstracMethodResponseAdapter methodResponseAdapter : methodResponseAdapters) {
                methodResponseList.add(methodResponseAdapter.adaptee());
            }

            return methodResponseList.toArray(MethodResponse[]::new);
        }
        return super.handle(handlerRequest);
    }
}
