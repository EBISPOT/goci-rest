package uk.ac.ebi.spot.goci.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class UnpublishedStudy {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    @JsonProperty("study_tag")
    private String studyTag;

    @JsonProperty("study_accession")
    private String accession;

    @JsonProperty("genotyping_technology")
    private String genotypingTechnology;

    @JsonProperty("array_manufacturer")
    private String arrayManufacturer;

    @JsonProperty("array_information")
    private String arrayInformation;

    @JsonProperty("imputation")
    private Boolean imputation;

    @JsonProperty("variant_count")
    private Integer variantCount;

    @JsonProperty("sample_description")
    private String sampleDescription;

    @JsonProperty("statistical_model")
    private String statisticalModel;

    @JsonProperty("study_description")
    private String studyDescription;

    @JsonProperty("trait")
    private String trait;

    @JsonProperty("efo_trait")
    private String efoTrait;

    @JsonProperty("background_trait")
    private String backgroundTrait;

    @JsonProperty("background_efo_trait")
    private String backgroundEfoTrait;

    @JsonProperty("checksum")
    private String checksum;

    @JsonProperty("summary_statistics_file")
    private String summaryStatsFile;

    @JsonProperty("submission_id")
    private String submissionId;

    @JsonProperty("globus_folder")
    private String globusFolder;

    @JsonProperty("summary_statistics_assembly")
    private String sumStatsAssembly;

    @JsonProperty("cohort")
    private String cohort;

    @JsonProperty("cohort_id")
    private String cohortId;
    
    private Date createdDate;

    @Transient
    private String file;

    @ManyToMany
    @JoinTable(name = "unpublished_study_to_work", joinColumns = @JoinColumn(name = "study_id"), inverseJoinColumns =
    @JoinColumn(name = "work_id"))
    @JsonManagedReference
    @JsonProperty("body_of_work")
    private Collection<BodyOfWork> bodiesOfWork;

    @OneToMany(mappedBy = "study", orphanRemoval = true)
    @JsonManagedReference
    private Collection<UnpublishedAncestry> unpublishedAncestries;

    @JsonProperty("agreed_to_cc0")
    private Boolean agreedToCc0;

    public String getFile() {
        return String.format("/%s/", this.getAccession());
    }
}
