package com.notmarra.notcore;

import com.notmarra.notcore.plugin.CheckUpdate;
import org.bukkit.plugin.java.JavaPlugin;

public final class NotCore extends JavaPlugin {

    public static NotCore instance;

    public static NotCore getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        CheckUpdate.checkUpdate();

        getLogger().info("Enabled successfully!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Disabled successfully!");
    }
}
