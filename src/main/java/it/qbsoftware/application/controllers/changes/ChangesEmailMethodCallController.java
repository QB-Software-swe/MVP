package it.qbsoftware.application.controllers.changes;

import java.util.ArrayList;

import com.google.inject.Inject;

import it.qbsoftware.adapters.in.jmaplib.method.call.changes.ChangesEmailMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.changes.ChangesEmailMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesEmailMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.ChangesEmailMethodCall;
import rs.ltt.jmap.common.method.error.CannotCalculateChangesMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidResultReferenceMethodErrorResponse;

public class ChangesEmailMethodCallController extends ControllerHandlerBase {
    private final ChangesEmailMethodCallUsecase changesEmailMethodCallUsecase;

    @Inject
    public ChangesEmailMethodCallController(final ChangesEmailMethodCallUsecase changesEmailMethodCallUsecase) {
        this.changesEmailMethodCallUsecase = changesEmailMethodCallUsecase;
    }

    @Override
    public MethodResponse handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof ChangesEmailMethodCall changesEmailMethodCall) {
            final ChangesEmailMethodCallAdapter ChangesEmailMethodCallAdapter = new ChangesEmailMethodCallAdapter(
                    changesEmailMethodCall);

            ChangesEmailMethodResponseAdapter changesEmailMethodResponseAdapter = null;

            try {
                changesEmailMethodResponseAdapter = (ChangesEmailMethodResponseAdapter) changesEmailMethodCallUsecase
                        .call(ChangesEmailMethodCallAdapter, handlerRequest.previousResponses());
            } catch (final AccountNotFoundException accountNotFoundException) {
                return new InvalidArgumentsMethodErrorResponse();
            } catch (final CannotCalculateChangesException CannotCalculateChangesException) {
                return new CannotCalculateChangesMethodErrorResponse();
            } catch (final InvalidArgumentsException invalidArgumentsException) {
                return new InvalidArgumentsMethodErrorResponse();
            }

            return changesEmailMethodResponseAdapter.adaptee();
        }
        return super.handle(handlerRequest);
    }
}
