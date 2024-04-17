package it.qbsoftware.application.controllers.other;

import it.qbsoftware.adapters.in.jmaplib.method.call.other.EchoMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.MethodResponseAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.other.EchoMethodResponseBuilderAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.ports.in.usecase.EchoMethodCallUsecase;
import it.qbsoftware.business.services.EchoMethodCallSerivce;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.core.EchoMethodCall;
import java.util.ArrayList;

public class EchoMethodCallController extends ControllerHandlerBase {

    @Override
    public MethodResponse[] handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof EchoMethodCall echoMethodCall) {
            EchoMethodCallAdapter echoMethodCallAdapter = new EchoMethodCallAdapter(echoMethodCall);

            EchoMethodCallUsecase echoMethodCallSerivce = new EchoMethodCallSerivce(
                    new EchoMethodResponseBuilderAdapter());

            MethodResponseAdapter[] methodResponseAdapters = (MethodResponseAdapter[]) echoMethodCallSerivce
                    .call(echoMethodCallAdapter);

            ArrayList<MethodResponse> methodResponseList = new ArrayList<>();

            for (MethodResponseAdapter methodResponseAdapter : methodResponseAdapters) {
                methodResponseList.add(methodResponseAdapter.methodResponse());
            }

            return methodResponseList.toArray(new MethodResponse[0]);
        }

        return super.handle(handlerRequest);
    }
}