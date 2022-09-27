package uk.ac.ebi.spot.goci.model;

import java.util.List;
import java.util.Map;

public interface CatalogDataMapper {

    List<CatalogHeaderBinding> getRequiredDatabaseFields();

    CatalogHeaderBinding getOutputField();

    /**
     * Takes a map of column headings to data items, where the keys are the database header bindings and the values are
     * all fields that were flagged as required in the {@link uk.ac.ebi.spot.goci.model.CatalogHeaderBinding}.  Produces
     * a single value, that should be inserted into the mapped spreadsheet of results in the column dictated by the
     * catalog header binding "output field"
     *
     * @param databaseValues the data acquired from the database
     * @return a single string for the produced output (may have merged several fields)
     */
    String produceOutput(Map<CatalogHeaderBinding, String> databaseValues);
}
