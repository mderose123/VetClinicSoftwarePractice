package model.client;

public enum Species {
    CANINE("CANINE"),
    FELINE("FELINE"),
    RODENT("RODENT"),
    BIRD("BIRD");

    private String speciesName;

    // EFFECTS: sets species
    Species (String species) {
        this.speciesName = species;
    }


    // EFFECTS: returns description of Status
    public String getSpecies() {
        return speciesName;
    }

    // EFFECTS: returns description of Status
    @Override
    public String toString() {
        return speciesName;
    }

}
