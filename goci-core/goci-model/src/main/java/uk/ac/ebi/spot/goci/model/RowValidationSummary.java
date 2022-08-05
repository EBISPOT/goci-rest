package uk.ac.ebi.spot.goci.model;

import java.util.Collection;

public class RowValidationSummary {

    private AssociationUploadRow row;

    private Collection<ValidationError> errors;

    public AssociationUploadRow getRow() {
        return row;
    }

    public void setRow(AssociationUploadRow row) {
        this.row = row;
    }

    public Collection<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(Collection<ValidationError> errors) {
        this.errors = errors;
    }
}
