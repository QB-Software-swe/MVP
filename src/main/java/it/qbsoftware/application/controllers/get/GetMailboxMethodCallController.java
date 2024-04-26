package it.qbsoftware.application.controllers.get;

import java.util.ArrayList;

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
    public MethodResponse handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetMailboxMethodCall getMailboxMethodCall) {

            final GetMailboxMethodCallAdapter getMailboxMethodCallAdapter = new GetMailboxMethodCallAdapter(
                    getMailboxMethodCall);

            GetMailboxMethodResponseAdapter getMailboxMethodResponseAdapter = null;
            try {
                getMailboxMethodResponseAdapter = (GetMailboxMethodResponseAdapter) getMailboxMethodCallService
                        .call(getMailboxMethodCallAdapter, handlerRequest.previousResponses());
            } catch (final AccountNotFoundException accountNotFoundException) {
                return new InvalidArgumentsMethodErrorResponse();
            } catch (final InvalidResultReferenceExecption invalidResultReferenceExecption) {
                return new InvalidResultReferenceMethodErrorResponse();
            } catch (final InvalidArgumentsException invalidArgumentsException) {
                return new InvalidArgumentsMethodErrorResponse();
            }
            return getMailboxMethodResponseAdapter.adaptee();
        }

        return super.handle(handlerRequest);
    }
}
