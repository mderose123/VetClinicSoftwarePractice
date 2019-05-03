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
    void testCbAbInvalidHourInputTooLarge() {
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
    void testCbABInvalidHourInputNegative() {
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
    void testCbABInvalidMinuteInputTooLarge() {
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
    void testCbABInvalidMinuteInputNegative() {
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



}
