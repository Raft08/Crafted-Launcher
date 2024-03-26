package be.raft.launcher.plugin.loader;

import be.raft.launcher.api.plugin.PluginClassLoader;
import be.raft.launcher.plugin.PluginInfo;
import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;

public class CraftedPluginClassLoader extends URLClassLoader implements PluginClassLoader {
    static {
        registerAsParallelCapable();
    }

    private final PluginInfo pluginInfo;
    private final Logger logger;
    private final Path sourceFile;
    private final GroupClassLoader groupLoader;

    private boolean allowsExternalUsage = true;

    public CraftedPluginClassLoader(@NotNull GroupClassLoader group, @NotNull Path sourceFile, @NotNull PluginInfo info) throws MalformedURLException {
        super(new URL[] {sourceFile.toUri().toURL()});

        Preconditions.checkNotNull(group);
        Preconditions.checkNotNull(sourceFile);
        Preconditions.checkNotNull(info);

        Preconditions.checkArgument(sourceFile.toFile().isFile(), "Plugin file could not be found!");

        this.pluginInfo = info;
        this.logger = LoggerFactory.getLogger(this.pluginInfo.id());
        this.sourceFile = sourceFile;
        this.groupLoader = group;

        group.addModuleLoader(this);
    }

    public Class<?> loadClass(String name, boolean resolve, boolean checkRoot, boolean checkModules) throws ClassNotFoundException {
        try {
            return super.loadClass(name, resolve);
        } catch (ClassNotFoundException ignored) {}

        if (checkRoot) {
            try {
                return this.groupLoader.loadClass(name, resolve, true, false);
            } catch (ClassNotFoundException ignored) {}
        }

        if (checkModules) {
            try {
                return this.groupLoader.loadClass(name, resolve, false, true);
            } catch (ClassNotFoundException ignored) {}
        }

        throw new ClassNotFoundException(name);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return this.loadClass(name, resolve, true, true);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return this.loadClass(name, false);
    }

    @Override
    public PluginInfo meta() {
        return this.pluginInfo;
    }

    @Override
    public Logger logger() {
        return this.logger;
    }

    @Override
    public boolean allowsExternalClasspathUsage() {
        return this.allowsExternalUsage;
    }

    @Override
    public void allowsExternalClasspathUsage(boolean allow) {
        this.allowsExternalUsage = allow;
    }

    public Path getSourceFile() {
        return sourceFile;
    }

    public GroupClassLoader getGroupLoader() {
        return groupLoader;
    }
}
