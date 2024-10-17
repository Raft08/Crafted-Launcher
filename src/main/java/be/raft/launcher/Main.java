package be.raft.launcher;

import fr.atlasworld.common.logging.LogUtils;
import javafx.application.Application;
import org.slf4j.Logger;
import oshi.SystemInfo;

public class Main {

    private static final Logger LOGGER = LogUtils.getLogger();

    public static void main(String[] args) {
        LOGGER.info("Starting launcher..");

        SystemInfo sys = SystemManager.SYSTEM;

        Application.launch(CraftedLauncher.class, args);
    }
}
