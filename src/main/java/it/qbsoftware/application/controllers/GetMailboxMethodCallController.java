package it.qbsoftware.application.controllers;

import it.qbsoftware.adapters.jmaplib.AccountStateRepositoryAdapter;
import it.qbsoftware.adapters.jmaplib.GetMailboxMethodCallAdapter;
import it.qbsoftware.adapters.jmaplib.GetMailboxMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.jmaplib.MailboxBuilderAdapter;
import it.qbsoftware.adapters.jmaplib.MailboxInfoRepositoryAdapter;
import it.qbsoftware.adapters.jmaplib.MethodResponseAdapter;
import it.qbsoftware.business.ports.in.usecase.GetMailboxMethodCallUsecase;
import it.qbsoftware.business.services.GetMailboxMethodCallService;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.mailbox.GetMailboxMethodCall;

import java.util.ArrayList;

public class GetMailboxMethodCallController extends ControllerHandlerBase{

    @Override
    public MethodResponse[] handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetMailboxMethodCall getMailboxMethodCall) {
            
            GetMailboxMethodCallAdapter getMailboxMethodCallAdapter = new GetMailboxMethodCallAdapter(getMailboxMethodCall);

            GetMailboxMethodCallUsecase getMailboxMethodCallService = new GetMailboxMethodCallService(new MailboxInfoRepositoryAdapter(), new MailboxBuilderAdapter(), new GetMailboxMethodResponseBuilderAdapter(), new AccountStateRepositoryAdapter());

            MethodResponseAdapter[] methodResponseAdapters = (MethodResponseAdapter[]) getMailboxMethodCallService.call(getMailboxMethodCallAdapter, handlerRequest.previousResponses());

            ArrayList<MethodResponse> methodResponseList = new ArrayList<>();

            for (MethodResponseAdapter methodResponseAdapter : methodResponseAdapters) {
                methodResponseList.add(methodResponseAdapter.methodResponse());
            }

            return methodResponseList.toArray(new MethodResponse[0]);
        }

        return super.handle(handlerRequest);
    }
}
