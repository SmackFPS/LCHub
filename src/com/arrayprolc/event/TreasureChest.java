package com.arrayprolc.event;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.arrayprolc.sound.SoundManager;

public class TreasureChest {
	Player p;
	Location loc;
	boolean done = false;

	public TreasureChest(Player pl){
		p = pl;
		loc = new Location(pl.getWorld(), Math.round(pl.getLocation().getX()), Math.round(pl.getLocation().getY()), Math.round(pl.getLocation().getZ()));
//		keepPlayerInside();
		spawnChest();
	}

	@SuppressWarnings("deprecation")
	public void spawnChest(){
		for(int x = 0; x < 2; x++){
			for(int z = 0; z < 2; z++){
				Location loc2 = loc;
				loc2 = loc2.add(x, 0, z);

			}
		}
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("HubPlugin"), new BukkitRunnable(){
			int ticks = 0;
			public void run(){
				if(done || ticks > 5){
					this.cancel();
				}
				ticks++;
				switch(ticks){
				case 1: 

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
	
	@SuppressWarnings("deprecation")
	public void changeChestState(Location loc, boolean open) {
	    for (Player p : loc.getWorld().getPlayers()) {
	        p.playNote(loc, (byte) 1, (byte) (open ? 1 : 0));
	    }
	}

}
