package it.qbsoftware.application.controllers.changes;

import com.google.inject.Inject;
import it.qbsoftware.adapters.in.jmaplib.method.call.changes.ChangesMailboxMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.changes.ChangesMailboxMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesMailboxMethodCallUsecase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.mailbox.ChangesMailboxMethodCall;
import rs.ltt.jmap.common.method.error.CannotCalculateChangesMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;

public class ChangesMailboxMethodCallController extends ControllerHandlerBase {
    private final Logger logger = LoggerFactory.getLogger(ChangesEmailMethodCallController.class);
    private final ChangesMailboxMethodCallUsecase changesMailboxMethodCallUsecase;

    @Inject
    public ChangesMailboxMethodCallController(
            final ChangesMailboxMethodCallUsecase changesMailboxMethodCallUsecase) {
        this.changesMailboxMethodCallUsecase = changesMailboxMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall()
                instanceof ChangesMailboxMethodCall changesMailboxMethodCall) {
            final ChangesMailboxMethodCallAdapter changesMailboxMethodCallAdapter =
                    new ChangesMailboxMethodCallAdapter(changesMailboxMethodCall);
            logger.info("Match and handle method call recived");
            try {
                final ChangesMailboxMethodResponseAdapter changesMailboxMethodResponseAdapter =
                        (ChangesMailboxMethodResponseAdapter)
                                changesMailboxMethodCallUsecase.call(
                                        changesMailboxMethodCallAdapter,
                                        handlerRequest.previousResponses());

                return new MethodResponse[] {changesMailboxMethodResponseAdapter.adaptee()};
            } catch (final AccountNotFoundException accountNotFoundException) {
                return new MethodResponse[] {new InvalidArgumentsMethodErrorResponse()};
            } catch (final CannotCalculateChangesException CannotCalculateChangesException) {
                return new MethodResponse[] {new CannotCalculateChangesMethodErrorResponse()};
            } catch (final InvalidArgumentsException invalidArgumentsException) {
                return new MethodResponse[] {new InvalidArgumentsMethodErrorResponse()};
            }
        }
        return super.handle(handlerRequest);
    }
}
