package it.qbsoftware.application.controllers.set;

import com.google.inject.Inject;
import it.qbsoftware.adapters.in.jmaplib.method.call.set.SetEmailMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.set.SetEmailMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.StateMismatchException;
import it.qbsoftware.business.ports.in.usecase.set.SetEmailMethodCallUsecase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.SetEmailMethodCall;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.StateMismatchMethodErrorResponse;

public class SetEmailMethodCallController extends ControllerHandlerBase {
    private final Logger logger = LoggerFactory.getLogger(SetEmailMethodCallController.class);
    private final SetEmailMethodCallUsecase setEmailMethodCallUsecase;

    @Inject
    public SetEmailMethodCallController(final SetEmailMethodCallUsecase setEmailMethodCallUsecase) {
        this.setEmailMethodCallUsecase = setEmailMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof SetEmailMethodCall setEmailMethodCall) {
            final SetEmailMethodCallAdapter setEmailMethodCallAdapter =
                    new SetEmailMethodCallAdapter(setEmailMethodCall);
            logger.info("Match and handle method call recived");
            try {
                final SetEmailMethodResponseAdapter setEmailMethodResponseAdapter =
                        (SetEmailMethodResponseAdapter)
                                setEmailMethodCallUsecase.call(
                                        setEmailMethodCallAdapter,
                                        handlerRequest.previousResponses());

                return new MethodResponse[] {setEmailMethodResponseAdapter.adaptee()};
            } catch (final AccountNotFoundException accountNotFoundException) {
                return new MethodResponse[] {new InvalidArgumentsMethodErrorResponse()};
            } catch (final StateMismatchException stateMismatchException) {
                return new MethodResponse[] {new StateMismatchMethodErrorResponse()};
            }
        }
        return super.handle(handlerRequest);
    }
}
