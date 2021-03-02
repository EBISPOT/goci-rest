package uk.ac.ebi.spot.goci.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Laurent on 15/05/15.
 */
@Entity
public class Location {

    @Id
    @GeneratedValue
    private Long id;

    private String chromosomeName;

    private Long chromosomePosition;

    @ManyToOne
    @JsonManagedReference
    private Region region;

    @ManyToMany(mappedBy = "locations")
    @JsonBackReference
    private Collection<SingleNucleotidePolymorphism> snps = new ArrayList<>();

    // JPA no-args constructor
    public Location() {}

    public Location(String chromosomeName,
                    Long chromosomePosition,
                    Region region) {
        this.chromosomeName = chromosomeName;
        this.chromosomePosition = chromosomePosition;
        this.region = region;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChromosomeName() {
        return chromosomeName;
    }

    public void setChromosomeName(String chromosomeName) {
        this.chromosomeName = chromosomeName;
    }

    public Long getChromosomePosition() {
        return chromosomePosition;
    }

    public void setChromosomePosition(Long chromosomePosition) {
        this.chromosomePosition = chromosomePosition;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }


    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", chromosomeName='" + chromosomeName + '\'' +
                ", chromosomePosition='" + chromosomePosition + '\'' +
                '}';
    }

    public Collection<SingleNucleotidePolymorphism> getSnps() {
        return snps;
    }

    public void setSnps(Collection<SingleNucleotidePolymorphism> snps) {
        this.snps = snps;
    }
}
