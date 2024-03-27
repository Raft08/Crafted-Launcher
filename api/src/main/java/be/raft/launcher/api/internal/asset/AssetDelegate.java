package be.raft.launcher.api.internal.asset;

import javafx.scene.image.Image;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Internal API components, changes in classes or function marked with the
 * {@link org.jetbrains.annotations.ApiStatus.Internal} annotation should not get interacted with directly.
 * Changes in these components won't get notified in changelogs.
 */
@ApiStatus.Internal
public interface AssetDelegate {
    @NotNull
    String translate(@NotNull String key);

    @Nullable
    Image image(@NotNull String path);
}
