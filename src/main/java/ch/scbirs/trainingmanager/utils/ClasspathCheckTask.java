package ch.scbirs.trainingmanager.utils;

import ch.scbirs.trainingmanager.Main;
import ch.scbirs.trainingmanager.updater.UpdatePerformer;
import javafx.concurrent.Task;

import javax.swing.*;

/**
 * @author Tim
 * @since 07 - 2014
 */
public class ClasspathCheckTask extends Task<Void> {

    @Override
    protected Void call() throws Exception {
        try {
            updateMessage(Main.instance.translation.getString("ui.message.splash.classpath.Validate"));
            Class.forName("org.controlsfx.dialog.Dialogs");
        } catch (final ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    Main.instance.translation.getString("ui.message.error.ClasspathInvalid"),
                    Main.instance.translation.getString("ui.message.error.title.ClasspathInvalid"),
                    JOptionPane.ERROR_MESSAGE
            );
            new UpdatePerformer().run();
        }
        return null;
    }
}
