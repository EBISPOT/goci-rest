package uk.ac.ebi.spot.goci.model;

import javax.persistence.Entity;

@Entity
public class YearlyTotalsSummaryView extends TotalsSummaryView {

    public YearlyTotalsSummaryView() {
    }

    public YearlyTotalsSummaryView(Integer year, String curator, Integer curatorTotal, String curationStatus) {
        super(year, curator, curatorTotal, curationStatus);
    }
}
