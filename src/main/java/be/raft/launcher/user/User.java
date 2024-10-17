package be.raft.launcher.user;

import java.util.UUID;

public class User {
    private final UUID userId;
    private final String username;

    private final UserPreferences preferences;

    public User(UUID userId, String username, UserPreferences preferences) {
        this.userId = userId;
        this.username = username;
        this.preferences = preferences;
    }
}
