package uk.ac.ebi.spot.goci.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
public class EnsemblGene {

    @Id
    @GeneratedValue
    private Long id;

    private String ensemblGeneId;

    @ManyToOne
    @JoinTable(name = "GENE_ENSEMBL_GENE",
               joinColumns = @JoinColumn(name = "ENSEMBL_GENE_ID"),
               inverseJoinColumns = @JoinColumn(name = "GENE_ID"))
    @JsonBackReference
    private Gene gene;

    public EnsemblGene() {
    }

    public EnsemblGene(String ensemblGeneId, Gene gene) {
        this.ensemblGeneId = ensemblGeneId;
        this.gene = gene;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnsemblGeneId() {
        return ensemblGeneId;
    }

    public void setEnsemblGeneId(String ensemblGeneId) {
        this.ensemblGeneId = ensemblGeneId;
    }

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }
}
