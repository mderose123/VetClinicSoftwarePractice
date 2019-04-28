package Test;

import model.client.Owner;
import model.client.Pet;
import model.client.Species;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestPet {

    private Pet testPet;
    private Owner owner;

    @Before
    public void runBefore() {
        testPet = new Pet("Fido", owner);
    }

    @Test
    public void testConstructor() {
        assertEquals("Fido", testPet.getName());
        assertEquals(0, testPet.getBookingTime());
        assertNull(testPet.getSpecies());
        assertNull(testPet.getBreed());
        assertNull(testPet.getColour());
        assertNull(testPet.getOwner());
        //assertEquals(0.0, testPet.getMicrochip());
        assertTrue(testPet.getBehaviorTags().isEmpty());
    }

    @Test
    public void testChangePetName() {
        assertEquals("Fido", testPet.getName());
        testPet.setName("Bruno");
        assertEquals("Bruno", testPet.getName());
    }

    @Test
    public void testChangeSpeciesBreedColour() {
        assertNull(testPet.getSpecies());
        assertNull(testPet.getBreed());
        assertNull(testPet.getColour());
        testPet.setSpecies(Species.CANINE);
        testPet.setBreed("PitBull");
        testPet.setColour("Brown");
        assertEquals(Species.CANINE, testPet.getSpecies());
        assertEquals("PitBull", testPet.getBreed());
        assertEquals("Brown", testPet.getColour());
    }

    @Test
    public void testChangeOwner() {
        assertNull(testPet.getOwner());

        //Owner testOwner = new Owner("Charlotte");
       // testPet.setOwner(testOwner);
        assertEquals("Charlotte", testPet.getOwner().getName());

        //Owner testOwner2 = new Owner("Matthew");
      //  testPet.setOwner(testOwner2);
        assertEquals("Matthew", testPet.getOwner().getName());
    }

    @Test
    public void testAddBehaviorTag() {
        assertTrue(testPet.getBehaviorTags().isEmpty());
        testPet.addBehaviorTag("Aggressive");
        assertTrue(testPet.containsBehavior("Aggressive"));
    }

    @Test
    public void testAddMultipleBehaviorTags() {
        assertTrue(testPet.getBehaviorTags().isEmpty());
        testPet.addBehaviorTag("Aggressive");
        testPet.addBehaviorTag("Nervous");
        testPet.addBehaviorTag("Fearful");
        assertTrue(testPet.containsBehavior("Aggressive"));
        assertTrue(testPet.containsBehavior("Nervous"));
        assertTrue(testPet.containsBehavior("Fearful"));
        assertFalse(testPet.containsBehavior("Gentle"));
    }

    @Test
    public void testRemoveBehaviorTag() {
        assertTrue(testPet.getBehaviorTags().isEmpty());
        testPet.addBehaviorTag("Aggressive");
        assertTrue(testPet.containsBehavior("Aggressive"));
        testPet.removeBehaviorTag("Aggressive");
        assertFalse(testPet.containsBehavior("Aggressive"));
        assertTrue(testPet.getBehaviorTags().isEmpty());
    }

    @Test
    public void testRemoveMultipleBehaviorTags() {
        assertTrue(testPet.getBehaviorTags().isEmpty());
        testPet.addBehaviorTag("Aggressive");
        testPet.addBehaviorTag("Fearful");
        testPet.addBehaviorTag("Nervous");

        testPet.removeBehaviorTag("Aggressive");
        assertFalse(testPet.containsBehavior("Aggressive"));
        testPet.removeBehaviorTag("Fearful");
        assertFalse(testPet.containsBehavior("Fearful"));
        testPet.removeBehaviorTag("Nervous");
        assertFalse(testPet.containsBehavior("Nervous"));

        assertTrue(testPet.getBehaviorTags().isEmpty());
    }

    //ADD TESTS FOR MICROCHIP
}
