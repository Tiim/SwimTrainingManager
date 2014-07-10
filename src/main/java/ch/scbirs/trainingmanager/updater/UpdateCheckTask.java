package ch.scbirs.trainingmanager.updater;

import javafx.application.Platform;
import javafx.concurrent.Task;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;


/**
 * @author Tim
 * @since 07 - 2014
 */
public class UpdateCheckTask extends Task<Void> {
    @Override
    protected Void call() throws Exception {
        updateMessage("Checking for updates.");
        if (VersionChecker.isNewVersionAvailable()) {
            updateMessage("New version available.");
        } else {
            updateMessage("No new version available");
        }
        return null;
    }
}
