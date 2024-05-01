package it.qbsoftware.application.controllers.get;

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

public class GetEmailSubmissionMethodCallController extends ControllerHandlerBase {
    private final GetEmailSubmissionMethodCallUsecase getEmailSubmissionMethodCallUsecase;

    @Inject
    public GetEmailSubmissionMethodCallController(
            final GetEmailSubmissionMethodCallUsecase getEmailSubmissionMethodCallUsecase) {
        this.getEmailSubmissionMethodCallUsecase = getEmailSubmissionMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetEmailSubmissionMethodCall getEmailSubmissionMethodCall) {
            final GetEmailSubmissionMethodCallAdapter getEmailSubmissionMethodCallAdapter = new GetEmailSubmissionMethodCallAdapter(
                    getEmailSubmissionMethodCall);

            try {
                final GetEmailSubmissionMethodResponseAdapter getEmailSubmissionMethodResponseAdapter = (GetEmailSubmissionMethodResponseAdapter) getEmailSubmissionMethodCallUsecase
                        .call(getEmailSubmissionMethodCallAdapter, handlerRequest.previousResponses());

                return new MethodResponse[] { getEmailSubmissionMethodResponseAdapter.adaptee() };
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
