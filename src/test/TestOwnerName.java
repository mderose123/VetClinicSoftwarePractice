package test;

import model.client.OwnerName;
import model.client.Prefix;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestOwnerName {

    private OwnerName ownerName;

    @Before
    public void runBefore() {
        ownerName = new OwnerName(Prefix.MS, "Charlotte", "Olenka", "Stewart");
    }

    @Test
    public void testConstructor() {
        assertEquals(ownerName.getFirstName(), "Charlotte");
        assertEquals(ownerName.getMiddleName(), "Olenka");
        assertEquals(ownerName.getLastName(), "Stewart");
        assertNotEquals(ownerName.getFirstName(), "Matthew");
        assertNotEquals(ownerName.getFirstName(), "Rob");
        assertNotEquals(ownerName.getFirstName(), "DeRose");
    }

    @Test
    public void testChangePrefix() {
        assertEquals(ownerName.getPrefix(), Prefix.MS);
        ownerName.setPrefix(Prefix.MR);
        assertEquals(ownerName.getPrefix(), Prefix.MR);
    }

    @Test
    public void testChangeFirstName() {
        assertEquals(ownerName.getFirstName(), "Charlotte");
        ownerName.setFirstName("Matthew");
        assertEquals(ownerName.getFirstName(), "Matthew");
    }

    @Test
    public void testChangeMiddleName() {
        assertEquals(ownerName.getMiddleName(), "Olenka");
        ownerName.setMiddleName("Rob");
        assertEquals(ownerName.getMiddleName(), "Rob");
    }

    @Test
    public void testChangeLastName() {
        assertEquals(ownerName.getLastName(), "Stewart");
        ownerName.setLastName("DeRose");
        assertEquals(ownerName.getLastName(), "DeRose");
    }

    @Test
    public void testNoMiddleName() {
        OwnerName ownerName2 = new OwnerName(Prefix.MR, "Bob", null, "Ross");
        assertNull(ownerName2.getMiddleName());
        ownerName2.setMiddleName("Rob");
        assertEquals(ownerName2.getMiddleName(), "Rob");
    }
}
