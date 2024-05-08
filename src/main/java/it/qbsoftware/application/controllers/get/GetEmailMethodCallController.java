package it.qbsoftware.application.controllers.get;

import com.google.inject.Inject;
import it.qbsoftware.adapters.in.jmaplib.method.call.get.GetEmailMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.get.GetEmailMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.InvalidArgumentsException;
import it.qbsoftware.business.domain.exception.InvalidResultReferenceExecption;
import it.qbsoftware.business.ports.in.usecase.get.GetEmailMethodCallUsecase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.GetEmailMethodCall;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidResultReferenceMethodErrorResponse;

public class GetEmailMethodCallController extends ControllerHandlerBase {
    private final Logger logger = LoggerFactory.getLogger(GetEmailMethodCallController.class);
    private final GetEmailMethodCallUsecase getEmailMethodCallUsecase;

    @Inject
    public GetEmailMethodCallController(final GetEmailMethodCallUsecase getEmailMethodCallUsecase) {
        this.getEmailMethodCallUsecase = getEmailMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetEmailMethodCall getEmailMethodCall) {

            final GetEmailMethodCallAdapter getEmailMethodCallAdapter =
                    new GetEmailMethodCallAdapter(getEmailMethodCall);
            logger.info("Match and handle method call recived");
            try {
                final GetEmailMethodResponseAdapter getEmailMethodResponseAdapter =
                        (GetEmailMethodResponseAdapter)
                                getEmailMethodCallUsecase.call(
                                        getEmailMethodCallAdapter,
                                        handlerRequest.previousResponses());

                return new MethodResponse[] {getEmailMethodResponseAdapter.adaptee()};
            } catch (final AccountNotFoundException accountNotFoundException) {
                return new MethodResponse[] {new InvalidArgumentsMethodErrorResponse()};
            } catch (final InvalidResultReferenceExecption invalidResultReferenceExecption) {
                return new MethodResponse[] {new InvalidResultReferenceMethodErrorResponse()};
            } catch (final InvalidArgumentsException invalidArgumentsException) {
                return new MethodResponse[] {new InvalidArgumentsMethodErrorResponse()};
            }
        }

        return super.handle(handlerRequest);
    }
}
