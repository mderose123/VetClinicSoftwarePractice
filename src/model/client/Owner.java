package model.client;

import java.util.*;

public class Owner {
    private OwnerName name;
    private Set<Pet> pets;

    private String email;
    //private Address address;
    private int phoneNumber;
    private String alerts;

    public Owner(OwnerName name) {
       this.name = name;
       pets = new HashSet<>();

        setPhoneNumber(phoneNumber);
        setAlerts(alerts);
        setEmail(email);
    }

    public OwnerName getName() {
        return name;
    }

    public void setName(OwnerName name) {
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

    public String getAlerts() {
        return alerts;
    }

    public void setAlerts(String alerts) {
        this.alerts = alerts;
    }

    public Set<Pet> getPets() {
        return pets;
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
           // pet.addOwner(this);
        }
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

    public void removePet(Pet pet) {
        Pet newPet = pet;
        if(pets.contains(pet)) {
            for(Pet p:pets) {
                if(p.equals(pet)) {
                    newPet = p;
                }
            }
            pets.remove(pet);
          //  newPet.removeOwner(this);
        }
    }


}
