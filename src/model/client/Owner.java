package model.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Owner {
    private String name;
    private List<Pet> pets;
    private Prefix prefix;

    //private String name;
    private String email;
    private int phoneNumber;
    private String alerts;

    public Owner(String name) {
       this.name = name;
       pets = new ArrayList<>();

       // setPrefix(prefix);
        setPhoneNumber(phoneNumber);
        setAlerts(alerts);
        setEmail(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Pet> getPets() {
        return Collections.unmodifiableList(pets);
    }

    public String getAlerts() {
        return alerts;
    }

    public void setAlerts(String alerts) {
        this.alerts = alerts;
    }




    public void removePet(String petName) {
        List<Pet> toRemove = new ArrayList<>();
        for (Pet p : pets) {
            if (ownsPet(petName)) {
                toRemove.add(p);
            }
        }
        pets.removeAll(toRemove);
    }


    // EFFECTS: returns true if owner owns a pet with petName,
    //          returns false otherwise
    public boolean ownsPet(String petName) {
        for (Pet p : pets) {
            if (p.getName().equals(petName)) {
                return true;
            }
        } return false;
    }

    public void addPet(Pet pet) {
        if(!pets.contains(pet)) {
            pets.add(pet);
            pet.addOwner(this);
        }
    }

    public void removePet(Pet pet) {
        Pet newPet = pet;
        if(pets.contains(pet)) {
            for(Pet p:pets) {
                if(p.equals(pet)) {
                    newPet = p;
                }
            }
            pets.remove(pet);
            newPet.removeOwner(this);
        }
    }



//    public Prefix getPrefix() {
//        return name.getPrefix();
//    }
//
//
//    public void setPrefix(Prefix prefix) {
//        if(!name.getPrefix().equals(prefix)) {
//            name.setPrefix(prefix);
//        }
//    }

//    public String getFirstName() {
//        return name.getFirstName();
//    }
//
//    public String getMiddleName() {
//        return name.getMiddleName();
//    }
//
//    public String getLastName() {
//        return name.getLastName();
//    }
//
//    public void setFirstName(String newFirstName) {
//        if(!newFirstName.equals(name.getFirstName())) {
//            this.name.setFirstName(newFirstName);
//        }
//    }
//
//    public void setMiddleName(String newMiddleName) {
//        if(!newMiddleName.equals(name.getMiddleName())) {
//            this.name.setFirstName(newMiddleName);
//        }
//    }
//
//    public void setLastName(String newLastName) {
//        if(!newLastName.equals(name.getLastName())) {
//            this.name.setFirstName(newLastName);
//        }
//    }




}
