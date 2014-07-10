package ch.scbirs.trainingmanager.updater;

import ch.scbirs.trainingmanager.utils.Constants;
import ch.scbirs.trainingmanager.utils.Utils;

import java.io.IOException;

/**
 * @author Tim
 * @since 07 - 2014
 */
public class UpdatePerformer implements Runnable {

    @Override
    public void run() {
        try {
            if (VersionChecker.isNewUpdaterVersionAvailable()) {
                System.out.println("Updating updater..");
                updateUpdater();
            }
            launchUpdater();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    private void launchUpdater() throws IOException {
        System.out.println("Launch updater:");
        System.out.println(Constants.UPDATER_COMMAND);
        Runtime.getRuntime().exec(Constants.UPDATER_COMMAND);
    }

    private void updateUpdater() throws IOException {
        System.out.println("Downloading updater");
        Utils.downloadFile(Constants.REMOTE_UPDATER_URL, Constants.LOCAL_UPDATER_URL);
        System.out.println("Downloading updater version");
        Utils.downloadFile(Constants.REMOTE_UPDATER_VERSION_URL, Constants.LOCAL_UPDATER_VERSION_URL);
    }
}
