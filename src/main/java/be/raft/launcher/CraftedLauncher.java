package be.raft.launcher;

import be.raft.launcher.file.FileManager;
import be.raft.launcher.util.Version;
import fr.atlasworld.common.logging.LogUtils;
import org.slf4j.Logger;

public class CraftedLauncher {
    private static final Logger LOGGER = LogUtils.getLoggerFullName();

    public static void main(String[] args) {
        LOGGER.info("Starting Crafted {}", Version.VERSION.getVersionMessage());
        LOGGER.info("Java version: " + System.getProperty("java.version") + ", VM: " + System.getProperty("java.vm.name")
                + ", vendor: " + System.getProperty("java.vendor"));
        LOGGER.info("Installation Directory: {}", FileManager.getWorkingDirectory());
        LOGGER.info("User Directory: {}", FileManager.getUserDirectory());
    }
}
