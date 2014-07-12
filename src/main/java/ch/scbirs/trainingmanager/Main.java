package ch.scbirs.trainingmanager;

import ch.scbirs.trainingmanager.updater.UpdateCheckTask;
import ch.scbirs.trainingmanager.updater.UpdatePerformer;
import ch.scbirs.trainingmanager.updater.VersionChecker;
import ch.scbirs.trainingmanager.utils.Lang;
import ch.scbirs.trainingmanager.utils.TaskRunner;
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
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

public class Main extends Application {

    private static final int SPLASH_WIDTH = 620;
    private static final int SPLASH_HEIGHT = 350;

    public static Main instance;

    public TaskRunner taskRunner = new TaskRunner();

    public Parent paneTraining;
    public Parent paneSeries;
    public Parent root;

    private Parent splash;

    @Override
    public void init() throws Exception {
        instance = this;

        /// Initialize splash screen
        final ImageView splashImage = new ImageView(new Image(Main.class.getResourceAsStream("splash-pool-text.png")));
        final Label label = new Label();
        label.textProperty().bind(taskRunner.messageProperty());
        splash = new VBox(splashImage, label);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final Stage splashStage = new Stage(StageStyle.UNDECORATED);
        showSplash(splashStage, primaryStage);

        paneTraining = FXMLLoader.load(getClass().getResource("paneTraining.fxml"));
        paneSeries = FXMLLoader.load(getClass().getResource("paneSeries.fxml"));
        root = FXMLLoader.load(getClass().getResource("stage.fxml"));

        showMainStage(primaryStage);

        final FadeTransition trans = new FadeTransition(Duration.seconds(3), splash);
        trans.setFromValue(1);
        trans.setToValue(0);
        trans.setOnFinished((t) -> splashStage.close());

        taskRunner.addTasks(new UpdateCheckTask(),
                () -> Platform.runLater(() -> {
                    trans.play();
                    primaryStage.show();
                    splashStage.toFront();
                    if (VersionChecker.isNewVersionAvailable()) {
                        versionCheck();
                    }
                    primaryStage.setTitle(Lang.WINDOW_TITLE + " - " + VersionChecker.getCurrentVersion());
                }));

        taskRunner.start();
    }

    private void versionCheck() {
        try {
            final Action action = Dialogs.create()
                    .owner(root)
                    .title("Version")
                    .masthead("A new version is available.")
                    .message("Do you want the program to update?\n" +
                            "If so, it has to restart itself.")
                    .showConfirm();
            if (action == Dialog.Actions.YES) {
                taskRunner.addTasks(() -> new UpdatePerformer().run());
            }
        } catch (NoClassDefFoundError e) {
            System.out.println(e);
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

    public static void main(final String[] args) {
        launch(args);
    }
}
