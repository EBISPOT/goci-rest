package uk.ac.ebi.spot.goci.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import uk.ac.ebi.spot.goci.model.UnpublishedStudy;

@RepositoryRestResource(path = "unpublished-studies")
public interface UnpublishedStudyRepository extends JpaRepository<UnpublishedStudy, Long> {

    @RestResource(path = "query", rel = "accession")
    UnpublishedStudy findByAccession(String accession);
}
