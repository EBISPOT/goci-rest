package uk.ac.ebi.spot.goci.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import uk.ac.ebi.spot.goci.model.Location;

/**
 * Created by Laurent on 18/05/15.
 * <p>
 * * @author lgil
 * <p>
 * Repository accessing Location entity object
 */
@RepositoryRestResource//(exported = false)
public interface LocationRepository extends JpaRepository<Location, Long> {

    @RestResource(exported = false)
    Location findByChromosomeNameAndChromosomePositionAndRegionName(String chromosomeName,
                                                                    Integer chromosomePosition,
                                                                    String regionName);
}
