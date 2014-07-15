package ch.scbirs.trainingmanager;

import ch.scbirs.trainingmanager.updater.UpdateCheckTask;
import ch.scbirs.trainingmanager.updater.UpdatePerformer;
import ch.scbirs.trainingmanager.updater.VersionChecker;
import ch.scbirs.trainingmanager.utils.Lang;
import ch.scbirs.trainingmanager.utils.TaskRunner;
import ch.scbirs.trainingmanager.utils.lang.LoadTranslationTask;
import ch.scbirs.trainingmanager.utils.lang.Translation;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import javax.swing.*;
import java.io.IOException;

public class Main extends Application {

    public static final TaskRunner taskRunner = new TaskRunner();
    private static final int SPLASH_WIDTH = 620;
    private static final int SPLASH_HEIGHT = 350;
    public static Main instance;

    public Translation translation;

    public Parent paneTraining;
    public Parent paneSeries;
    public Parent root;

    private Parent splash;

    public static void main(final String[] args) {
        try {
            Class.forName("org.controlsfx.dialog.Dialogs");
        } catch (final ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Application not correctly installed, updater will correct that.",
                    "Invalid Install", JOptionPane.ERROR_MESSAGE);
            new UpdatePerformer().run();
        }
        launch(args);
    }

    @Override
    public void init() throws Exception {
        instance = this;

        /// Initialize splash screen
        final ImageView splashImage = new ImageView(new Image(Main.class.getResourceAsStream("splash-pool-text.png")));
        final Label label = new Label();
        final Label versionLabel = new Label("Version: " + VersionChecker.getCurrentVersion());
        final StackPane stackPane = new StackPane(splashImage, versionLabel);
        label.textProperty().bind(taskRunner.messageProperty());
        versionLabel.setFont(Font.font("Arial Black", FontWeight.EXTRA_BOLD, 20));
        versionLabel.setTextFill(Color.ORANGE);
        splash = new VBox(stackPane, label);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final Stage splashStage = new Stage(StageStyle.UNDECORATED);
        showSplash(splashStage, primaryStage);

        final FadeTransition trans = new FadeTransition(Duration.seconds(1.5), splash);
        trans.setFromValue(1);
        trans.setToValue(0.5);
        trans.setOnFinished((t) -> {
            splashStage.close();
            primaryStage.show();
            primaryStage.toFront();
        });


        final Runnable loadingFinished = () -> Platform.runLater(() -> {
            trans.play();
            primaryStage.setTitle(Lang.WINDOW_TITLE + " - " + VersionChecker.getCurrentVersion());
            if (VersionChecker.isNewVersionAvailable()) {
                versionCheck();
            }
            try {
                paneTraining = FXMLLoader.load(getClass().getResource("paneTraining.fxml"), translation);
                paneSeries = FXMLLoader.load(getClass().getResource("paneSeries.fxml"), translation);
                root = FXMLLoader.load(getClass().getResource("stage.fxml"), translation);
            } catch (IOException e) {
                e.printStackTrace();
            }
            showMainStage(primaryStage);
        });


        taskRunner.addTasks(
                new LoadTranslationTask(this),
                new UpdateCheckTask(),
                loadingFinished
        );

        taskRunner.start();
    }

    private void versionCheck() {
        final Action action = Dialogs.create()
                .owner(root)
                .title("Version")
                .masthead("A new version is available.")
                .message("Do you want the program to update?\n" +
                                "If so, it has to restart itself.\n" +
                                "This Version: " + VersionChecker.getCurrentVersion() +
                                " New Version: " + VersionChecker.getRemoteVersion()
                ).showConfirm();
        if (action == Dialog.Actions.YES) {
            taskRunner.addTasks(() -> new UpdatePerformer().run());
        }
    }

    @Override
    public void stop() throws Exception {

    }

    private void showSplash(final Stage stage, final Stage owner) {
        final Scene splashScene = new Scene(splash);
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        stage.setScene(splashScene);
        stage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        stage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        stage.show();
    }

    private void showMainStage(final Stage stage) {
        stage.setTitle(Lang.WINDOW_TITLE);
        stage.setScene(new Scene(root));
        stage.setMinHeight(600);
        stage.setMinWidth(800);
    }
}
