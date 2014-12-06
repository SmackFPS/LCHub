package com.arrayprolc.event;

import java.util.Random;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.util.Vector;

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
		for(World w : Bukkit.getWorlds()) w.setGameRuleValue("reducedDebugInfo", "true");
		for(World w : Bukkit.getWorlds()) w.setGameRuleValue("doDaylightCycle", "false");
		
	}
}
//