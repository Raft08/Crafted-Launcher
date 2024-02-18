package be.raft.launcher;

import be.raft.launcher.file.FileManager;
import be.raft.launcher.ui.LauncherApplication;
import be.raft.launcher.util.OS;
import be.raft.launcher.util.Version;
import fr.atlasworld.common.logging.LogUtils;
import javafx.application.Application;
import org.slf4j.Logger;

import javax.swing.*;

public class Launcher {
    private static final Logger LOGGER = LogUtils.getLoggerFullName();

    public static void main(String[] args) {
        LOGGER.info("Starting Crafted {}", Version.VERSION.getVersionMessage());
        LOGGER.info("Java version: " + System.getProperty("java.version") + ", VM: " + System.getProperty("java.vm.name")
                + ", vendor: " + System.getProperty("java.vendor"));
        LOGGER.info("Installation Directory: {}", FileManager.getWorkingDirectory());
        LOGGER.info("User Directory: {}", FileManager.getUserDirectory());

        if (OS.getBitsSize() == OS.BIT32) {
            JOptionPane.showMessageDialog(null, "<html>You are trying to run Crafted Launcher on 32-bit computer.<br>"
                    + "Mojang has dropped support for 32-bit processor for modern Minecraft.<br>"
                    + "Your configuration is unsupported by the launcher.", "Crafted-Launcher", JOptionPane.WARNING_MESSAGE);
            LOGGER.error("Detected 32-bit processor, configuration unsupported.");
            System.exit(-1);
        }

        // Properties
        System.setProperty("crafted.ui.theme.selection", "automatic");
        System.setProperty("crafted.ui.lang", "en_us");

        Application.launch(LauncherApplication.class, args);
    }
}