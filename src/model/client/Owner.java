package model.client;

import java.util.*;

public class Owner {
    private OwnerName name;
    private Set<Pet> pets;

    private String email;
    //private Address address;
    private PhoneNumber phoneNumber;
    private String alert;

    public Owner(OwnerName name) {
       this.name = name;
       pets = new HashSet<>();

        setPhoneNumber(phoneNumber);
        setAlert(alert);
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

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber pn) {
        phoneNumber = pn;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alerts) {
        this.alert = alerts;
    }

    public void removeAlert() {
        alert = null;
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
