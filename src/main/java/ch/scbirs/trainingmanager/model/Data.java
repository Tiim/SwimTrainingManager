package ch.scbirs.trainingmanager.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Tim
 * @since 07 - 2014
 */
public class Data {
    private static final Data INSTANCE = new Data();
    private final ObservableList<Series> series = FXCollections.observableArrayList();

    private Data() {
    }

    public static Data getInstance() {
        return INSTANCE;
    }

    public ObservableList<Series> getSeries() {
        return series;
    }
}
