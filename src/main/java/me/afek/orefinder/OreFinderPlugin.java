package me.afek.orefinder;

import lombok.Getter;
import me.afek.orefinder.config.Settings;
import me.afek.orefinder.listener.FinderListener;
import me.afek.orefinder.utils.StringHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class OreFinderPlugin extends JavaPlugin {

    @Getter
    private static OreFinderPlugin instance;
    private CooldownManager cooldownmanager;

    public void onEnable() {
        instance = this;
        Settings.reload(new File(this.getDataFolder(), "config.yml"));
        this.cooldownmanager = new CooldownManager();
        Bukkit.getPluginManager().registerEvents(new FinderListener(this.cooldownmanager), this);
        getCommand("orefinder").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("orefinder.command.reload"))
            return false;

        if (args.length == 0 || !args[0].equalsIgnoreCase("reload")) {
            sender.sendMessage(StringHelper.color("&cUsing: /orefinder reload"));
            return true;
        }

        Settings.reload(new File(this.getDataFolder(), "config.yml"));
        sender.sendMessage(StringHelper.color("&aConfig is reloaded!"));
        return true;
    }

    @Override
    public void onDisable() {
        this.cooldownmanager.clearAll();
    }
}
