package com.notmarra.notcore;

import com.notmarra.notcore.plugin.CheckUpdate;
import com.notmarra.notcore.utils.File;
import com.notmarra.notcore.utils.Updater;
import org.bukkit.plugin.java.JavaPlugin;

public final class NotCore extends JavaPlugin {

    public Updater Updater;
    public File File;

    @Override
    public void onEnable() {
        instance = this;
        Updater = new Updater();
        File = new File();

        CheckUpdate.checkUpdate();

        getLogger().info("Enabled successfully!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Disabled successfully!");
    }

    public static NotCore instance;

    public static NotCore getInstance() {
        return instance;
    }


}
