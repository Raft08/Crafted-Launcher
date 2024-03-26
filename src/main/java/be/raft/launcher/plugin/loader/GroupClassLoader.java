package be.raft.launcher.plugin.loader;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GroupClassLoader extends ClassLoader {
    private final ClassLoader rootClassLoader;
    private final List<CraftedPluginClassLoader> pluginClassLoaders;

    public GroupClassLoader(@NotNull ClassLoader rootClassLoader) {
        Preconditions.checkNotNull(rootClassLoader);

        this.rootClassLoader = rootClassLoader;
        this.pluginClassLoaders = new ArrayList<>();
    }

    public void addModuleLoader(@NotNull CraftedPluginClassLoader loader) {
        Preconditions.checkNotNull(loader);

        this.pluginClassLoaders.add(loader);
    }

    public Class<?> loadClass(String name, boolean resolve, boolean checkRoot, boolean checkModules) throws ClassNotFoundException {
        if (checkRoot) {
            try {
                return this.rootClassLoader.loadClass(name);
            } catch (ClassNotFoundException ignored) {}
        }

        if (checkModules) {
            for (CraftedPluginClassLoader loader : pluginClassLoaders) {
                if (!loader.allowsExternalClasspathUsage())
                    continue;

                try {
                    return loader.loadClass(name, resolve, false, false);
                } catch (ClassNotFoundException ignored) {}
            }
        }

        throw new ClassNotFoundException(name);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }
}
