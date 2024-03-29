package uk.ac.ebi.spot.goci.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Region {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "region")
    @JsonBackReference
    private Collection<Location> locations;

    public Region() {
    }

    public Region(String name) {
        this.name = name;
    }

    public Region(String name,
                  Collection<Location> locations) {
        this.name = name;
        this.locations = locations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Location> getLocations() {
        return locations;
    }

    public void setLocations(Collection<Location> locations) {
        this.locations = locations;
    }

    @Override public String toString() {
        return "Region{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", locations=" + locations +
                '}';
    }
}
