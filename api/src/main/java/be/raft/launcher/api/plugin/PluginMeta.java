package be.raft.launcher.api.plugin;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Plugin Meta-Data.
 */
public interface PluginMeta {

    /**
     * Get the plugin identifier. Matching regex (a-Z, -, _)
     *
     * @return plugin identifier.
     */
    @NotNull
    String id();

    /**
     * Get the plugin name in the active locale of the launcher.
     *
     * @return plugin name.
     */
    @NotNull
    String name();

    /**
     * Get the plugin version.
     *
     * @return plugin version.
     */
    @NotNull
    String version();

    /**
     * Get the plugin description in the active locale of the launcher.
     *
     * @return plugin description.
     */
    @NotNull
    String description();

    /**
     * Get the plugin authors.
     *
     * @return Immutable List of set authors, returns an empty list if no authors we're provided.
     */
    @NotNull
    List<String> authors();

    /**
     * Get the plugin contributors.
     *
     * @return Immutable List of set contributors, returns an empty list if no contributors we're provided.
     */
    @NotNull
    List<String> contributors();

    /**
     * Get the plugin's categories.
     *
     * @return Immutable list of set categories, returns a list with only the 'misc' category if no categories we're provided.
     */
    @NotNull
    List<String> categories();
}
