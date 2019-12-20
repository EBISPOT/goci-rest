package uk.ac.ebi.spot.goci.model.projection;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.rest.core.config.Projection;
import uk.ac.ebi.spot.goci.model.EfoTrait;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Projection(name = "baseEfo", types = { EfoTrait.class })
public interface EfoTraitProjection {
    Long getId();

    @NotBlank
    String getTrait();

    @NotBlank
    String getUri();

    String getShortForm();

}
