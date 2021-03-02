package uk.ac.ebi.spot.goci.config;

import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

/**
 * Created by dwelter on 18/09/17.
 */

@Component
public class SpringDataRestCustomisation extends RepositoryRestConfigurerAdapter  {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

        config.withEntityLookup().forRepository(StudyRepository.class, Study::getAccessionId, StudyRepository::findByAccessionId);

        config.withEntityLookup().forRepository(SingleNucleotidePolymorphismRepository.class, SingleNucleotidePolymorphism::getRsId, SingleNucleotidePolymorphismRepository::findByRsId);

        config.withEntityLookup().forRepository(EfoTraitRepository.class, EfoTrait::getShortForm, EfoTraitRepository::findByShortForm);
    }
}
