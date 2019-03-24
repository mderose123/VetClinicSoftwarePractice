package model.client;

public class BehaviorTag {
    private String behaviorName;


    // EFFECTS: creates a behavior tag with the given name
    public BehaviorTag(String behaviorName) {
        this.behaviorName = behaviorName;
    }

    // EFFECTS: returns the name of this behavior tag
    public String getBehaviorName() {
        return behaviorName;
    }

}
