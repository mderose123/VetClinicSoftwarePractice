package test.schedulerTest;

import exceptions.AppointmentBookedException;
import exceptions.InvalidTimeException;
import exceptions.NullArgumentException;
import model.client.Owner;
import model.client.OwnerName;
import model.client.Pet;
import model.client.Prefix;
import model.scheduler.GroomingSchedule;
import model.scheduler.Schedule;
import model.scheduler.Time;
import org.junit.Before;
import org.junit.Test;


import java.util.Calendar;
import java.util.Date;

import static junit.framework.TestCase.fail;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class TestGroomingSchedule {
    Schedule schedule;
    Date date;
    Calendar calendar;
    Owner owner;
    Pet pet;


    @Before
    public void runBefore() {
        calendar =  Calendar.getInstance();
        date = calendar.getTime();
        schedule = new GroomingSchedule(date);
        owner = new Owner(new OwnerName(Prefix.MR, "dale", "mc", "dale"));
        pet = new Pet("Boo", owner);
    }

    @Test
    public void testConstructor() {
        assertEquals(96, schedule.getBookings().size());
        System.out.println(schedule.getBookings());

    }

    @Test
    public void testConstructorThrowsNullArgumentException() {
        try {
            Schedule schedule1 = new GroomingSchedule(null);
            fail("Should have thrown a NullArgumentException");
        } catch (NullArgumentException e) {
            //do nothing
        }
    }

    @Test
    public void testCbAbInvalidHourInputTooLarge() {
        try {
            schedule.setLaterFirstBookableAppointment(100, 20);
            fail("Should have thrown InvalidTimeException");
        } catch (InvalidTimeException e) {
            //do nothing
        } catch (AppointmentBookedException e ) {
            fail("Should not have thrown AppointmentBookedException");
        }
    }

    @Test
    public void testCbABInvalidHourInputNegative() {
        try {
            schedule.setLaterFirstBookableAppointment(-2, 20);
            fail("Should have thrown InvalidTimeException");
        } catch (InvalidTimeException e) {
            //do nothing
        } catch (AppointmentBookedException e ) {
            fail("Should not have thrown AppointmentBookedException");
        }
    }

    @Test
    public void testCbABInvalidMinuteInputTooLarge() {
        try {
            schedule.setLaterFirstBookableAppointment(20, 200);
            fail("Should have thrown InvalidTimeException");
        } catch (InvalidTimeException e) {
            //do nothing
        } catch (AppointmentBookedException e ) {
            fail("Should not have thrown AppointmentBookedException");
        }
    }


    @Test
    public void testCbABInvalidMinuteInputNegative() {
        try {
            schedule.setLaterFirstBookableAppointment(20, -20);
            fail("Should have thrown InvalidTimeException");
        } catch (InvalidTimeException e) {
            //do nothing
        } catch (AppointmentBookedException e ) {
            fail("Should not have thrown AppointmentBookedException");
        }
    }

    @Test
    public void testSlFbAValidInput() {
        try {
            schedule.setLaterFirstBookableAppointment(3, 0);
        } catch (InvalidTimeException e) {
            fail("Should not have thrown InvalidTimeException");
        } catch (AppointmentBookedException e ) {
            fail("Should not have thrown AppointmentBookedException");
        }
        assertEquals(84, schedule.getBookings().size());
        System.out.println(schedule.getBookings());
    }

    @Test
    public void testCheckBookingAvailabilityNullTime(){
        try{
            schedule.checkBookingAvailability(null, 1);
            fail("Should have thrown NullArgumentException");
        } catch(NullArgumentException e) {
            //do nothing
        }
    }

    @Test
    public void testCheckBookingAvailabilityIncorrectTimeSlotInput(){
        try{
            Time newTime = new Time();
            schedule.checkBookingAvailability(newTime, 0);
            fail("Should have thrown InvalidTimeException");
        } catch(InvalidTimeException e) {
            //do nothing
        }
        try{
            Time newTime = new Time();
            schedule.checkBookingAvailability(newTime, 97);
            fail("Should have thrown InvalidTimeException");
        } catch(InvalidTimeException e) {
            //do nothing
        }
    }

    @Test
    public void testCheckBookingAvailabilityValidOneTimeSlotInputReturnTrue() {
            Time newTime = new Time();
            assertTrue(schedule.checkBookingAvailability(newTime, 1));
    }

    @Test
    public void testCheckBookingAvailabilityValidMultiTimeSlotInputReturnTrue() {
        Time newTime = new Time();
        assertTrue(schedule.checkBookingAvailability(newTime, 50));
    }

    @Test
    public void testGetEarliestBookableTime(){
        assertEquals(new Time(0,0), schedule.getEarliestBookableTime());
        try {
            schedule.setLaterFirstBookableAppointment(3, 0);
        } catch (AppointmentBookedException e) {
            fail("Should not have thrown AppointmentBookedException");
        }
        assertEquals(new Time(3,0), schedule.getEarliestBookableTime());
    }

    @Test
    public void testGetLatestBookableTime(){
        assertEquals(new Time(23,45), schedule.getLatestBookableTime());
        try {
            schedule.setEarlierLastBookableAppointment(3,0);
        } catch (AppointmentBookedException e) {
            fail("Should not have thrown AppointmentBookedException");
        }
        assertEquals(new Time(3,0), schedule.getLatestBookableTime());
    }

    @Test
    public void testTotalNumberOfBookableAppointments() {
        assertEquals(96, schedule.getTotalBookableAppointments());
        try {
            schedule.setEarlierLastBookableAppointment(3,0);
        } catch (AppointmentBookedException e) {
            fail("Should not have thrown AppointmentBookedException");
        }
        assertEquals(new Time(3,0), schedule.getLatestBookableTime());
        assertEquals(13, schedule.getTotalBookableAppointments());

    }











}
