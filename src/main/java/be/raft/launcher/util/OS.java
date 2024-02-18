package be.raft.launcher.util;

public class OS {
    public static final int WINDOWS = 0;
    public static final int MAC = 1;
    public static final int LINUX = 2;

    public static final int BIT32 = 32;
    public static final int BIT64 = 64;

    public static final String X86_64 = "x86_64";
    public static final String AARCH64 = "aarch64";

    public static int getOS() {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().contains("mac") || os.toLowerCase().contains("os x"))
            return MAC;
        if (os.toLowerCase().contains("win"))
            return WINDOWS;
        return LINUX;
    }

    public static int getBitsSize() {
        boolean is64bit;
        if (getOS() == WINDOWS) {
            is64bit = (System.getenv("ProgramFiles(x86)") != null);
        } else {
            is64bit = (System.getProperty("os.arch").contains("64"));
        }
        if (is64bit)
            return BIT64;
        return BIT32;
    }

    public static String getArchitecture() {
        String osArch = System.getProperty("os.arch");
        if (osArch.contains(X86_64) || osArch.contains("amd64")) {
            return X86_64;
        } else if (osArch.contains(AARCH64)) {
            return AARCH64;
        } else {
            return osArch;
        }
    }
}
