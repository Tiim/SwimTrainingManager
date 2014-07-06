package ch.scbirs.trainingmanager;

import javax.swing.*;
import java.lang.reflect.Method;

/**
 * Wraps the default main class, so that people with an outdated java version get an error message.
 *
 * @author Tim
 * @since 07 - 2014
 */

public class MainWrapper {
    private static final float MIN_JAVA_VERSION_REQUIRED = 1.8f;

    /**
     * Main method
     *
     * @param args
     */
    @SuppressWarnings({"unchecked", "TryWithIdenticalCatches"})
    public static void main(String[] args) {
        if (!checkVersion()) {
            try {
                // Maybe the class can be used, idk :D
                Class mainc = Class.forName("ch.scbirs.trainingmanager.Main");
                Method mainm = mainc.getMethod("main", String[].class);
                mainm.invoke(null, (Object) args);
            } catch (Exception e) {
                System.err.println("Can't start application:");
                e.printStackTrace(System.err);
                JOptionPane.showMessageDialog(null, "Can't start the application!", "Old Java Version",
                        JOptionPane.ERROR_MESSAGE);
            } catch (UnsupportedClassVersionError e) {
                System.err.println("Can't start application:");
                e.printStackTrace(System.err);
                JOptionPane.showMessageDialog(null, "Can't start the application!", "Old Java Version",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("Found java version: " + System.getProperty("java.specification.version")
                    + "(" + System.getProperty("java.version") + ")");
            Main.main(args);
        }
    }

    /**
     * Checks if the java version is equal or greater than {@link #MIN_JAVA_VERSION_REQUIRED}
     *
     * @return true if the java version is supported
     */
    private static boolean checkVersion() {
        String version = System.getProperty("java.specification.version");
        float v = Float.parseFloat(version);
        if (v < MIN_JAVA_VERSION_REQUIRED) {
            String w = "Found java version: " + version + "(" + System.getProperty("java.version") + ")\n"
                    + "Minimal version required: " + MIN_JAVA_VERSION_REQUIRED + "\n"
                    + "This program may run unstable";

            System.err.println(w);
            JOptionPane.showMessageDialog(null, w, "Old Java Version", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}
