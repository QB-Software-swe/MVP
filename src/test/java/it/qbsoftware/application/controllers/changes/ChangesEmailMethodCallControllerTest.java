package it.qbsoftware.application.controllers.changes;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.adapters.in.jmaplib.method.response.changes.ChangesEmailMethodResponseAdapter;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.usecase.changes.ChangesEmailMethodCallUsecase;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.response.email.ChangesEmailMethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ChangesEmailMethodCallControllerTest {

    @Mock
    ChangesEmailMethodCallUsecase changesEmailMethodCallUsecase;

    @Mock
    ListMultimapPort<String,ResponseInvocationPort> previousResponses;

    @Mock
    ChangesEmailMethodResponseAdapter changesEmailMethodResponseAdapter;

    @Mock
    MethodCall methodCall;

    @Mock
    ChangesEmailMethodResponse changesEmailMethodResponse;

    @InjectMocks
    ChangesEmailMethodCallController changesEmailMethodCallController;




}
