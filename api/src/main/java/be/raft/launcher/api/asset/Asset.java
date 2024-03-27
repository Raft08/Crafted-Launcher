package be.raft.launcher.api.asset;

import be.raft.launcher.api.internal.asset.AssetDelegate;
import com.google.common.base.Preconditions;
import javafx.scene.image.Image;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public final class Asset {
    public static final String DEAD_IMAGE_PATH = "not_found.png";

    private static AssetDelegate delegate;

    @ApiStatus.Internal
    public void updateDelegate(@NotNull AssetDelegate newDelegate) {
        if (delegate != null)
            throw new UnsupportedOperationException("Delegate instance cannot be changed!");

        Preconditions.checkNotNull(newDelegate);
        delegate = newDelegate;
    }

    /**
     * Load a new image.
     *
     * @param path path to the image.
     * @return image found, or a dead image icon if it could not be found.
     */
    public static Image image(@NotNull String path) {
        Preconditions.checkNotNull(path);
        Image image = delegate.image(path);

        if (image == null)
            return delegate.image(DEAD_IMAGE_PATH);

        return image;
    }
}
