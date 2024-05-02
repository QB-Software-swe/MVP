package it.qbsoftware.application.controllers.changes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import it.qbsoftware.adapters.in.jmaplib.method.call.changes.ChangesEmailMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.changes.ChangesEmailMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesEmailMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.ChangesEmailMethodCall;
import rs.ltt.jmap.common.method.error.CannotCalculateChangesMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;

public class ChangesEmailMethodCallController extends ControllerHandlerBase {
    private final Logger logger = LoggerFactory.getLogger(ChangesEmailMethodCallController.class);
    private final ChangesEmailMethodCallUsecase changesEmailMethodCallUsecase;

    @Inject
    public ChangesEmailMethodCallController(final ChangesEmailMethodCallUsecase changesEmailMethodCallUsecase) {
        this.changesEmailMethodCallUsecase = changesEmailMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof ChangesEmailMethodCall changesEmailMethodCall) {
            final ChangesEmailMethodCallAdapter ChangesEmailMethodCallAdapter = new ChangesEmailMethodCallAdapter(
                    changesEmailMethodCall);
            logger.info("Match and handle method call recived");
            try {
                final ChangesEmailMethodResponseAdapter changesEmailMethodResponseAdapter = (ChangesEmailMethodResponseAdapter) changesEmailMethodCallUsecase
                        .call(ChangesEmailMethodCallAdapter, handlerRequest.previousResponses());

                return new MethodResponse[] { changesEmailMethodResponseAdapter.adaptee() };
            } catch (final AccountNotFoundException accountNotFoundException) {
                return new MethodResponse[] { new InvalidArgumentsMethodErrorResponse() };
            } catch (final CannotCalculateChangesException CannotCalculateChangesException) {
                return new MethodResponse[] { new CannotCalculateChangesMethodErrorResponse() };
            } catch (final InvalidArgumentsException invalidArgumentsException) {
                return new MethodResponse[] { new InvalidArgumentsMethodErrorResponse() };
            }
        }
        return super.handle(handlerRequest);
    }
}
