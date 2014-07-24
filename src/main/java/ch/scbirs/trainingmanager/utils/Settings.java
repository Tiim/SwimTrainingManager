package ch.scbirs.trainingmanager.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

/**
 * @author Tim
 * @since 07 - 2014
 */
public class Settings {

    private final Path path;
    private final Properties props;
    private boolean changed = false;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Settings(final Path file) throws IOException {
        this.path = file;
        props = new Properties();
        props.load(Files.newInputStream(file, StandardOpenOption.CREATE));
        for (final Object s : props.keySet()) {
            if (!(s instanceof String)) {
                throw new IllegalStateException("Property key " + s + " is of class " + s.getClass());
            }
            final String key = (String) s;
            final String prefix = key.split(":")[0];
            switch (prefix) {
                case "I":
                    Integer.parseInt(props.getProperty(key));
                    break;
                case "B":
                    Boolean.parseBoolean(props.getProperty(key));
                    break;
                case "F":
                    Float.parseFloat(props.getProperty(key));
                    break;
                case "S":
                    break;
                default:
                    throw new IllegalStateException("Unknown prefix '" + prefix + "' on key " + key);
            }
        }
    }

    public int getInteger(final String key, final int def) {
        final String prefixedKey = "I:" + key;
        if (props.containsKey(prefixedKey)) {
            return Integer.parseInt(props.getProperty(prefixedKey));
        }
        return def;
    }

    public boolean getBool(final String key, final boolean def) {
        final String prefixedKey = "B:" + key;
        if (props.containsKey(prefixedKey)) {
            return Boolean.parseBoolean(props.getProperty(prefixedKey));
        }
        return def;
    }

    public float getFloat(final String key, final float def) {
        final String prefixedKey = "F:" + key;
        if (props.containsKey(prefixedKey)) {
            return Float.parseFloat(props.getProperty(prefixedKey));
        }
        return def;
    }

    public String getString(final String key, final String def) {
        final String prefixedKey = "S:" + key;
        if (props.containsKey(prefixedKey)) {
            return props.getProperty(prefixedKey);
        }
        return def;
    }


    public void setString(final String key, final String value) {
        final String prefixedKey = "S:" + key;
        if (!props.containsKey(prefixedKey) || !props.getProperty(prefixedKey).equals(value)) {
            changed = true;
        }
        props.setProperty(prefixedKey, value);
    }

    public void setInteger(final String key, final int value) {
        final String prefixedKey = "I:" + key;
        final String val = Integer.toString(value);
        if (!props.containsKey(prefixedKey) || !props.getProperty(prefixedKey).equals(val)) {
            changed = true;
        }
        props.setProperty(prefixedKey, val);
    }

    public void setBool(final String key, final boolean value) {
        final String prefixedKey = "B:" + key;
        final String val = Boolean.toString(value);
        if (!props.containsKey(prefixedKey) || !props.getProperty(prefixedKey).equals(val)) {
            changed = true;
        }
        props.setProperty(prefixedKey, Boolean.toString(value));
    }

    public void setFloat(final String key, final float value) {
        final String prefixedKey = "F:" + key;
        final String val = Float.toString(value);
        if (!props.containsKey(prefixedKey) || !props.getProperty(prefixedKey).equals(val)) {
            changed = true;
        }
        props.setProperty(prefixedKey, Float.toString(value));
    }

    public void save() {
        if (changed) {
            try {
                props.store(Files.newOutputStream(path, StandardOpenOption.CREATE), "Config File");
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }
}
