package uk.ac.ebi.spot.goci.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.ebi.spot.goci.model.EfoTrait;
import uk.ac.ebi.spot.goci.model.SingleNucleotidePolymorphism;
import uk.ac.ebi.spot.goci.model.projection.EfoTraitProjection;
import uk.ac.ebi.spot.goci.repository.EfoTraitRepository;

//@RestController
public class EfoTraitController {

    @Autowired
    private PagedResourcesAssembler assembler;

    @Autowired
    private EfoTraitRepository efoTraitRepository;

    @Autowired
    private RepositoryRestMvcConfiguration configuration;

    @RequestMapping(value = "/api/efoTraits/{trait}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, "application/vnd.gwas.api.v1+json"})
    public ResponseEntity<?> search(@PathVariable String trait,
                                    @PageableDefault(size = 20, page = 0) Pageable pageable,
                                    final PersistentEntityResourceAssembler entityAssembler) {
        Page<EfoTrait> traits = efoTraitRepository.findByTraitIgnoreCase(trait, pageable);
        Resources<Resource<EfoTrait>> resource = assembler.toResource(traits, entityAssembler);

        for(Resource<EfoTrait> traitResource : resource.getContent())  {
            LinkBuilder
                    link = configuration.entityLinks().linkForSingleResource(SingleNucleotidePolymorphism.class,
                    traitResource.getContent().getShortForm());
            traitResource.add(link.slash("?projection=baseEfo").withSelfRel());
        }

        return new ResponseEntity(resource, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/efoTraits/{trait}",
            method = RequestMethod.GET,
            produces = {"application/vnd.gwas.api.v2+json"})
    public ResponseEntity<?> searchVersionTwo(@PathVariable String trait,
                                    @PageableDefault(size = 20, page = 0) Pageable pageable,
                                    final PersistentEntityResourceAssembler entityAssembler) {
        Page<EfoTrait> traits = efoTraitRepository.findByTraitIgnoreCaseProjection(trait, pageable);
        Resources<Resource<EfoTrait>> resource = assembler.toResource(traits, entityAssembler);

        for(Resource<EfoTrait> traitResource : resource.getContent())  {
            LinkBuilder
                    link = configuration.entityLinks().linkForSingleResource(EfoTrait.class,
                    traitResource.getContent().getShortForm());
            traitResource.add(link.slash("?projection=baseEfo").withSelfRel());
        }

        return new ResponseEntity(resource, HttpStatus.OK);
    }
}
