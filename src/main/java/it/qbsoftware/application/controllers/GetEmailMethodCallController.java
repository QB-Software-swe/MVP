package it.qbsoftware.application.controllers;

import java.util.ArrayList;

import it.qbsoftware.adapters.jmaplib.AccountStateRepositoryAdapter;
import it.qbsoftware.adapters.jmaplib.EmailBuilderAdapter;
import it.qbsoftware.adapters.jmaplib.EmailRepositoryAdapter;
import it.qbsoftware.adapters.jmaplib.GetEmailMethodCallAdapter;
import it.qbsoftware.adapters.jmaplib.GetEmailMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.jmaplib.InvalidResultReferenceMethodErrorResponseAdapter;
import it.qbsoftware.adapters.jmaplib.MethodResponseAdapter;
import it.qbsoftware.adapters.jmaplib.error.InvalidArgumentsMethodErrorResponseAdapter;
import it.qbsoftware.adapters.jmaplib.utils.ResultReferenceResolverAdapter;
import it.qbsoftware.business.ports.in.usecase.get.GetEmailMethodCallUsecase;
import it.qbsoftware.business.services.get.GetEmailMethodCallService;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.GetEmailMethodCall;

public class GetEmailMethodCallController extends ControllerHandlerBase {
    @Override
    public MethodResponse[] handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetEmailMethodCall getEmailMethodCall) {

            GetEmailMethodCallAdapter getEmailMethodCallAdapter = new GetEmailMethodCallAdapter(getEmailMethodCall);

            GetEmailMethodCallUsecase getEmailMethodCallService = new GetEmailMethodCallService(
                    new EmailRepositoryAdapter(), new InvalidResultReferenceMethodErrorResponseAdapter(),
                    new ResultReferenceResolverAdapter(), new GetEmailMethodResponseBuilderAdapter(),
                    new EmailBuilderAdapter(), new InvalidArgumentsMethodErrorResponseAdapter(),
                    new AccountStateRepositoryAdapter());

            MethodResponseAdapter[] methodResponseAdapters = (MethodResponseAdapter[]) getEmailMethodCallService
                    .call(getEmailMethodCallAdapter, handlerRequest.previousResponses());

            ArrayList<MethodResponse> methodResponseList = new ArrayList<>();

            for (MethodResponseAdapter methodResponseAdapter : methodResponseAdapters) {
                methodResponseList.add(methodResponseAdapter.methodResponse());
            }

            return methodResponseList.toArray(new MethodResponse[0]);
        }

        return super.handle(handlerRequest);
    }
}
