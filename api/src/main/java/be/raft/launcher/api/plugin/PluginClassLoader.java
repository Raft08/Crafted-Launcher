package be.raft.launcher.api.plugin;

import org.slf4j.Logger;

/**
 * ClassLoader used for loading in classes of plugins.
 */
public interface PluginClassLoader {

    /**
     * Retrieve the meta information of the plugin.
     *
     * @return the meta information of the plugin.
     */
    PluginMeta meta();

    /**
     * Retrieve the plugin's logger.
     *
     * @return logger of the plugin.
     */
    Logger logger();

    /**
     * Checks whether the plugin allows external plugins to use its classpath.
     *
     * @return true if external plugins are allowed to use this loader's classpath.
     */
    boolean allowsExternalClasspathUsage();

    /**
     * Sets whether the plugin allows external plugins to use its classpath.
     * <p>
     * By default, this is set to true.
     *
     * @param allow whether external plugins are allowed to use this loader's classpath.
     */
    void allowsExternalClasspathUsage(boolean allow);
}
