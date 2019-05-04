package model.scheduler;

import exceptions.InvalidTimeException;

import java.util.Objects;

public class Time {
    private int hour;
    private int minute;

    public Time() {
      hour = 0;
      minute = 0;
    }

    public Time(int hour, int minute) {
        checkInvalidTimeException(hour, minute);
        this.hour = hour;
        this.minute = minute;
    }

    public void setHour(int hour) throws InvalidTimeException {
        if(hour <0 || hour >23) {
            throw new InvalidTimeException("Hour must be between 0 and 23");
        }
        this.hour = hour;
    }

    public void setMinute(int minute ) throws InvalidTimeException {
        if(minute < 0 || minute > 59 || minute % 15 != 0) {
            throw new InvalidTimeException("Minute must be between 0 and 59 and a multiple of 15");
        }
        this.minute = minute;
    }

    public int getHour(){
        return hour;
    }

    public int getMinute(){
        return minute;
    }

    public boolean isBefore(Time time){
        if(this.getHour() < time.getHour()) {
            return true;
        } else {
            return (this.getHour() == time.getHour() && this.getMinute() < time.getMinute());
        }
    }

    public boolean isAfter(Time time){
        if (this.getHour() > time.getHour() ) {
            return true;
        } else {
            return this.getHour() == time.getHour() && this.getMinute() > time.getMinute();
        }
    }

    public boolean isMidnight(){
        return this.getHour() == 0 && this.getMinute() == 0;
    }


    public void addAppointmentTimeAllotment() {
        if(minute >= 0 && minute <= 30) {
            minute += Schedule.APPOINTMENT_TIME_ALLOTMENT;
        } else if (hour <= 22) {
            hour++;
            minute = 0;
        } else {
            hour = 0;
            minute = 0;
        }
    }

    public String toString() {
        String s = "";
        int i = this.getHour() - 12;
        String ap ="";
        if(i >= 1 && i< 10) {
            s = "0" + i + ":";
            ap = "PM";
        } else if (i>=10 && i<=12) {
            s = i + ":";
            ap = "PM";
        } else if (i == 0)  {
            s = this.getHour() + ":";
            ap = "PM";
        } else {
            if(this.getHour() < 10) {
                s = "0" + this.getHour()+":";
                ap = "AM";
            } else {
                s = this.getHour() + ":";
                ap = "AM";
            }
        }
        if(this.getMinute()<10) {
            s += "0"  + this.getMinute() + ap;
        } else {
            s += this.getMinute() + ap;
        }
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return hour == time.hour &&
                minute == time.minute;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour, minute);
    }

    public static void checkInvalidTimeException(int hour, int minute) {
        if (hour < 0 || hour > 23) {
            throw new InvalidTimeException("Hour must be between 0 and 23");
        }
        if ((minute < 0 || minute > 45)) {
            throw new InvalidTimeException("Minute must be between 0 and 45 and a multiple of 15");
        }
        if( minute % Schedule.APPOINTMENT_TIME_ALLOTMENT != 0) {
            throw new InvalidTimeException("Minute must be between 0 and 45 and a multiple of 15");
        }
    }




}
