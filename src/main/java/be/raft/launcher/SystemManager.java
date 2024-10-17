package be.raft.launcher;

import fr.atlasworld.common.file.DataUnit;
import fr.atlasworld.common.logging.LogUtils;
import org.slf4j.Logger;
import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;
import oshi.software.os.linux.LinuxOperatingSystem;
import oshi.software.os.mac.MacOperatingSystem;
import oshi.software.os.windows.WindowsOperatingSystem;

import javax.swing.*;

public final class SystemManager {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final SystemInfo SYSTEM = new SystemInfo();

    private SystemManager() {
        throw new UnsupportedOperationException();
    }
}
