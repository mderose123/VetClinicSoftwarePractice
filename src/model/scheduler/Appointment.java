package model.scheduler;

import exceptions.EmptyStringException;
import exceptions.InvalidTimeException;
import exceptions.NullArgumentException;
import model.client.Owner;
import model.client.Pet;

public class Appointment {
    private Schedule schedule;
    private Pet pet;
    private ServiceProvided service;
    private String appointmentDescription;
    private int timeSlots;
    private Time earliestTimeSlot;


    public Appointment( Pet pet, String description, int timeSlots ) {
        testInputExceptions(pet, description, timeSlots);
        this.pet = pet;
        this.appointmentDescription = description;
        this.timeSlots = timeSlots;
    }



    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public ServiceProvided getService() {
        return service;
    }

    public void setService(ServiceProvided service) {
        this.service = service;
    }

    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    public int getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(int timeSlots) {
        this.timeSlots = timeSlots;
    }

    public Time getEarliestTimeSlot() {
        return earliestTimeSlot;
    }

    public void setEarliestTimeSlot(Time time) {
        this.earliestTimeSlot = time;
    }

    private void testInputExceptions(Pet p, String des, int tS) {
        if(p == null) {
            throw new NullArgumentException("Pet cannot be null");
        } else if(tS<=0 || tS >schedule.getBookings().size()) {
            throw new InvalidTimeException("Time slots has to be greater than zero and less than or equal to number of available booking times");
        } else if(des == null) {
            throw new NullArgumentException("Description cannot be null");
        }
    }


}
