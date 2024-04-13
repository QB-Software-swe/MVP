package it.qbsoftware.application.controllers;

import java.util.ArrayList;

import it.qbsoftware.adapters.jmaplib.GetIdentityMethodCallAdapter;
import it.qbsoftware.adapters.jmaplib.InvalidResultReferenceMethodErrorResponseAdapter;
import it.qbsoftware.adapters.jmaplib.MethodResponseAdapter;
import it.qbsoftware.adapters.jmaplib.GetIdentityMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.jmaplib.AccountStateRepositoryAdapter;
import it.qbsoftware.adapters.jmaplib.InvalidResultReferenceMethodErrorResponseAdapter;
import it.qbsoftware.adapters.jmaplib.ResultReferenceResolverAdapter;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.identity.GetIdentityMethodCall;
import rs.ltt.jmap.common.method.response.identity.GetIdentityMethodResponse.GetIdentityMethodResponseBuilder;
import it.qbsoftware.business.ports.in.jmap.utility.ResultReferenceResolverPort;
import it.qbsoftware.business.ports.in.usecase.GetIdentityMethodCallUsecase;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.services.GetIdentityMethodCallService;

public class GetIdentityMethodCallController extends ControllerHandlerBase{

    @Override
    public MethodResponse[] handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetIdentityMethodCall getIdentityMethodCall) {
            
            GetIdentityMethodCallAdapter getIdentityMethodCallAdapter = new GetIdentityMethodCallAdapter(getIdentityMethodCall);

            GetIdentityMethodCallUsecase getIdentityMethodCallService = new GetIdentityMethodCallService(new GetIdentityMethodResponseBuilderAdapter(), new AccountStateRepositoryAdapter(), new InvalidResultReferenceMethodErrorResponseAdapter(), new ResultReferenceResolverAdapter());

            MethodResponseAdapter[] methodResponseAdapters = (MethodResponseAdapter[]) getIdentityMethodCallService.call(getIdentityMethodCallAdapter, handlerRequest.previousResponses());

            ArrayList<MethodResponse> methodResponseList = new ArrayList<>();

            for (MethodResponseAdapter methodResponseAdapter : methodResponseAdapters) {
                methodResponseList.add(methodResponseAdapter.methodResponse());
            }

            return methodResponseList.toArray(new MethodResponse[0]);
        }

        return super.handle(handlerRequest);
    }

}
