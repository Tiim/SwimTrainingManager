package ch.scbirs.trainingmanager.utils;

import ch.scbirs.trainingmanager.Main;
import javafx.concurrent.Task;

import java.nio.file.Paths;

/**
 * @author Tim
 * @since 07 - 2014
 */
public class SettingsLoadTask extends Task<Void> {

    @Override
    protected Void call() throws Exception {
        updateMessage(Main.instance.translation.getString("ui.message.splash.settings.Load"));
        Main.instance.settings = new Settings(Paths.get(Main.SETTINGS_FILE));
        return null;
    }
}
