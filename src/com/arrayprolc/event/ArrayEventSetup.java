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
	
	public static void setupEvents(){
		PluginManager pm = Bukkit.getServer().getPluginManager();
		plugin = Main.getInstance();
		pm.registerEvents(new ClickInventory(), plugin);
		pm.registerEvents(new TabHeaderSetup(), plugin);
		pm.registerEvents(new BuildListener(), plugin);
		pm.registerEvents(new TreasureChestListener(), plugin);
		pm.registerEvents(new MenuListener(), plugin);
		pm.registerEvents(new PlayerChat(), plugin);
		pm.registerEvents(new Speedways(), plugin);
		pm.registerEvents(new VillagerSpawn(), plugin);
		for(World w : Bukkit.getWorlds()) w.setGameRuleValue("reducedDebugInfo", "true");
		for(World w : Bukkit.getWorlds()) w.setGameRuleValue("doDaylightCycle", "false");
		TPSMeter.setupTPSMeter();
		
		pm.registerEvents(new ParticleMenu(), plugin);
		pm.registerEvents(new TreasureChestManager(), plugin);
	}
}
//