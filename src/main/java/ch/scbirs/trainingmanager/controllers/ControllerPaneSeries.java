package ch.scbirs.trainingmanager.controllers;

import ch.scbirs.trainingmanager.model.Data;
import ch.scbirs.trainingmanager.model.Series;
import ch.scbirs.trainingmanager.uicontrols.Spinner;
import javafx.beans.property.ReadOnlyObjectProperty;
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

    ///// Normal Class Variables
    private final ObservableList<SeriesPart> seriesParts = FXCollections.observableArrayList();
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
    private TextField txtMaterials;
    @FXML
    private TextField txtNotes;

    @FXML
    @SuppressWarnings("unchecked")
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

        listSeriesParts.getSelectionModel().selectedItemProperty().addListener((event) ->
                onSelectSeriesPart(((ReadOnlyObjectProperty<SeriesPart>) event).get()));
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
        final SeriesPart p = listSeriesParts.getSelectionModel().getSelectedItem();
        if (p == null) {
            return;
        }
        final int index = listSeriesParts.getSelectionModel().getSelectedIndex();
        if (index <= 0) {
            return;
        }
        final SeriesPart pUp = listSeriesParts.getItems().get(index - 1);
        listSeriesParts.getItems().set(index - 1, p);
        listSeriesParts.getItems().set(index, pUp);
        listSeriesParts.getSelectionModel().select(index - 1);
    }

    @FXML
    private void onMoveDownButton(final ActionEvent event) {
        final SeriesPart p = listSeriesParts.getSelectionModel().getSelectedItem();
        if (p == null) {
            return;
        }
        final int index = listSeriesParts.getSelectionModel().getSelectedIndex();
        if (index >= listSeriesParts.getItems().size() - 1) {
            return;
        }
        final SeriesPart pUp = listSeriesParts.getItems().get(index + 1);
        listSeriesParts.getItems().set(index + 1, p);
        listSeriesParts.getItems().set(index, pUp);
        listSeriesParts.getSelectionModel().select(index + 1);
    }

    @FXML
    private void onAddPartButton(final ActionEvent event) {
        final SeriesPart p = new SeriesPart();
        p.setNr((int) txtNr.getValue());
        p.setDistance((int) txtDistance.getValue());
        p.setMaterials(txtMaterials.getText());
        p.setNotes(txtNotes.getText());
        p.setRepetitions((int) txtRepetitions.getValue());
        p.setStroke(txtStroke.getText());
        p.setTotalDistance((int) txtTotalDistance.getValue());
        seriesParts.add(p);
        listSeriesParts.getSelectionModel().select(seriesParts.indexOf(p));
    }

    @FXML
    private void onRemovePartButton(final ActionEvent event) {

    }

    private void onSelectSeriesPart(final SeriesPart newP) {
        if (newP == null) {
            return; //unselected
        }

        txtNr.setValue(newP.getNr());
        txtDistance.setValue(newP.getDistance());
        txtMaterials.setText(newP.getMaterials());
        txtNotes.setText(newP.getNotes());
        txtRepetitions.setValue(newP.getRepetitions());
        txtStroke.setText(newP.getStroke());
        txtTotalDistance.setValue(newP.getTotalDistance());
    }

    @FXML
    private void onSetButton(final ActionEvent actionEvent) {
        final SeriesPart selected = listSeriesParts.getSelectionModel().getSelectedItem();
        final int index = listSeriesParts.getSelectionModel().getSelectedIndex();
        if (selected == null) {
            onAddPartButton(actionEvent);
        } else {
            selected.setNr((int) txtNr.getValue());
            selected.setDistance((int) txtDistance.getValue());
            selected.setMaterials(txtMaterials.getText());
            selected.setNotes(txtNotes.getText());
            selected.setRepetitions((int) txtRepetitions.getValue());
            selected.setStroke(txtStroke.getText());
            selected.setTotalDistance((int) txtTotalDistance.getValue());
            seriesParts.set(index, selected);
            listSeriesParts.getSelectionModel().select(index);
        }
    }
}
