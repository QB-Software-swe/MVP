package it.qbsoftware.application.controllers.get;

import java.util.ArrayList;

import it.qbsoftware.adapters.in.jmaplib.method.call.get.GetThreadMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.AbstracMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.ports.in.usecase.get.GetThreadMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.thread.GetThreadMethodCall;

public class GetThreadMethodCallController extends ControllerHandlerBase {
    private final GetThreadMethodCallUsecase getThreadMethodCallUsecase;

    public GetThreadMethodCallController(final GetThreadMethodCallUsecase getThreadMethodCallUsecase) {
        this.getThreadMethodCallUsecase = getThreadMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetThreadMethodCall getThreadMethodCall) {
            final GetThreadMethodCallAdapter getThreadMethodCallAdapter = new GetThreadMethodCallAdapter(
                    getThreadMethodCall);

            final AbstracMethodResponseAdapter[] methodResponseAdapters = (AbstracMethodResponseAdapter[]) getThreadMethodCallUsecase
                    .call(getThreadMethodCallAdapter, handlerRequest.previousResponses());

            ArrayList<MethodResponse> methodResponseList = new ArrayList<>();

            for (AbstracMethodResponseAdapter methodResponseAdapter : methodResponseAdapters) {
                methodResponseList.add(methodResponseAdapter.adaptee());
            }

            return methodResponseList.toArray(MethodResponse[]::new);
        }
        return super.handle(handlerRequest);
    }
}
