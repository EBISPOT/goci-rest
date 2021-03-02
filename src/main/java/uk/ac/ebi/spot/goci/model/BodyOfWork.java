package uk.ac.ebi.spot.goci.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BodyOfWork{
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    @Column(name = "pub_id")
    @JsonProperty("publication_id")
    private String publicationId;
    @JsonProperty("pubmed_id")
    private String pubMedId;
    private String journal;
    private String title;
    @JsonProperty("first_author")
    private String firstAuthor;
//    private DepositionAuthor correspondingAuthor;
    @Column(name = "pub_date")
    @JsonProperty("publication_date")
    private Date publicationDate;
//    private String status;
    private String doi;

    @JsonBackReference
    @ManyToMany
    @JoinTable(name = "unpublished_study_to_work", joinColumns = @JoinColumn(name = "work_id"), inverseJoinColumns =
    @JoinColumn(name = "study_id"))
    private Collection<UnpublishedStudy> studies;
}
