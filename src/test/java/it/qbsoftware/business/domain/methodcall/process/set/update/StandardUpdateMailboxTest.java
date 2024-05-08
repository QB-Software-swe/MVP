package it.qbsoftware.business.domain.methodcall.process.set.update;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import it.qbsoftware.business.domain.entity.changes.AccountState;
import it.qbsoftware.business.domain.entity.changes.tracker.MailboxChangesTracker;
import it.qbsoftware.business.domain.exception.AccountNotFoundException;
import it.qbsoftware.business.domain.exception.set.SetNotFoundException;
import it.qbsoftware.business.domain.util.get.CreationIdResolverPort;
import it.qbsoftware.business.ports.in.guava.ListMultimapPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxBuilderPort;
import it.qbsoftware.business.ports.in.jmap.entity.MailboxPort;
import it.qbsoftware.business.ports.in.jmap.entity.ResponseInvocationPort;
import it.qbsoftware.business.ports.in.jmap.entity.SetErrorEnumPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetMailboxMethodCallPort;
import it.qbsoftware.business.ports.out.domain.AccountStateRepository;
import it.qbsoftware.business.ports.out.domain.MailboxChangesTrackerRepository;
import it.qbsoftware.business.ports.out.jmap.MailboxRepository;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class StandardUpdateMailboxTest {

    @Mock MailboxRepository mailboxRepository;

    @Mock SetErrorEnumPort setErrorEnumPort;

    @Mock AccountStateRepository accountStateRepository;

    @Mock MailboxChangesTrackerRepository mailboxChangesTrackerRepository;

    @Mock MailboxChangesTracker mailboxChangesTracker;

    @Mock CreationIdResolverPort creationIdResolverPort;

    @Mock AccountState accountState;

    @Mock MailboxPort mailboxPort;

    @Mock MailboxBuilderPort mailboxBuilderPort;

    @Mock SetMailboxMethodCallPort setMailboxMethodCallPort;

    @Mock Map<String, String> successSubmissionMailboxIdResolve;

    @Mock ListMultimapPort<String, ResponseInvocationPort> previousResponses;

    @InjectMocks StandardUpdateMailbox standardUpdateMailbox;

    @Test
    public void testUpdateMailboxWithNullGetOnSuccessUpdateMailbox()
            throws AccountNotFoundException {
        Map<String, Object> mapObj = new HashMap<>();
        mapObj.put("name/name", "name/name");
        Map<String, Map<String, Object>> mapMailboxToUpdate = new HashMap<>();
        mapMailboxToUpdate.put("name/name", mapObj);
        when(setMailboxMethodCallPort.getUpdate()).thenReturn(mapMailboxToUpdate);

        UpdatedResult<MailboxPort> result =
                standardUpdateMailbox.update(setMailboxMethodCallPort, previousResponses);

        assertTrue(result.updated().isEmpty());
        // assertTrue(result.notUpdated().isEmpty());
        assertNotNull(result);
    }

    @Test
    public void testUpdateMailboxWithNotNullGetOnSuccessUpdateMailbox() throws Exception {
        Map<String, Boolean> map = new HashMap<>();
        map.put("#", true);
        Map<String, Object> mapObj = new HashMap<>();
        mapObj.put("#", map);
        Map<String, Map<String, Object>> mapMailboxToUpdate = new HashMap<>();
        mapMailboxToUpdate.put("#", mapObj);

        when(setMailboxMethodCallPort.getUpdate()).thenReturn(mapMailboxToUpdate);
        when(mailboxRepository.retriveOne(any())).thenReturn(mailboxPort);
        when(mailboxPort.toBuilder()).thenReturn(mailboxBuilderPort);

        UpdatedResult<MailboxPort> result =
                standardUpdateMailbox.update(setMailboxMethodCallPort, previousResponses);

        assertTrue(result.updated().isEmpty());
        assertNotNull(result);
    }

    @Test
    public void testUpdateMailboxWithSetInvalidPatchException() throws Exception {

        Map<String, Object> mapObj = new HashMap<>();
        mapObj.put("name", "name");
        Map<String, Map<String, Object>> mapMailboxToUpdate = new HashMap<>();
        mapMailboxToUpdate.put("name", mapObj);

        UpdatedResult<MailboxPort> result =
                standardUpdateMailbox.update(setMailboxMethodCallPort, previousResponses);

        assertNotNull(result);
    }

    @Test
    public void testUpdateMailboxMethodCallWithNullIdsToUpdate() throws AccountNotFoundException {

        when(setMailboxMethodCallPort.getUpdate()).thenReturn(null);

        UpdatedResult<MailboxPort> result =
                standardUpdateMailbox.update(setMailboxMethodCallPort, previousResponses);

        assertTrue(result.updated().isEmpty());
        assertTrue(result.notUpdated().isEmpty());
        assertNotNull(result);
    }

    @Test
    public void testUpdateMailboxMethodCallWithSetInvalidPatchException() throws Exception {

        Map<String, Object> mapObj = new HashMap<>();
        mapObj.put("/keywords", true);
        Map<String, Map<String, Object>> mapMailboxToUpdate = new HashMap<>();
        mapMailboxToUpdate.put("/keywords", mapObj);

        when(setMailboxMethodCallPort.getUpdate()).thenReturn(mapMailboxToUpdate);
        when(mailboxRepository.retriveOne(any())).thenReturn(mailboxPort);
        when(mailboxPort.toBuilder()).thenReturn(mailboxBuilderPort);

        when(setMailboxMethodCallPort.accountId()).thenReturn("accountId");

        UpdatedResult<MailboxPort> result =
                standardUpdateMailbox.update(setMailboxMethodCallPort, previousResponses);

        assertNotNull(result);
    }

    @Test
    public void testUpdateMailboxMethodCallWithSetNotFoundException() throws Exception {

        Map<String, Object> mapObj = new HashMap<>();
        mapObj.put("/keywords", true);
        Map<String, Map<String, Object>> mapMailboxToUpdate = new HashMap<>();
        mapMailboxToUpdate.put("/keywords", mapObj);
        SetNotFoundException setNotFoundException = new SetNotFoundException();

        when(setMailboxMethodCallPort.getUpdate()).thenReturn(mapMailboxToUpdate);
        when(mailboxRepository.retriveOne(any())).thenThrow(setNotFoundException);
        when(setMailboxMethodCallPort.accountId()).thenReturn("accountId");
        UpdatedResult<MailboxPort> result =
                standardUpdateMailbox.update(setMailboxMethodCallPort, previousResponses);

        assertNotNull(result);
    }
}
