package model.scheduler;


import exceptions.AppointmentBookedException;
import exceptions.InvalidTimeException;
import exceptions.NullArgumentException;

import java.util.*;


public abstract class Schedule {
    private HashMap<Time, Appointment> bookings;
    private Date date;
    private Calendar calendar;
    public static final int APPOINTMENT_TIME_ALLOTMENT = 15;
    private Time time;
    private Time earliestBookableTime;
    private Time latestBookableTime;



    Schedule(Date date) throws NullArgumentException {
        if (date == null) {
            throw new NullArgumentException();
        }

        this.date = date;
        this.calendar = Calendar.getInstance();
        calendar.setTime(date);
        bookings = new HashMap<>();
        time = new Time();
        earliestBookableTime = new Time();
        latestBookableTime = new Time(23,45);
        bookings.put(time, null);
        do{
            time.addAppointmentTimeAllotment();
            bookings.put(new Time(time.getHour(), time.getMinute()), null);
        } while (!time.equals(new Time()));

    }

    public HashMap<Time, Appointment> getBookings() {
        return bookings;
    }

    public Date getDate() {
        return date;
    }

    //MODIFIES: this
    //EFFECTS: removes booking times before given hour and minute, set earliestBookableTime
    public void setLaterFirstBookableAppointment(int hour, int minute) throws InvalidTimeException, AppointmentBookedException {
        checkInvalidTimeException(hour, minute);
        Time refTime = new Time(earliestBookableTime.getHour(), earliestBookableTime.getMinute());
        checkBookedAppointmentsBefore(hour, minute, refTime);
        do {
            bookings.remove(refTime);
            refTime.addAppointmentTimeAllotment();
        } while (refTime.isBefore(new Time(hour, minute)) || refTime.equals(new Time(hour, minute)));
        earliestBookableTime.setHour(hour);
        earliestBookableTime.setMinute(minute);
    }

    //MODIFIES: this
    //EFFECTS: removes bookable times after given hour and minute, set latestBookableTime
    public void setEarlierLastBookableAppointment(int hour, int minute) throws InvalidTimeException, AppointmentBookedException {
        checkInvalidTimeException(hour, minute);
        Time refTime = new Time(hour,minute);
        checkBookedAppointmentsAfter(hour, minute, refTime);
        do {
            bookings.remove(refTime);
            refTime.addAppointmentTimeAllotment();
        } while (refTime.isBefore( latestBookableTime) || refTime.equals(latestBookableTime));
        latestBookableTime.setHour(hour);
        latestBookableTime.setMinute(minute);
    }


