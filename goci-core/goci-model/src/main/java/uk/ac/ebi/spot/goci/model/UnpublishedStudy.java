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
    private String accession;
    @JsonProperty("genotyping_technology")
        private String genotypingTechnology;
    @JsonProperty("array_manufacturer")
    private String arrayManufacturer;
//    private String arrayInformation;
//    private Boolean imputation;
//    private Integer variantCount;
//    private String sampleDescription;
    @JsonProperty("statistical_model")
    private String statisticalModel;
//    private String studyDescription;
    private String trait;
    //    private String efoTrait;
    @JsonProperty("background_trait")
    private String backgroundTrait;
    //    private String backgroundEfoTrait;
    private String checksum;
    @Column(name = "summary_stats_file")
    private String summaryStatisticsFile;
    @JsonProperty("submission_id")
    private String submissionId;
    @JsonProperty("globus_folder")
    private String globusFolder;
    //    private String summaryStatisticsAssembly;
//    private String cohort;
//    private String cohortId;
    @JsonProperty("created_date")
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

}
