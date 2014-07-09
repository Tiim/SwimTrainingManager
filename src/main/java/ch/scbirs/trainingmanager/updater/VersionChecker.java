package ch.scbirs.trainingmanager.updater;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Tim
 * @since 07 - 2014
 */
public class VersionChecker {
    private static final String REMOTE_VERSION_URL = "https://dl.dropboxusercontent.com/u/49598155/sm/version.txt";
    private static Version currentVersion = null;
    private static Version remoteVersion = null;

    public static Version getCurrentVersion() {
        if (currentVersion != null) {
            return currentVersion;
        }
        InputStream is = VersionChecker.class.getResourceAsStream("/version.txt");
        if (is != null) {
            Reader r = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(r);
            try {
                currentVersion = new Version(br.readLine());
            } catch (IOException | IllegalArgumentException e) {
                System.err.println("Could not read version");
                System.err.println(e);
                currentVersion = new Version();
            }
        } else {
            System.err.println("Could not find version file");
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
            URL remote = new URL(REMOTE_VERSION_URL);
            URLConnection conn = remote.openConnection();
            BufferedInputStream is = new BufferedInputStream(conn.getInputStream());
            byte[] contents = new byte[20];
            int bytesRead;
            String str = "";
            while ((bytesRead = is.read(contents)) != -1) {
                str += new String(contents, 0, bytesRead);
            }
            v = new Version(str);
        } catch (IOException e) {
            e.printStackTrace();
            v = new Version();
        }
        remoteVersion = v;
    }

    public static boolean isNewVersionAvailable() {
        Version current = getCurrentVersion();
        Version remote = getRemoteVersion();
        return current.compareTo(remote) < 0;
    }
}
