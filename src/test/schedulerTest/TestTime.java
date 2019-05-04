package test.schedulerTest;

import exceptions.InvalidTimeException;
import model.scheduler.Time;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class TestTime {
    Time time;

    @Before
    public void runBefore() {
        time = new Time();
    }

    @Test
    public void testConstructor() {
        testTimeFields(0, 0);
    }

    @Test
    public void testSetHourOne(){
        try {
            time.setHour(1);
        } catch (InvalidTimeException e) {
            fail("Should not have thrown InvalidTimeException");
        }
        testTimeFields(1, 0);
    }

    @Test
    public void testSetHour23(){
        try {
            time.setHour(23);
        } catch (InvalidTimeException e) {
            fail("Should not have thrown InvalidTimeException");
        }
        testTimeFields(23, 0);

    }


    @Test
    public void testSetHour24() {
        try {
            time.setHour(24);
            fail("Should have thrown InvalidTimeException");
        } catch (InvalidTimeException e) {
            //do nothing
        }
    }

    @Test
    public void testSetHourNegative(){
        try {
            time.setHour(-1);
            fail("Should have thrown InvalidTimeException");
        } catch (InvalidTimeException e) {
            //do nothing
        }

    }

    @Test
    public void testSetMinuteNegative(){
        try {
            time.setMinute(-1);
            fail("Should have thrown InvalidTimeException");
        } catch (InvalidTimeException e) {
            //do nothing
        }

    }

    @Test
    public void testSetMinute61(){
        try {
            time.setMinute(61);
            fail("Should have thrown InvalidTimeException");
        } catch (InvalidTimeException e) {
            //do nothing
        }

    }

    @Test
    public void testSetMinute42(){
        try {
            time.setMinute(42);
            fail("Should have thrown InvalidTimeException");
        } catch (InvalidTimeException e) {
            //do nothing
        }

    }

    @Test
    public void testSetMinute45(){
        try {
            time.setMinute(45);
        } catch (InvalidTimeException e) {
            fail("Should have thrown InvalidTimeException");
        }
        testTimeFields(0,45);
    }

    @Test
    public void testAddAppointmentTimeAllotmentFromZeroMinutes(){
        time.addAppointmentTimeAllotment();
        testTimeFields(0,15);
    }

    @Test
    public void testAddAppointmentTimeAllotmentFrom45Minutes(){
        try {
            time.setMinute(45);
        } catch (InvalidTimeException e) {
            fail("Should have thrown InvalidTimeException");
        }
        testTimeFields(0,45);
        time.addAppointmentTimeAllotment();
        testTimeFields(1,0);
    }

    @Test
    public void testAddAppointmentTimeAllotmentFourTimes(){
        time.addAppointmentTimeAllotment();
        testTimeFields(0,15);
        time.addAppointmentTimeAllotment();
        testTimeFields(0,30);
        time.addAppointmentTimeAllotment();
        testTimeFields(0,45);
        time.addAppointmentTimeAllotment();
        testTimeFields(1,0);
    }

    @Test
    public void testAddAppointmentTimeAllotmentLastTimeMidnight(){
        try{
            time.setHour(23);
        } catch (InvalidTimeException e) {
            fail("Should not have thrown InvalidTimeException");
        }
        time.addAppointmentTimeAllotment();
        testTimeFields(23,15);
        time.addAppointmentTimeAllotment();
        testTimeFields(23,30);
        time.addAppointmentTimeAllotment();
        testTimeFields(23,45);
        time.addAppointmentTimeAllotment();
        testTimeFields(0,0);
    }

    @Test
    public void testAddAppointmentTimeAllotmentLastTime2300Hours(){
        try{
            time.setHour(22);
        } catch (InvalidTimeException e) {
            fail("Should not have thrown InvalidTimeException");
        }
        time.addAppointmentTimeAllotment();
        testTimeFields(22,15);
        time.addAppointmentTimeAllotment();
        testTimeFields(22,30);
        time.addAppointmentTimeAllotment();
        testTimeFields(22,45);
        time.addAppointmentTimeAllotment();
        testTimeFields(23,0);
    }

    @Test
    public void testIsBeforeTrue() {
        Time compareTime = new Time(23, 45);
        assertTrue(time.isBefore(compareTime));
    }

    @Test
    public void testIsBeforeFalse() {
        Time compareTime = new Time(14, 45);
        try {
            time.setMinute(45);
            time.setHour(15);
        } catch (InvalidTimeException e) {
            fail("Should not have thrown an InvalidTimeException");
        }
        assertFalse(time.isBefore(compareTime));
    }

    @Test
    public void testIsBeforeHourSameMinuteDifferentTrue(){
        Time compareTime = new Time(0,45);
        assertTrue(time.isBefore(compareTime));
    }

    @Test
    public void testIsAfterTrue() {
        Time compareTime = new Time(14, 30);
        try {
            time.setMinute(45);
            time.setHour(15);
        } catch (InvalidTimeException e) {
            fail("Should not have thrown an InvalidTimeException");
        }
        assertTrue(time.isAfter(compareTime));
    }

    @Test
    public void testIsAfterFalse() {
        Time compareTime = new Time(23, 45);
        assertFalse(time.isAfter(compareTime));
    }

    @Test
    public void testisAfterHourSameMinuteDifferentTrue(){
        Time compareTime = new Time(0, 0);
        try {
            time.setMinute(45);
            time.setHour(0);
        } catch (InvalidTimeException e) {
            fail("Should not have thrown an InvalidTimeException");
        }
        assertTrue(time.isAfter(compareTime));
    }

    @Test
    public void testToString() {
        assertEquals("00:00AM", time.toString());
    }


    public void testTimeFields(int i, int j) {
        assertEquals(i, time.getHour());
        assertEquals(j, time.getMinute());
    }




}
