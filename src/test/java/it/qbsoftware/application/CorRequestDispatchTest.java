package it.qbsoftware.application;

import com.google.gson.Gson;
import it.qbsoftware.application.controllers.changes.ChangesEmailMethodCallController;
import it.qbsoftware.application.controllers.changes.ChangesIdentityMethodCallController;
import it.qbsoftware.application.controllers.changes.ChangesMailboxMethodCallController;
import it.qbsoftware.application.controllers.changes.ChangesThreadMethodCallController;
import it.qbsoftware.application.controllers.get.GetEmailMethodCallController;
import it.qbsoftware.application.controllers.get.GetIdentityMethodCallController;
import it.qbsoftware.application.controllers.get.GetMailboxMethodCallController;
import it.qbsoftware.application.controllers.get.GetThreadMethodCallController;
import it.qbsoftware.application.controllers.other.EchoMethodCallController;
import it.qbsoftware.application.controllers.query.QueryEmailMethodCallController;
import it.qbsoftware.application.controllers.set.SetEmailMethodCallController;
import it.qbsoftware.application.controllers.set.SetEmailSubmissionMethodCallController;
import it.qbsoftware.application.controllers.set.SetIdentityMethodCallController;
import it.qbsoftware.application.controllers.set.SetMailboxMethodCallController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rs.ltt.jmap.common.Request;
import rs.ltt.jmap.common.method.MethodCall;
import rs.ltt.jmap.common.method.MethodResponse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class CorRequestDispatchTest {

    @Mock private EchoMethodCallController echoMethodCallController;

    @Mock private GetEmailMethodCallController getEmailMethodCallController;

    @Mock private GetIdentityMethodCallController getIdentityMethodCallController;

    @Mock private GetMailboxMethodCallController getMailboxMethodCallController;

    @Mock private GetThreadMethodCallController getThreadMethodCallController;

    @Mock private ChangesEmailMethodCallController changesEmailMethodCallController;

    @Mock private ChangesIdentityMethodCallController changesIdentityMethodCallController;

    @Mock private ChangesMailboxMethodCallController changesMailboxMethodCallController;

    @Mock private ChangesThreadMethodCallController changesThreadMethodCallController;

    @Mock private SetEmailMethodCallController setEmailMethodCallController;

    @Mock private SetIdentityMethodCallController setIdentityMethodCallController;

    @Mock private SetMailboxMethodCallController setMailboxMethodCallController;

    @Mock private SetEmailSubmissionMethodCallController setEmailSubmissionMethodCallController;

    @Mock private QueryEmailMethodCallController queryEmailMethodCallController;

    @Mock private MethodCall methodCall;

    @Mock private MethodResponse methodResponse;

    @Mock private Request request;

    @Mock private Gson gson;

    @InjectMocks private CorRequestDispatch corRequestDispatch;

    @Test
    public void testDispatch() {}
}
