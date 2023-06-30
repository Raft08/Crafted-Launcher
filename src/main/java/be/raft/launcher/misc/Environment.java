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

import be.raft.launcher.CraftedLauncher;
import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

public class Environment {
    private final OperatingSystem os;

    private Environment(OperatingSystem os) {
        this.os = os;
    }

    public long getAvailableMemory() {
        return ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getFreeMemorySize();
    }

    public long getTotalMemory() {
        return ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalMemorySize();
    }

    public OperatingSystem getOs() {
        return os;
    }

    //Static
    private static Environment environment;

    public static Environment getEnvironment() {
        if (environment == null) {
            environment = new Environment(OperatingSystem.getOs());
        }

        return environment;
    }

    public enum OperatingSystem {
        LINUX, WINDOWS, MACOS;

        public static OperatingSystem getOs() {
            String osStr = System.getProperty("os.name");

            CraftedLauncher.logger.info(osStr);

            if (osStr.contains("win")) {
                return WINDOWS;
            } else if (osStr.contains("mac")) {
                return MACOS;
            } else {
                return LINUX;
            }
        }
    }
}
