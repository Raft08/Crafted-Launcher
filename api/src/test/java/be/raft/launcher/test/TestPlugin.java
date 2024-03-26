package be.raft.launcher.test;

import be.raft.launcher.api.CraftedLauncher;
import be.raft.launcher.api.LauncherPlugin;
import com.google.inject.Inject;
import org.slf4j.Logger;

public class TestPlugin extends LauncherPlugin {
    public static Logger LOGGER;

    @Inject
    public TestPlugin(CraftedLauncher launcher) {
        this.classLoader.allowsExternalClasspathUsage(false); // Test Plugin classes here should not be used out-side of here.

        LOGGER = this.logger();

        LOGGER.info("Hello World!");
    }
}
