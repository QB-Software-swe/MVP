package it.qbsoftware.application.controllers.get;

import java.util.ArrayList;

import com.google.inject.Inject;

import it.qbsoftware.adapters.in.jmaplib.method.call.get.GetMailboxMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.AbstracMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.ports.in.usecase.get.GetMailboxMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.mailbox.GetMailboxMethodCall;

public class GetMailboxMethodCallController extends ControllerHandlerBase {
    private final GetMailboxMethodCallUsecase getMailboxMethodCallService;

    @Inject
    public GetMailboxMethodCallController(final GetMailboxMethodCallUsecase getMailboxMethodCallService) {
        this.getMailboxMethodCallService = getMailboxMethodCallService;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetMailboxMethodCall getMailboxMethodCall) {

            GetMailboxMethodCallAdapter getMailboxMethodCallAdapter = new GetMailboxMethodCallAdapter(
                    getMailboxMethodCall);

            AbstracMethodResponseAdapter[] methodResponseAdapters = (AbstracMethodResponseAdapter[]) getMailboxMethodCallService
                    .call(getMailboxMethodCallAdapter, handlerRequest.previousResponses());

            ArrayList<MethodResponse> methodResponseList = new ArrayList<>();

            for (AbstracMethodResponseAdapter methodResponseAdapter : methodResponseAdapters) {
                methodResponseList.add(methodResponseAdapter.adaptee());
            }

            return methodResponseList.toArray(MethodResponse[]::new);
        }

        return super.handle(handlerRequest);
    }
}
