package uk.ac.ebi.spot.goci.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import uk.ac.ebi.spot.goci.model.EfoTrait;
import uk.ac.ebi.spot.goci.model.projection.EfoTraitProjection;

import java.util.List;

/**
 * Created by emma on 04/12/14.
 *
 * @author emma
 *         <p>
 *         Repository accessing EfoTrait entity object
 */

@RepositoryRestResource
public interface EfoTraitRepository extends JpaRepository<EfoTrait, Long> {
    @RestResource(exported = false)
    List<EfoTrait> findByStudiesIdAndStudiesHousekeepingCatalogPublishDateIsNotNullAndStudiesHousekeepingCatalogUnpublishDateIsNull(
            Long studyId);

    @RestResource(exported = false)
    List<EfoTrait> findByStudiesIdAndStudiesHousekeepingCatalogPublishDateIsNotNullAndStudiesHousekeepingCatalogUnpublishDateIsNull(
            Sort sort,
            Long studyId);

    @RestResource(exported = false)
    Page<EfoTrait> findByStudiesIdAndStudiesHousekeepingCatalogPublishDateIsNotNullAndStudiesHousekeepingCatalogUnpublishDateIsNull(
            Pageable pageable,
            Long studyId);

    @RestResource(exported = false)
    List<EfoTrait> findByAssociationsId(Long associationId);

    @RestResource(exported = false)
    List<EfoTrait> findByAssociationsId(Sort sort, Long associationId);

    @RestResource(exported = false)
    Page<EfoTrait> findByAssociationsId(Pageable pageable, Long associationId);

    @RestResource(exported = false)
    List<EfoTrait> findByUri(String uri);

    @RestResource(exported = false)
    List<EfoTrait> findByUri(Sort sort, String uri);

    @RestResource(path = "findByEfoUri", rel = "findByEfoUri")
    Page<EfoTrait> findByUri(Pageable pageable, String uri);

    @RestResource(path = "findByPubmedId", rel = "findByPubmedId")
    Page<EfoTrait> findByStudiesPublicationIdPubmedId(String pubmedId, Pageable pageable);

    @RestResource(path = "findByEfoTrait", rel = "findByEfoTrait")
    Page<EfoTrait> findByTraitIgnoreCase(String trait, Pageable pageable);

    @RestResource(exported = false)
    @Query("select new EfoTrait(efo.trait, efo.uri, efo.shortForm) from EfoTrait efo where efo" +
            ".shortForm = :trait")
    Page<EfoTrait> findByTraitIgnoreCaseProjection(String trait, Pageable pageable);

    @RestResource(exported = false)
    EfoTrait findByTraitIgnoreCase(String trait);

    @CrossOrigin
    @RestResource
    EfoTrait findByShortForm(String shortForm);

    @RestResource(exported = false)
    EfoTrait findByTrait(String trait);
}

