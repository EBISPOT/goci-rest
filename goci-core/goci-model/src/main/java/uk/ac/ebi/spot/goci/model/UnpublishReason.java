package uk.ac.ebi.spot.goci.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UnpublishReason {

    @Id
    @GeneratedValue
    private Long id;

    private String reason;

    public UnpublishReason() {
    }

    public UnpublishReason(Long id, String unpublishReason) {
        this.id = id;
        this.reason = unpublishReason;
    }

    public Long getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "UnpublishReason{" +
                "id=" + id +
                ", unpublishReason='" + reason + '\'' +
                '}';
    }
}
