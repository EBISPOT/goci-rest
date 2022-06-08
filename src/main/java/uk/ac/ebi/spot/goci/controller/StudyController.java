package uk.ac.ebi.spot.goci.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.ebi.spot.goci.model.UnpublishedStudy;
import uk.ac.ebi.spot.goci.repository.UnpublishedStudyRepository;

import java.util.List;

@RestController
public class StudyController {

    @Autowired
    private UnpublishedStudyRepository studyRepository;

    @CrossOrigin
    @GetMapping(value = "/api/studies/unpublished", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUnpublishedStudies() {
         List<UnpublishedStudy> studies = studyRepository.findAll();
         studies.forEach(unpublishedStudy -> unpublishedStudy.setFile("/" + unpublishedStudy.getAccession() + "/"));
        return ResponseEntity.ok(studies);
    }
}
