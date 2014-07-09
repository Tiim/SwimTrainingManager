package ch.scbirs.trainingmanager;

import ch.scbirs.trainingmanager.updater.VersionChecker;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Main instance;

    public Parent paneTraining;
    public Parent paneSeries;


    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;

        if (VersionChecker.isNewVersionAvailable()) {
            System.out.println("New version available!");
            System.out.println(VersionChecker.getRemoteVersion());
        }

        paneTraining = FXMLLoader.load(getClass().getResource("paneTraining.fxml"));
        paneSeries = FXMLLoader.load(getClass().getResource("paneSeries.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("stage.fxml"));


        primaryStage.setTitle("Trainings Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
