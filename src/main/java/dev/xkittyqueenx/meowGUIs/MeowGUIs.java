package dev.xkittyqueenx.meowGUIs;

import org.bukkit.plugin.java.JavaPlugin;

public final class MeowGUIs extends JavaPlugin {

    private static JavaPlugin plugin;

    private static GUIManager guiManager;

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static GUIManager getGuiManager() {
        return guiManager;
    }

    @Override
    public void onEnable() {
        plugin = this;
        guiManager = new GUIManager();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
