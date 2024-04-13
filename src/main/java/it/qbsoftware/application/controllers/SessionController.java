package it.qbsoftware.application.controllers;

import it.qbsoftware.adapters.jmaplib.AccountBuilderAdapter;
import it.qbsoftware.adapters.jmaplib.MethodResponseAdapter;
import it.qbsoftware.adapters.jmaplib.SessionResourceAdapter;
import it.qbsoftware.adapters.jmaplib.SessionResourceBuilderAdapter;
import it.qbsoftware.adapters.jmaplib.UserSessionResourceRepositoryAdapter;
import it.qbsoftware.business.services.SessionService;
import it.qbsoftware.business.ports.in.usecase.SessionUsecase;
import rs.ltt.jmap.common.SessionResource;
import rs.ltt.jmap.common.method.MethodResponse;

import java.util.ArrayList;


public class SessionController extends ControllerHandlerBase{

    @Override
    public MethodResponse[] handle(HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof SessionResource sessionResource) {
            
            SessionResourceAdapter sessionResourceAdapter = new SessionResourceAdapter(sessionResource);

            SessionUsecase sessionService = new SessionService(new SessionResourceBuilderAdapter(), new AccountBuilderAdapter(),new UserSessionResourceRepositoryAdapter());

            MethodResponseAdapter[] methodResponseAdapters = (MethodResponseAdapter[]) sessionService.call(sessionResourceAdapter, handlerRequest.previousResponses());

            ArrayList<MethodResponse> methodResponseList = new ArrayList<>();

            for (MethodResponseAdapter methodResponseAdapter : methodResponseAdapters) {
                methodResponseList.add(methodResponseAdapter.methodResponse());
            }

            return methodResponseList.toArray(new MethodResponse[0]);
        }

        return super.handle(handlerRequest);
    }
}
