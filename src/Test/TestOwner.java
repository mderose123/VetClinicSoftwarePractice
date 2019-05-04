package test;


import model.client.*;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class TestOwner {
    private Owner owner;
    private OwnerName ownerName;

    @Before
    public void runBefore() {
        ownerName = new OwnerName(Prefix.MS, "Charlotte", "Olenka", "Stewart");
        owner = new Owner(ownerName);
    }

    @Test
    public void testConstructor() {
        assertEquals(owner.getName(), ownerName);
        assertNull(owner.getEmail());
        assertNull(owner.getPhoneNumber());
        assertTrue(owner.getPets().isEmpty());
        assertNull(owner.getAlert());
    }

    @Test
    public void testChangeOwnerName() {
        assertEquals(owner.getName(), ownerName);
        assertEquals(owner.getName().getPrefix(), Prefix.MS);
        assertEquals(owner.getName().getFirstName(), "Charlotte");
        assertEquals(owner.getName().getMiddleName(), "Olenka");
        assertEquals(owner.getName().getLastName(), "Stewart");

        OwnerName ownerName2 = new OwnerName(Prefix.MR, "Bob", "Hugo", "Ross");
        owner.setName(ownerName2);

        assertEquals(owner.getName(), ownerName2);
        assertEquals(owner.getName().getPrefix(), Prefix.MR);
        assertEquals(owner.getName().getFirstName(), "Bob");
        assertEquals(owner.getName().getMiddleName(), "Hugo");
        assertEquals(owner.getName().getLastName(), "Ross");
    }

    @Test
    public void testChangeOwnerNameNoMiddleName() {
        assertEquals(owner.getName(), ownerName);
        assertEquals(owner.getName().getPrefix(), Prefix.MS);
        assertEquals(owner.getName().getFirstName(), "Charlotte");
        assertEquals(owner.getName().getMiddleName(), "Olenka");
        assertEquals(owner.getName().getLastName(), "Stewart");

        OwnerName ownerName2 = new OwnerName(Prefix.MR, "Bob", null, "Ross");
        owner.setName(ownerName2);

        assertEquals(owner.getName(), ownerName2);
        assertEquals(owner.getName().getPrefix(), Prefix.MR);
        assertEquals(owner.getName().getFirstName(), "Bob");
        assertNull(owner.getName().getMiddleName());
        assertEquals(owner.getName().getLastName(), "Ross");
    }

    @Test
    public void testAddOnePet() {
        Pet pet1 = new Pet("Fido", owner);
        owner.addPet(pet1);
        assertTrue(owner.ownsPet("Fido"));
        assertTrue(owner.getPets().contains(pet1));
    }

    @Test
    public void testAddMultiplePets() {
        Pet pet1 = new Pet("Fido", owner);
        Pet pet2 = new Pet("Rascal", owner);
        Pet pet3 = new Pet("Megan", owner);
        owner.addPet(pet1);
        owner.addPet(pet2);
        owner.addPet(pet3);

        assertTrue(owner.ownsPet("Fido"));
        assertTrue(owner.ownsPet("Rascal"));
        assertTrue(owner.ownsPet("Megan"));
        assertTrue(owner.getPets().contains(pet1));
        assertTrue(owner.getPets().contains(pet2));
        assertTrue(owner.getPets().contains(pet3));
    }

    @Test
    public void testAddDuplcatePets() {
        Pet pet1 = new Pet("Fido", owner);
        Pet pet2 = new Pet("Fido", owner);
        Pet pet3 = new Pet("Megan", owner);
        owner.addPet(pet1);
        owner.addPet(pet2);
        owner.addPet(pet3);

        assertTrue(owner.ownsPet("Fido"));
        assertTrue(owner.ownsPet("Megan"));
        assertEquals(owner.getPets().size(), 2);
    }

    @Test
    public void testRemoveOnePet() {
        Pet pet1 = new Pet("Fido", owner);
        Pet pet2 = new Pet("Megan", owner);
        owner.addPet(pet1);
        owner.addPet(pet2);

        assertTrue(owner.ownsPet("Fido"));
        assertTrue(owner.ownsPet("Megan"));
        assertEquals(owner.getPets().size(), 2);

        owner.removePet(pet1);
        assertFalse(owner.ownsPet("Fido"));
        assertTrue(owner.ownsPet("Megan"));
        assertEquals(owner.getPets().size(), 1);
    }

    @Test
    public void testRemoveAllPets() {
        Pet pet1 = new Pet("Fido", owner);
        Pet pet2 = new Pet("Megan", owner);
        owner.addPet(pet1);
        owner.addPet(pet2);

        assertTrue(owner.ownsPet("Fido"));
        assertTrue(owner.ownsPet("Megan"));
        assertEquals(owner.getPets().size(), 2);

        owner.removePet(pet1);
        owner.removePet(pet2);
        assertFalse(owner.ownsPet("Fido"));
        assertFalse(owner.ownsPet("Megan"));
        assertTrue(owner.getPets().isEmpty());
    }

    @Test
    public void testChangeEmailAddress() {
        assertNull(owner.getEmail());
        owner.setEmail("costewart4@gmail.com");
        assertEquals(owner.getEmail(), "costewart4@gmail.com");
    }

    @Test
    public void testChangePhoneNumber() {
        assertNull(owner.getPhoneNumber());
        PhoneNumber pn = new PhoneNumber(604, 353, 7014);
        owner.setPhoneNumber(pn);
        assertEquals(owner.getPhoneNumber(), pn);
    }

    @Test
    public void removePhoneNumber() {
        assertNull(owner.getPhoneNumber());
        PhoneNumber pn = new PhoneNumber(604, 353, 7014);
        owner.setPhoneNumber(pn);
        assertEquals(owner.getPhoneNumber(), pn);
        owner.setPhoneNumber(null);
        assertNull(owner.getPhoneNumber());
    }

    @Test
    public void addAlert() {
        assertNull(owner.getAlert());
        owner.setAlert("Do not feed their pet any treats!");
        assertEquals(owner.getAlert(), "Do not feed their pet any treats!");
    }

    @Test
    public void removeAlert() {
        assertNull(owner.getAlert());
        owner.setAlert("Do not feed their pet any treats!");
        assertEquals(owner.getAlert(), "Do not feed their pet any treats!");
        owner.removeAlert();
        assertNull(owner.getAlert());
    }

}

