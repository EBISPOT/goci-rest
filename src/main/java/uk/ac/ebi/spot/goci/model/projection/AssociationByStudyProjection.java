package uk.ac.ebi.spot.goci.model.projection;

import org.springframework.data.rest.core.config.Projection;
import uk.ac.ebi.spot.goci.model.Association;
import uk.ac.ebi.spot.goci.model.EfoTrait;
import uk.ac.ebi.spot.goci.model.Locus;
import uk.ac.ebi.spot.goci.model.Study;

import java.util.Collection;


/**
 * Created by dwelter on 12/09/17.
 */

@Projection(name = "associationByStudy", types = {Association.class})
public interface AssociationByStudyProjection {

    String getRiskFrequency();
    String getPvalueDescription();
    Integer getPvalueMantissa();
    Integer getPvalueExponent();
    double getPvalue();
    Boolean getMultiSnpHaplotype();
    Boolean getSnpInteraction();
    String getSnpType();
    Float getStandardError();
    String getRange();

    String getDescription();
    Float getOrPerCopyNum();
    Float getBetaNum();
    String getBetaUnit();
    String getBetaDirection();

    Collection<Locus> getLoci();

    Collection<EfoTrait> getEfoTraits();

    Study getStudy();
}


