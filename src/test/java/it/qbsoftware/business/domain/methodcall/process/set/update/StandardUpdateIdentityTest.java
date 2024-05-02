package it.qbsoftware.business.domain.methodcall.process.set.update;

import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import it.qbsoftware.business.ports.in.jmap.entity.IdentityPort;
import it.qbsoftware.business.ports.in.jmap.method.call.set.SetIdentityMethodCallPort;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class StandardUpdateIdentityTest {
    @Mock
    SetIdentityMethodCallPort setIdentityMethodCallPort;

    @InjectMocks
    private StandardUpdateIdentity standardUpdateIdentity;

    @Test
    public void testUpdate() {
        UpdatedResult<IdentityPort> result = standardUpdateIdentity.update(setIdentityMethodCallPort);
        
        assertNull(result.updated());
        assertNull(result.notUpdated());
    }

}

