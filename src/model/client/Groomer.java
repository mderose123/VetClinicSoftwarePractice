package model.client;

import java.util.HashMap;
import java.util.Map;

public class Groomer {

    private Map<Integer, Pet> bookings;

    public Groomer() {
        bookings = new HashMap<>();
        for (int i = 0; i <= 17; i++) {
            bookings.put(i, null);
        }
    }

    public void makeNewBooking(Pet p, int bookingTime) {
        if (bookingTime >= bookings.size()) {
            System.out.println("Invalid booking time");
        }

        System.out.println("Pet " + p.getName() + " has been booked at " + bookingTime);
        bookings.put(bookingTime, p);
        p.setBookedTime(bookingTime);

    }

    public void printBookingsList() {
        for (int i = 9; i < bookings.size(); i++) {
            Pet p = bookings.get(i);
            if (p != null) {
                System.out.println(i + "hrs: ");
                p.printName();
            }
        }
    }

    //MODIFIES: this and Pet
    //EFFECTS:  changes the pet booked in the bookings, and let's the Pet know the new booking time.
    public void changeBooking(Pet pet, int newTime) {
        int bookedTime = pet.getBookingTime();
        System.out.print(pet.getName() + "'s time is changing from " + bookedTime);
        System.out.println(" to " + newTime);
        bookings.put(bookedTime, null);
        bookings.put(newTime, pet);
        pet.setBookedTime(newTime);
    }

    //EFFECTS: returns true if the customer is found at the booking time.
    public boolean verifyBooking(Pet p, int bookingTime) {
        Pet bookedPet = bookings.get(bookingTime);
        if (bookedPet == null) {
            System.out.println("There is no pet booked at that time");
            return false;
        }
        if (bookedPet.getName().equals(p.getName())) {
            System.out.println("Yes the pet is booked at that time");
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: returns true if the pet is booked at the booking time
    public boolean confirmBookedName(String pName, int bookingTime){
        if (bookings.get(bookingTime)!= null) {
            Pet bookedPet = bookings.get(bookingTime);
            String bookedPetName = bookedPet.getName();
            return bookedPetName.equals(pName);
        }
        return false;
    }
}

