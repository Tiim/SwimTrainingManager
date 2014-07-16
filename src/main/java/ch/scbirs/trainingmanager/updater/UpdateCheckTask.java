package ch.scbirs.trainingmanager.updater;

import ch.scbirs.trainingmanager.Main;
import javafx.concurrent.Task;


/**
 * @author Tim
 * @since 07 - 2014
 */
public class UpdateCheckTask extends Task<Void> {
    @Override
    protected Void call() throws Exception {
        updateMessage(Main.instance.translation.getString("ui.message.splash.version.Checking"));
        if (VersionChecker.isNewVersionAvailable()) {
            updateMessage(Main.instance.translation.getString("ui.message.splash.version.NewVersion"));
        } else {
            updateMessage(Main.instance.translation.getString("ui.message.splash.version.NoNewVersion"));
        }
        return null;
    }
}
