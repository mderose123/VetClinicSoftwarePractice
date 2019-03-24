package Test;

import model.client.Owner;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class TestOwner {
    private Owner owner;

    @Before
    public void runBefore() {
        owner = new Owner("Charlotte");
    }

    @Test
    public void testConstructor() {
        assertTrue(owner.getName().equals("Charlotte"));
       // assertNull(owner.getPrefix());
        assertNull(owner.getEmail());
        assertEquals(0, owner.getPhoneNumber());
        assertTrue(owner.getPets().isEmpty());
        assertTrue(owner.getAlerts().isEmpty());
    }
}

