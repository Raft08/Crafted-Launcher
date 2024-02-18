<p align="center"><img src="./src/main/resources/assets/img/icon.png" width="150px" height="150px" alt="crafted launcher logo"></p>

<h1 align="center">Crafted Launcher</h1>

[<p align="center"><img src="https://img.shields.io/github/downloads/raft08/Crafted-Launcher/total.svg?style=for-the-badge" alt="downloads">](https://github.com/raft08/crafted-launcher/releases)

<p align="center">Easily create Minecraft Mod-Packs without any borders!</p>
<p align="center">/!\ Launcher is still under expensive development, expect bugs or unfinished features! /!\</p>


## Planned Features

* 📀 **Launcher Plugins/Extensions.**
    * Other authentication system's than Microsoft.
    * Third-party clients (Like (Neo)Forge or Fabric).
    * Other mod distribution platforms than CurseForge or Modrinth.
* 🛜 **Offline Editing.**
    * Launch your game without requiring an internet connection.
    * Creation of offline profile creating with already downloaded versions.
    * Launcher automatically reduce bandwidth usage on metered connexions. 
* 💾 **Launcher Wide Files.**
    * Make your files apply on every profile, from options to resources/shader packs.
* ✍️ **Configuration / Recipes Editor**
  * Human and Easily readable configuration editor
  * Edit/Delete/Create recipes with a nice UI.
* ⬇️ **Automatic Updates**.
  * No annoying update popups, updates are installed in the background.

#### Want to support? Leave a ⭐ star on the repository!

## Development
**Want to create a plugin or extension for the launcher?**

Here is a quick guide to get you started!

### Build Tool Configuration

Gradle :
````groovy
repositories {
    maven {
        name = "AtlasWorld Repo"
        url = "https://repository.atlasworld.fr/repository/maven-public/"
    }
}

dependencies {
    compileOnly "be.raft.launcher:crafted-api:${version}"
}
````
Maven :
````xml
<repositories>
    <repository>
        <id>atlasworld-repo</id>
        <name>AtlasWorld Repo</name>
        <url>https://repository.atlasworld.fr/repository/maven-public/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>be.raft.launcher</groupId>
        <artifactId>crafted-api</artifactId>
        <version>${version}</version>
        <scope>compile</scope>
    </dependency>
</dependencies>
````

### Plugin JSON
The plugin json defines the information about your plugin.

It should be stored in the /resources/plugin.json path.
````json5
{
  "name": "My First Plugin",
  "id": "my_first_plugin", // Must match regex [undefined]
  "main": "net.me.plugin.MyFirstPlugin", // Main Class of the plugin.
  "description": "This is my first plugin!", // Optional
  "authors": ["Me!"],
  "contributors": ["NicePerson"], // Optional
  "credit": "Thanks to a KindPerson", // Optional
  "categories": ["other"], // Many available categories are available (authentication, editor, mod-repo, ect..)
  "launcher_version": ["1.0.0", "1.1.0"], // API compatibility range (from 1.0.0 to 1.0.9)
  "requires_restart": false // Optional: Whether when enabling/disabling the plugin the launcher needs to restart
}
````
