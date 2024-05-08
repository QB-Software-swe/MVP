package it.qbsoftware.application.controllers.get;

import com.google.inject.Inject;
import it.qbsoftware.adapters.in.jmaplib.method.call.get.GetThreadMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetThreadMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.ports.in.usecase.get.GetThreadMethodCallUsecase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.thread.GetThreadMethodCall;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidResultReferenceMethodErrorResponse;

public class GetThreadMethodCallController extends ControllerHandlerBase {
    private final Logger logger = LoggerFactory.getLogger(GetThreadMethodCallController.class);
    private final GetThreadMethodCallUsecase getThreadMethodCallUsecase;

    @Inject
    public GetThreadMethodCallController(
            final GetThreadMethodCallUsecase getThreadMethodCallUsecase) {
        this.getThreadMethodCallUsecase = getThreadMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetThreadMethodCall getThreadMethodCall) {
            final GetThreadMethodCallAdapter getThreadMethodCallAdapter =
                    new GetThreadMethodCallAdapter(getThreadMethodCall);
            logger.info("Match and handle method call recived");
            try {
                final GetThreadMethodResponseAdapter methodResponseAdapters =
                        (GetThreadMethodResponseAdapter)
                                getThreadMethodCallUsecase.call(
                                        getThreadMethodCallAdapter,
                                        handlerRequest.previousResponses());

                return new MethodResponse[] {methodResponseAdapters.adaptee()};
            } catch (final AccountNotFoundException accountNotFoundException) {
                return new MethodResponse[] {new InvalidArgumentsMethodErrorResponse()};
            } catch (final InvalidResultReferenceExecption invalidResultReferenceExecption) {
                return new MethodResponse[] {new InvalidResultReferenceMethodErrorResponse()};
            } catch (final InvalidArgumentsException invalidArgumentsException) {
                return new MethodResponse[] {new InvalidArgumentsMethodErrorResponse()};
            }
        }
        return super.handle(handlerRequest);
    }
}
