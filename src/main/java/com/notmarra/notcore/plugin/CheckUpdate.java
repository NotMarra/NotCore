package com.notmarra.notcore.plugin;

import com.notmarra.notcore.NotCore;
import com.notmarra.notcore.utils.Updater;
import org.bukkit.plugin.Plugin;

public class CheckUpdate {
    public static void checkUpdate() {

        Plugin pl = NotCore.getInstance();
        Updater updater = new Updater();
        String url = "https://api.github.com/repos/NotMarra/NotCore/releases/latest";
        String pluginURL = "https://github.com/NotMarra/NotCore/releases";

        if (pl.getDescription().getVersion().contains("SNAPSHOT")) {
            pl.getLogger().warning("You are running on a snapshot build of " + pl.getName() + "!");
        } else if (pl.getDescription().getVersion().contains("DEV")) {
            pl.getLogger().warning("You are running on a development build of " + pl.getName() + "!");
        } else {
            int update = updater.isUpToDate(pl.getDescription().getVersion(), url);

            if (update == 2) {
                pl.getLogger().warning("Failed to fetch version!");
            } else if (update == 1) {
                NotCore.getInstance().getLogger().info("You are running the latest version of " + pl.getName() + "!");
            } else if (update == 0) {
                pl.getLogger().warning("-----------------------------------------------------");
                pl.getLogger().warning("There is a new version of " + pl.getName() + " available!");
                pl.getLogger().warning("You are running on version v" + pl.getDescription().getVersion() + ", the latest version is v" + updater.getLatestVersion(url) + ".");
                pl.getLogger().warning("Download it from " + pluginURL);
                pl.getLogger().warning("-----------------------------------------------------");
            }
        }
    }
}
