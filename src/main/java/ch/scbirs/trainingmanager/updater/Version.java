package ch.scbirs.trainingmanager.updater;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tim
 * @since 07 - 2014
 */
public class Version implements Comparable {

    private static final Pattern VERSION_PATTERN = Pattern.compile("v(\\d+)\\.(\\d+)\\.(\\d+)");

    private final int major;
    private final int minor;
    private final int patch;

    public Version() {
        this(0, 0, 0);
        System.err.println("assuming v0.0.0");
    }

    public Version(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public Version(String v) {
        Matcher m = VERSION_PATTERN.matcher(v);
        if (!m.matches()) {
            throw new IllegalArgumentException("Version '" + v + " must match the version regexp");
        }
        this.major = Integer.parseInt(m.group(1));
        this.minor = Integer.parseInt(m.group(2));
        this.patch = Integer.parseInt(m.group(3));
    }


    @Override
    public String toString() {
        return String.format("v%d.%d.%d", major, minor, patch);
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Version)) {
            return 0;
        }
        Version v = (Version) o;
        //Just in case
        if (v.major != major) {
            return (major - v.major) * 1000;
        }
        if (v.minor != minor) {
            return (minor - v.minor) * 100;
        }
        if (v.patch != patch) {
            return patch - v.patch;
        }
        return 0;
    }

    public boolean isDevBuild() {
        return major == 0 && minor == 0 && patch == 0;
    }
}
