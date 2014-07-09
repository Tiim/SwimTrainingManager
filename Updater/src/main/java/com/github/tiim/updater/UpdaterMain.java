package com.github.tiim.updater;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Error codes:
 * 1 - Wrong argument count
 * 2 - Download error
 * 3 - Execution error
 *
 * @author Tim
 * @since 07 - 2014
 */
public class UpdaterMain {
    public static void main(String[] args) {
        if (args.length != 2) {
            abort("Couldn't download the updated version. Please do it manually.\n" +
                    "Error Code: 1");
        }
        System.out.println(System.getProperty("user.dir"));
        String url = args[0];
        String exec = args[1];

        try {
            download(url);
        } catch (IOException e) {
            abort("Couldn't download the updated version. Please do it manually.\n" +
                    "Error Code: 2\n" +
                    e.getMessage());
            e.printStackTrace();
        }

        try {
            execute(exec);
        } catch (IOException e) {
            abort("Couldn't restart the updated version. Please do it manually.\n" +
                    "Error Code: 3\n" +
                    e.getMessage());
            e.printStackTrace();
        }

    }

    private static void execute(String exec) throws IOException {
        Process p = Runtime.getRuntime().exec(exec);
        System.out.println("Execute " + exec);
        try {
            System.out.println(p.waitFor());
        } catch (InterruptedException ignored) {
        }
    }

    private static void download(String url) throws IOException {
        URL u = new URL(url);
        String fileName = url.substring(url.lastIndexOf('/') + 1);
        URLConnection con = u.openConnection();
        System.out.println("Downloading " + url + " to " + fileName);
        try (InputStream is = con.getInputStream(); FileOutputStream fos = new FileOutputStream(fileName)) {
            byte[] data = new byte[1024];
            int n;
            while ((n = is.read(data)) != -1) {
                fos.write(data, 0, n);
            }
        }
    }


    public static void abort(String message) {
        System.err.println(message);
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}
