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

import be.raft.launcher.gui.panel.LoadingPanel;
import be.raft.launcher.gui.theme.DefaultTheme;
import javafx.application.Application;
import javafx.stage.Stage;

public class GuiLauncher extends Application {
    private Window window;

    @Override
    public void start(Stage stage) throws Exception {
        launcher = this;
        this.window = new Window(stage, DefaultTheme.THEME);
        this.window.setPanel(new LoadingPanel(this));
        this.window.show();
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window.close();
        this.window = window;
        this.window.show();
    }

    //Static
    public static GuiLauncher launcher;

    public static GuiLauncher getLauncher() {
        return launcher;
    }
}
