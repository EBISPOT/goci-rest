package uk.ac.ebi.spot.goci.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.ac.ebi.spot.goci.model.Author;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicationDto {

    private Long id;
    private String pubmedId;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="BST")
    private Date publicationDate;

    private String publication;
    private String title;

    @JsonProperty("author")
    private Author firstAuthor;

}
