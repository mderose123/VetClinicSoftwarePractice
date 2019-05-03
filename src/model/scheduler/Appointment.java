package model.scheduler;

import model.client.Pet;

public class Appointment {
    private Schedule schedule;
    private Pet pet;
    private ServiceProvided service;
    private String appointmentDescription;
    private int timeSlots;

    public Appointment(Schedule schedule, Pet pet, ServiceProvided service, String description, int timeSlots ) {
        this.schedule = schedule;
        this.pet = pet;
        this.service = service;
        this.appointmentDescription = description;
        this.timeSlots = timeSlots;

    }





}
