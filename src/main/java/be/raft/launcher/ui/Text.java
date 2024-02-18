package be.raft.launcher.ui;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import fr.atlasworld.common.logging.LogUtils;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class Text {
    private static final Logger LOGGER = LogUtils.getLoggerFullName();
    private static final String DEFAULT_LANG = "en_us";
    private static final String LANGUAGE_PROPERTY_KEY = "crafted.ui.lang";

    private static JsonObject translation;

    static {
        String lang = System.getProperty(LANGUAGE_PROPERTY_KEY);

        if (lang == null)
            lang = DEFAULT_LANG;

        update(lang);
    }

    public static String translate(@NotNull String key) {
        Preconditions.checkNotNull(key);

        String lang = System.getProperty(LANGUAGE_PROPERTY_KEY);

        if (translation.has(key))
            return translation.get(key).getAsString();

        LOGGER.warn("Missing language entry in {}.json : {}!", lang, key);

        return key;
    }

    public static Font getFont(String name, int size) {
        return Font.loadFont(Text.class.getResourceAsStream("/assets/font/" + name + ".ttf"), size);
    }

    public static void update(@NotNull String language) {
        Preconditions.checkNotNull(language);

        try (InputStream stream = Text.class.getResourceAsStream("/assets/lang/" + language + ".json")) {
            if (stream != null) {
                Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
                translation = new Gson().fromJson(reader, JsonObject.class);
                reader.close();
            } else {
                LOGGER.warn("Requested language '{}' could not be found! Falling back on '{}'.", language, DEFAULT_LANG);
                update(DEFAULT_LANG);
            }
        } catch (IOException | JsonParseException e) {
            if (!language.equals(DEFAULT_LANG))
                update(DEFAULT_LANG);

            JOptionPane.showMessageDialog(null, "<html><b>Installation Corrupted!</b><br><br>"
                            + "Unable to read language files!<br><br>" + e, "Crafted-Launcher",
                    JOptionPane.WARNING_MESSAGE);

            LOGGER.error("Could not read {}.json file!", language, e);
            System.exit(-1);
        }

        System.setProperty(LANGUAGE_PROPERTY_KEY, language);
    }
}
