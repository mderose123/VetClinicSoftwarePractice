package model.scheduler;


import exceptions.AppointmentBookedException;
import exceptions.InvalidTimeException;
import exceptions.NullArgumentException;
import model.client.Pet;

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
        bookings = new LinkedHashMap<>();
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

    public Time getEarliestBookableTime() {
        return earliestBookableTime;
    }

    public Time getLatestBookableTime() {
        return latestBookableTime;
    }

    public int getTotalBookableAppointments() {
        int count = 0;
        Time newTime = new Time(earliestBookableTime.getHour(), earliestBookableTime.getMinute());
        while(newTime.isBefore(latestBookableTime) || !newTime.equals(latestBookableTime)) {
            if(bookings.get(newTime) == null) {
                count++;
            }
            newTime.addAppointmentTimeAllotment();
        }
        if(newTime.equals(latestBookableTime)) {
            if(bookings.get(earliestBookableTime) == null) {
                count++;
            }
            return count;
        }
        return count;
    }

    public boolean checkBookingAvailability(Time startTime, int numTimeSlots) {
        if(startTime == null) {
            throw new NullArgumentException();
        }
        if(numTimeSlots<=0 || numTimeSlots > bookings.size()) {
            throw new InvalidTimeException("Number of Time Slots must be >0 and <bookings.size()");
        }
        Time newTime = new Time(startTime.getHour(), startTime.getMinute());
        do{
            if(bookings.get(newTime) != null) {
                return false;
            }
            newTime.addAppointmentTimeAllotment();
            numTimeSlots--;
        } while (numTimeSlots != 0);
        return true;
    }

    public boolean checkBookingAvailability(Time startTime, Time endTime) {
        if(startTime == null) {
            throw new NullArgumentException();
        }
        if(startTime.isAfter(endTime)) {
            throw new InvalidTimeException("Start Time must be before or the same as the End Time");
        }
        do{
            if(bookings.get(startTime) != null) {
                return false;
            }
            startTime.addAppointmentTimeAllotment();
        } while (!startTime.equals(endTime));
        return true;
    }

    public List<Appointment> getAppointmentsBookedInGivenTimeFrame(Time startTime, int numTimeSlots) {
        if(startTime == null) {
            throw new NullArgumentException();
        }
        if(numTimeSlots<=0 || numTimeSlots > bookings.size()) {
            throw new InvalidTimeException("Number of Time Slots must be >0 and <bookings.size()");
        }
        List<Appointment> appointments = new ArrayList<>();
        do{
            if(bookings.get(startTime) != null) {
                appointments.add(bookings.get(startTime));
            }
            startTime.addAppointmentTimeAllotment();
            numTimeSlots--;
        } while (numTimeSlots != 0);
        return appointments;
    }

    public List<Appointment> getAppointmentsBookedInGivenTimeFrame(Time startTime, Time endTime) {
        if(startTime == null || endTime == null) {
            throw new NullArgumentException();
        }
        if(startTime.isAfter(endTime)) {
            throw new InvalidTimeException("Start Time must be before or the same as End Time");
        }
        List<Appointment> appointments = new ArrayList<>();
        do{
            if(bookings.get(startTime) != null) {
                appointments.add(bookings.get(startTime));
            }
            startTime.addAppointmentTimeAllotment();
        } while (!startTime.equals(endTime));
        return appointments;
    }

    public void bookNewAppointment(Time time, Pet pet, String description, int numTimeSlots) throws AppointmentBookedException {
        if(time == null || pet == null || description == null ) {
            throw new NullArgumentException();
        }
        if(numTimeSlots < 1 || numTimeSlots > bookings.size()) {
            throw new InvalidTimeException();
        }
        if(!checkBookingAvailability(time, numTimeSlots)) {
            throw new AppointmentBookedException("Given time frame is unavailable to book appointments");
        } else {
            Appointment newAppointment = new Appointment(this, pet, description, numTimeSlots);
            newAppointment.setEarliestTimeSlot(time);
            while(numTimeSlots != 0) {
                bookings.put(time, newAppointment);
                time.addAppointmentTimeAllotment();
                numTimeSlots--;
            }
        }
    }

    public void bookExistingAppointment(Time time, Appointment appointment) throws AppointmentBookedException {
        if(time == null || appointment == null) {
            throw new NullArgumentException();
        }
        Time newTime = new Time(time.getHour(), time.getMinute());
        if(!checkBookingAvailability(newTime, appointment.getTimeSlots())) {
            throw new AppointmentBookedException("Given time frame is unavailable to book appointments");
        } else {
            appointment.setEarliestTimeSlot(new Time(time.getHour(), time.getMinute()));
            int numTimeSlots = appointment.getTimeSlots();
            while(numTimeSlots != 0) {
                bookings.put(time, appointment);
                time.addAppointmentTimeAllotment();
                numTimeSlots--;
            }
        }
    }

    public void changeAppointment(Time time, Appointment appointment) throws AppointmentBookedException {
        if(time == null || appointment == null) {
            throw new NullArgumentException();
        }
        if(!checkBookingAvailability(time, appointment.getTimeSlots())) {
            throw new AppointmentBookedException("Given time frame is unavailable to book appointments");
        } else {
            cancelAppointment(appointment);
            bookExistingAppointment(time, appointment);
        }
    }

    public void cancelAppointment(Appointment appointment) {
        if(appointment == null) {
            throw new NullArgumentException();
        }
        if(bookings.containsValue(appointment)) {
            Time newTime = appointment.getEarliestTimeSlot();
            int numTimeSlots = appointment.getTimeSlots();
            do {
                bookings.remove(newTime, appointment);
                newTime.addAppointmentTimeAllotment();
                numTimeSlots--;
            } while (numTimeSlots != 0);
            appointment.setSchedule(null);
            appointment.setEarliestTimeSlot(null);
        }
    }

    public boolean confirmAppointment(Time confirmTime, Appointment appointment) {
        if(confirmTime == null || appointment == null) {
            throw new NullArgumentException();
        }
        int numTimeSlots = appointment.getTimeSlots();
        if(!bookings.containsValue(appointment) && !confirmTime.equals(appointment.getEarliestTimeSlot())) {
            return false;
        } else {
            do{
                if(!bookings.get(confirmTime).equals(appointment)) {
                    return false;
                }
                confirmTime.addAppointmentTimeAllotment();
                numTimeSlots--;
            } while(numTimeSlots != 0);
            return true;
        }
    }



    //MODIFIES: this
    //EFFECTS: removes booking times before given hour and minute, set earliestBookableTime
    public void setLaterFirstBookableAppointment(int hour, int minute) throws AppointmentBookedException {
        Time.checkInvalidTimeException(hour, minute);
        if(earliestBookableTime.equals(new Time(hour, minute))) {
            throw new InvalidTimeException("Given time is the same as current earliest bookable time");
        }
        Time refTime = new Time(earliestBookableTime.getHour(), earliestBookableTime.getMinute());
        checkBookedAppointmentsBefore(hour, minute, refTime);
        do {
            bookings.remove(refTime);
            refTime.addAppointmentTimeAllotment();
        } while (refTime.isBefore(new Time(hour, minute)));
        earliestBookableTime.setHour(hour);
        earliestBookableTime.setMinute(minute);
    }

    //MODIFIES: this
    //EFFECTS: removes bookable times after given hour and minute, set latestBookableTime
    public void setEarlierLastBookableAppointment(int hour, int minute) throws InvalidTimeException, AppointmentBookedException {
        Time.checkInvalidTimeException(hour, minute);
        if(latestBookableTime.equals(new Time(hour, minute))) {
            throw new InvalidTimeException("Given time is the same as current latest bookable time");
        }
        Time refTime = new Time(hour,minute);
        refTime.addAppointmentTimeAllotment();
        checkBookedAppointmentsAfter(refTime);
        refTime.setHour(hour);
        refTime.setMinute(minute);
        while (refTime.isBefore( latestBookableTime) || !refTime.equals(latestBookableTime)) {
            refTime.addAppointmentTimeAllotment();
            bookings.remove(refTime);
        }
        latestBookableTime.setHour(hour);
        latestBookableTime.setMinute(minute);
    }


    //MODIFIES: this
    //EFFECTS: adds bookable times from given hour and minute up to 00:00 AM
    public void setEarlierEarliestBookableTime(int hour, int minute) throws InvalidTimeException {
        Time.checkInvalidTimeException(hour, minute);
        if (earliestBookableTime.isMidnight()) {
            throw new InvalidTimeException("Current Earliest Booking Time is 00:00 AM");
        } else if (earliestBookableTime.isBefore(new Time(hour, minute))) {
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
        Time.checkInvalidTimeException(hour, minute);
        if (latestBookableTime.equals(new Time(23,45))) {
            throw new InvalidTimeException("Current Latest Booking Time is currently 11:45 PM");
        } else if (latestBookableTime.isAfter(new Time(hour, minute))) {
            throw new InvalidTimeException("Given time must be before the current earliest bookable time");
        } else if (latestBookableTime.equals(new Time(hour, minute))){
            throw new InvalidTimeException("Given time is the same as Latest Booking Time");
        } else {
            Time newTime = new Time(hour, minute);
            do {
                latestBookableTime.addAppointmentTimeAllotment();
                bookings.put(new Time(latestBookableTime.getHour(), latestBookableTime.getMinute()), null);
            } while (latestBookableTime.isBefore(newTime) || !latestBookableTime.equals(newTime));

        }
    }




//****************************************HELPER FUNCTIONS*************************************************************
    //MODIFIES: none
    //EFFECTS: checks to see if appointments booked before provided hour and minute
    private void checkBookedAppointmentsBefore(int hour, int minute, Time refTime) throws AppointmentBookedException, InvalidTimeException {
        do {
            if(bookings.get(refTime) != null) {
                throw new AppointmentBookedException("Appointment booked at " + refTime);
            }
            refTime.addAppointmentTimeAllotment();
        } while (refTime.isBefore(new Time(hour, minute)));
        refTime.setHour(0);
        refTime.setMinute(0);
    }

    //MODIFIES: none
    //EFFECTS: checks to see if appointments booked after provided hour and minute
    private void checkBookedAppointmentsAfter( Time refTime) throws AppointmentBookedException {
        Time newTime = new Time(refTime.getHour(), refTime.getMinute());
        do {
            if(bookings.get(refTime) != null) {
                throw new AppointmentBookedException("Appointment booked at " + refTime);
            }
            refTime.addAppointmentTimeAllotment();
        } while (refTime.isBefore(latestBookableTime) || !refTime.equals(latestBookableTime));
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
