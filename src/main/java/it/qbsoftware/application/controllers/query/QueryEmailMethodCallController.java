package it.qbsoftware.application.controllers.query;

import com.google.inject.Inject;

import it.qbsoftware.adapters.in.jmaplib.method.call.query.QueryEmailMethodCallAdapter;
import it.qbsoftware.adapters.in.jmaplib.method.response.query.QueryEmailMethodResponseAdapter;
import it.qbsoftware.application.controllers.ControllerHandlerBase;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.query.QueryAnchorNotFoundException;
import it.qbsoftware.business.ports.in.usecase.query.QueryEmailMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.call.email.QueryEmailMethodCall;
import rs.ltt.jmap.common.method.error.AnchorNotFoundMethodErrorResponse;
import rs.ltt.jmap.common.method.error.InvalidArgumentsMethodErrorResponse;

public class QueryEmailMethodCallController extends ControllerHandlerBase {
    private final QueryEmailMethodCallUsecase queryEmailMethodCallUsecase;

    @Inject
    public QueryEmailMethodCallController(final QueryEmailMethodCallUsecase queryEmailMethodCallUsecase) {
        this.queryEmailMethodCallUsecase = queryEmailMethodCallUsecase;
    }

    @Override
    public MethodResponse[] handle(final HandlerRequest handlerRequest) {
        if (handlerRequest.methodCall() instanceof QueryEmailMethodCall queryEmailMethodCall) {
            final QueryEmailMethodCallAdapter queryEmailMethodCallAdapter = new QueryEmailMethodCallAdapter(
                    queryEmailMethodCall);

            try {
                final QueryEmailMethodResponseAdapter queryEmailMethodResponseAdapter = (QueryEmailMethodResponseAdapter) queryEmailMethodCallUsecase
                        .call(queryEmailMethodCallAdapter, handlerRequest.previousResponses());

                return new MethodResponse[] { queryEmailMethodResponseAdapter.adaptee() };
            } catch (final QueryAnchorNotFoundException queryAnchorNotFoundException) {
                return new MethodResponse[] { new AnchorNotFoundMethodErrorResponse() };
            } catch (final AccountNotFoundException accountNotFoundException) {
                return new MethodResponse[] { new InvalidArgumentsMethodErrorResponse() };
            }
        }

        return super.handle(handlerRequest);
    }
}
