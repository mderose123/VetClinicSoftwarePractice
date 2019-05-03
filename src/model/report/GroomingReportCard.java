package model.report;

import exceptions.NullArgumentException;
import model.scheduler.ServiceProvided;
import model.client.Pet;

import java.util.Calendar;
import java.util.Date;

public class GroomingReportCard extends ReportCard {
    private Date dateGroomed;
    private Calendar referenceCalendar;
    private Pet pet;
    private ServiceProvided service;

    public GroomingReportCard(Pet pet) throws NullArgumentException {
        super(pet);
        referenceCalendar = Calendar.getInstance();
        dateGroomed = referenceCalendar.getTime();
        service = new ServiceProvided();
    }

    public GroomingReportCard(Pet pet, Date dateGroomed) throws NullArgumentException {
        super(pet);
        if(dateGroomed == null) {
            throw new NullArgumentException("Date must not be null");
        }
        referenceCalendar = Calendar.getInstance();
        this.dateGroomed = dateGroomed;
        service = new ServiceProvided();
    }

    public GroomingReportCard(Pet pet, Date dateGroomed, ServiceProvided service) throws NullArgumentException {
        super(pet);
        if(dateGroomed == null || service == null) {
            throw new NullArgumentException("Date must not be null");
        }
        referenceCalendar = Calendar.getInstance();
        this.dateGroomed = dateGroomed;
        this.service = service;
    }

    public Date getDateGroomed() {
        return dateGroomed;
    }

    public void setDateGroomed(Date dateGroomed) throws NullArgumentException {
       if(dateGroomed == null) {
           throw new NullArgumentException("Date must not be null");
       }
        this.dateGroomed = dateGroomed;
    }

    public ServiceProvided getService() {
        return service;
    }

    public void setService(ServiceProvided service) throws NullArgumentException {
        if(service == null) {
            throw new NullArgumentException("Service must not be null");
        }
        this.service = service;
    }
}
