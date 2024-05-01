package it.qbsoftware.application.controllers.get;

import com.google.inject.Inject;

import it.qbsoftware.adapters.in.jmaplib.method.call.get.GetMailboxMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetMailboxMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.ports.in.usecase.get.GetMailboxMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.mailbox.GetMailboxMethodCall;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidResultReferenceMethodErrorResponse;

public class GetMailboxMethodCallController extends ControllerHandlerBase {
    private final GetMailboxMethodCallUsecase getMailboxMethodCallService;

    @Inject
    public GetMailboxMethodCallController(final GetMailboxMethodCallUsecase getMailboxMethodCallService) {
        this.getMailboxMethodCallService = getMailboxMethodCallService;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetMailboxMethodCall getMailboxMethodCall) {

            final GetMailboxMethodCallAdapter getMailboxMethodCallAdapter = new GetMailboxMethodCallAdapter(
                    getMailboxMethodCall);

            try {
                final GetMailboxMethodResponseAdapter getMailboxMethodResponseAdapter = (GetMailboxMethodResponseAdapter) getMailboxMethodCallService
                        .call(getMailboxMethodCallAdapter, handlerRequest.previousResponses());

                return new MethodResponse[] { getMailboxMethodResponseAdapter.adaptee() };
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
