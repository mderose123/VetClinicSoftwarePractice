package test.schedulerTest;

import exceptions.AppointmentBookedException;
import exceptions.InvalidTimeException;
import exceptions.NullArgumentException;
import model.scheduler.GroomingSchedule;
import model.scheduler.Schedule;
import org.junit.Before;
import org.junit.Test;


import java.util.Calendar;
import java.util.Date;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;


public class TestGroomingSchedule {
    Schedule schedule;
    Date date;
    Calendar calendar;

    @Before
    public void runBefore() {
        calendar =  Calendar.getInstance();
        date = calendar.getTime();
        schedule = new GroomingSchedule(date);
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



}
