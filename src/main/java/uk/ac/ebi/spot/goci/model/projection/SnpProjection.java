package uk.ac.ebi.spot.goci.model.projection;

import org.springframework.data.rest.core.config.Projection;
import uk.ac.ebi.spot.goci.model.SingleNucleotidePolymorphism;


import java.util.Date;

@Projection(name = "baseSnp", types = { SingleNucleotidePolymorphism.class })
public interface SnpProjection {
    Long getId();

    String getRsId();

    Long getMerged();

    String getFunctionalClass();

    Date getLastUpdateDate();

}
