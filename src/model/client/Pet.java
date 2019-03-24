package model.client;

import exceptions.NullArgumentException;
import model.notes.Note;
import model.report.ReportCard;

import java.util.*;

public class Pet {
    private Owner owner;
    private String name;
    private String breed;
    private Species species;
    private String colour;
    private int age;
    private double microchip;

    List<Owner> owners;
    private List<ReportCard> reportCards;
    private List<Note> petNotes;
    private Set<BehaviorTag> behaviorTags;
    private int bookedTime;

    public Pet(String name) {

        this.name = name;
        setOwner(owner);

        setBreed(breed);
        setSpecies(species);
        setOwner(owner);
        setColour(colour);
        setMicrochip(microchip);
        behaviorTags = new HashSet<>();
        this.bookedTime = 0;

        this.petNotes = new ArrayList<>();
        reportCards = new ArrayList<>();

        //this.lastName = primaryOwner.getLastName();
        //this.firstName = firstName;
        this.owners = new ArrayList<>();

//        if(!owners.contains(primaryOwner)) {
//            owners.add(primaryOwner);
//            primaryOwner.addPet(this);
//        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBookingTime() {
        return bookedTime;
    }

    public void setBookedTime(int time) {
        bookedTime = time;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMicrochip() {
        return microchip;
    }

    public void setMicrochip(double microchip) {
        this.microchip = microchip;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Set<BehaviorTag> getBehaviorTags() {
        return behaviorTags;
    }

    public void addBehaviorTag(String tagName) {
        if (!containsBehavior(tagName)) {
            behaviorTags.add(new BehaviorTag(tagName));
        }
    }

    public void removeBehaviorTag(String tagName) {

        List<BehaviorTag> toRemove = new ArrayList<>();
        for (BehaviorTag bt : behaviorTags) {
            if (containsBehavior(tagName)) {
                toRemove.add(bt);
            }
        }
        behaviorTags.removeAll(toRemove);
    }

    // EFFECTS: returns true if pet exhibits a behavior with behaviorName,
    //          returns false otherwise
    public boolean containsBehavior(String behaviorName) {
        for (BehaviorTag t : behaviorTags) {
            if (t.getBehaviorName().equals(behaviorName)) {
                return true;
            }
        } return false;
    }

    public void printName() {
        System.out.println(getName());
    }

    // EFFECTS: returns the bookedTime of this pet's appointment
    public int confirmBooking() {
        System.out.println(name+ ": Confirming that Fido is booked at "+bookedTime);
        return bookedTime;
    }

    public List<Owner> getOwners() {
        return Collections.unmodifiableList(owners);
    }

    public void addOwner(Owner owner) {
        if(!owners.contains(owner)) {
            owners.add(owner);
        }
    }

    public void removeOwner(Owner owner) {
        Owner deRefOwner = owner;
        if(owners.contains(owner)) {
            owners.remove(owner);
            deRefOwner.removePet(this);
        }
    }

    public void addReportCard(ReportCard rc) {
        if(!reportCards.contains(rc)) {
            reportCards.add(rc);
        }
    }

    public List<ReportCard> getReportCardFromDate(Date date) {
        List<ReportCard> reportCardsOnDate = new ArrayList<>();
        for(ReportCard r: reportCards) {
            if (r.getDate().equals(date)) {
                reportCardsOnDate.add(r);
            }
        }
        return reportCardsOnDate;
    }

    public void removeReportCard(ReportCard rc) {
        if(reportCards.contains(rc)) {
            reportCards.remove(rc);
        }
    }

    public void addNote(Note note) throws NullArgumentException {
       if(note == null) {
           throw new NullArgumentException("Note must not be null");
       }
        if(!petNotes.contains(note)) {
            petNotes.add(note);
        }
    }





}