    //MODIFIES: this
    //EFFECTS: adds bookable times from given hour and minute up to 00:00 AM
    public void setEarlierEarliestBookableTime(int hour, int minute) throws InvalidTimeException {
        checkInvalidTimeException(hour, minute);
        if (earliestBookableTime.isMidnight()) {
            throw new InvalidTimeException("Current Earliest Booking Time is 00:00 AM");
        } else if (earliestBookableTime.isAfter(new Time(hour, minute))) {
            throw new InvalidTimeException("Given time must be before the current earliest bookable time");
        } else if(earliestBookableTime.equals(new Time(hour, minute))) {
            throw new InvalidTimeException("Given time is the same as Earliest Booking Time");
        } else {
            Time newTime = new Time(hour, minute);
            do {
                bookings.put(new Time(newTime.getHour(), newTime.getMinute()), null);
                newTime.addAppointmentTimeAllotment();
            } while (newTime.isBefore(earliestBookableTime));
            earliestBookableTime.setHour(hour);
            earliestBookableTime.setMinute(minute);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds bookable times up to given hour and minute before 11:45 PM
    public void setLaterLatestBookableTime(int hour, int minute) throws InvalidTimeException {
        checkInvalidTimeException(hour, minute);
        if (latestBookableTime.getHour()== 23 && latestBookableTime.getMinute() == 45) {
            throw new InvalidTimeException("Current Latest Booking Time is currently 11:45 PM");
        } else if (latestBookableTime.isBefore(new Time(hour, minute))) {
            throw new InvalidTimeException("Given time must be before the current earliest bookable time");
        } else if (latestBookableTime.equals(new Time(hour, minute))){
            Time newTime = new Time(hour, minute);
            do {
                bookings.put(new Time(newTime.getHour(), newTime.getMinute()), null);
                newTime.addAppointmentTimeAllotment();
            } while (newTime.isBefore(earliestBookableTime));
            earliestBookableTime.setHour(hour);
            earliestBookableTime.setMinute(minute);
        }
    }

//****************************************HELPER FUNCTIONS*************************************************************
    private void checkInvalidTimeException(int hour, int minute) throws InvalidTimeException {
        if (hour < 0 || hour > 23) {
            throw new InvalidTimeException("Hour must be between 0 and 23");
        }
        if ((minute < 0 || minute > 45) && minute % 15 == 0) {
            throw new InvalidTimeException("Minute must be between 0 and 45 and a multiple of 15");
        }
    }


    //MODIFIES: none
    //EFFECTS: checks to see if appointments booked before provided hour and minute
    private void checkBookedAppointmentsBefore(int hour, int minute, Time refTime) throws AppointmentBookedException {
        do {
            if(bookings.get(refTime) != null) {
                throw new AppointmentBookedException("Appointment booked at " + refTime);
            }
            refTime.addAppointmentTimeAllotment();
        } while (refTime.getHour() <= hour && refTime.getMinute() <= minute);
    }

    //MODIFIES: none
    //EFFECTS: checks to see if appointments booked before provided hour and minute
    private void checkBookedAppointmentsAfter(int hour, int minute, Time refTime) throws AppointmentBookedException {
        do {
            if(bookings.get(refTime) != null) {
                throw new AppointmentBookedException("Appointment booked at " + refTime);
            }
            refTime.addAppointmentTimeAllotment();
        } while (refTime.getHour() >= hour && refTime.getMinute() >= minute);
    }




}

//
//    public void makeNewBooking(Pet p, int bookingTime) {
//        if (bookingTime >= bookings.size()) {
//            System.out.println("Invalid booking time");
//        }
//
//        System.out.println("Pet " + p.getName() + " has been booked at " + bookingTime);
//        bookings.put(bookingTime, p);
//        p.setBookedTime(bookingTime);
//
//    }
//
//    public void printBookingsList() {
//        for (int i = 9; i < bookings.size(); i++) {
//            Pet p = bookings.get(i);
//            if (p != null) {
//                System.out.println(i + "hrs: ");
//                p.printName();
//            }
//        }
//    }
//
//    //MODIFIES: this and Pet
//    //EFFECTS:  changes the pet booked in the bookings, and let's the Pet know the new booking time.
//    public void changeBooking(Pet pet, int newTime) {
//        int bookedTime = pet.getBookingTime();
//        System.out.print(pet.getName() + "'s time is changing from " + bookedTime);
//        System.out.println(" to " + newTime);
//        bookings.put(bookedTime, null);
//        bookings.put(newTime, pet);
//        pet.setBookedTime(newTime);
//    }
//
//    //EFFECTS: returns true if the customer is found at the booking time.
//    public boolean verifyBooking(Pet p, int bookingTime) {
//        Pet bookedPet = bookings.get(bookingTime);
//        if (bookedPet == null) {
//            System.out.println("There is no pet booked at that time");
//            return false;
//        }
//        if (bookedPet.getName().equals(p.getName())) {
//            System.out.println("Yes the pet is booked at that time");
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    //EFFECTS: returns true if the pet is booked at the booking time
//    public boolean confirmBookedName(String pName, int bookingTime){
//        if (bookings.get(bookingTime)!= null) {
//            Pet bookedPet = bookings.get(bookingTime);
//            String bookedPetName = bookedPet.getName();
//            return bookedPetName.equals(pName);
//        }
//        return false;
//    }
//}
