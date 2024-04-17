package it.qbsoftware.application.controllers.get;

import java.util.ArrayList;

import it.qbsoftware.adapters.in.jmaplib.method.call.get.GetMailboxMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.MethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.ports.in.usecase.get.GetMailboxMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.mailbox.GetMailboxMethodCall;

public class GetMailboxMethodCallController extends ControllerHandlerBase {

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetMailboxMethodCall getMailboxMethodCall) {

            GetMailboxMethodCallAdapter getMailboxMethodCallAdapter = new GetMailboxMethodCallAdapter(
                    getMailboxMethodCall);

            // TODO: fixme, il costruttore è completamente andato
            GetMailboxMethodCallUsecase getMailboxMethodCallService = null;

            MethodResponseAdapter[] methodResponseAdapters = (MethodResponseAdapter[]) getMailboxMethodCallService
                    .call(getMailboxMethodCallAdapter, handlerRequest.previousResponses());

            ArrayList<MethodResponse> methodResponseList = new ArrayList<>();

            for (MethodResponseAdapter methodResponseAdapter : methodResponseAdapters) {
                methodResponseList.add(methodResponseAdapter.methodResponse());
            }

            return methodResponseList.toArray(new MethodResponse[0]);
        }

        return super.handle(handlerRequest);
    }
}
