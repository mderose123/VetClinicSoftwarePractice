package test;

import model.client.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestOwner {
    private Owner owner;

    @BeforeEach
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

