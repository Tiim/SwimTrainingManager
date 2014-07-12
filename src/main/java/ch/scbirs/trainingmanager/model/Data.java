package ch.scbirs.trainingmanager.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Tim
 * @since 07 - 2014
 */
public class Data {
    private static final Data INSTANCE = new Data();

    public static Data getInstance() {
        return INSTANCE;
    }

    private Data() {
    }

    private final ObservableList<Series> series = FXCollections.observableArrayList();

    public ObservableList<Series> getSeries() {
        return series;
    }
}
