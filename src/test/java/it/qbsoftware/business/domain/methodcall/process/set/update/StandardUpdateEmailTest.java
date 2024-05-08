package it.qbsoftware.business.domain.methodcall.process.set.update;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.EmailChangesTracker;
import it.qbsoftware.business.domain.entity.changes.tracker.MailboxChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.util.get.CreationIdResolverPort;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.EmailPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailMethodCallPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetEmailSubmissionMethodCallPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.EmailChangesTrackerRepository;
import it.qbsoftware.business.ports.out.domain.MailboxChangesTrackerRepository;
import it.qbsoftware.business.ports.out.jmap.EmailRepository;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class StandardUpdateEmailTest {

    @Mock EmailRepository emailRepository;

    @Mock SetErrorEnumPort setErrorEnumPort;

    @Mock AccountStateRepository accountStateRepository;

    @Mock EmailChangesTrackerRepository emailChangesTrackerRepository;

    @Mock EmailChangesTracker emailChangesTracker;

    @Mock MailboxChangesTrackerRepository mailboxChangesTrackerRepository;

    @Mock MailboxChangesTracker mailboxChangesTracker;

    @Mock CreationIdResolverPort creationIdResolverPort;

    @Mock AccountState accountState;

    @Mock EmailPort emailPort;

    @Mock EmailBuilderPort emailBuilderPort;

    @Mock SetEmailMethodCallPort setEmailMethodCallPort;

    @Mock SetEmailSubmissionMethodCallPort setEmailSubmissionMethodCallPort;

    @Mock Map<String, String> successSubmissionEmailIdResolve;

    @Mock ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @InjectMocks StandardUpdateEmail standardUpdateEmail;

    @Test
    public void testUpdateEmailSubmissionWithNullGetOnSuccessUpdateEmail()
            throws AccountNotFoundException {

        when(setEmailSubmissionMethodCallPort.getOnSuccessUpdateEmail()).thenReturn(null);

        UpdatedResult<EmailPort> result =
                standardUpdateEmail.update(
                        setEmailSubmissionMethodCallPort,
                        previousResponses,
                        successSubmissionEmailIdResolve);

        assertTrue(result.updated().isEmpty());
        assertTrue(result.notUpdated().isEmpty());
        assertNotNull(result);
    }

    @Test
    public void testUpdateEmailSubmissionWithNotNullGetOnSuccessUpdateEmailKeywords()
            throws Exception {
        Map<String, Object> mapObj = new HashMap<>();
        mapObj.put("keywords/", true);
        mapObj.put("keywords/", false);
        Map<String, Map<String, Object>> mapEmailToUpdate = new HashMap<>();
        mapEmailToUpdate.put("#", mapObj);

        when(setEmailSubmissionMethodCallPort.getOnSuccessUpdateEmail())
                .thenReturn(mapEmailToUpdate);
        when(emailRepository.retriveOne(any())).thenReturn(emailPort);
        when(emailPort.toBuilder()).thenReturn(emailBuilderPort);

        UpdatedResult<EmailPort> result =
                standardUpdateEmail.update(
                        setEmailSubmissionMethodCallPort,
                        previousResponses,
                        successSubmissionEmailIdResolve);

        assertTrue(result.updated().isEmpty());
        assertNotNull(result);
    }

    @Test
    public void testUpdateEmailSubmissionWithNotNullGetOnSuccessUpdateEmailMailboxIds()
            throws Exception {
        Map<String, Object> mapObj = new HashMap<>();
        mapObj.put("mailboxIds/", true);
        Map<String, Map<String, Object>> mapEmailToUpdate = new HashMap<>();
        mapEmailToUpdate.put("#", mapObj);

        when(setEmailSubmissionMethodCallPort.getOnSuccessUpdateEmail())
                .thenReturn(mapEmailToUpdate);
        when(emailRepository.retriveOne(any())).thenReturn(emailPort);
        when(emailPort.toBuilder()).thenReturn(emailBuilderPort);

        UpdatedResult<EmailPort> result =
                standardUpdateEmail.update(
                        setEmailSubmissionMethodCallPort,
                        previousResponses,
                        successSubmissionEmailIdResolve);

        assertTrue(result.updated().isEmpty());
        assertNotNull(result);
    }

    @Test
    public void testUpdateEmailSubmissionWithNotHashtag() throws Exception {
        Map<String, Boolean> map = new HashMap<>();
        map.put("key", true);
        Map<String, Object> mapObj = new HashMap<>();
        mapObj.put("key", map);
        Map<String, Map<String, Object>> mapEmailToUpdate = new HashMap<>();
        mapEmailToUpdate.put("key", mapObj);

        when(setEmailSubmissionMethodCallPort.getOnSuccessUpdateEmail())
                .thenReturn(mapEmailToUpdate);
        when(emailRepository.retriveOne(any())).thenReturn(emailPort);
        when(emailPort.toBuilder()).thenReturn(emailBuilderPort);

        UpdatedResult<EmailPort> result =
                standardUpdateEmail.update(
                        setEmailSubmissionMethodCallPort,
                        previousResponses,
                        successSubmissionEmailIdResolve);

        assertTrue(result.updated().isEmpty());
        assertNotNull(result);
    }

    @Test
    public void testUpdateEmailSubmissionWithSetInvalidPatchException() throws Exception {

        Map<String, Object> mapObj = new HashMap<>();
        mapObj.put("/keywords", true);
        Map<String, Map<String, Object>> mapEmailToUpdate = new HashMap<>();
        mapEmailToUpdate.put("/keywords", mapObj);

        when(setEmailSubmissionMethodCallPort.accountId()).thenReturn("accountId");

        UpdatedResult<EmailPort> result =
                standardUpdateEmail.update(
                        setEmailSubmissionMethodCallPort,
                        previousResponses,
                        successSubmissionEmailIdResolve);

        assertNotNull(result);
    }

    @Test
    public void testUpdateEmailMethodCallWithMailboxMap() throws Exception {
        Map<String, Boolean> map = new HashMap<>();
        map.put("key", true);
        Map<String, Object> mapObj = new HashMap<>();
        mapObj.put("mailboxIds/string", map);
        Map<String, Map<String, Object>> mapEmailToUpdate = new HashMap<>();
        mapEmailToUpdate.put("/keywords", mapObj);

        when(setEmailMethodCallPort.getUpdate()).thenReturn(mapEmailToUpdate);
        when(emailRepository.retriveOne(any())).thenReturn(emailPort);
        when(emailPort.toBuilder()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.reset()).thenReturn(emailBuilderPort);
        when(emailBuilderPort.build()).thenReturn(emailPort);

        when(setEmailMethodCallPort.accountId()).thenReturn("accountId");
        when(accountStateRepository.retrive(anyString())).thenReturn(accountState);
        when(accountState.increaseState()).thenReturn(accountState);
        when(accountState.state()).thenReturn("state");

        when(mailboxChangesTrackerRepository.retrive(any())).thenReturn(mailboxChangesTracker);

        UpdatedResult<EmailPort> result =
                standardUpdateEmail.update(setEmailMethodCallPort, previousResponses);

        assertNotNull(result);
    }

    @Test
    public void testUpdateEmailMethodCallWithSetInvalidPatchException() throws Exception {

        Map<String, Object> mapObj = new HashMap<>();
        mapObj.put("/keywords", true);
        Map<String, Map<String, Object>> mapEmailToUpdate = new HashMap<>();
        mapEmailToUpdate.put("/keywords", mapObj);

        when(setEmailMethodCallPort.getUpdate()).thenReturn(mapEmailToUpdate);
        when(emailRepository.retriveOne(any())).thenReturn(emailPort);
        when(emailPort.toBuilder()).thenReturn(emailBuilderPort);

        when(setEmailMethodCallPort.accountId()).thenReturn("accountId");

        UpdatedResult<EmailPort> result =
                standardUpdateEmail.update(setEmailMethodCallPort, previousResponses);

        assertNotNull(result);
    }
}
