package test.schedulerTest;

import exceptions.AppointmentBookedException;
import exceptions.InvalidTimeException;
import exceptions.NullArgumentException;
import model.client.Owner;
import model.client.OwnerName;
import model.client.Pet;
import model.client.Prefix;
import model.scheduler.Appointment;
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
    Pet pet1;
    Pet pet2;
    Appointment appointment1;
    Appointment appointment2;



    @Before
    public void runBefore() {
        calendar =  Calendar.getInstance();
        date = calendar.getTime();
        schedule = new GroomingSchedule(date);
        owner = new Owner(new OwnerName(Prefix.MR, "dale", "mc", "dale"));
        pet1 = new Pet("Boo", owner);
        pet2 = new Pet("Cheeto", owner);
        appointment1 = new Appointment(schedule, pet1, "appointment 1", 3);
        appointment2 = new Appointment(schedule, pet2, "appointment 2", 4);
    }
//***************************Constructor Tests***********************************
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
//****************************bookExistingAppointment Tests********************************
    @Test
    public void testBookExistingAppointmentNullArgumentExceptionThrown(){
        try{
            schedule.bookExistingAppointment(null, appointment1);
            fail("Should have thrown NullArgumentException");
        } catch (NullArgumentException e) {
            //do nothing
        } catch (AppointmentBookedException e) {
            fail("Should not have thrown AppointmentBookedException");
        }
        try{
            schedule.bookExistingAppointment(new Time(),null);
            fail("Should have thrown NullArgumentException");
        } catch (NullArgumentException e) {
            //do nothing
        } catch (AppointmentBookedException e) {
            fail("Should not have thrown AppointmentBookedException");
        }


    }

    @Test
    public void testBookOneExistingAppointmentValidInputNoExceptionThrown() {
        try{
            schedule.bookExistingAppointment(new Time(), appointment1);
        } catch (AppointmentBookedException e) {
            fail("Should not have thrown AppointmentBookedException");
        }
        assertEquals(appointment1, schedule.getBookings().get(new Time()));
        assertEquals(appointment1, schedule.getBookings().get(new Time(0,15)));
        assertEquals(appointment1, schedule.getBookings().get(new Time(0,30)));
        System.out.println(schedule.getBookings());
    }

    @Test
    public void testBookTwoExistingAppointmentValidInputNoExceptionThrown() {
        try{
            schedule.bookExistingAppointment(new Time(), appointment1);
        } catch (AppointmentBookedException e) {
            fail("Should not have thrown AppointmentBookedException");
        }
        assertEquals(appointment1, schedule.getBookings().get(new Time()));
        assertEquals(appointment1, schedule.getBookings().get(new Time(0,15)));
        assertEquals(appointment1, schedule.getBookings().get(new Time(0,30)));

        try{
            schedule.bookExistingAppointment(new Time(12,0), appointment2);
        } catch (AppointmentBookedException e) {
            fail("Should not have thrown AppointmentBookedException");
        }
        Time newTime = appointment2.getEarliestTimeSlot();
        int numTimeSlots = appointment2.getTimeSlots();
        while(numTimeSlots != 0) {
            assertEquals(appointment2, schedule.getBookings().get(newTime) );
            newTime.addAppointmentTimeAllotment();
            numTimeSlots--;
        }
        System.out.println(schedule.getBookings());
    }


//****************************checkBookableAppointmentsBefore Tests************************
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
//***************************setLaterFirstBookableAppointment Valid Input Tests******************
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
//***************************checkBookingAvailability Tests***************************
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
    public void testCheckBookingAvailabilityStartAndEndTimeInputValid() {
        Time startTime = new Time();
        Time endTime = new Time(23,45);
        assertTrue(schedule.checkBookingAvailability(startTime, endTime));
    }

    @Test
    public void testCheckBookingAvailabilityStartAndEndTimeInputValidAppointmentBooked() {
        try{
            schedule.bookExistingAppointment(new Time(12,15), appointment1 );
        } catch (AppointmentBookedException e) {
            fail("Should not have thrown AppointmentBookedException");
        }
        Time startTime = new Time();
        Time endTime = new Time(23,45);
        assertFalse(schedule.checkBookingAvailability(startTime, endTime));
        System.out.println(schedule.getBookings());


    }

//*********************************getEarliestBookableAppointment Tests**************
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
//*******************************getLatestBookableAppointment Tests****************
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
//*******************************getTotalNumberOfBookableAppointments**************
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
