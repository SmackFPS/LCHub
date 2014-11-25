package com.arrayprolc.event;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;

import me.mike1665.Main.Main;

public class ArrayEventSetup {

	static Main plugin;
	
	public static void setupEvents(Main instance){
		PluginManager pm = Bukkit.getServer().getPluginManager();
		plugin = instance;
		pm.registerEvents(new ClickInventory(plugin), plugin);
		pm.registerEvents(new TabHeaderSetup(plugin), plugin);
		pm.registerEvents(new BuildListener(plugin), plugin);
		pm.registerEvents(new TreasureChestListener(plugin), plugin);
		for(World w : Bukkit.getWorlds()) w.setGameRuleValue("reducedDebugInfo", "true");
		for(World w : Bukkit.getWorlds()) w.setGameRuleValue("doDaylightCycle", "false");
	}
	
}
