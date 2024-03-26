package be.raft.launcher.api;

import be.raft.launcher.api.plugin.Plugin;
import be.raft.launcher.api.plugin.PluginClassLoader;
import be.raft.launcher.api.plugin.PluginMeta;
import fr.atlasworld.common.concurrent.action.FutureAction;
import fr.atlasworld.common.concurrent.action.SimpleFutureAction;
import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;

public class LauncherPlugin implements Plugin {
    protected final PluginClassLoader classLoader;

    private final PluginMeta meta;
    private final Logger logger;

    private final SimpleFutureAction<Void> deactivationFuture;

    private boolean active;

    protected LauncherPlugin() {
        if (!(this.getClass().getClassLoader() instanceof PluginClassLoader loader))
            throw new IllegalStateException("Plugin must be loaded with a PluginClassLoader!");

        this.classLoader = loader;

        this.meta = this.classLoader.meta();
        this.logger = this.classLoader.logger();

        this.deactivationFuture = new SimpleFutureAction<>();
    }

    @ApiStatus.Internal
    public FutureAction<Void> deactivate() {
        this.onDeactivation();

        this.active = false;
        this.deactivationFuture.complete(null);

        return this.deactivationFuture;
    }

    /**
     * Called when the plugin is deactivated.
     * Deactivation of plugin can only happen in two situations.
     *
     * <ul>
     *     <li>User disabled your plugin.</li>
     *     <li>Launcher is shutting down.</li>
     * </ul>
     *
     * If your plugin has been disabled by the user,
     * depending on the value of {@code requires_restart} set in your {@code plugin.json}
     * the launcher will restart.
     */
    protected void onDeactivation() {
    }

    @Override
    public PluginMeta meta() {
        return this.meta;
    }

    @Override
    public FutureAction<Void> deactivationFuture() {
        return this.deactivationFuture;
    }

    @Override
    public boolean active() {
        return this.active;
    }

    protected final Logger logger() {
        return this.logger;
    }
}
