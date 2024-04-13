package it.qbsoftware.application.controllers;

import java.util.ArrayList;

import it.qbsoftware.adapters.jmaplib.EmailBuilderAdapter;
import it.qbsoftware.adapters.jmaplib.EmailRepositoryAdapter;
import it.qbsoftware.adapters.jmaplib.GetEmailMethodCallAdapter;
import it.qbsoftware.adapters.jmaplib.GetEmailMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.jmaplib.InvalidResultReferenceMethodErrorResponseAdapter;
import it.qbsoftware.adapters.jmaplib.MethodResponseAdapter;
import it.qbsoftware.adapters.jmaplib.ResultReferenceAdapter;
import it.qbsoftware.business.ports.in.usecase.GetEmailMethodCallUsecase;
import it.qbsoftware.business.services.GetEmailMethodCallService;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.GetEmailMethodCall;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;

public class GetEmailMethodCallController extends ControllerHandlerBase{
    @Override
    public MethodResponse[] handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetEmailMethodCall getEmailMethodCall) {
            
            GetEmailMethodCallAdapter getEmailMethodCallAdapter = new GetEmailMethodCallAdapter(getEmailMethodCall);

            GetEmailMethodCallUsecase getEmailMethodCallService = new GetEmailMethodCallService(new EmailRepositoryAdapter(), new InvalidResultReferenceMethodErrorResponseAdapter(), new ResultReferenceAdapter(), new GetEmailMethodResponseBuilderAdapter(), new EmailBuilderAdapter());

            MethodResponseAdapter[] methodResponseAdapters = (MethodResponseAdapter[]) getEmailMethodCallService.call(getEmailMethodCallAdapter, handlerRequest.previousResponses());

            ArrayList<MethodResponse> methodResponseList = new ArrayList<>();

            for (MethodResponseAdapter methodResponseAdapter : methodResponseAdapters) {
                methodResponseList.add(methodResponseAdapter.methodResponse());
            }

            return methodResponseList.toArray(new MethodResponse[0]);
        }

        return super.handle(handlerRequest);
    }
}

