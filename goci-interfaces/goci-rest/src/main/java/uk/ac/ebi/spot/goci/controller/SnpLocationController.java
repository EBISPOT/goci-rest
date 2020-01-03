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
import uk.ac.ebi.spot.goci.model.SingleNucleotidePolymorphism;
import uk.ac.ebi.spot.goci.repository.SingleNucleotidePolymorphismRepository;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * Created by dwelter on 19/09/17.
 */

@RestController
public class SnpLocationController {


    @Autowired
    private PagedResourcesAssembler snpAssembler;

    @Autowired
    private SingleNucleotidePolymorphismRepository singleNucleotidePolymorphismRepository;

    @Autowired
    private RepositoryRestMvcConfiguration configuration;

    @CrossOrigin
    @RequestMapping(value = "/api/snpLocation/{range}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> search(@PathVariable String range,
                                    @RequestParam(value="snpOnly", required = false) boolean snpOnly,
                                    @PageableDefault(size = 20, page = 0) Pageable pageable,
                                    final PersistentEntityResourceAssembler entityAssembler)
            throws UnsupportedEncodingException {

        String chrom = range.split(":")[0];
        String locs = range.split(":")[1];

        long start = Long.parseLong(locs.split("-")[0]);
        long end = Long.parseLong(locs.split("-")[1]);

        Page<SingleNucleotidePolymorphism> snps = null;
        if (snpOnly) {
            snps = singleNucleotidePolymorphismRepository
                    .findIdsByLocationsChromosomeNameAndLocationsChromosomePositionBetween(chrom, start, end, pageable);

        } else {
            snps = singleNucleotidePolymorphismRepository
                    .findByLocationsChromosomeNameAndLocationsChromosomePositionBetween(chrom, start, end, pageable);
        }

        Resources<Resource<SingleNucleotidePolymorphism>> resource = snpAssembler.toResource(snps, entityAssembler);

        for(Resource<SingleNucleotidePolymorphism> snpres : resource.getContent())  {
            LinkBuilder link = configuration.entityLinks().linkForSingleResource(SingleNucleotidePolymorphism.class,
                    URLEncoder.encode(snpres.getContent().getRsId(), "UTF-8"));
            snpres.add(link.slash("/associations?projection=associationBySnp").withRel("associationsBySnpSummary"));
        }


        return new ResponseEntity(resource, HttpStatus.OK);

    }
}
