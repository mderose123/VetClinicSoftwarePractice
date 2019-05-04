package test.schedulerTest;

import exceptions.AppointmentBookedException;
import exceptions.InvalidTimeException;
import exceptions.NullArgumentException;
import model.scheduler.GroomingSchedule;
import model.scheduler.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


public class TestGroomingSchedule {
    Schedule schedule;
    Date date;
    Calendar calendar;

    @BeforeEach
    void runBefore() {
        calendar =  Calendar.getInstance();
        date = calendar.getTime();
        schedule = new GroomingSchedule(date);
    }

    @Test
    void testConstructor() {
        assertEquals(96, schedule.getBookings().size());
        System.out.println(schedule.getBookings());

    }

    @Test
    void testConstructorThrowsNullArgumentException() {
        try {
            Schedule schedule1 = new GroomingSchedule(null);
            fail("Should have thrown a NullArgumentException");
        } catch (NullArgumentException e) {
            //do nothing
        }
    }

    @Test
    void testCiTeInvalidHourInputTooLarge() {
        try {
            schedule.setLaterFirstBookableAppointment(100, 20);
            fail("Should have thrown InvalidTimeException");
        } catch (InvalidTimeException e) {
            //do nothing
        } catch (AppointmentBookedException e ) {
            fail("Should not have thrown AppointmentBookedException");
        }
    }
//*************************Testing checkInvalidTimeException method****************
    @Test
    void testCiTeInvalidHourInputNegative() {
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
    void testCiTeInvalidMinuteInputTooLarge() {
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
    void testCiTeInvalidMinuteInputNegative() {
        try {
            schedule.setLaterFirstBookableAppointment(20, -20);
            fail("Should have thrown InvalidTimeException");
        } catch (InvalidTimeException e) {
            //do nothing
        } catch (AppointmentBookedException e ) {
            fail("Should not have thrown AppointmentBookedException");
        }
    }
//**************************Testing setLaterFirstBookableAppointment Method**************************
    @Test
    void testSlFbAValidInput() {
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
    void testSlFbASameTimeAsEarliestBookingTime() {
        try {
            schedule.setLaterFirstBookableAppointment(0, 0);
            fail("Should not have thrown InvalidTimeException");
        } catch (InvalidTimeException e) {
            //do nothing
        } catch (AppointmentBookedException e) {
            fail("Should not have thrown AppointmentBookedException");
        }
        assertEquals(96, schedule.getBookings().size());

    }


//*************************Testing setEarlierLatestBookableAppointment Method*********************

    @Test
    void testSeLbAValidInput(){
        try {
            schedule.setEarlierLastBookableAppointment(23, 0);
        } catch (InvalidTimeException e) {
            fail("Should not have thrown InvalidTimeException");
        } catch (AppointmentBookedException e ) {
            fail("Should not have thrown AppointmentBookedException");
        }
        assertEquals(93, schedule.getBookings().size());
        System.out.println(schedule.getBookings());
    }

    @Test
    void testSeLbASameTimeAsLatestBookingTime() {
        try {
            schedule.setEarlierLastBookableAppointment(23, 45);
            fail("Should have thrown InvalidTimeException");
        } catch (InvalidTimeException e) {
            //do nothing
        } catch (AppointmentBookedException e) {
            fail("Should not have thrown AppointmentBookedException");
        }
        assertEquals(96, schedule.getBookings().size());
    }
//*****************test setEarlierEarliestBookingTime***********
    @Test
    void testSeEbAAtMidnight() {
        try {
            schedule.setEarlierEarliestBookableTime(0,0);
            fail("Should have thrown InvalidTimeException");
        } catch (InvalidTimeException e) {
            //do nothing
        }
        assertEquals(96, schedule.getBookings().size());
    }

    @Test
    void testSeEbAAtAfterEarliestBookableAppointment() {

        try {
            schedule.setLaterFirstBookableAppointment(0,45);
            schedule.setEarlierEarliestBookableTime(1,45);
            fail("Should have thrown InvalidTimeException");
        } catch (InvalidTimeException e) {
            //do nothing
        } catch (AppointmentBookedException e) {
            fail("Should not have thrown AppointmentBookedException");
        }
        assertEquals(93, schedule.getBookings().size());
    }

    @Test
    void testSeEbAAtAtSameTimeAsEarliestBookableAppointment() {

        try {
            schedule.setLaterFirstBookableAppointment(0,45);
            schedule.setEarlierEarliestBookableTime(0,45);
            fail("Should have thrown InvalidTimeException");
        } catch (InvalidTimeException e) {
            //do nothing
        } catch (AppointmentBookedException e) {
            fail("Should not have thrown AppointmentBookedException");
        }
        assertEquals(93, schedule.getBookings().size());
    }

    @Test
    void testSeEbAAtValidInput() {
        try {
            schedule.setLaterFirstBookableAppointment(0,45);
            schedule.setEarlierEarliestBookableTime(0,00);
        } catch (InvalidTimeException e) {
            fail("Should have thrown InvalidTimeException");
        } catch (AppointmentBookedException e) {
            fail("Should not have thrown AppointmentBookedException");
        }
        assertEquals(96, schedule.getBookings().size());
    }
//*****************test setLaterLatestBookableAppointment***********
    @Test
    void testLlBaAt1145() {
        try {
            schedule.setLaterLatestBookableTime(23,45);
            fail("Should have thrown InvalidTimeException");
        } catch (InvalidTimeException e) {
            //do nothing
        }
        assertEquals(96, schedule.getBookings().size());
    }

    @Test
    void testLlBaAtEarlierTimeThanLatestBookableTime() {
        try {
            schedule.setEarlierLastBookableAppointment(23,0);
            schedule.setLaterLatestBookableTime(22,45);
            fail("Should have thrown InvalidTimeException");
        } catch (InvalidTimeException e) {
            //do nothing
        } catch (AppointmentBookedException e) {
            fail("Should not have thrown AppointmentBookedException");
        }
        assertEquals(93, schedule.getBookings().size());
    }

    @Test
    void testLlBaAtSameTimeAsCurrentLatestBookableTime() {
        try {
            schedule.setEarlierLastBookableAppointment(23,0);
            schedule.setLaterLatestBookableTime(23,0);
            fail("Should have thrown InvalidTimeException");
        } catch (InvalidTimeException e) {
            //do nothing
        } catch (AppointmentBookedException e) {
            fail("Should not have thrown AppointmentBookedException");
        }
        assertEquals(93, schedule.getBookings().size());
    }

    @Test
    void testLlBaValidInput() {
        try {
            schedule.setEarlierLastBookableAppointment(23,0);
            schedule.setLaterLatestBookableTime(23,45);
        } catch (InvalidTimeException e) {
            fail("Should have thrown InvalidTimeException");
        } catch (AppointmentBookedException e) {
            fail("Should not have thrown AppointmentBookedException");
        }
        assertEquals(96, schedule.getBookings().size());
    }




}



