package uk.ac.ebi.spot.goci.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class WeeklyProgressView {

    @Id
    private Long id;

    private Date weekStartDay;

    private Long studyId;

    private String eventType;

    public WeeklyProgressView() {
    }

    public WeeklyProgressView(Long id,
                              Date weekStartDay,
                              Long studyId,
                              String eventType,
                              Integer numberOfStudies) {
        this.id = id;
        this.weekStartDay = weekStartDay;
        this.studyId = studyId;
        this.eventType = eventType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getWeekStartDay() {
        return weekStartDay;
    }

    public void setWeekStartDay(Date weekStartDay) {
        this.weekStartDay = weekStartDay;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getStudyId() {
        return studyId;
    }

    public void setStudyId(Long studyId) {
        this.studyId = studyId;
    }
}
