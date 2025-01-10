package uk.ac.ebi.spot.goci.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import uk.ac.ebi.spot.goci.model.UnpublishedStudy;

@RepositoryRestResource(path = "unpublished-studies")
public interface UnpublishedStudyRepository extends JpaRepository<UnpublishedStudy, Long> {

    @RestResource(path = "query", rel = "accession")
    UnpublishedStudy findByAccession(String accession);

    @Query("Select study.accession as studyId, study.cohort as cohort " +

            "from UnpublishedStudy study"

                //            " WHERE (pub.pubmedId = :pubmedId OR :pubmedId = '*') " +
                //            " AND ((lower(firstAuthor.fullnameStandard) = lower(:author)) OR :author = '*') " +
                //            " AND (curator.id = :curator OR :curator = 0) " +
                //            " AND (efoTraits.id = :efoTraitId OR :efoTraitId = 0) " +
                //            " AND (diseaseTrait.id = :diseaseTraitId OR :diseaseTraitId = 0) " +
                //            " AND (status.id LIKE :status OR :status = 0) " +
                //            " AND (study.accessionId = :accessionId OR :accessionId = '*') " +
                //            " AND (study.id = :studyId OR :studyId = 0) " +
                //
                //            " AND (study.gxe = :gxe OR :gxe IS NULL) " +
                //            " AND (study.gxg = :gxg OR :gxg IS NULL) " +
                //            " AND (study.cnv = :cnv OR :cnv IS NULL) " +
                //            " AND (gtech.genotypingTechnology = :genotypeTech OR :genotypeTech = '*') " +
                //
                //            " AND ((lower(notes.textNote) LIKE lower(CONCAT('%',:notesQuery,'%'))) OR :notesQuery = '*') "
    )
    Page<UnpublishedStudy> findByMultipleFilters(Pageable pageable);
}
