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

package be.raft.launcher.gui.theme;

import be.raft.launcher.CraftedLauncher;
import be.raft.launcher.file.GameFileManager;
import javafx.scene.image.Image;

import java.io.File;
import java.util.Random;

public class SimpleTheme implements Theme {
    private final String name;
    private final String id;
    private final String description;
    private final String version;
    private final String[] authors;
    private final String credit;

    public SimpleTheme(String name, String id, String description, String version, String[] authors, String credit) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.version = version;
        this.authors = authors;
        this.credit = credit;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    @Override
    public String[] getAuthors() {
        return this.authors;
    }

    @Override
    public String getCredit() {
        return this.credit;
    }

    @Override
    public String getStylesheet() {
        File styleSheet = GameFileManager.getGameDirectoryFile(Theme.themeDir + "/" + this.id + "/css/" + Theme.cssFile);

        if (!styleSheet.isFile()) {
            CraftedLauncher.logger.error("Cannot find css style file for {}, file is supposed to be located at '{}'", this.name, styleSheet);
            return DefaultTheme.THEME.getStylesheet();
        }

        return styleSheet.toString();
    }

    @Override
    public Image getImage(String image) {
        File imageFile = GameFileManager.getGameDirectoryFile(Theme.themeDir + "/" + this.id + "/images/" + image);

        if (!imageFile.isFile()) {
            CraftedLauncher.logger.error("Cannot find image '{}' for {}, file is supposed to be located at '{}'", image, this.name, imageFile);
            return DefaultTheme.THEME.getImage(image);
        }

        return new Image(imageFile.toString());
    }

    @Override
    public Image getBackground() {
        File backgroundDir = GameFileManager.getGameDirectoryFile(Theme.themeDir + "/" + this.id + "/images/background");
        File[] backgrounds = backgroundDir.listFiles(file -> file.isFile() &&
                (file.getName().endsWith(".png") || file.getName().endsWith(".jpg")));

        if (backgrounds == null || backgrounds.length < 1) {
            CraftedLauncher.logger.error("Cannot find any background for {}, files supposed to be located at '{}'", this.name, backgroundDir);
            return DefaultTheme.THEME.getBackground();
        }

        return new Image(backgrounds[new Random().nextInt(backgrounds.length)].toString());
    }
}
