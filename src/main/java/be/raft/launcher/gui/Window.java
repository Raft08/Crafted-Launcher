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
import be.raft.launcher.gui.theme.Theme;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.Closeable;
import java.io.IOException;

public class Window implements Closeable {
    private final Stage stage;
    private final GridPane layout;
    private Panel panel;
    private Theme theme;

    public Window(Stage stage, Theme theme) {
        this.stage = stage;
        this.layout = new GridPane();
        this.theme = theme;

        //Initialize
        Scene scene = new Scene(this.layout);
        scene.getStylesheets().add(theme.getStylesheet());
        this.stage.setScene(scene);
    }

    public void setPanel(Panel panel) {
        //Clear old panel
        this.layout.getChildren().clear();
        this.panel = panel;

        //Initialize the panel
        panel.init(this.stage, this.theme);
        panel.getLayout().setId(panel.toString());

        //Apply the panel
        this.layout.getChildren().add(panel.getLayout());
    }

    public void show() {
        this.stage.show();
    }

    public void hide() {
        this.stage.hide();
    }
    @Override
    public void close() {
        this.stage.close();
    }

    public Stage getStage() {
        return stage;
    }

    public GridPane getLayout() {
        return layout;
    }

    public Panel getPanel() {
        return panel;
    }

    public Theme getTheme() {
        return theme;
    }

}
