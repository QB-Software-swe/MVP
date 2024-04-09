package it.qbsoftware.business.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import it.qbsoftware.business.ports.in.jmap.EchoMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.EchoMethodResponseBuilderPort;
import it.qbsoftware.business.ports.in.jmap.MethodResponsePort;
import it.qbsoftware.business.ports.in.usecase.EchoMethodCallUsecase;
import it.qbsoftware.business.services.EchoMethodCallSerivce;
import rs.ltt.jmap.common.method.response.core.EchoMethodResponse;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EchoMethodCallServiceTest {
    @Test
    public void callEchoMethodCallService() {
        // Call obj
        //String payload = "TestPayload";
        //EchoMethodCallPort mockEchoMethodCallPort = mock(EchoMethodCallPort.class);
        //when(mockEchoMethodCallPort.payload()).thenReturn(payload);

        // Response
        //MethodResponsePort mockMethodResponsePort = mock(MethodResponsePort.class);

        // Return
        //EchoMethodResponseBuilderPort mockEchoMethodResponseBuilderPort = mock(EchoMethodResponseBuilderPort.class);
        //when(mockEchoMethodResponseBuilderPort.payload(null)).thenReturn(mockEchoMethodResponseBuilderPort);
        //when(mockEchoMethodResponseBuilderPort.build()).thenReturn(mockMethodResponsePort);

        //EchoMethodCallUsecase echoMethodCallUsecase = new EchoMethodCallSerivce(mockEchoMethodResponseBuilderPort);

        //assertEquals(echoMethodCallUsecase.call(mockEchoMethodCallPort).getClass(), EchoMethodResponse.class);
    }
}
