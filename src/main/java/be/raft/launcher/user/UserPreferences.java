package be.raft.launcher.user;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import fr.atlasworld.common.file.reader.JsonFileReader;
import org.jetbrains.annotations.NotNull;

public class UserPreferences {



    private final User user;
    private final JsonFileReader<JsonObject> reader;

    public UserPreferences(@NotNull User user) {
        Preconditions.checkNotNull(user, "A user is required to read preferences!");

        this.user = user;
        this.reader = null;
    }


}
