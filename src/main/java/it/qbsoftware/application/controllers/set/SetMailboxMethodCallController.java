package it.qbsoftware.application.controllers.set;

import java.util.ArrayList;

import it.qbsoftware.adapters.in.jmaplib.method.call.set.SetMailboxMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.MethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.ports.in.usecase.set.SetMailboxMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.mailbox.SetMailboxMethodCall;

public class SetMailboxMethodCallController extends ControllerHandlerBase {
    private final SetMailboxMethodCallUsecase setMailboxMethodCallUsecase;

    public SetMailboxMethodCallController(final SetMailboxMethodCallUsecase setMailboxMethodCall) {
        this.setMailboxMethodCallUsecase = setMailboxMethodCall;
    }

    @Override
    public MethodResponse[] handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof SetMailboxMethodCall setMailboxMethodCall) {

            SetMailboxMethodCallAdapter setMailboxMethodCallAdapter = new SetMailboxMethodCallAdapter(
                    setMailboxMethodCall);

            MethodResponseAdapter[] methodResponseAdapters = (MethodResponseAdapter[]) setMailboxMethodCallUsecase
                    .call(setMailboxMethodCallAdapter, handlerRequest.previousResponses());

            ArrayList<MethodResponse> methodResponseList = new ArrayList<>();

            for (MethodResponseAdapter methodResponseAdapter : methodResponseAdapters) {
                methodResponseList.add(methodResponseAdapter.adaptee());
            }

            return methodResponseList.toArray(new MethodResponse[0]);
        }

        return super.handle(handlerRequest);
    }
}
