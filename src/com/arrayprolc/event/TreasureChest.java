package com.arrayprolc.event;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TreasureChest {
	Player p;
	Location loc;
	boolean done = false;

	public TreasureChest(Player pl){
		p = pl;
		loc = pl.getLocation().getBlock().getLocation();
		keepPlayerInside();
		spawnChest();
	}

	@SuppressWarnings("deprecation")
	public void spawnChest(){
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("HubPlugin"), new BukkitRunnable(){
			int ticks = 0;
			public void run(){
				if(done || ticks > 5){
					this.cancel();
					return;
				}
				ticks++;
				switch(ticks){
				case 1: 
					for(int x = -2; x < 2; x++){
						for(int z = -2; z < 2; z++){
							Location loc2 = loc;
							loc2 = loc2.add(x, -1, z);
							p.sendBlockChange(loc2, Material.STONE, (byte)0);
							sendBreak(p, Material.STONE, loc2);
						}
					}
				case 2:
				case 3:
				case 4:
				case 5:
				}

			}
		}, 0, 20*2);
	}

	@SuppressWarnings("deprecation")
	public void keepPlayerInside(){

		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("HubPlugin"), new BukkitRunnable(){
			int ticks = 0;
			public void run(){
				if(done){
					this.cancel();
					return;
				}
				ticks++;
				if(p.getLocation().distance(loc) > 1){
					p.teleport(loc);
				}

			}
		}, 0, 1);
	}

	@SuppressWarnings("deprecation")
	public static void sendBreak(Player p, Material m, Location loc)
	{
		p.getWorld().playEffect(loc, Effect.STEP_SOUND, m.getId());
	}

}
