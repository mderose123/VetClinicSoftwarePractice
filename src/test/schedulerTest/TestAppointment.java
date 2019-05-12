package test.schedulerTest;

import exceptions.InvalidTimeException;
import exceptions.NullArgumentException;
import model.client.Owner;
import model.client.OwnerName;
import model.client.Pet;
import model.client.Prefix;
import model.scheduler.Appointment;
import model.scheduler.GroomingSchedule;
import model.scheduler.Schedule;
import model.scheduler.ServiceProvided;
import org.junit.Before;
import org.junit.Test;


import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;


import java.util.Calendar;
import java.util.Date;

public class TestAppointment {
    Appointment appointment;
    Schedule schedule;
    Date date;
    Calendar calendar;
    ServiceProvided service;
    Pet pet;
    Owner owner;

    @Before
    void runBefore() {
        calendar = Calendar.getInstance();
        date = calendar.getTime();
        schedule = new GroomingSchedule(date);
        service = new ServiceProvided();
        owner = new Owner(new OwnerName(Prefix.MR, "dale", "mc", "dale"));
        pet = new Pet("buppy", owner);
        try {
            appointment = new Appointment( pet, "appointment details", 1);
        } catch (NullArgumentException e) {
            fail("Should not have thrown NullArgumentException");
        } catch (InvalidTimeException e) {
            fail("Should not have thrown InvalidTimeException");
        }
    }

    @Test
    void testConstructorThrowsNullArgumentException(){
        try {
            appointment = new Appointment(pet, "appointment details", 1);
            fail("Should not have thrown NullArgumentException");
        } catch (NullArgumentException e) {
            //do nothing
        } catch (InvalidTimeException e) {
            fail("Should not have thrown InvalidTimeException");
        }
        try {
            appointment = new Appointment( null,  "appointment details", 1);
            fail("Should not have thrown NullArgumentException");
        } catch (NullArgumentException e) {
            //do nothing
        } catch (InvalidTimeException e) {
            fail("Should not have thrown InvalidTimeException");
        }
        try {
            appointment = new Appointment( pet, "appointment details", 1);
            fail("Should not have thrown NullArgumentException");
        } catch (NullArgumentException e) {
            //do nothing
        } catch (InvalidTimeException e) {
            fail("Should not have thrown InvalidTimeException");
        }
        try {
            appointment = new Appointment(pet,  null, 1);
            fail("Should not have thrown NullArgumentException");
        } catch (NullArgumentException e) {
            //do nothing
        } catch (InvalidTimeException e) {
            fail("Should not have thrown InvalidTimeException");
        }
    }

    @Test
    void testConstructorLessThanOneTimeSlots(){
        try {
            appointment = new Appointment(pet, "appointment details", 0);
            fail("Should have thrown InvalidTimeException");
        } catch (NullArgumentException e) {
            fail("Should not have thrown NullArgumentException");
        } catch (InvalidTimeException e) {
            //do nothing
        }
        try {
            appointment = new Appointment( pet, "appointment details", -10);
            fail("Should have thrown InvalidTimeException");
        } catch (NullArgumentException e) {
            fail("Should not have thrown NullArgumentException");
        } catch (InvalidTimeException e) {
            //do nothing
        }

    }

    @Test
    void testConstructorMoreThanScheduleTimeSlots() {
        try {
            appointment = new Appointment(pet, "appointment details", 97);
            fail("Should have thrown InvalidTimeException");
        } catch (NullArgumentException e) {
            fail("Should not have thrown NullArgumentException");
        } catch (InvalidTimeException e) {
            //do nothing
        }
    }

    @Test
    void testSetDescriptionValidInput() {
        appointment.setAppointmentDescription("f");
        assertEquals("f", appointment.getAppointmentDescription());
    }



}
