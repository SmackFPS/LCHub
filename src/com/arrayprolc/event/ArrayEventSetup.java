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
		setupFountain(instance);
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
	static int ticks = 0;
	public static void setupFountain(Main instance){
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(instance, new Runnable(){
			public void run(){
				ticks++;
				for(World w : Bukkit.getWorlds()){
					Location fountain = new Location(w, 38, 150, 81);
					
					if(ticks % 2 == 0){
						ticks = 0;
						Material m = Material.STAINED_GLASS; 
						spawnRandomFallingSand(m, (byte)3, fountain);
						/*Random rand = new Random();
						int i = rand.nextInt(1);
						
						switch(i){
						case 0: spawnRandomFallingSand(m, (byte)3, fountain);
						case 1: spawnRandomFallingSand(m, (byte)9, fountain);
						}*/
					}
					for(Entity e : fountain.getWorld().getEntities()) if(e instanceof FallingBlock){
						FallingBlock b = (FallingBlock)e;
						for(int i = 0; i < 3; i++){
							Location loc = b.getLocation().add(0, -i, 0);
							if(loc.getBlock().getType() != Material.AIR){
								if(b.getTicksLived() > 5) b.remove();
							} 
						}
					}
					return;
				}

			}
		}, 0, 1);
	}
	static Random rand = new Random();
	static void spawnRandomFallingSand(Material type, byte data, Location loc){
	//	if(!new Location(loc.getWorld(), 38, 146, 78).getBlock().getType().equals(Material.REDSTONE_LAMP_ON)) return;
		@SuppressWarnings("deprecation")
		FallingBlock fall = loc.getWorld().spawnFallingBlock(loc, type, data);
		fall.setDropItem(false);
		fall.setVelocity(new Vector((rand.nextDouble()-0.5)/8, rand.nextDouble()*1.2, (rand.nextDouble()-0.5)/8));
	}
	
	@SuppressWarnings("deprecation")
	public static void breakBlockParticles(Block b){
		for(Player p : Bukkit.getOnlinePlayers()){
			p.playEffect(b.getLocation(), Effect.STEP_SOUND, b.getType().getId());
		}
	}
}
//