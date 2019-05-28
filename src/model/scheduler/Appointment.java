package model.scheduler;

import exceptions.InvalidTimeException;
import exceptions.NullArgumentException;
import model.client.Pet;

public class Appointment {
    private Schedule schedule;
    private Pet pet;
    private ServiceProvided service;
    private String appointmentDescription;
    private int timeSlots;
    private Time earliestTimeSlot;

    public Appointment(Schedule schedule, String description, int timeSlots) {
        testInputExceptions(schedule, description, timeSlots);
        this.appointmentDescription = description;
        this.timeSlots = timeSlots;
    }


    public Appointment(Schedule schedule, Pet pet, String description, int timeSlots ) {
        testInputExceptions(schedule, pet, description, timeSlots);
        this.pet = pet;
        this.appointmentDescription = description;
        this.timeSlots = timeSlots;
    }

    public Appointment(Schedule schedule, Pet pet, String description, ServiceProvided service, int timeSlots ) {
        testInputExceptions(schedule, pet, description, service, timeSlots);
        this.pet = pet;
        this.appointmentDescription = description;
        this.timeSlots = timeSlots;
        this.service = service;
    }



    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule ) {
        //stub
        if(schedule == null) {
            throw new NullArgumentException("Schedule cannot be null");
        }
        this.schedule = schedule;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        //stub
        if(pet == null) {
            throw new NullArgumentException("Pet cannot be null");
        }
        this.pet = pet;
    }

    public ServiceProvided getService() {
        return service;
    }

    public void setService(ServiceProvided service) {
        if(service == null) {
            throw new NullArgumentException("Service cannot be null");
        }
        this.service = service;
    }

    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        if(appointmentDescription == null) {
            throw new NullArgumentException("Description cannot be null");
        }
        this.appointmentDescription = appointmentDescription;
    }

    public int getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(int timeSlots) {
       //stub
        this.timeSlots = timeSlots;
    }

    public Time getEarliestTimeSlot() {
        return earliestTimeSlot;
    }

    public void setEarliestTimeSlot(Time time) {
        //stub
        if(time == null) {
            throw new NullArgumentException("Time cannot be null");
        }
        this.earliestTimeSlot = time;
    }

    private void testInputExceptions(Schedule schedule, String des, int tS) {
        if(schedule == null) {
            throw new NullArgumentException("Schedule cannot be null");
        } else if(tS<=0 || tS >schedule.getBookings().size()) {
            throw new InvalidTimeException("Time slots has to be greater than zero and less than or equal to number of available booking times");
        } else if(des == null) {
            throw new NullArgumentException("Description cannot be null");
        }
    }

    private void testInputExceptions(Schedule schedule, Pet p, String des, int tS) {
        if(schedule == null) {
            throw new NullArgumentException("Schedule cannot be null");
        } else if(p == null) {
            throw new NullArgumentException("Pet cannot be null");
        } else if(tS<=0 || tS >schedule.getBookings().size()) {
            throw new InvalidTimeException("Time slots has to be greater than zero and less than or equal to number of available booking times");
        } else if(des == null) {
            throw new NullArgumentException("Description cannot be null");
        }
    }

    private void testInputExceptions(Schedule schedule, Pet p, String des, ServiceProvided service, int tS) {
        if(schedule == null) {
            throw new NullArgumentException("Schedule cannot be null");
        }else if(p == null || service == null) {
            throw new NullArgumentException("Pet or Service cannot be null");
        } else if(tS<=0 || tS >schedule.getBookings().size()) {
            throw new InvalidTimeException("Time slots has to be greater than zero and less than or equal to number of available booking times");
        } else if(des == null) {
            throw new NullArgumentException("Description cannot be null");
        }
    }


}
