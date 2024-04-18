package it.qbsoftware.application.controllers.get;

import java.util.ArrayList;

import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.identity.GetIdentityMethodCall;
import it.qbsoftware.adapters.in.jmaplib.method.call.get.GetIdentityMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.MethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.ports.in.usecase.get.GetIdentityMethodCallUsecase;

public class GetIdentityMethodCallController extends ControllerHandlerBase {

    @Override
    public MethodResponse[] handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetIdentityMethodCall getIdentityMethodCall) {

            GetIdentityMethodCallAdapter getIdentityMethodCallAdapter = new GetIdentityMethodCallAdapter(
                    getIdentityMethodCall);

            GetIdentityMethodCallUsecase getIdentityMethodCallService = null;

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
