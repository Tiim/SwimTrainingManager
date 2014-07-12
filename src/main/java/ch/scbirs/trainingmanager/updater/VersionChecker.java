package ch.scbirs.trainingmanager.updater;

import ch.scbirs.trainingmanager.utils.Constants;
import ch.scbirs.trainingmanager.utils.Utils;

import java.io.*;

/**
 * @author Tim
 * @since 07 - 2014
 */
public class VersionChecker {
    private static Version currentVersion = null;
    private static Version remoteVersion = null;

    public static Version getCurrentVersion() {
        if (currentVersion != null) {
            return currentVersion;
        }
        final String version = VersionChecker.class.getPackage().getImplementationVersion();
        if (version != null) {
            currentVersion = new Version(version);
        } else {
            currentVersion = new Version();
        }
        return currentVersion;
    }

    public static Version getRemoteVersion() {
        if (remoteVersion != null) {
            return remoteVersion;
        }
        reloadRemoteVersion();
        return remoteVersion;
    }

    public static void reloadRemoteVersion() {
        Version v;
        try {
            v = new Version(Utils.downloadString(Constants.REMOTE_PROGRAM_VERSION_URL));
        } catch (IOException e) {
            e.printStackTrace();
            v = new Version();
        }
        remoteVersion = v;
    }

    public static boolean isNewUpdaterVersionAvailable() {
        try {
            Version remote = new Version(Utils.downloadString(Constants.REMOTE_UPDATER_VERSION_URL));
            Version local = new Version(Utils.readString(Constants.LOCAL_UPDATER_VERSION_URL));
            return local.compareTo(remote) < 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean isNewVersionAvailable() {
        Version current = getCurrentVersion();
        Version remote = getRemoteVersion();

        return !(current.isDevBuild() || remote.isDevBuild()) && current.compareTo(remote) < 0;

    }
}
