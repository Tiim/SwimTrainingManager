package ch.scbirs.trainingmanager.utils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Tim
 * @since 07 - 2014
 */
public final class Utils {
    public static String downloadString(final String url) throws IOException {
        final URL remote = new URL(url);
        final URLConnection conn = remote.openConnection();
        final BufferedInputStream is = new BufferedInputStream(conn.getInputStream());
        final byte[] contents = new byte[20];
        int bytesRead;
        String str = "";
        while ((bytesRead = is.read(contents)) != -1) {
            str += new String(contents, 0, bytesRead);
        }
        return str;
    }

    public static String readString(final String file) throws IOException {
        final BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
        final byte[] contents = new byte[20];
        int bytesRead;
        String str = "";
        while ((bytesRead = is.read(contents)) != -1) {
            str += new String(contents, 0, bytesRead);
        }
        return str;
    }

    public static void downloadFile(final String url, final String file) throws IOException {
        final URL remote = new URL(url);
        final URLConnection conn = remote.openConnection();
        final InputStream is = conn.getInputStream();
        final FileOutputStream fos = new FileOutputStream(file);
        final byte[] contents = new byte[1024];
        int bytesRead;
        while ((bytesRead = is.read(contents)) != -1) {
            fos.write(contents, 0, bytesRead);
        }
    }

}
