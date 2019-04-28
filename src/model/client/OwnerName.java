package model.client;

import exceptions.EmptyStringException;
import exceptions.NullArgumentException;

public class OwnerName {
    private Prefix prefix;
    private String firstName;
    private String middleName;
    private String lastName;

    public OwnerName(Prefix prefix, String fName, String mName, String lName) {
        //if(fName == null || lName == null || fName.isEmpty() || lName.isEmpty()) {
       //     throw new EmptyStringException();
        //}

        //if(prefix == null) {
          //  throw new NullArgumentException();
        //}

        this.prefix = prefix;
        this.firstName = fName;
        this.lastName = lName;
        this.middleName = mName;
    }

    public Prefix getPrefix() {
        return prefix;
    }

    public void setPrefix(Prefix prefix) {
        this.prefix = prefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
