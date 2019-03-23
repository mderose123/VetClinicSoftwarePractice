package model.notes;

import exceptions.NullArgumentException;
import model.client.Pet;
import model.report.ReportCard;

public abstract class Note {
    private Pet pet;
    private ReportCard reportCard;
    private String message;

    public Note(Pet pet, String message) {
        this.pet = pet;
        this.message = message;
    }

    public Note(Pet pet, String message, ReportCard reportCard) {
        this.pet = pet;
        this.message = message;
        this.reportCard = reportCard;
    }

    public void editMessage() {
        //stub ui?
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) throws NullArgumentException {
        if(pet == null) {
            throw new NullArgumentException("Pet must not be null");
        }
        this.pet = pet;

    }



}
