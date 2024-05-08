package it.qbsoftware.application.controllers.changes;

import com.google.inject.Inject;
import it.qbsoftware.adapters.in.jmaplib.method.call.changes.ChangesIdentityMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.changes.ChangesIdentityMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesIdentityMethodCallUsecase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.identity.ChangesIdentityMethodCall;
import rs.ltt.jmap.common.method.error.CannotCalculateChangesMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;

public class ChangesIdentityMethodCallController extends ControllerHandlerBase {
    private final Logger logger =
            LoggerFactory.getLogger(ChangesIdentityMethodCallController.class);
    private final ChangesIdentityMethodCallUsecase changesIdentityMethodCallUsecase;

    @Inject
    public ChangesIdentityMethodCallController(
            final ChangesIdentityMethodCallUsecase changesIdentityMethodCallUsecase) {
        this.changesIdentityMethodCallUsecase = changesIdentityMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall()
                instanceof ChangesIdentityMethodCall changesIdentityMethodCall) {
            final ChangesIdentityMethodCallAdapter changesIdentityMethodCallAdapter =
                    new ChangesIdentityMethodCallAdapter(changesIdentityMethodCall);
            logger.info("Match and handle method call recived");
            try {
                final ChangesIdentityMethodResponseAdapter changesIdentityMethodResponseAdapter =
                        (ChangesIdentityMethodResponseAdapter)
                                changesIdentityMethodCallUsecase.call(
                                        changesIdentityMethodCallAdapter,
                                        handlerRequest.previousResponses());
                return new MethodResponse[] {changesIdentityMethodResponseAdapter.adaptee()};

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
