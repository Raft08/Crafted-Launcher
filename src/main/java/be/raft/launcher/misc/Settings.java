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

package be.raft.launcher.misc;

import be.raft.launcher.file.GameFileManager;
import be.raft.launcher.file.loader.GsonFileLoader;

import java.io.File;
import java.util.Locale;

public class Settings {
    private boolean firstLaunch;
    private String lang;

    private Settings(boolean firstLaunch, String lang) {
        this.firstLaunch = firstLaunch;
        this.lang = lang;
    }

    public boolean isFirstLaunch() {
        return firstLaunch;
    }

    public String getLang() {
        return lang;
    }

    public void setFirstLaunch(boolean firstLaunch) {
        this.firstLaunch = firstLaunch;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void save() {
        new GsonFileLoader<Settings>(GameFileManager.getGameDirectoryFile(SETTINGS_FILE), Settings.class).save(this);
    }

    //Static
    public static final String SETTINGS_FILE = "settings.json";
    private static Settings settings;

    public static Settings getSettings() {
        if (settings == null) {
            GsonFileLoader<Settings> settingsLoader = new GsonFileLoader<>(GameFileManager.getGameDirectoryFile(SETTINGS_FILE),
                    Settings.class);

            if (!settingsLoader.fileExists()) {
                settingsLoader.createFile();
                settingsLoader.save(new Settings(true, Locale.getDefault().toString().toLowerCase()));
            }

            settings = settingsLoader.load();
        }

        return settings;
    }
}
