package ch.scbirs.trainingmanager.controllers;

import ch.scbirs.trainingmanager.Main;
import ch.scbirs.trainingmanager.updater.UpdatePerformer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

public class ControllerStage {

    @FXML
    private Tab tabTraining;

    @FXML
    private Tab tabSeries;

    @FXML
    public void initialize() {
        tabTraining.setContent(Main.instance.paneTraining);
        tabSeries.setContent(Main.instance.paneSeries);
    }

    @FXML
    private void forceUpdate(final ActionEvent actionEvent) {
        //OnRelease: Remove this version
        Main.taskRunner.addTasks(() -> new UpdatePerformer().run());
    }
}

