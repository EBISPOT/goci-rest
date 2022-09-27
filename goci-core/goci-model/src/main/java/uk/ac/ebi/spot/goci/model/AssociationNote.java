package uk.ac.ebi.spot.goci.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Association")
public class AssociationNote extends Note {
}
