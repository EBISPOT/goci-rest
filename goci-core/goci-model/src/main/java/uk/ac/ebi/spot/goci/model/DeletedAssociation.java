package uk.ac.ebi.spot.goci.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class DeletedAssociation {
    @Id
    private Long id;

    private Long studyId;

    @OneToMany
    @JoinTable(name = "DELETED_ASSOCIATION_EVENT",
               joinColumns = @JoinColumn(name = "DELETED_ASSOCIATION_ID"),
               inverseJoinColumns = @JoinColumn(name = "EVENT_ID"))
    private Collection<Event> events = new ArrayList<>();

    public DeletedAssociation() {
    }

    public DeletedAssociation(Long id, Long studyId, Collection<Event> events) {
        this.id = id;
        this.studyId = studyId;
        this.events = events;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudyId() {
        return studyId;
    }

    public void setStudyId(Long studyId) {
        this.studyId = studyId;
    }

    public Collection<Event> getEvents() {
        return events;
    }

    public void setEvents(Collection<Event> events) {
        this.events = events;
    }
}
