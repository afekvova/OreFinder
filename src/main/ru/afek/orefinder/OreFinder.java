package ru.afek.orefinder;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import ru.afek.orefinder.config.Settings;
import ru.afek.orefinder.listener.FinderListener;
import ru.afek.orefinder.utils.StringHelper;

public class OreFinder extends JavaPlugin {

	private static OreFinder instance;
	private CooldownManager cooldownmanager;

	public void onEnable() {
		instance = this;
		Settings.reload(new File("plugins/OreFinder", "config.yml"));
		this.cooldownmanager = new CooldownManager();
		Bukkit.getPluginManager()
				.registerEvents(new FinderListener(cooldownmanager), this);
		getCommand("orefinder").setExecutor(this);
	}

	public static OreFinder getInstance() {
		return OreFinder.instance;
	}

	public CooldownManager getCooldownManager() {
		return cooldownmanager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (!sender.hasPermission("orefinder.command.reload")) {
			return false;
		}

		if (args.length == 0 || !args[0].equalsIgnoreCase("reload")) {
			sender.sendMessage(
					StringHelper.color("&cUsing: /orefinder reload"));
			return true;
		}

		Settings.reload(new File("plugins/OreFinder", "config.yml"));
		sender.sendMessage(StringHelper.color("&aConfig is reloaded!"));
		return true;
	}

	@Override
	public void onDisable() {
		this.cooldownmanager.clearAll();
	}
}
