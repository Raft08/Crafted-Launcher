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

package be.raft.launcher;

import be.raft.launcher.file.GameFileManager;
import be.raft.launcher.file.loader.JsonStreamLoader;
import be.raft.launcher.gui.GuiLauncher;
import be.raft.launcher.gui.Text;
import be.raft.launcher.misc.LaunchArguments;
import be.raft.launcher.misc.Settings;
import com.google.gson.JsonObject;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.Locale;
import java.util.Set;

public class CraftedLauncher {
    public static final Logger logger = LoggerFactory.getLogger("Crafted-Launcher");
    private static LaunchArguments launchArgs;

    public static void main(String[] args) {
        CraftedLauncher.logger.info("Validating environment..");

        //Env validation
        if (!validateJavaFx()) {
            return;
        }

        //Argument parsing
        launchArgs = LaunchArguments.buildArguments(args);


        //Misc
        System.setProperty("prism.lcdtext", "false"); //Enhance smoothness of the text
        if (!GameFileManager.getGameDirectory().isDirectory()) {
            GameFileManager.getGameDirectory().mkdirs();
        }

        //Initialize Translator
        Text.init();

        Application.launch(GuiLauncher.class);
    }

    private static boolean validateJavaFx() {
        try {
            Class.forName("javafx.application.Application");
            CraftedLauncher.logger.info("JavaFX validation successful!");
            return true;
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error:\n" + e.getMessage() + "\nnot found!" +
                    "\nUnable to start the launcher!", "error", JOptionPane.ERROR_MESSAGE);
            CraftedLauncher.logger.error("JavaFX validation failed!", e);
            return false;
        }
    }

    public static LaunchArguments getLaunchArgs() {
        return launchArgs;
    }
}
