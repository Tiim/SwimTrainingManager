package ch.scbirs.trainingmanager.controllers;

import ch.scbirs.trainingmanager.model.Data;
import ch.scbirs.trainingmanager.model.Series;
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
    private TextField txtNr;
    @FXML
    private TextField txtSeriesName;
    @FXML
    private TextField txtTotalDistance;
    @FXML
    private TextField txtRepetitions;
    @FXML
    private TextField txtDistance;
    @FXML
    private TextField txtStroke;
    @FXML
    private TextField txtMaterial;
    @FXML
    private TextField txtNotes;


    ///// Normal Class Variables
    private ObservableList<SeriesPart> seriesParts = FXCollections.observableArrayList();


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

    private void onSelectSeries(Series newValue) {

    }

    @FXML
    private void onAddSeriesButton(ActionEvent event) {

    }

    @FXML
    private void onRemoveSeriesButton(ActionEvent event) {

    }

    @FXML
    private void onMoveUpButton(ActionEvent event) {

    }

    @FXML
    private void onMoveDownButton(ActionEvent event) {

    }

    @FXML
    private void onAddPartButton(ActionEvent event) {
        //TODO: Error checking (parsable ints etc)
        SeriesPart p = new SeriesPart();
        p.setNr(Integer.parseInt(txtNr.getText()));
        p.setDistance(Integer.parseInt(txtDistance.getText()));
        p.setMaterials(txtMaterial.getText());
        p.setNotes(txtNotes.getText());
        p.setRepetitions(Integer.parseInt(txtRepetitions.getText()));
        p.setStroke(txtStroke.getText());
        p.setTotalDistance(Integer.parseInt(txtTotalDistance.getText()));
        seriesParts.addAll(p);
    }

    @FXML
    private void onRemovePartButton(ActionEvent event) {

    }
}
