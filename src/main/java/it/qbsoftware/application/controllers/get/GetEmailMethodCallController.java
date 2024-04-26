package it.qbsoftware.application.controllers.get;

import java.util.ArrayList;

import com.google.inject.Inject;

import it.qbsoftware.adapters.in.jmaplib.method.call.get.GetEmailMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.MethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.ports.in.usecase.get.GetEmailMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.GetEmailMethodCall;

public class GetEmailMethodCallController extends ControllerHandlerBase {
    private final GetEmailMethodCallUsecase getEmailMethodCallUsecase;

    @Inject
    public GetEmailMethodCallController(final GetEmailMethodCallUsecase getEmailMethodCallUsecase) {
        this.getEmailMethodCallUsecase = getEmailMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof GetEmailMethodCall getEmailMethodCall) {

            GetEmailMethodCallAdapter getEmailMethodCallAdapter = new GetEmailMethodCallAdapter(getEmailMethodCall);

            MethodResponseAdapter[] methodResponseAdapters = (MethodResponseAdapter[]) getEmailMethodCallUsecase
                    .call(getEmailMethodCallAdapter, handlerRequest.previousResponses());

            ArrayList<MethodResponse> methodResponseList = new ArrayList<>();

            for (MethodResponseAdapter methodResponseAdapter : methodResponseAdapters) {
                methodResponseList.add(methodResponseAdapter.adaptee());
            }

            return methodResponseList.toArray(MethodResponse[]::new);
        }

        return super.handle(handlerRequest);
    }
}
