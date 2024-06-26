package it.qbsoftware.application.controllers.set;

import com.google.inject.Inject;
import it.qbsoftware.adapters.in.jmaplib.method.call.set.SetEmailSubmissionMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.set.SetEmailMethodResponseAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.set.SetEmailSubmissionMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.domain.methodresponse.SetEmailSubmissionMethodResponse;
import it.qbsoftware.business.ports.in.usecase.set.SetEmailSubmissionMethodCallUsecase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.submission.SetEmailSubmissionMethodCall;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;

public class SetEmailSubmissionMethodCallController extends ControllerHandlerBase {
    private final Logger logger =
            LoggerFactory.getLogger(SetEmailSubmissionMethodCallController.class);
    private final SetEmailSubmissionMethodCallUsecase setEmailSubmissionMethodCallUsecase;

    @Inject
    public SetEmailSubmissionMethodCallController(
            final SetEmailSubmissionMethodCallUsecase setEmailSubmissionMethodCallUsecase) {
        this.setEmailSubmissionMethodCallUsecase = setEmailSubmissionMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall()
                instanceof SetEmailSubmissionMethodCall setEmailSubmissionMethodCall) {
            final SetEmailSubmissionMethodCallAdapter setEmailSubmissionMethodCallAdapter =
                    new SetEmailSubmissionMethodCallAdapter(setEmailSubmissionMethodCall);
            logger.info("Match and handle method call recived");
            try {
                final SetEmailSubmissionMethodResponse setEmailSubmissionMethodResponse =
                        setEmailSubmissionMethodCallUsecase.call(
                                setEmailSubmissionMethodCallAdapter,
                                handlerRequest.previousResponses());
                return new MethodResponse[] {
                    ((SetEmailSubmissionMethodResponseAdapter)
                                    setEmailSubmissionMethodResponse
                                            .setEmailSubmissionMethodResponsePort())
                            .adaptee(),
                    ((SetEmailMethodResponseAdapter)
                                    setEmailSubmissionMethodResponse.setEmailMethodResponsePort())
                            .adaptee()
                };
            } catch (final AccountNotFoundException accountNotFoundException) {
                return new MethodResponse[] {new InvalidArgumentsMethodErrorResponse()};
            } catch (SetNotFoundException e) {
                return new MethodResponse[] {new InvalidArgumentsMethodErrorResponse()};
            }
        }

        return super.handle(handlerRequest);
    }
}
