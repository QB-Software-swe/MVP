package it.qbsoftware.application.controllers.get;

import java.util.ArrayList;

import it.qbsoftware.adapters.in.jmaplib.method.call.get.GetEmailSubmissionMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.MethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.ports.in.usecase.get.GetEmailSubmissionMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.submission.GetEmailSubmissionMethodCall;

public class GetEmailSubmissionMethodCallService extends ControllerHandlerBase {
    private final GetEmailSubmissionMethodCallUsecase getEmailSubmissionMethodCallUsecase;

    public GetEmailSubmissionMethodCallService(
            final GetEmailSubmissionMethodCallUsecase getEmailSubmissionMethodCallUsecase) {
        this.getEmailSubmissionMethodCallUsecase = getEmailSubmissionMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetEmailSubmissionMethodCall getEmailSubmissionMethodCall) {
            final GetEmailSubmissionMethodCallAdapter getEmailSubmissionMethodCallAdapter = new GetEmailSubmissionMethodCallAdapter(
                    getEmailSubmissionMethodCall);

            MethodResponseAdapter[] methodResponseAdapters = (MethodResponseAdapter[]) getEmailSubmissionMethodCallUsecase
                    .call(getEmailSubmissionMethodCallAdapter, handlerRequest.previousResponses());

            ArrayList<MethodResponse> methodResponseList = new ArrayList<>();

            for (MethodResponseAdapter methodResponseAdapter : methodResponseAdapters) {
                methodResponseList.add(methodResponseAdapter.adaptee());
            }

            return methodResponseList.toArray(MethodResponse[]::new);
        }
        return super.handle(handlerRequest);
    }
}
