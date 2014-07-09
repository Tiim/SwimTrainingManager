package ch.scbirs.trainingmanager.utils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Tim
 * @since 07 - 2014
 */
public class Utils {
    public static String downloadString(String url) throws IOException {
        URL remote = new URL(url);
        URLConnection conn = remote.openConnection();
        BufferedInputStream is = new BufferedInputStream(conn.getInputStream());
        byte[] contents = new byte[20];
        int bytesRead;
        String str = "";
        while ((bytesRead = is.read(contents)) != -1) {
            str += new String(contents, 0, bytesRead);
        }
        return str;
    }

    public static String readString(String file) throws IOException {
        BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
        byte[] contents = new byte[20];
        int bytesRead;
        String str = "";
        while ((bytesRead = is.read(contents)) != -1) {
            str += new String(contents, 0, bytesRead);
        }
        return str;
    }

    public static void downloadFile(String url, String file) throws IOException {
        URL remote = new URL(url);
        URLConnection conn = remote.openConnection();
        InputStream is = conn.getInputStream();
        FileOutputStream fos = new FileOutputStream(file);
        byte[] contents = new byte[1024];
        int bytesRead;
        while ((bytesRead = is.read(contents)) != -1) {
            fos.write(contents, 0, bytesRead);
        }
    }

}
