package it.qbsoftware.application.controllers.set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import it.qbsoftware.adapters.in.jmaplib.method.call.set.SetMailboxMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.set.SetMailboxMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.application.controllers.changes.ChangesEmailSubmissionMethodCallController;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.StateMismatchException;
import it.qbsoftware.business.ports.in.usecase.set.SetMailboxMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.mailbox.SetMailboxMethodCall;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.StateMismatchMethodErrorResponse;

public class SetMailboxMethodCallController extends ControllerHandlerBase {
    private final Logger logger = LoggerFactory.getLogger(SetMailboxMethodCallController.class);
    private final SetMailboxMethodCallUsecase setMailboxMethodCallUsecase;

    @Inject
    public SetMailboxMethodCallController(final SetMailboxMethodCallUsecase setMailboxMethodCall) {
        this.setMailboxMethodCallUsecase = setMailboxMethodCall;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof SetMailboxMethodCall setMailboxMethodCall) {

            final SetMailboxMethodCallAdapter setMailboxMethodCallAdapter = new SetMailboxMethodCallAdapter(
                    setMailboxMethodCall);
                    logger.info("Match and handle method call recived");
            try {
                final SetMailboxMethodResponseAdapter setMailboxMethodResponseAdapter = (SetMailboxMethodResponseAdapter) setMailboxMethodCallUsecase
                        .call(setMailboxMethodCallAdapter, handlerRequest.previousResponses());

                return new MethodResponse[] { setMailboxMethodResponseAdapter.adaptee() };
            } catch (final AccountNotFoundException accountNotFoundException) {
                return new MethodResponse[] { new InvalidArgumentsMethodErrorResponse() };
            } catch (final StateMismatchException stateMismatchException) {
                return new MethodResponse[] { new StateMismatchMethodErrorResponse() };
            }
        }
        return super.handle(handlerRequest);
    }
}
