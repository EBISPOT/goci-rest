package uk.ac.ebi.spot.goci.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date eventDate;

    private String eventType;

    @ManyToOne
    private SecureUser user;

    private String eventDescription;

    @Transient
    public int weekOfYear() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(this.eventDate);
        cal.add(Calendar.DATE, +1);
        int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);

        return weekOfYear;
    }

    public Event() {
    }

    public Event(Date eventDate, String eventDescription, String eventType, SecureUser user) {
        this.eventDate = eventDate;
        this.eventDescription = eventDescription;
        this.eventType = eventType;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public SecureUser getUser() {
        return user;
    }

    public void setUser(SecureUser user) {
        this.user = user;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}
