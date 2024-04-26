package it.qbsoftware.application.controllers.get;

import java.util.ArrayList;
import com.google.inject.Inject;

import it.qbsoftware.adapters.in.jmaplib.method.call.get.GetEmailMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetEmailMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.ports.in.jmap.method.response.MethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.get.GetEmailMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.GetEmailMethodCall;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidResultReferenceMethodErrorResponse;

public class GetEmailMethodCallController extends ControllerHandlerBase {
    private final GetEmailMethodCallUsecase getEmailMethodCallUsecase;

    @Inject
    public GetEmailMethodCallController(final GetEmailMethodCallUsecase getEmailMethodCallUsecase) {
        this.getEmailMethodCallUsecase = getEmailMethodCallUsecase;
    }

    @Override
    public MethodResponse handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetEmailMethodCall getEmailMethodCall) {

            final GetEmailMethodCallAdapter getEmailMethodCallAdapter = new GetEmailMethodCallAdapter(
                    getEmailMethodCall);

            GetEmailMethodResponseAdapter getEmailMethodResponseAdapter = null;
            try {
                getEmailMethodResponseAdapter = (GetEmailMethodResponseAdapter) getEmailMethodCallUsecase
                        .call(getEmailMethodCallAdapter, handlerRequest.previousResponses());
            } catch (final AccountNotFoundException accountNotFoundException) {
                return new InvalidArgumentsMethodErrorResponse();
            } catch (final InvalidResultReferenceExecption invalidResultReferenceExecption) {
                return new InvalidResultReferenceMethodErrorResponse();
            } catch (final InvalidArgumentsException invalidArgumentsException) {
                return new InvalidArgumentsMethodErrorResponse();
            }

            return getEmailMethodResponseAdapter.adaptee();
        }

        return super.handle(handlerRequest);
    }
}
