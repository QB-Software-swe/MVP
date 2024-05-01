package it.qbsoftware.application.controllers.get;

import com.google.inject.Inject;

import it.qbsoftware.adapters.in.jmaplib.method.call.get.GetIdentityMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetIdentityMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.ports.in.usecase.get.GetIdentityMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.identity.GetIdentityMethodCall;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidResultReferenceMethodErrorResponse;

public class GetIdentityMethodCallController extends ControllerHandlerBase {
    private final GetIdentityMethodCallUsecase getIdentityMethodCallUsecase;

    @Inject
    public GetIdentityMethodCallController(final GetIdentityMethodCallUsecase getIdentityMethodCallUsecase) {
        this.getIdentityMethodCallUsecase = getIdentityMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetIdentityMethodCall getIdentityMethodCall) {

            final GetIdentityMethodCallAdapter getIdentityMethodCallAdapter = new GetIdentityMethodCallAdapter(
                    getIdentityMethodCall);

            try {
                final GetIdentityMethodResponseAdapter getIdentityMethodResponseAdapter = (GetIdentityMethodResponseAdapter) getIdentityMethodCallUsecase
                        .call(getIdentityMethodCallAdapter, handlerRequest.previousResponses());

                return new MethodResponse[] { getIdentityMethodResponseAdapter.adaptee() };
            } catch (final AccountNotFoundException accountNotFoundException) {
                return new MethodResponse[] { new InvalidArgumentsMethodErrorResponse() };
            } catch (final InvalidResultReferenceExecption invalidResultReferenceExecption) {
                return new MethodResponse[] { new InvalidResultReferenceMethodErrorResponse() };
            } catch (final InvalidArgumentsException invalidArgumentsException) {
                return new MethodResponse[] { new InvalidArgumentsMethodErrorResponse() };
            }
        }

        return super.handle(handlerRequest);
    }

}
