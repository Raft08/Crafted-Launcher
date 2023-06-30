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

import javafx.scene.image.Image;

import java.util.Random;

public class DefaultTheme implements Theme {

    private DefaultTheme() {
    }

    @Override
    public String getName() {
        return "Default";
    }

    @Override
    public String getId() {
        return "default";
    }

    @Override
    public String getDescription() {
        return "Launcher's Default Theme";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String[] getAuthors() {
        return new String[] {"RaftDev"};
    }

    @Override
    public String getCredit() {
        return "";
    }

    @Override
    public String getStylesheet() {
        return "theme/default/css/" + Theme.cssFile;
    }

    @Override
    public Image getImage(String image) {
        return new Image("theme/default/images/" + image);
    }

    @Override
    public Image getBackground() {
        int backgroundCount = 7;
        return new Image("theme/default/images/background/" + new Random().nextInt(backgroundCount) + ".jpg");
    }

    public static final DefaultTheme THEME = new DefaultTheme();
}
