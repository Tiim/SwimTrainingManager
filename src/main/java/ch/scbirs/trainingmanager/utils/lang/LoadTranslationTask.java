package ch.scbirs.trainingmanager.utils.lang;

import ch.scbirs.trainingmanager.Main;
import javafx.concurrent.Task;

/**
 * @author Tim
 * @since 07 - 2014
 */
public class LoadTranslationTask extends Task<Void> {

    private final Main main;

    public LoadTranslationTask(final Main main) {
        this.main = main;
    }

    @Override
    protected Void call() throws Exception {
        updateMessage("Loading language");
        main.translation = new Translation();
        return null;
    }
}
