package com.github.tiim.updater;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Error codes:
 * 1 - Wrong argument count
 * 2 - Download error
 * 3 - Execution error
 * <p>
 * Arguments:
 * Download url
 * Execution command
 *
 * @author Tim
 * @since 07 - 2014
 */
public final class UpdaterMain {
    public static void main(final String[] args) {
        if (args.length != 2) {
            abort("Couldn't download the updated version. Please do it manually.\n" +
                    "Error Code: 1");
        }
        JOptionPane.showMessageDialog(null, "Updater", "sdf", JOptionPane.WARNING_MESSAGE);
        System.out.println(System.getProperty("user.dir"));
        final String url = args[0];
        final String exec = args[1];

        try {
            downloadAndExtract(url);
        } catch (final IOException e) {
            final StringWriter w = new StringWriter();
            e.printStackTrace(new PrintWriter(w));
            abort("Couldn't download the updated version. Please do it manually.\n" +
                    "Error Code: 2\n" +
                    w.toString());
            e.printStackTrace();
        }

        try {
            execute(exec);
        } catch (final IOException e) {
            abort("Couldn't restart the updated version. Please do it manually.\n" +
                    "Error Code: 3\n" +
                    e.getMessage());
            e.printStackTrace();
        }

    }

    private static void execute(final String exec) throws IOException {
        final Process p = Runtime.getRuntime().exec(exec);
        System.out.println("Execute " + exec);
        try {
            System.out.println(p.waitFor());
        } catch (final InterruptedException ignored) {
        }
    }

    private static void downloadAndExtract(final String url) throws IOException {
        final URL u = new URL(url);
        final URLConnection con = u.openConnection();
        System.out.println("Downloading " + url);

        final File outputFolder = new File(".");

        try (ZipInputStream zis = new ZipInputStream(con.getInputStream())) {
            ZipEntry e = zis.getNextEntry();
            while (e != null) {
                if (!e.isDirectory()) {
                    final String zipFileName = e.getName();
                    final File zipNewFile = new File(outputFolder, zipFileName);
                    System.out.println("Extracting " + zipFileName + " to " + zipNewFile);
                    if (!new File(zipNewFile.getParent()).mkdirs()) {
                        System.err.println("Could not make folder " + zipNewFile.getParent());
                    }
                    try (FileOutputStream fos = new FileOutputStream(zipNewFile)) {
                        int len;
                        final byte[] buffer = new byte[1024];
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
                e = zis.getNextEntry();
            }
        }
//        try (InputStream is = con.getInputStream(); FileOutputStream fos = new FileOutputStream(fileName)) {
//            final byte[] data = new byte[1024];
//            int n;
//            while ((n = is.read(data)) != -1) {
//                fos.write(data, 0, n);
//            }
//        }
    }


    private static void abort(final String message) {
        System.err.println(message);
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}
