package it.qbsoftware.application.controllers.get;

import java.util.ArrayList;

import com.google.inject.Inject;

import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.identity.GetIdentityMethodCall;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidResultReferenceMethodErrorResponse;
import it.qbsoftware.adapters.in.jmaplib.method.call.get.GetIdentityMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetIdentityMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.ports.in.usecase.get.GetIdentityMethodCallUsecase;

public class GetIdentityMethodCallController extends ControllerHandlerBase {
    private final GetIdentityMethodCallUsecase getIdentityMethodCallUsecase;

    @Inject
    public GetIdentityMethodCallController(final GetIdentityMethodCallUsecase getIdentityMethodCallUsecase) {
        this.getIdentityMethodCallUsecase = getIdentityMethodCallUsecase;
    }

    @Override
    public MethodResponse handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetIdentityMethodCall getIdentityMethodCall) {

            final GetIdentityMethodCallAdapter getIdentityMethodCallAdapter = new GetIdentityMethodCallAdapter(
                    getIdentityMethodCall);

            GetIdentityMethodResponseAdapter getIdentityMethodResponseAdapter = null;
            try {
                getIdentityMethodResponseAdapter = (GetIdentityMethodResponseAdapter) getIdentityMethodCallUsecase
                        .call(getIdentityMethodCallAdapter, handlerRequest.previousResponses());
            } catch (final AccountNotFoundException accountNotFoundException) {
                return new InvalidArgumentsMethodErrorResponse();
            } catch (final InvalidResultReferenceExecption invalidResultReferenceExecption) {
                return new InvalidResultReferenceMethodErrorResponse();
            } catch (final InvalidArgumentsException invalidArgumentsException) {
                return new InvalidArgumentsMethodErrorResponse();
            }

            return getIdentityMethodResponseAdapter.adaptee();
        }

        return super.handle(handlerRequest);
    }

}
