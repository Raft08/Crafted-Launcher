package be.raft.launcher.api.plugin;

import fr.atlasworld.common.concurrent.action.FutureAction;

/**
 * Module interface represents a plugin on the launcher.
 */
public interface Plugin {

    /**
     * Retrieve the meta information of the plugin.
     *
     * @return meta information of the plugin.
     */
    PluginMeta meta();

    /**
     * Retrieve the deactivation future of this plugin.
     *
     * @return deactivation future of the plugin.
     */
    FutureAction<Void> deactivationFuture();

    /**
     * Checks whether this plugin is active on the launcher.
     *
     * @return true if the plugin is active, false otherwise.
     */
    boolean active();
}
