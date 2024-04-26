package it.qbsoftware.application.controllers.set;

import com.google.inject.Inject;

import it.qbsoftware.adapters.in.jmaplib.method.call.set.SetEmailMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.set.SetEmailMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.StateMismatchException;
import it.qbsoftware.business.ports.in.usecase.set.SetEmailMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.SetEmailMethodCall;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.StateMismatchMethodErrorResponse;

public class SetEmailMethodCallController extends ControllerHandlerBase {
    private final SetEmailMethodCallUsecase setEmailMethodCallUsecase;

    @Inject
    public SetEmailMethodCallController(final SetEmailMethodCallUsecase setEmailMethodCallUsecase) {
        this.setEmailMethodCallUsecase = setEmailMethodCallUsecase;
    }

    @Override
    public MethodResponse handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof SetEmailMethodCall setEmailMethodCall) {
            final SetEmailMethodCallAdapter setEmailMethodCallAdapter = new SetEmailMethodCallAdapter(
                    setEmailMethodCall);

            try {
                final SetEmailMethodResponseAdapter setEmailMethodResponseAdapter = (SetEmailMethodResponseAdapter) setEmailMethodCallUsecase
                        .call(setEmailMethodCallAdapter, handlerRequest.previousResponses());
                return setEmailMethodResponseAdapter.adaptee();
            } catch (final AccountNotFoundException accountNotFoundException) {
                return new InvalidArgumentsMethodErrorResponse();
            } catch (final StateMismatchException stateMismatchException) {
                return new StateMismatchMethodErrorResponse();
            }
        }

        return super.handle(handlerRequest);
    }
}
