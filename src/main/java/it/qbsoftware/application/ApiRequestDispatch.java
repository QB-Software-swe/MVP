package it.qbsoftware.application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.qbsoftware.adapters.guava.ListMultimapAdapter;
import it.qbsoftware.adapters.jmaplib.ResponseInvocationAdapter;
import it.qbsoftware.application.controllers.EchoMethodCallController;
import it.qbsoftware.application.controllers.HandlerRequest;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import rs.ltt.jmap.common.GenericResponse;
import rs.ltt.jmap.common.Request;
import rs.ltt.jmap.common.Response;
import rs.ltt.jmap.common.method.MethodResponse;
import rs.ltt.jmap.common.method.response.core.EchoMethodResponse;
import rs.ltt.jmap.gson.JmapAdapters;

import java.util.Arrays;

//FIXME: Non c'è nessun controllo sulla corretezza della MethodCall, vanno verificate (vedi PoC, funzione request)
public class ApiRequestDispatch {
    EchoMethodCallController echoMethodCallController;
    static Gson GSON;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        JmapAdapters.register(gsonBuilder);
        GSON = gsonBuilder.create();
    }

    public ApiRequestDispatch() {
        this.echoMethodCallController = new EchoMethodCallController();
    }

    public String Dispatch(String jmapRequest) {
        //System.out.println(GSON.toJson(EchoMethodResponse.builder().libraryName("Alpha").build()));

        Request request;
        try {
            request = GSON.fromJson(jmapRequest, Request.class);
        } catch (Exception e) {
            e.printStackTrace();
            return "Too Bad"; //TODO Invalid JSON
        }
        Request.Invocation[] methodCalls = request.getMethodCalls();

        var previousResponses = new ListMultimapAdapter<String, ResponseInvocationPort>();

        for (final Request.Invocation invocation : methodCalls) {
            final String invocationId = invocation.getId();

            MethodResponse[] methodResponses = echoMethodCallController.handle(
                    new HandlerRequest(invocation.getMethodCall(), previousResponses));

            Arrays.stream(methodResponses).sequential().forEach(
                    response -> previousResponses.put(invocationId, new ResponseInvocationAdapter(new Response.Invocation(response, invocationId))));
        }

        // FIXME: Session state fissato a zero, ma non è conforme allo standard così
        GenericResponse genericResponse = new Response(previousResponses.values().toArray(new Response.Invocation[0]),
                "0");

        return GSON.toJson(genericResponse); // Tornare la vera risposta
    }
}
