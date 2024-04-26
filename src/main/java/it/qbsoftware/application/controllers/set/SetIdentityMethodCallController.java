package it.qbsoftware.application.controllers.set;

import it.qbsoftware.adapters.in.jmaplib.method.call.set.SetIdentityMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.MethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.ports.in.usecase.set.SetIdentityMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.identity.SetIdentityMethodCall;

import java.util.ArrayList;

public class SetIdentityMethodCallController extends ControllerHandlerBase {
    private final SetIdentityMethodCallUsecase setIdentityMethodCallUsecase;

    public SetIdentityMethodCallController(final SetIdentityMethodCallUsecase setIdentityMethodCallUsecase) {
        this.setIdentityMethodCallUsecase = setIdentityMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof SetIdentityMethodCall setIdentityMethodCall) {
            final SetIdentityMethodCallAdapter setIdentityMethodCallAdapter = new SetIdentityMethodCallAdapter(
                    setIdentityMethodCall);

            final MethodResponseAdapter[] methodResponseAdapters = (MethodResponseAdapter[]) setIdentityMethodCallUsecase
                    .call(setIdentityMethodCallAdapter, handlerRequest.previousResponses());

            ArrayList<MethodResponse> methodResponseList = new ArrayList<>();

            for (MethodResponseAdapter methodResponseAdapter : methodResponseAdapters) {
                methodResponseList.add(methodResponseAdapter.adaptee());
            }

            return methodResponseList.toArray(MethodResponse[]::new);
        }
        return super.handle(handlerRequest);
    }
}
