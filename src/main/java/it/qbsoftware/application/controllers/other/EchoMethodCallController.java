package it.qbsoftware.application.controllers.other;

import it.qbsoftware.adapters.in.jmaplib.method.call.other.EchoMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.other.EchoMethodResponseAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.other.EchoMethodResponseBuilderAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.ports.in.usecase.EchoMethodCallUsecase;
import it.qbsoftware.business.services.EchoMethodCallSerivce;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.core.EchoMethodCall;

public class EchoMethodCallController extends ControllerHandlerBase {

    @Override
    public MethodResponse handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof EchoMethodCall echoMethodCall) {
            EchoMethodCallAdapter echoMethodCallAdapter = new EchoMethodCallAdapter(echoMethodCall);

            EchoMethodCallUsecase echoMethodCallSerivce = new EchoMethodCallSerivce(
                    new EchoMethodResponseBuilderAdapter());

            final EchoMethodResponseAdapter echoMethodResponseAdapter = (EchoMethodResponseAdapter) echoMethodCallSerivce
                    .call(echoMethodCallAdapter);

            return echoMethodResponseAdapter.adaptee();
        }

        return super.handle(handlerRequest);
    }
}