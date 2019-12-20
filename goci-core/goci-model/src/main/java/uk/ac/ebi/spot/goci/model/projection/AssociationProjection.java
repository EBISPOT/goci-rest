package uk.ac.ebi.spot.goci.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Where;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.format.annotation.DateTimeFormat;
import uk.ac.ebi.spot.goci.model.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Projection(name = "baseAssociation", types = { Association.class })
public interface AssociationProjection {
    Long getId();

    String getRiskFrequency();

    String getPvalueDescription();

    Integer getPvalueMantissa();

    Integer getPvalueExponent();

    Boolean getMultiSnpHaplotype();

    Boolean getSnpInteraction();

    @JsonIgnore
    Boolean getSnpApproved();

    String getSnpType();

    Float getStandardError();

    String getRange();

    String getDescription();

    // OR specific values
    Float getOrPerCopyNum();

    @JsonIgnore
    Float getOrPerCopyRecip();

    @JsonIgnore
    String orPerCopyRecipRange();

    // Beta specific values
    Float betaNum();

    String betaUnit();

    String betaDirection();

    @OneToOne
    Study study();

    Date getLastMappingDate();

    @JsonIgnore
    String getLastMappingPerformedBy();

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date getLastUpdateDate();

    @JsonIgnore
    Collection<Note> getNotes();

}
