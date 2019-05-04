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


    public Appointment(Schedule schedule, Pet pet, ServiceProvided service, String description, int timeSlots )  throws  InvalidTimeException{
        testInputExceptions(schedule, pet, description, service, timeSlots);
        this.schedule = schedule;
        this.pet = pet;
        this.service = service;
        this.appointmentDescription = description;
        this.timeSlots = timeSlots;
        earliestTimeSlot = new Time();
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

    private void testInputExceptions(Schedule sc, Pet p, String des, ServiceProvided se, int tS) throws InvalidTimeException {
        if(sc == null) {
            throw new NullArgumentException("Schedule cannot be null");
        } else if(p == null) {
            throw new NullArgumentException("Pet cannot be null");
        } else if(se == null) {
            throw new NullArgumentException("Service cannot be null");
        } else if(tS<=0 || tS >sc.getBookings().size()) {
            throw new InvalidTimeException("Time slots has to be greater than zero and less than or equal to number of available booking times");
        } else if(des == null) {
            throw new NullArgumentException("Description cannot be null");
        }
    }


}
