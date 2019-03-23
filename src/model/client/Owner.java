package model.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Owner {
    private OwnerName name;
    private List<Pet> pets;

    public Owner(OwnerName name) {
       this.name = name;
       this.pets = new ArrayList<>();
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

    public List<Pet> getPets() {
        return Collections.unmodifiableList(pets);
    }

    public OwnerName getName() {
        return name;
    }

    public Prefix getPrefix() {
        return name.getPrefix();
    }


    public void setPrefix(Prefix prefix) {
        if(!name.getPrefix().equals(prefix)) {
            name.setPrefix(prefix);
        }
    }

    public String getFirstName() {
        return name.getFirstName();
    }

    public String getMiddleName() {
        return name.getMiddleName();
    }

    public String getLastName() {
        return name.getLastName();
    }

    public void setFirstName(String newFirstName) {
        if(!newFirstName.equals(name.getFirstName())) {
            this.name.setFirstName(newFirstName);
        }
    }

    public void setMiddleName(String newMiddleName) {
        if(!newMiddleName.equals(name.getMiddleName())) {
            this.name.setFirstName(newMiddleName);
        }
    }

    public void setLastName(String newLastName) {
        if(!newLastName.equals(name.getLastName())) {
            this.name.setFirstName(newLastName);
        }
    }




}
