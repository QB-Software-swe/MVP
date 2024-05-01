package it.qbsoftware.application.controllers.changes;

import com.google.inject.Inject;

import it.qbsoftware.adapters.in.jmaplib.method.call.changes.ChangesThreadMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.changes.ChangesThreadMethodResponseAdapter;
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

public class ChangesThreadMethodCallController extends ControllerHandlerBase {
    private final ChangesThreadMethodCallUsecase changesThreadMethodCallUsecase;

    @Inject
    public ChangesThreadMethodCallController(final ChangesThreadMethodCallUsecase changesThreadMethodCallUsecase) {
        this.changesThreadMethodCallUsecase = changesThreadMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof ChangesThreadMethodCall changesThreadMethodCall) {
            final ChangesThreadMethodCallAdapter changesThreadMethodCallAdapter = new ChangesThreadMethodCallAdapter(
                    changesThreadMethodCall);

            try {
                final ChangesThreadMethodResponseAdapter changesThreadMethodResponseAdapter = (ChangesThreadMethodResponseAdapter) changesThreadMethodCallUsecase
                        .call(changesThreadMethodCallAdapter, handlerRequest.previousResponses());
                        
                return new MethodResponse[] { changesThreadMethodResponseAdapter.adaptee() };
            } catch (final AccountNotFoundException accountNotFoundException) {
                return new MethodResponse[] { new InvalidArgumentsMethodErrorResponse() };
            } catch (final CannotCalculateChangesException CannotCalculateChangesException) {
                return new MethodResponse[] { new CannotCalculateChangesMethodErrorResponse() };
            } catch (final InvalidArgumentsException invalidArgumentsException) {
                return new MethodResponse[] { new CannotCalculateChangesMethodErrorResponse() };
            }
        }
        return super.handle(handlerRequest);
    }
}
