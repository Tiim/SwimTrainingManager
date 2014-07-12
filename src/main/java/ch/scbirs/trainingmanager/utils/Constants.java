package ch.scbirs.trainingmanager.utils;

/**
 * @author Tim
 * @since 07 - 2014
 */
public class Constants {

    public static final String REMOTE_LOCATION = "https://dl.dropboxusercontent.com/u/49598155/sm";
    public static final String REMOTE_PROGRAM_VERSION_URL = REMOTE_LOCATION + "/version.txt";
    public static final String REMOTE_UPDATER_VERSION_URL = REMOTE_LOCATION + "/updaterVersion.txt";
    public static final String REMOTE_PROGRAM_URL = REMOTE_LOCATION + "/dist.zip";
    public static final String REMOTE_UPDATER_URL = REMOTE_LOCATION + "/Updater.jar";

    public static final String LOCAL_UPDATER_URL = "Updater.jar";
    public static final String LOCAL_PROGRAM_URL = "TrainingManager.jar";
    public static final String LOCAL_UPDATER_VERSION_URL = "updaterVersion.txt";

    public static final String UPDATER_COMMAND;

    static {
        UPDATER_COMMAND = String.format(
                "java -jar %s \"%s\" \"java -jar %s\"",
                LOCAL_UPDATER_URL,
                REMOTE_PROGRAM_URL,
                LOCAL_PROGRAM_URL
        );
    }
}
