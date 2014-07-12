package ch.scbirs.trainingmanager.controllers;

import ch.scbirs.trainingmanager.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.ToolBar;

public class ControllerStage {

    @FXML
    private Tab tabTraining;

    @FXML
    private Tab tabSeries;

    @FXML
    private ToolBar toolbar;


    @FXML
    public void initialize() {
        tabTraining.setContent(Main.instance.paneTraining);
        tabSeries.setContent(Main.instance.paneSeries);
    }
}

