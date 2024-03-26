package be.raft.launcher.plugin;

import be.raft.launcher.api.plugin.PluginMeta;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.regex.Pattern;

public class PluginInfo implements PluginMeta {
    public static final Gson PLUGIN_GSON = new GsonBuilder()
            .create();

    public static final String ID_REGEX = "^[a-z0-9-_]+$";

    private final @NotNull String id;
    private final @NotNull String version;
    private final @Nullable List<String> authors;
    private final @Nullable List<String> contributors;
    private final @Nullable List<String> categories;
    private final @SerializedName("requires_restart") boolean requiredRestart;

    // Internal Field
    private final @NotNull @SerializedName("main") String mainClass;


    public PluginInfo(@NotNull String id, @NotNull String version, @Nullable List<String> authors,
                      @Nullable List<String> contributors, @Nullable List<String> categories, boolean requiredRestart,
                      @NotNull String mainClass) {

        this.id = id;
        this.version = version;
        this.authors = authors;
        this.contributors = contributors;
        this.categories = categories;
        this.requiredRestart = requiredRestart;
        this.mainClass = mainClass;
    }

    {
        this.validateInfo(); // Run when new instance is created.
    }


    private void validateInfo() {
        Preconditions.checkNotNull(this.id);
        Preconditions.checkNotNull(this.version);
        Preconditions.checkNotNull(this.mainClass);

        Preconditions.checkArgument(!this.id.isEmpty(), "ID may not be empty!");
        Preconditions.checkArgument(!this.version.isEmpty(), "Version must not be empty!");
        Preconditions.checkArgument(!this.mainClass.isEmpty(), "Main Class must not be empty!");

        Preconditions.checkArgument(Pattern.matches(ID_REGEX, this.id), "Module ID must match regex: [" + ID_REGEX + "]");
    }

    @Override
    public @NotNull String id() {
        return this.id;
    }

    @Override
    public @NotNull String name() {
        return null; // TODO: Name Translation
    }

    @Override
    public @NotNull String version() {
        return this.version;
    }

    @Override
    public @NotNull String description() {
        return null; // TODO: Description Translation
    }

    @Override
    public @NotNull List<String> authors() {
        return List.copyOf(this.authors == null ? List.of() : this.authors);
    }

    @Override
    public @NotNull List<String> contributors() {
        return List.copyOf(this.contributors == null ? List.of() : this.contributors);
    }

    @Override
    public @NotNull List<String> categories() {
        return List.copyOf(this.categories == null ? List.of("misc") : this.categories);
    }

    public @NotNull String mainClass() {
        return this.mainClass;
    }
}
