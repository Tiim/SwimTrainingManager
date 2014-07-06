package ch.scbirs.trainingmanager.controllers;

import ch.scbirs.trainingmanager.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.ToolBar;

import java.io.IOException;

public class ControllerStage {

    @FXML
    private Tab tabTraining;

    @FXML
    private Tab tabSeries;

    @FXML
    private ToolBar toolbar;


    @FXML
    public void initialize() throws IOException {
        tabTraining.setContent(Main.instance.paneTraining);
        tabSeries.setContent(Main.instance.paneSeries);
    }
}

