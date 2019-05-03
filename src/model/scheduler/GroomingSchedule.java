package model.scheduler;

import exceptions.InvalidTimeException;
import exceptions.NullArgumentException;

import java.util.Date;

public class GroomingSchedule extends Schedule {

    public GroomingSchedule(Date date) {
        super(date);
    }
}
