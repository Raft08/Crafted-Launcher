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

package be.raft.launcher.gui.panel;

import be.raft.launcher.gui.GuiLauncher;
import be.raft.launcher.gui.Placing;
import be.raft.launcher.gui.Text;
import be.raft.launcher.gui.theme.Theme;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jetbrains.annotations.NotNull;

public class LoadingPanel extends Panel {
    public LoadingPanel(GuiLauncher guiLauncher) {
        super(guiLauncher);
    }

    @Override
    public void init(Stage stage, Theme theme) {
        //stage
        stage.centerOnScreen();
        stage.setTitle("Crafted Launcher - Loading");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setHeight(420);
        stage.setWidth(720);

        this.guiLauncher.getWindow().getLayout().setBackground(new Background(new BackgroundImage(theme.getBackground(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, true))));

        //Layout
        Label title = new Label(Text.translate("loading.launcher.title"));
        title.setId("loading-title");

        Placing.setCanTakeAllSize(title);
        Placing.setCenterV(title);

        ProgressBar loadingBar = new ProgressBar();
        loadingBar.setId("loading-bar");

        Placing.setCanTakeAllSize(loadingBar);
        Placing.setCenterV(loadingBar);

        Label loadingDescription = new Label(Text.translate("generic.launcher.loading"));
        loadingDescription.setId("loading-description");

        Placing.setCanTakeAllSize(loadingDescription);
        Placing.setCenterV(loadingDescription);

        this.layout.getChildren().addAll(title, loadingBar, loadingDescription);
    }

    @Override
    public @NotNull String toString() {
        return "loading-panel";
    }
}
