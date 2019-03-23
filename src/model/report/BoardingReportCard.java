package model.report;

import exceptions.InvalidTimeException;
import exceptions.NullArgumentException;
import model.client.Pet;

import java.util.Calendar;
import java.util.Date;

public class BoardingReportCard extends ReportCard{
    private Date dateDroppedOff;
    private Date datePickedUp;
    private Calendar referenceCalendar;

    public BoardingReportCard(Pet pet) throws NullArgumentException {
        super(pet);
        referenceCalendar = Calendar.getInstance();
        dateDroppedOff = referenceCalendar.getTime();
        datePickedUp = referenceCalendar.getTime();
    }

    public BoardingReportCard(Pet pet, Date datePickedUp) throws NullArgumentException {
        super(pet);
        if(datePickedUp == null) {
            throw new NullArgumentException("Date must not be null");
        }
        referenceCalendar = Calendar.getInstance();
        this.dateDroppedOff = referenceCalendar.getTime();
        this.datePickedUp = datePickedUp;
    }

    public BoardingReportCard(Pet pet, Date dateDroppedOff, Date datePickedUp) throws NullArgumentException {
        super(pet);
        if(dateDroppedOff == null || datePickedUp == null) {
            throw new NullArgumentException("Date must not be null");
        }
        referenceCalendar = Calendar.getInstance();
        this.dateDroppedOff = dateDroppedOff;
        this.datePickedUp = datePickedUp;
    }

    public void setDateDroppedOff(Date date) throws NullArgumentException {
        if(date == null) {
            throw new NullArgumentException("Date must not be null");
        }
        this.dateDroppedOff = date;
    }

    public void setDatePickedUp(Date date) throws NullArgumentException {
        if(date == null) {
            throw new NullArgumentException("Date must not be null");
        }
        this.datePickedUp = date;
    }

    public void setDropOffTime(int hr, int min) throws InvalidTimeException {
        if(hr < 0 || hr > 23) {
            throw new InvalidTimeException("Hour must be between 0 and 23");
        } else if (min < 0 || min >60 ) {
            throw new InvalidTimeException("Minute must be between 0 and 59");
        } else {
            referenceCalendar.setTime(dateDroppedOff);
            referenceCalendar.set(Calendar.HOUR_OF_DAY, hr);
            referenceCalendar.set(Calendar.MINUTE, min);
            dateDroppedOff = referenceCalendar.getTime();
        }
    }

    public void setPickUpTime(int hr, int min) throws InvalidTimeException {
        if(hr < 0 || hr > 23) {
            throw new InvalidTimeException("Hour must be between 0 and 23");
        } else if (min < 0 || min >60 ) {
            throw new InvalidTimeException("Minute must be between 0 and 59");
        } else {
            referenceCalendar.setTime(datePickedUp);
            referenceCalendar.set(Calendar.HOUR_OF_DAY, hr);
            referenceCalendar.set(Calendar.MINUTE, min);
            datePickedUp = referenceCalendar.getTime();
        }
    }




}
