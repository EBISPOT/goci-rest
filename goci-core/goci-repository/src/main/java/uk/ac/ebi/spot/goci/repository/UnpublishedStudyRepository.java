package uk.ac.ebi.spot.goci.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import uk.ac.ebi.spot.goci.model.UnpublishedStudy;

@RepositoryRestResource(path = "unpublished-studies")
public interface UnpublishedStudyRepository extends JpaRepository<UnpublishedStudy, Long> {

    @RestResource(path = "filter", rel = "filter")
    @Query("SELECT u FROM UnpublishedStudy u LEFT JOIN u.bodiesOfWork b " +
            "WHERE (:firstAuthor IS NULL OR LOWER(b.firstAuthor) LIKE LOWER(CONCAT('%', :firstAuthor, '%'))) " +
            "AND (:accession IS NULL OR LOWER(u.accession) LIKE LOWER(CONCAT('%', :accession, '%'))) " +
            "AND (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
            "AND (:trait IS NULL OR LOWER(u.trait) LIKE LOWER(CONCAT('%', :trait, '%'))) "
    )
    Page<UnpublishedStudy> findByCriteria(
            @Param("firstAuthor") String firstAuthor,
            @Param("accession") String accession,
            @Param("title") String title,
            @Param("trait") String trait,
            Pageable pageable);
}
