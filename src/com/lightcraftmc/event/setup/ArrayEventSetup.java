package com.lightcraftmc.event.setup;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import com.lightcraftmc.event.BuildListener;
import com.lightcraftmc.event.TPSMeter;
import com.lightcraftmc.event.TabHeaderSetup;
import com.lightcraftmc.event.TreasureChestListener;
import com.lightcraftmc.event.VillagerSpawn;
import com.lightcraftmc.event.handlers.menu.ClickInventory;
import com.lightcraftmc.fusebox.build.listener.BuildSettings;
import com.lightcraftmc.hub.main.Main;
import com.lightcraftmc.menu.ParticleMenu;
import com.lightcraftmc.rank.RankManager;
import com.lightcraftmc.speedways.Speedways;

public class ArrayEventSetup {

	static Main plugin;
	
	public static void setupEvents(){
		PluginManager pm = Bukkit.getServer().getPluginManager();
		plugin = Main.getInstance();
		pm.registerEvents(new ClickInventory(), plugin);
		pm.registerEvents(new TabHeaderSetup(), plugin);
		pm.registerEvents(new BuildListener(), plugin);
		pm.registerEvents(new TreasureChestListener(), plugin);
		pm.registerEvents(new Speedways(), plugin);
		pm.registerEvents(new VillagerSpawn(), plugin);
		for(World w : Bukkit.getWorlds()) w.setGameRuleValue("reducedDebugInfo", "true");
		for(World w : Bukkit.getWorlds()) w.setGameRuleValue("doDaylightCycle", "false");
		TPSMeter.setupTPSMeter();
		RankManager.init();
		pm.registerEvents(new ParticleMenu(), plugin);
		BuildSettings.disableServerHunger();
		BuildSettings.revokeBlockBreak(Bukkit.getWorlds().get(0).getName());
		removeEntities();
		
	}
	private static void removeEntities(){
		for(World w : Bukkit.getWorlds()) for(Entity e : w.getEntities()) if(!(e instanceof Player)) e.remove();
	}

	public static void disable(){
		
	}
}
//
