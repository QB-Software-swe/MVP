package it.qbsoftware.application.controllers.changes;

import it.qbsoftware.adapters.in.jmaplib.method.call.changes.ChangesMailboxMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.AbstracMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesMailboxMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.mailbox.ChangesMailboxMethodCall;

import java.util.ArrayList;

public class ChangesMailboxMethodCallController extends ControllerHandlerBase {
    private final ChangesMailboxMethodCallUsecase changesMailboxMethodCallUsecase;

    public ChangesMailboxMethodCallController(final ChangesMailboxMethodCallUsecase changesMailboxMethodCallUsecase) {
        this.changesMailboxMethodCallUsecase = changesMailboxMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof ChangesMailboxMethodCall changesMailboxMethodCall) {
            final ChangesMailboxMethodCallAdapter changesMailboxMethodCallAdapter = new ChangesMailboxMethodCallAdapter(
                    changesMailboxMethodCall);

            final AbstracMethodResponseAdapter[] methodResponseAdapters = (AbstracMethodResponseAdapter[]) changesMailboxMethodCallUsecase
                    .call(changesMailboxMethodCallAdapter, handlerRequest.previousResponses());

            ArrayList<MethodResponse> methodResponseList = new ArrayList<>();

            for (AbstracMethodResponseAdapter methodResponseAdapter : methodResponseAdapters) {
                methodResponseList.add(methodResponseAdapter.adaptee());
            }

            return methodResponseList.toArray(MethodResponse[]::new);
        }
        return super.handle(handlerRequest);
    }
}
