package ru.Finder;

import org.bukkit.plugin.java.*;
import org.bukkit.*;
import ru.Finder.Listener.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;

public class Main extends JavaPlugin
{
    private static Main instance;
    public static String prefix;
    public static String formatfindmessage;
    public static String formatnofindmessage;
    public static int range;
    public static int cooldown;
    public static boolean workcooldown;
    
    public void onEnable() {
        Main.prefix = this.getConfig().getString("Prefix");
        Main.cooldown = this.getConfig().getInt("cooldowntime");
        Main.workcooldown = this.getConfig().getBoolean("cooldown");
        Main.formatfindmessage = this.getConfig().getString("formatfindmessage");
        Main.formatnofindmessage = this.getConfig().getString("formatnofindmessage");
        Main.range = this.getConfig().getInt("range");
        (Main.instance = this).saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents((Listener)new Finder(), (Plugin)this);
    }
    
    public static Main getInctance() {
        return Main.instance;
    }
}
