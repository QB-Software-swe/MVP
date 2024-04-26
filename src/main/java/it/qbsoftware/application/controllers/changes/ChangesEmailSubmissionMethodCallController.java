package it.qbsoftware.application.controllers.changes;

import java.util.ArrayList;

import com.google.inject.Inject;

import it.qbsoftware.adapters.in.jmaplib.method.call.changes.ChangesEmailSubmissionMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.changes.ChangesEmailSubmissionMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesEmailSubmissionMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.submission.ChangesEmailSubmissionMethodCall;
import rs.ltt.jmap.common.method.error.CannotCalculateChangesMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;

public class ChangesEmailSubmissionMethodCallController extends ControllerHandlerBase {
    private final ChangesEmailSubmissionMethodCallUsecase changesEmailSubmissionMethodCallUsecase;

    @Inject
    public ChangesEmailSubmissionMethodCallController(
            final ChangesEmailSubmissionMethodCallUsecase changesEmailSubmissionMethodCallUsecase) {
        this.changesEmailSubmissionMethodCallUsecase = changesEmailSubmissionMethodCallUsecase;
    }

    @Override
    public MethodResponse handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof ChangesEmailSubmissionMethodCall changesEmailSubmissionMethodCall) {
            final ChangesEmailSubmissionMethodCallAdapter changesEmailSubmissionMethodCallAdapter = new ChangesEmailSubmissionMethodCallAdapter(
                    changesEmailSubmissionMethodCall);

            ChangesEmailSubmissionMethodResponseAdapter changesEmailSubmissionMethodResponseAdapter;
            try {
                changesEmailSubmissionMethodResponseAdapter = (ChangesEmailSubmissionMethodResponseAdapter) changesEmailSubmissionMethodCallUsecase
                        .call(changesEmailSubmissionMethodCallAdapter, handlerRequest.previousResponses());
            } catch (final AccountNotFoundException accountNotFoundException) {
                return new InvalidArgumentsMethodErrorResponse();
            } catch (final CannotCalculateChangesException CannotCalculateChangesException) {
                return new CannotCalculateChangesMethodErrorResponse();
            } catch (final InvalidArgumentsException invalidArgumentsException) {
                return new InvalidArgumentsMethodErrorResponse();
            }

            return changesEmailSubmissionMethodResponseAdapter.adaptee();
        }
        return super.handle(handlerRequest);
    }
}
