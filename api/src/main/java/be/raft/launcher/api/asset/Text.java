package be.raft.launcher.api.asset;

import be.raft.launcher.api.internal.asset.AssetDelegate;
import com.google.common.base.Preconditions;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Text Component, Mainly used for when creating new User Interfaces,
 * this allows to display translated text using {@link #translate(String)},
 * or literal using {@link #literal(String)}.
 */
public final class Text {
    private static AssetDelegate delegate;

    public static Text literal(@NotNull String literal) {
        Preconditions.checkNotNull(literal);

        return new Text(literal);
    }

    public static Text translate(@NotNull String key) {
        Preconditions.checkNotNull(key);

        return new Text(delegate.translate(key));
    }

    // Non-Static
    private final String content;

    private Text(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @ApiStatus.Internal
    public void updateDelegate(@NotNull AssetDelegate newDelegate) {
        if (delegate != null)
            throw new UnsupportedOperationException("Delegate instance cannot be changed!");

        Preconditions.checkNotNull(newDelegate);
        delegate = newDelegate;
    }
}
