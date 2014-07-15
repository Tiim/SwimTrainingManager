package ch.scbirs.trainingmanager.utils.lang;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author Tim
 * @since 07 - 2014
 */
public class Translation extends ResourceBundle {
    private static final String DEFAULT_LANGUAGE = "en_US";

    private final Properties props;

    public Translation(final String lang) {
        props = new Properties();
        try {
            props.load(Translation.class.getResourceAsStream(lang + ".properties"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public Translation() {
        this(DEFAULT_LANGUAGE);
    }


    @Override
    protected Object handleGetObject(final String key) {
        return props.getProperty(key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Enumeration<String> getKeys() {
        return (Enumeration) props.keys();
    }
}
