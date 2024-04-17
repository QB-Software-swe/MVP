package it.qbsoftware.application.controllers.get;

import java.util.ArrayList;

import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.identity.GetIdentityMethodCall;
import it.qbsoftware.adapters.in.jmaplib.error.InvalidResultReferenceMethodErrorResponseAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.call.get.GetIdentityMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.MethodResponseAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetIdentityMethodResponseBuilderAdapter;
import it.qbsoftware.adapters.in.jmaplib.util.ResultReferenceResolverAdapter;
import it.qbsoftware.adapters.out.AccountStateRepositoryAdapter;
import it.qbsoftware.adapters.out.IdentityRepositoryAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
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
