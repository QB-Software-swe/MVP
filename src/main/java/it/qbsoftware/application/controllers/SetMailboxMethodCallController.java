package it.qbsoftware.application.controllers;

import java.util.ArrayList;

import it.qbsoftware.adapters.in.jmaplib.MailboxBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.MailboxInfoRepositoryAdapter;
import it.qbsoftware.adapters.in.jmaplib.MethodResponseAdapter;
import it.qbsoftware.adapters.in.jmaplib.RoleAdapter;
import it.qbsoftware.adapters.in.jmaplib.SetMailboxMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.SetMailboxMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.error.SetErrorAdapter;
import it.qbsoftware.adapters.in.jmaplib.error.StateMismatchMethodErrorResponseAdapter;
import it.qbsoftware.adapters.in.jmaplib.utils.CaseFormatAdapter;
import it.qbsoftware.adapters.in.jmaplib.utils.SplitterAdapter;
import it.qbsoftware.business.ports.in.usecase.set.SetMailboxMethodCallUsecase;
import it.qbsoftware.business.services.set.SetMailboxMethodCallService;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.mailbox.SetMailboxMethodCall;

public class SetMailboxMethodCallController extends ControllerHandlerBase {
    @Override
    public MethodResponse[] handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof SetMailboxMethodCall setMailboxMethodCall) {

            SetMailboxMethodCallAdapter setMailboxMethodCallAdapter = new SetMailboxMethodCallAdapter(
                    setMailboxMethodCall);

            SetMailboxMethodCallUsecase setMailboxMethodCallService = new SetMailboxMethodCallService(
                    new SetMailboxMethodResponseBuilderAdapter(), new StateMismatchMethodErrorResponseAdapter(),
                    new MailboxBuilderAdapter(), new MailboxInfoRepositoryAdapter(), new SetErrorAdapter(),
                    new SplitterAdapter(), new CaseFormatAdapter(), new RoleAdapter(null));

            MethodResponseAdapter[] methodResponseAdapters = (MethodResponseAdapter[]) setMailboxMethodCallService
                    .call(setMailboxMethodCallAdapter);

            ArrayList<MethodResponse> methodResponseList = new ArrayList<>();

            for (MethodResponseAdapter methodResponseAdapter : methodResponseAdapters) {
                methodResponseList.add(methodResponseAdapter.methodResponse());
            }

            return methodResponseList.toArray(new MethodResponse[0]);
        }

        return super.handle(handlerRequest);
    }
}
