package model.client;

import exceptions.NullArgumentException;
import model.notes.Note;
import model.report.ReportCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Pet {
    Owner primaryOwner;
    String firstName;
    String lastName;
    List<Owner> owners;
    List<ReportCard> reportCards;
    List<Note> petNotes;

    public Pet(String firstName, Owner primaryOwner) {
        this.primaryOwner = primaryOwner;
        this.lastName = primaryOwner.getLastName();
        this.firstName = firstName;
        this.owners = new ArrayList<>();
        this.petNotes = new ArrayList<>();
        if(!owners.contains(primaryOwner)) {
            owners.add(primaryOwner);
            primaryOwner.addPet(this);
        }
        reportCards = new ArrayList<>();
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

    public Owner getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(Owner primaryOwner) {
        this.primaryOwner = primaryOwner;
        this.lastName = primaryOwner.getLastName();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Owner> getOwners() {
        return Collections.unmodifiableList(owners);
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
