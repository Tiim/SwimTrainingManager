package ch.scbirs.trainingmanager.utils;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Tim
 * @since 07 - 2014
 */
public class TaskRunner extends Thread {

    private final SimpleStringProperty message = new SimpleStringProperty();
    private final LinkedBlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();


    public TaskRunner() {
        super("Task Runner");
        setDaemon(true);
    }

    public void addTasks(final Runnable... tasks) {
        this.tasks.addAll(Arrays.asList(tasks));
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                final Runnable t = tasks.take();
                if (t instanceof Task) {
                    Platform.runLater(() -> message.bind(((Task) t).messageProperty()));
                }
                try {
                    t.run();
                } catch (final Exception e) {
                    e.printStackTrace();
                }
                if (t instanceof Task) {
                    Platform.runLater(message::unbind);
                }
            }
        } catch (final InterruptedException ignored) {
        }
    }

    public SimpleStringProperty messageProperty() {
        return message;
    }
}
