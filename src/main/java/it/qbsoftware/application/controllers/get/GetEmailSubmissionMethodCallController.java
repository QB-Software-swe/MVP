package it.qbsoftware.application.controllers.get;

import java.util.ArrayList;

import com.google.inject.Inject;

import it.qbsoftware.adapters.in.jmaplib.method.call.get.GetEmailSubmissionMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetEmailSubmissionMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.ports.in.usecase.get.GetEmailSubmissionMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.submission.GetEmailSubmissionMethodCall;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidResultReferenceMethodErrorResponse;
import rs.ltt.jmap.common.method.response.submission.GetEmailSubmissionMethodResponse;

public class GetEmailSubmissionMethodCallController extends ControllerHandlerBase {
    private final GetEmailSubmissionMethodCallUsecase getEmailSubmissionMethodCallUsecase;

    @Inject
    public GetEmailSubmissionMethodCallController(
            final GetEmailSubmissionMethodCallUsecase getEmailSubmissionMethodCallUsecase) {
        this.getEmailSubmissionMethodCallUsecase = getEmailSubmissionMethodCallUsecase;
    }

    @Override
    public MethodResponse handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetEmailSubmissionMethodCall getEmailSubmissionMethodCall) {
            final GetEmailSubmissionMethodCallAdapter getEmailSubmissionMethodCallAdapter = new GetEmailSubmissionMethodCallAdapter(
                    getEmailSubmissionMethodCall);

            GetEmailSubmissionMethodResponseAdapter getEmailSubmissionMethodResponseAdapter = null;
            try {
                getEmailSubmissionMethodResponseAdapter = (GetEmailSubmissionMethodResponseAdapter) getEmailSubmissionMethodCallUsecase
                        .call(getEmailSubmissionMethodCallAdapter, handlerRequest.previousResponses());
            } catch (final AccountNotFoundException accountNotFoundException) {
                return new InvalidArgumentsMethodErrorResponse();
            } catch (final InvalidResultReferenceExecption invalidResultReferenceExecption) {
                return new InvalidResultReferenceMethodErrorResponse();
            } catch (final InvalidArgumentsException invalidArgumentsException) {
                return new InvalidArgumentsMethodErrorResponse();
            }

            return getEmailSubmissionMethodResponseAdapter.adaptee();
        }
        return super.handle(handlerRequest);
    }
}
