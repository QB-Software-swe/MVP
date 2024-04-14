package it.qbsoftware.application.controllers;

import java.util.ArrayList;

import it.qbsoftware.adapters.jmaplib.GetIdentityMethodCallAdapter;
import it.qbsoftware.adapters.jmaplib.InvalidResultReferenceMethodErrorResponseAdapter;
import it.qbsoftware.adapters.jmaplib.MethodResponseAdapter;
import it.qbsoftware.adapters.jmaplib.utils.ResultReferenceResolverAdapter;
import it.qbsoftware.adapters.jmaplib.GetIdentityMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.jmaplib.IdentityRepositoryAdapter;
import it.qbsoftware.adapters.jmaplib.AccountStateRepositoryAdapter;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.identity.GetIdentityMethodCall;
import it.qbsoftware.business.ports.in.usecase.get.GetIdentityMethodCallUsecase;
import it.qbsoftware.business.services.get.GetIdentityMethodCallService;

public class GetIdentityMethodCallController extends ControllerHandlerBase {

    @Override
    public MethodResponse[] handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetIdentityMethodCall getIdentityMethodCall) {

            GetIdentityMethodCallAdapter getIdentityMethodCallAdapter = new GetIdentityMethodCallAdapter(
                    getIdentityMethodCall);

            GetIdentityMethodCallUsecase getIdentityMethodCallService = new GetIdentityMethodCallService(
                    new IdentityRepositoryAdapter(), new GetIdentityMethodResponseBuilderAdapter(),
                    new AccountStateRepositoryAdapter(),
                    new InvalidResultReferenceMethodErrorResponseAdapter(), new ResultReferenceResolverAdapter(), null,
                    null); // FIXME: null null

            MethodResponseAdapter[] methodResponseAdapters = (MethodResponseAdapter[]) getIdentityMethodCallService
                    .call(getIdentityMethodCallAdapter, handlerRequest.previousResponses());

            ArrayList<MethodResponse> methodResponseList = new ArrayList<>();

            for (MethodResponseAdapter methodResponseAdapter : methodResponseAdapters) {
                methodResponseList.add(methodResponseAdapter.methodResponse());
            }

            return methodResponseList.toArray(new MethodResponse[0]);
        }

        return super.handle(handlerRequest);
    }

}
