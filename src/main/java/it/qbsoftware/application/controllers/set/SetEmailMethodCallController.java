package it.qbsoftware.application.controllers.set;

import it.qbsoftware.adapters.in.jmaplib.method.call.set.SetEmailMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.AbstracMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.ports.in.usecase.set.SetEmailMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.SetEmailMethodCall;

import java.util.ArrayList;

public class SetEmailMethodCallController extends ControllerHandlerBase {
    private final SetEmailMethodCallUsecase setEmailMethodCallUsecase;

    public SetEmailMethodCallController(final SetEmailMethodCallUsecase setEmailMethodCallUsecase) {
        this.setEmailMethodCallUsecase = setEmailMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof SetEmailMethodCall setEmailMethodCall) {
            final SetEmailMethodCallAdapter setEmailMethodCallAdapter = new SetEmailMethodCallAdapter(
                    setEmailMethodCall);

            final AbstracMethodResponseAdapter[] methodResponseAdapters = (AbstracMethodResponseAdapter[]) setEmailMethodCallUsecase
                    .call(setEmailMethodCallAdapter, handlerRequest.previousResponses());

            ArrayList<MethodResponse> methodResponseList = new ArrayList<>();

            for (AbstracMethodResponseAdapter methodResponseAdapter : methodResponseAdapters) {
                methodResponseList.add(methodResponseAdapter.adaptee());
            }

            return methodResponseList.toArray(MethodResponse[]::new);
        }
        return super.handle(handlerRequest);
    }
}
