package it.qbsoftware.application.controllers.changes;

import it.qbsoftware.adapters.in.jmaplib.method.call.changes.ChangesThreadMethodCallAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.changes.CannotCalculateChangesException;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesThreadMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.thread.ChangesThreadMethodCall;
import rs.ltt.jmap.common.method.error.CannotCalculateChangesMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;

import java.util.ArrayList;

import com.google.inject.Inject;

public class ChangesThreadMethodCallController extends ControllerHandlerBase {
    private final ChangesThreadMethodCallUsecase changesThreadMethodCallUsecase;

    @Inject
    public ChangesThreadMethodCallController(final ChangesThreadMethodCallUsecase changesThreadMethodCallUsecase) {
        this.changesThreadMethodCallUsecase = changesThreadMethodCallUsecase;
    }

    @Override
    public MethodResponse handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof ChangesThreadMethodCall changesThreadMethodCall) {
            final ChangesThreadMethodCallAdapter changesThreadMethodCallAdapter = new ChangesThreadMethodCallAdapter(
                    changesThreadMethodCall);

                    try {
            final AbstracMethodResponseAdapter[] methodResponseAdapters = (AbstracMethodResponseAdapter[]) changesThreadMethodCallUsecase
                    .call(changesThreadMethodCallAdapter, handlerRequest.previousResponses());
                } catch (final AccountNotFoundException accountNotFoundException) {
                return new InvalidArgumentsMethodErrorResponse();
            } catch (final CannotCalculateChangesException CannotCalculateChangesException) {
                return new CannotCalculateChangesMethodErrorResponse();
            } catch (final InvalidArgumentsException invalidArgumentsException) {
                return new InvalidArgumentsMethodErrorResponse();
            }
        }
        return super.handle(handlerRequest);
    }
}
