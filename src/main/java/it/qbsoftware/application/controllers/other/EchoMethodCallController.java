package it.qbsoftware.application.controllers.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import it.qbsoftware.adapters.in.jmaplib.method.call.other.EchoMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.other.EchoMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.application.controllers.changes.ChangesEmailSubmissionMethodCallController;
import it.qbsoftware.business.ports.in.usecase.EchoMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.core.EchoMethodCall;

public class EchoMethodCallController extends ControllerHandlerBase {
    private final Logger logger = LoggerFactory.getLogger(EchoMethodCallController.class);
    private final EchoMethodCallUsecase echoMethodCallUsecase;

    @Inject
    public EchoMethodCallController(final EchoMethodCallUsecase echoMethodCallUsecase) {
        this.echoMethodCallUsecase = echoMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof EchoMethodCall echoMethodCall) {
            logger.info("Match and handle method call recived");
            final EchoMethodCallAdapter echoMethodCallAdapter = new EchoMethodCallAdapter(echoMethodCall);

            final EchoMethodResponseAdapter echoMethodResponseAdapter = (EchoMethodResponseAdapter) echoMethodCallUsecase
                    .call(echoMethodCallAdapter);

            return new MethodResponse[] { echoMethodResponseAdapter.adaptee() };
        }

        return super.handle(handlerRequest);
    }
}