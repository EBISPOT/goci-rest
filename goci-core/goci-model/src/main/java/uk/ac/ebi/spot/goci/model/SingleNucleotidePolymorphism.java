package uk.ac.ebi.spot.goci.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Date;

@Entity
public class SingleNucleotidePolymorphism {
    @Id
    @GeneratedValue
    private Long id;

    private String rsId;

    private Long merged;

    private String functionalClass;

    private Date lastUpdateDate;

    @ManyToMany
    @JoinTable(name = "SNP_LOCATION",
               joinColumns = @JoinColumn(name = "SNP_ID"),
               inverseJoinColumns = @JoinColumn(name = "LOCATION_ID"))
    @JsonManagedReference
    private Collection<Location> locations;

    @OneToMany(mappedBy = "snp")
    @JsonManagedReference
    private Collection<GenomicContext> genomicContexts;

    @OneToMany(mappedBy = "snp")
    @JsonBackReference
    @RestResource(exported = false)
    private Collection<RiskAllele> riskAlleles;

    @JsonProperty("mergedInto")
    @ManyToOne
    @JoinTable(name = "SNP_MERGED_SNP",
               joinColumns = @JoinColumn(name = "SNP_ID_MERGED"),
               inverseJoinColumns = @JoinColumn(name = "SNP_ID_CURRENT"))
    private SingleNucleotidePolymorphism currentSnp;

    @ManyToMany
    @JoinTable(name = "ASSOCIATION_SNP_VIEW",
               joinColumns = @JoinColumn(name = "SNP_ID"),
               inverseJoinColumns = @JoinColumn(name = "ASSOCIATION_ID"))
    @JsonBackReference
    private Collection<Association> associations;

    @ManyToMany
    @JoinTable(name = "STUDY_SNP_VIEW",
               joinColumns = @JoinColumn(name = "SNP_ID"),
               inverseJoinColumns = @JoinColumn(name = "STUDY_ID"))
    private Collection<Study> studies;


    // JPA no-args constructor
    public SingleNucleotidePolymorphism() {
    }

    public SingleNucleotidePolymorphism(String rsId) {
        this.rsId = rsId;
    }

    public SingleNucleotidePolymorphism(String rsId,
                                        Long merged,
                                        String functionalClass,
                                        Date lastUpdateDate,
                                        Collection<Location> locations,
                                        Collection<GenomicContext> genomicContexts,
                                        Collection<RiskAllele> riskAlleles,
                                        SingleNucleotidePolymorphism currentSnp,
                                        Collection<Association> associations,
                                        Collection<Study> studies) {
        this.rsId = rsId;
        this.merged = merged;
        this.functionalClass = functionalClass;
        this.lastUpdateDate = lastUpdateDate;
        this.locations = locations;
        this.genomicContexts = genomicContexts;
        this.riskAlleles = riskAlleles;
        this.currentSnp = currentSnp;
        this.associations = associations;
        this.studies = studies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRsId() {
        return rsId;
    }

    public void setRsId(String rsId) {
        this.rsId = rsId;
    }

    public Long getMerged() { return merged; }

    public void setMerged(Long merged) {
        this.merged = merged;
    }

    public SingleNucleotidePolymorphism getCurrentSnp() { return currentSnp; }

    public void setCurrentSnp(SingleNucleotidePolymorphism currentSnp) { this.currentSnp = currentSnp; }

    public String getFunctionalClass() {
        return functionalClass;
    }

    public void setFunctionalClass(String functionalClass) {
        this.functionalClass = functionalClass;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Collection<Location> getLocations() {
        return locations;
    }

    public void setLocations(Collection<Location> locations) {
        this.locations = locations;
    }

    public Collection<GenomicContext> getGenomicContexts() {
        return genomicContexts;
    }

    public void setGenomicContexts(Collection<GenomicContext> genomicContexts) {
        this.genomicContexts = genomicContexts;
    }

    public Collection<RiskAllele> getRiskAlleles() {
        return riskAlleles;
    }

    public void setRiskAlleles(Collection<RiskAllele> riskAlleles) {
        this.riskAlleles = riskAlleles;
    }

    public Collection<Association> getAssociations() {
        return associations;
    }

    public void setAssociations(Collection<Association> associations) {
        this.associations = associations;
    }

    public Collection<Study> getStudies() {
        return studies;
    }

    public void setStudies(Collection<Study> studies) {
        this.studies = studies;
    }
}
