package be.raft.launcher;

import oshi.software.os.linux.LinuxOperatingSystem;
import oshi.software.os.mac.MacOperatingSystem;
import oshi.software.os.windows.WindowsOperatingSystem;

import java.io.File;

public final class FileManager {
    private static final String DATA_DIR_NAME = ".crafted";

    /**
     * Installation directory, where the program is run when in production.
     * <p>
     * This can also be simply used as working directory.
     */
    public static final File INSTALL_DIRECTORY = new File(System.getProperty("user.dir")).getAbsoluteFile();

    /**
     * Data Directory, represents where the apps store the user data like instances, caches, ...
     */
    public static final File DATA_DIRECTORY = dataDirectory();

    private FileManager() {
        throw new UnsupportedOperationException();
    }

    private static File dataDirectory() {
        return switch (SystemManager.SYSTEM.getOperatingSystem()) {
            case LinuxOperatingSystem linux -> new File(new File(System.getProperty("user.home")), ".local/share/" + DATA_DIR_NAME);
            case WindowsOperatingSystem windows -> new File(new File(System.getenv("APPDATA")), DATA_DIR_NAME);
            case MacOperatingSystem mac -> new File(new File(System.getProperty("user.home")), "Library/Application Support/" + DATA_DIR_NAME);
            default -> new File(new File(System.getProperty("user.home")), DATA_DIR_NAME);
        };
    }

    private static File cacheDirectory() {
        return new File(DATA_DIRECTORY, "cache");
    }

    private static File cacheFile(String filename) {
        return new File(dataDirectory(), filename);
    }
}
