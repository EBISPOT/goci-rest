package uk.ac.ebi.spot.goci.model.projection;

import javax.validation.constraints.NotBlank;
import org.springframework.data.rest.core.config.Projection;
import uk.ac.ebi.spot.goci.model.EfoTrait;

@Projection(name = "baseEfo", types = { EfoTrait.class })
public interface EfoTraitProjection {
    Long getId();

    @NotBlank
    String getTrait();

    @NotBlank
    String getUri();

    String getShortForm();

}
