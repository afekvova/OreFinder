package ru.Finder;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ru.Finder.Listener.Finder;

public class Main extends JavaPlugin {
	
	private static Main instance;
	public static String prefix;
	public static String formatfindmessage;
	public static String formatnofindmessage;
	public static int range;
	public static int cooldown;
	public static boolean workcooldown;
	
	public void onEnable() {
		prefix = getConfig().getString("Prefix");
        cooldown = getConfig().getInt("cooldowntime");
		workcooldown = getConfig().getBoolean("cooldown");
		formatfindmessage = getConfig().getString("formatfindmessage");
		formatnofindmessage = getConfig().getString("formatnofindmessage");
		range = getConfig().getInt("range");
		instance = this;
		
		saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new Finder(), this);
	}
	
	public static Main getInctance() {
		return instance;
	}
}
