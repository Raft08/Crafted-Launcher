package be.raft.launcher.util;

import fr.atlasworld.common.logging.LogUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class Version {
    private static final Logger LOGGER = LogUtils.getLoggerFullName();

    public static final Version VERSION;

    static {
        Properties properties = new Properties();
        try {
            properties.load(Version.class.getResourceAsStream("/version.properties"));
        } catch (IOException e) {
            LOGGER.error("Failed to load version!", e);

            // Fall-Back
            properties.setProperty("version", "???");
            properties.setProperty("commit", "???");
            properties.setProperty("branch", "???");
        }

        VERSION = new Version(properties);
    }

    private final String version;
    private final String commit;
    private final String branch;

    private Version(Properties properties) {
        this.version = properties.getProperty("version");
        this.commit = properties.getProperty("commit");
        this.branch = properties.getProperty("branch");
    }

    public String getVersion() {
        return version;
    }

    public String getCommit() {
        return commit;
    }

    public String getBranch() {
        return branch;
    }

    public String getVersionMessage() {
        return this.version + " (" + this.branch + ": " + this.commit.substring(0, 7) + ")";
    }

    @Override
    public String toString() {
        return this.version + " (" + this.branch + ": " + this.commit + ")";
    }
}
