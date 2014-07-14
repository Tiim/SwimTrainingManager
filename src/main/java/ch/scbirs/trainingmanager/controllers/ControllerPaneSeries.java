package ch.scbirs.trainingmanager.controllers;

import ch.scbirs.trainingmanager.model.Data;
import ch.scbirs.trainingmanager.model.Series;
import ch.scbirs.trainingmanager.uicontrols.Spinner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import static ch.scbirs.trainingmanager.model.Series.SeriesPart;

/**
 * @author Tim
 * @since 06 - 2014
 */
@SuppressWarnings("unused")
public class ControllerPaneSeries {

    ///// Table View Columns
    @FXML
    private TableColumn<SeriesPart, Integer> listClmNr;
    @FXML
    private TableColumn<SeriesPart, Integer> listClmTotalDistance;
    @FXML
    private TableColumn<SeriesPart, Integer> listClmRepetitions;
    @FXML
    private TableColumn<SeriesPart, Integer> listClmDistance;
    @FXML
    private TableColumn<SeriesPart, String> listClmStroke;
    @FXML
    private TableColumn<SeriesPart, String> listClmMaterials;
    @FXML
    private TableColumn<SeriesPart, String> listClmNotes;

    ///// Other Controls
    @FXML
    private TableView<SeriesPart> listSeriesParts;
    @FXML
    private ListView<Series> listSeries;
    @FXML
    private Spinner txtNr;
    @FXML
    private TextField txtSeriesName;
    @FXML
    private Spinner txtTotalDistance;
    @FXML
    private Spinner txtRepetitions;
    @FXML
    private Spinner txtDistance;
    @FXML
    private TextField txtStroke;
    @FXML
    private TextField txtMaterial;
    @FXML
    private TextField txtNotes;


    ///// Normal Class Variables
    private final ObservableList<SeriesPart> seriesParts = FXCollections.observableArrayList();


    @FXML
    private void initialize() {
        listClmNr.setCellValueFactory(new PropertyValueFactory<>("nr"));
        listClmTotalDistance.setCellValueFactory(new PropertyValueFactory<>("totalDistance"));
        listClmRepetitions.setCellValueFactory(new PropertyValueFactory<>("repetitions"));
        listClmDistance.setCellValueFactory(new PropertyValueFactory<>("distance"));
        listClmStroke.setCellValueFactory(new PropertyValueFactory<>("stroke"));
        listClmMaterials.setCellValueFactory(new PropertyValueFactory<>("materials"));
        listClmNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));

        listSeries.setItems(Data.getInstance().getSeries());
        listSeriesParts.setItems(seriesParts);
        listSeries.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                onSelectSeries(newValue));
    }

    private void onSelectSeries(final Series newValue) {

    }

    @FXML
    private void onAddSeriesButton(final ActionEvent event) {

    }

    @FXML
    private void onRemoveSeriesButton(final ActionEvent event) {

    }

    @FXML
    private void onMoveUpButton(final ActionEvent event) {

    }

    @FXML
    private void onMoveDownButton(final ActionEvent event) {

    }

    @FXML
    private void onAddPartButton(final ActionEvent event) {
        //TODO: Error checking (parsable ints etc)
        final SeriesPart p = new SeriesPart();
        p.setNr((int) txtNr.getValue());
        p.setDistance((int) txtDistance.getValue());
        p.setMaterials(txtMaterial.getText());
        p.setNotes(txtNotes.getText());
        p.setRepetitions((int) txtRepetitions.getValue());
        p.setStroke(txtStroke.getText());
        p.setTotalDistance((int) txtTotalDistance.getValue());
        seriesParts.addAll(p);
    }

    @FXML
    private void onRemovePartButton(final ActionEvent event) {

    }
}
