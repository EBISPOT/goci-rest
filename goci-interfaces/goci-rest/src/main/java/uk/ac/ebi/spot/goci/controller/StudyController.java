package uk.ac.ebi.spot.goci.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.ebi.spot.goci.model.MappingMetadata;
import uk.ac.ebi.spot.goci.model.UnpublishedStudy;
import uk.ac.ebi.spot.goci.repository.UnpublishedStudyRepository;

import java.util.Collection;
import java.util.List;

@RestController
public class StudyController {
    @Autowired
    private UnpublishedStudyRepository studyRepository;

    @Autowired
    private ResourceAssembler studyAssembler;

    @Autowired
    private RepositoryRestMvcConfiguration configuration;

    @CrossOrigin
    @RequestMapping(value = "/api/studies/unpublished", method = RequestMethod.GET, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUnpublishedStudies(final PersistentEntityResourceAssembler entityAssembler) {
         Collection<UnpublishedStudy> studies = studyRepository.findAll();
         studies.forEach(unpublishedStudy -> {
             unpublishedStudy.setFile(unpublishedStudy.getSubmissionId() +
                             "/" + unpublishedStudy.getAccession() + "_" + unpublishedStudy.getSummaryStatisticsFile());
         });
        return ResponseEntity.ok(studies);

    }
}
