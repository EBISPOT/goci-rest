package uk.ac.ebi.spot.goci.model.projection;

import org.springframework.data.rest.core.config.Projection;
import uk.ac.ebi.spot.goci.model.*;

import java.util.Collection;

/**
 * Created by dwelter on 08/09/17.
 */
@Projection(name = "associationBySnp", types = {Association.class})
public interface AssociationBySNPProjection {
    
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


      Collection<SingleNucleotidePolymorphism> getSnps();

      Collection<Locus> getLoci();

      Collection<EfoTrait> getEfoTraits();
}
