package uk.ac.ebi.spot.goci.model.projection;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.core.config.Projection;
import uk.ac.ebi.spot.goci.model.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Projection(name = "baseSnp", types = { SingleNucleotidePolymorphism.class })
public interface SnpProjection {
    Long getId();

    String getRsId();

    Long getMerged();

    String getFunctionalClass();

    Date getLastUpdateDate();

}
