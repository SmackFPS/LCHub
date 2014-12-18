package com.arrayprolc.event;

import me.mike1665.Main.Main;
import me.mike1665.chest.TreasureChestManager;
import me.mike1665.menu.ParticleMenu;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;

import com.arrayprolc.menu.MenuListener;
import com.arrayprolc.speedways.Speedways;

public class ArrayEventSetup {

	static Main plugin;
	
	public static void setupEvents(Main instance){
		PluginManager pm = Bukkit.getServer().getPluginManager();
		plugin = instance;
		pm.registerEvents(new ClickInventory(plugin), plugin);
		pm.registerEvents(new TabHeaderSetup(plugin), plugin);
		pm.registerEvents(new BuildListener(plugin), plugin);
		pm.registerEvents(new TreasureChestListener(plugin), plugin);
		pm.registerEvents(new MenuListener(plugin), plugin);
		pm.registerEvents(new PlayerChat(plugin), plugin);
		pm.registerEvents(new Speedways(plugin), plugin);
		pm.registerEvents(new VillagerSpawn(plugin), plugin);
		for(World w : Bukkit.getWorlds()) w.setGameRuleValue("reducedDebugInfo", "true");
		for(World w : Bukkit.getWorlds()) w.setGameRuleValue("doDaylightCycle", "false");
		TPSMeter.setupTPSMeter(instance);
		
		pm.registerEvents(new ParticleMenu(instance), plugin);
		pm.registerEvents(new TreasureChestManager(), plugin);
	}
}
//