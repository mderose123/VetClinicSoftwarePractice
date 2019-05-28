package model.scheduler;

import exceptions.InvalidTimeException;
import exceptions.NullArgumentException;

import java.util.Date;

public class BoardingSchedule extends Schedule {
    Date endDate;
    Date startDate;

    public BoardingSchedule(Date startDate, Date endDate) {
        super(startDate);
        this.endDate = endDate;
    }

    public void setEndDate(Date endDate) {
        if(endDate == null) {
            throw new NullArgumentException();
        }
        if(endDate.before(this.startDate)) {
            throw new InvalidTimeException();
        }
        this.endDate = endDate;
    }
}
