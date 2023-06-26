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

import java.util.Arrays;
import java.util.List;

public class LaunchArguments {
    private final boolean devEnv;

    public LaunchArguments(boolean devEnv) {
        this.devEnv = devEnv;
    }

    public boolean isDevEnv() {
        return devEnv;
    }

    public static LaunchArguments buildArguments(String[] args) {
        List<String> arguments = Arrays.stream(args).toList();

        boolean devEnv = arguments.contains(DEV_ENV);

        return new LaunchArguments(devEnv);
    }

    //Argument Static Definitions
    public static final String DEV_ENV = "-launcher:dev";
}
