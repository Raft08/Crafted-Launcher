package be.raft.launcher.file;

import javax.swing.*;
import java.io.File;

public class FileManager {
    static {
        File userDir = getUserDirectory();

        if (!userDir.isDirectory())
            if (!userDir.mkdirs()) {
                JOptionPane.showMessageDialog(null, "<html><b>Launcher failed to write to user directory!</b><br><br>"
                                + "Please make sure that Crafted-Launcher has permissions to read and write to " +
                                getUserDirectoryFile("/") + "<br>" + "Crafted-Launcher" + "<br>", "Crafted-Launcher",
                        JOptionPane.WARNING_MESSAGE);
                System.exit(-1);
            }
    }


    public static File getWorkingDirectoryFile(String filename) {
        return new File(getWorkingDirectory(), filename);
    }

    public static File getWorkingDirectory() {
        return new File(System.getProperty("user.dir"));
    }

    public static File getUserDirectoryFile(String filename) {
        return new File(getUserDirectory(), filename);
    }

    public static File getUserDirectory() {
        if (System.getenv("CRAFTED_HOME") != null)
            return new File(System.getenv("CRAFTED_HOME"));

        return new File(System.getProperty("user.home") + "/.crafted/");
    }
}
