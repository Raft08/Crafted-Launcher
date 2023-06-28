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

import be.raft.launcher.gui.panel.Panel;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Window {
    private final Stage stage;
    private final GridPane layout;
    private final String title;
    private Panel panel;

    public Window(Stage stage, String title, Panel panel) {
        this.stage = stage;
        this.layout = new GridPane();
        this.title = title;
        this.panel = panel;
    }

    public void setPanel(Panel panel) {
        //Clear old panel
        this.layout.getChildren().clear();
        this.panel = panel;

        //Initialize the panel
        panel.init();
        panel.getLayout().setId(panel.toString());

        //Apply the panel
        this.layout.getChildren().add(panel.getLayout());
    }

    public Stage getStage() {
        return stage;
    }

    public GridPane getLayout() {
        return layout;
    }

    public String getTitle() {
        return title;
    }

    public Panel getPanel() {
        return panel;
    }
}
