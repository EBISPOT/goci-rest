package uk.ac.ebi.spot.goci.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.ac.ebi.spot.goci.model.UnpublishedStudy;

public interface UnpublishedStudyRepository extends JpaRepository<UnpublishedStudy, Long> {}
