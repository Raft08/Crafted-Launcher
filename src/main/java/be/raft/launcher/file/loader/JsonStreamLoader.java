/*
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License
 *
 * This software is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License ("License").
 * You should have received a copy of the License along with this software.
 * If not, please visit: https://creativecommons.org/licenses/by-nc-sa/4.0/legalcode
 *
 * Summary of the License:
 * - You are free to:
 *     - Share: Copy and redistribute the software in any medium or format.
 *     - Adapt: Remix, transform, and build upon the software.
 *
 * Under the following terms:
 * - Attribution:
 *     - You must give appropriate credit to the original author (RaftDev) and provide a reference to the license.
 * - Non-Commercial:
 *     - You may not use this software for commercial purposes.
 * - Share-Alike:
 *     - If you remix, transform, or build upon this software, you must distribute your contributions under the same license as the original.
 *
 * For detailed license terms and conditions, please refer to the included License file or visit: https://creativecommons.org/licenses/by-nc-sa/4.0/legalcode
 *
 * © 2023 RaftDev. All Rights Reserved.
 * Crafted-Launcher is a project by RaftDev and is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
 */

package be.raft.launcher.file.loader;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class JsonStreamLoader extends FileLoader<JsonElement> {
    private final InputStream stream;

    public JsonStreamLoader(InputStream stream) {
        super(null);
        this.stream = stream;
    }

    @Override
    public void createFile() {
        throw new IllegalStateException("Cannot create file for an input stream!");
    }

    @Override
    public boolean fileExists() {
        return this.stream != null;
    }

    @Override
    public JsonElement load() {
        try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
            return JsonParser.parseReader(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(JsonElement value) {
        throw new IllegalStateException("Cannot save file for an input stream!");
    }

    public InputStream getStream() {
        return stream;
    }
}
