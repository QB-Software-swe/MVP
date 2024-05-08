package it.qbsoftware.business.domain.methodcall.statematch;

import static org.junit.Assert.assertThrows;

import it.qbsoftware.business.domain.exception.StateMismatchException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class StandardIfInStateMatchTest {

    @InjectMocks private StandardIfInStateMatch standardIfInStateMatch;

    @Test
    public void testMethodStateMatchCurrentWithNullMethodCallState() throws StateMismatchException {
        standardIfInStateMatch.methodStateMatchCurrent(null, "currentObjectState");
    }

    @Test
    public void testMethodStateMatchCurrentWithMatchingStates() throws StateMismatchException {
        standardIfInStateMatch.methodStateMatchCurrent("state", "state");
    }

    @Test
    public void testMethodStateMatchCurrentWithMismatchingStates() {
        assertThrows(
                StateMismatchException.class,
                () -> standardIfInStateMatch.methodStateMatchCurrent("state1", "state2"));
    }
}
