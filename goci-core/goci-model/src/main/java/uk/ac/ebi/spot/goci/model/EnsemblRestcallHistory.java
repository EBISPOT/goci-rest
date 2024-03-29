package uk.ac.ebi.spot.goci.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

@Entity
public class EnsemblRestcallHistory {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String requestType;

    @NotBlank
    private String ensemblParam;

    @NotBlank
    private String ensemblUrl;

    @Lob
    private String ensemblResponse;

    private String ensemblError;

    private String ensemblVersion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LAST_UPDATE_TIME")
    private Date lastUpdateTime;

    private String ensemblSwapRelease;

    @PrePersist
    protected void onCreate() {
        lastUpdateTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdateTime = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestType() { return requestType; }

    public void setRequestType(String requestType) { this.requestType = requestType; }

    public String getEnsemblParam() { return ensemblParam; }

    public void setEnsemblParam(String ensemblParam) { this.ensemblParam = ensemblParam; }

    public String getEnsemblUrl() { return ensemblUrl; }

    public void setEnsemblUrl(String ensemblUrl) { this.ensemblUrl = ensemblUrl; }

    @Lob
    public String getEnsemblResponse() { return ensemblResponse; }

    public void setEnsemblResponse(String ensemblResponse) { this.ensemblResponse = ensemblResponse; }

    public String getEnsemblError() { return ensemblError; }

    public void setEnsemblError(String ensemblError) { this.ensemblError = ensemblError; }

    public String getEnsemblVersion() { return ensemblVersion; }

    public void setEnsemblVersion(String ensemblVersion) { this.ensemblVersion = ensemblVersion; }

    public Date getLastUpdateTime() { return lastUpdateTime; }

    public void setLastUpdateTime(Date lastUpdateTime) { this.lastUpdateTime = lastUpdateTime; }

    public String getEnsemblSwapRelease() { return ensemblSwapRelease; }
}
