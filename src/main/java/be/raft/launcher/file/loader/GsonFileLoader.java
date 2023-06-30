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

import com.google.gson.Gson;

import java.io.File;
import java.lang.reflect.Type;

public class GsonFileLoader<T> extends FileLoader<T> {
    private final Gson gson;
    private final Type type;

    public GsonFileLoader(File file, Type type) {
        super(file);
        this.gson = new Gson();
        this.type = type;
    }

    public GsonFileLoader(File file, Gson gson, Type type) {
        super(file);
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T load() {
        return this.gson.fromJson(new StringFileLoader(this.file).load(), this.type);
    }

    @Override
    public void save(T value) {
        new StringFileLoader(this.file).save(this.gson.toJson(value));
    }
}
