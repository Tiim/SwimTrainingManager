package com.github.tiim.updater;

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

    private static final ProgressDialog dialog = new ProgressDialog();

    public static void main(final String[] args) throws InterruptedException {
        if (args.length == 1 && args[0].equals("gui")) {
            Thread.sleep(10000);
            System.exit(0);
        }


        if (args.length != 2) {
            dialog.message("Couldn't download the updated version. Please do it manually.\n" +
                    "Error Code: 1");
        }

        dialog.message(System.getProperty("user.dir"));
        final String url = args[0];
        final String exec = args[1];

        try {
            downloadAndExtract(url);
        } catch (final IOException e) {
            final StringWriter w = new StringWriter();
            e.printStackTrace(new PrintWriter(w));
            dialog.message("Couldn't download the updated version. Please do it manually.\n" +
                    "Error Code: 2\n" +
                    w.toString());
            e.printStackTrace();
        }

        try {
            execute(exec);
        } catch (final IOException e) {
            final StringWriter w = new StringWriter();
            e.printStackTrace(new PrintWriter(w));
            dialog.message("Couldn't restart the updated version. Please do it manually.\n" +
                    "Error Code: 3\n" +
                    w.toString());
            e.printStackTrace();
        }
        dialog.setProgress(100);
        Thread.sleep(2000);
        dialog.setVisible(false);
        System.exit(0);
    }

    private static void execute(final String exec) throws IOException {
        Runtime.getRuntime().exec(exec);
        dialog.message("Execute " + exec);
    }

    private static void downloadAndExtract(final String url) throws IOException {
        final URL u = new URL(url);
        final URLConnection con = u.openConnection();
        dialog.message("Downloading " + url);

        final File outputFolder = new File(".");

        try (ZipInputStream zis = new ZipInputStream(con.getInputStream())) {

            ZipEntry e = zis.getNextEntry();
            while (e != null) {
                if (!e.isDirectory()) {
                    final String zipFileName = e.getName();
                    final File zipNewFile = new File(outputFolder, zipFileName);
                    dialog.message("Extracting " + zipFileName + " to " + zipNewFile);
                    final File parent = new File(zipNewFile.getParent());
                    if (!parent.equals(outputFolder) && parent.mkdirs()) {
                        dialog.message("Could not make folder " + zipNewFile.getParent());
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
    }
}
