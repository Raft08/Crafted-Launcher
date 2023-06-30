/*
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License
 *
 * This software is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License ("License").
 * You should have received a copy of the License along with this software.
 * If not, please visit: https://creativecommons.org/licenses/by-nc-sa/4.0/legalcode
 *
 * Summary of the License:
 * - You are free to:
 *     - Share: Copy and redistribute the software in any medium or format.
 *     - Adapt: Remix, transform, and build upon the software.
 *
 * Under the following terms:
 * - Attribution:
 *     - You must give appropriate credit to the original author (RaftDev) and provide a reference to the license.
 * - Non-Commercial:
 *     - You may not use this software for commercial purposes.
 * - Share-Alike:
 *     - If you remix, transform, or build upon this software, you must distribute your contributions under the same license as the original.
 *
 * For detailed license terms and conditions, please refer to the included License file or visit: https://creativecommons.org/licenses/by-nc-sa/4.0/legalcode
 *
 * © 2023 RaftDev. All Rights Reserved.
 * Crafted-Launcher is a project by RaftDev and is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
 */

package be.raft.launcher.gui;

import be.raft.launcher.CraftedLauncher;
import be.raft.launcher.file.loader.JsonStreamLoader;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Text {
    private static Locale locale;
    private static JsonObject jsonLang;

    public static void load(Locale setLocale, JsonObject parsedLang) {
        locale = setLocale;
        jsonLang = parsedLang;
    }

    public static String translate(String key) {
        if (!jsonLang.keySet().contains(key)) {
            return key;
        }

        return jsonLang.get(key).getAsString();
    }

    public static Locale getLocale() {
        return locale;
    }

    public static JsonObject getJsonLang() {
        return jsonLang;
    }

    public static Map<String, List<String>> getAvailableLocale() {
        Map<String, List<String>> locales = new HashMap<>();

        //English
        locales.put("en", List.of("us"));

        return locales;
    }

    public static JsonObject loadLocale(String fileName) {
        try (InputStream stream = Text.class.getClassLoader().getResourceAsStream("lang/" + fileName)) {
            return new JsonStreamLoader(stream).load().getAsJsonObject();
        } catch (IOException e) {
            CraftedLauncher.logger.error("Unable to load '{}'!", fileName, e);
            return null;
        }
    }
}
