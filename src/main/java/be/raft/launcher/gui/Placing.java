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

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class Placing {
    public static void setCanTakeAllSize(Node node) {
        GridPane.setHgrow(node, Priority.ALWAYS);
        GridPane.setVgrow(node, Priority.ALWAYS);
    }

    public static void setCanTakeAllWidth(Node node) {
        GridPane.setHgrow(node, Priority.ALWAYS);
    }

    public static void setCanTakeAllHeight(Node node) {
        GridPane.setVgrow(node, Priority.ALWAYS);
    }

    public static void setLeft(Node node) {
        GridPane.setHalignment(node, HPos.LEFT);
    }

    public static void setRight(Node node) {
        GridPane.setHalignment(node, HPos.RIGHT);
    }

    public static void setTop(Node node) {
        GridPane.setValignment(node, VPos.TOP);
    }

    public static void setBottom(Node node) {
        GridPane.setValignment(node, VPos.BOTTOM);
    }

    public static void setBaseLine(Node node) {
        GridPane.setValignment(node, VPos.BASELINE);
    }

    public static void setCenterH(Node node) {
        GridPane.setHalignment(node, HPos.CENTER);
    }

    public static void setCenterV(Node node) {
        GridPane.setValignment(node, VPos.CENTER);
    }
}
