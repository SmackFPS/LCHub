package com.arrayprolc.event;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.arrayprolc.menu.Menu;
import com.arrayprolc.reward.Reward;

public class TreasureChest {
	Player p;
	Location loc;
	boolean done = false;
	Reward[] rewards;

	public TreasureChest(Player pl, Reward[] r){
		p = pl;
		loc = new Location(pl.getWorld(), Math.round(pl.getLocation().getX()), Math.round(pl.getLocation().getY()), Math.round(pl.getLocation().getZ()));
		loc.setY(loc.getY() - 1);
		//		keepPlayerInside();
		spawnChest();
		keepPlayerInside();
		TreasureChestListener.currentlyEnabled.add(pl.getUniqueId());
		rewards = r;
	}

	@SuppressWarnings("deprecation")
	public void spawnChest(){
		Bukkit.getServer().getScheduler().runTaskTimer(Bukkit.getPluginManager().getPlugin("HubPlugin"), new BukkitRunnable(){
			int ticks = 0;
			boolean ran = false;
			public void run(){
				if(done || ticks > 5){
					if(!ran) Bukkit.getServer().getScheduler().cancelTask(this.getTaskId());
				}
				if(!done && !ran){ this.runTask(Bukkit.getPluginManager().getPlugin("HubPlugin")); ran = true; }
				if(!done){
				ticks++;
				switch(ticks){
				case 1: generateCircle(loc, 1, Material.PACKED_ICE, (byte)0); return;
				case 2: return;
				case 3: generateCircle(loc, 2, Material.SNOW_BLOCK, (byte)0); return;
				case 4: generateCircle(loc.clone().add(0, 1, 0), 2, Material.LEAVES, (byte)0); return;
				case 5: {
					for(Player p2 : Bukkit.getOnlinePlayers())	p2.sendBlockChange(loc.clone().add(1, 1, 0), Material.CHEST, (byte)0);  for(Player p2 : Bukkit.getOnlinePlayers()) sendBreak(p2, Material.CHEST, loc.clone().add(1, 1, 0));
					for(Player p2 : Bukkit.getOnlinePlayers())	p2.sendBlockChange(loc.clone().add(-1, 1, 0), Material.CHEST, (byte)0);  for(Player p2 : Bukkit.getOnlinePlayers()) sendBreak(p2, Material.CHEST, loc.clone().add(-1, 1, 0));
					for(Player p2 : Bukkit.getOnlinePlayers())	p2.sendBlockChange(loc.clone().add(0, 1, 1), Material.CHEST, (byte)0); for(Player p2 : Bukkit.getOnlinePlayers()) sendBreak(p2, Material.CHEST, loc.clone().add(0, 1, 1));
					for(Player p2 : Bukkit.getOnlinePlayers())	p2.sendBlockChange(loc.clone().add(0, 1, -1), Material.CHEST, (byte)0);  for(Player p2 : Bukkit.getOnlinePlayers()) sendBreak(p2, Material.CHEST, loc.clone().add(0, 1, -1));
					giveItems();
					ticks = 6;
				}
				}
				}

			}
		}, 0, 20*1);
	}

	public void fireFirework(Location loc){
		final Firework fw = (Firework)loc.getWorld().spawn(loc, Firework.class);
		FireworkEffect effect = FireworkEffect.builder().trail(true).flicker(false).withColor(Color.AQUA, Color.PURPLE).with(FireworkEffect.Type.BURST).build();
		FireworkMeta fwm = fw.getFireworkMeta();

		fwm.clearEffects();
		fwm.addEffect(effect);
		fwm.setPower(0);
		fw.setFireworkMeta(fwm);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("HubPlugin"), new Runnable(){
			public void run(){ fw.detonate(); }
		}, 4);
	}

	@SuppressWarnings("deprecation")
	public void keepPlayerInside(){

		Bukkit.getServer().getScheduler().runTaskTimer(Bukkit.getPluginManager().getPlugin("HubPlugin"), new BukkitRunnable(){
			int ticks = 0;
			boolean ran = false;
			public void run(){
				if(done){
					Bukkit.getServer().getScheduler().cancelTask(this.getTaskId());
				}
				if(!done && !ran){ this.runTask(Bukkit.getPluginManager().getPlugin("HubPlugin")); ran = true; }
				if(!done){
				ticks++;
				
				if(p.getLocation().distance(loc) > 2){
					p.teleport(loc.clone().add(0.5, 1, 0.5));
				}
				for(Player p2 : Bukkit.getOnlinePlayers()){
					if(p != p2){
						if(p2.getLocation().distance(loc) < 3){
							p2.setVelocity(p2.getLocation().getDirection().multiply(-1.5));
						}
					}
				}
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

	public static ArrayList<Location> generateCircle(Location loc, Integer r, Material m, byte data) {
		ArrayList<Location> amount = new ArrayList<Location>();
		int total = 0;
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		for (double i = 0.0; i < 360.0; i += 1) {
			double angle = i * Math.PI / 180;
			x = round(loc.getBlockX() + r * Math.cos(angle));
			z = round(loc.getBlockZ() + r * Math.sin(angle));

			//  loc.getWorld().getBlockAt(x, y, z).setType(m);
			//  loc.getWorld().getBlockAt(x, y, z).setData(data);
			for(Player p2 : Bukkit.getOnlinePlayers()) p2.sendBlockChange(loc.getWorld().getBlockAt(x, y, z).getLocation(), m, data);

			if(!amount.contains(loc.getWorld().getBlockAt(x, y, z).getLocation())){
				total++;
				amount.add(loc.getWorld().getBlockAt(x,y,z).getLocation());
			}

		}
		for(Location loc3 : amount.toArray(new Location[amount.size()])){
			for(Player p2 : Bukkit.getOnlinePlayers())  sendBreak(p2, m, loc3);
		}
		return amount;
	}

	static int round(double r){
		return (int) Math.round(r);
	}

	@SuppressWarnings("deprecation")
	public void giveItems(){
		Bukkit.getServer().getScheduler().runTaskTimer(Bukkit.getPluginManager().getPlugin("HubPlugin"), new BukkitRunnable(){
			int ticks = 0;
			boolean ran = false;
			public void run(){
				if(done){
					Bukkit.getServer().getScheduler().cancelTask(this.getTaskId());
				}
				if(!done && !ran){ this.runTask(Bukkit.getPluginManager().getPlugin("HubPlugin")); ran = true; }
				if(!done){
				ticks++;
				if(ticks % 3 == 0 && ticks < 10) fireFirework(loc.clone().add(1, 3, 0));
				if(ticks % 3 == 0 && ticks < 10) fireFirework(loc.clone().add(-1, 3, 0));
				if(ticks % 3 == 0 && ticks < 10) fireFirework(loc.clone().add(0, 3, 1));
				if(ticks % 3 == 0 && ticks < 10) fireFirework(loc.clone().add(0, 3, -1));
				giveRewards();
				if(ticks > 20*7){
					for(int x = -5; x < 5; x++){
						for(int y = -5; y < 5; y++){
							for(int z = -5; z < 5; z++){
								Location n = loc.clone().add(x, y, z);
								for(Player p : Bukkit.getOnlinePlayers()) p.sendBlockChange(n, n.getBlock().getType(), n.getBlock().getData());
							}
						}
					}
					done();

				}
				}

			}
		}, 0, 1);
	}
	public void done(){

		if(!done){
			for(Player p2 : Bukkit.getOnlinePlayers()) sendBreak(p2, Material.CHEST, loc.clone().add(1, 1, 0));
			for(Player p2 : Bukkit.getOnlinePlayers()) sendBreak(p2, Material.CHEST, loc.clone().add(-1, 1, 0));
			for(Player p2 : Bukkit.getOnlinePlayers()) sendBreak(p2, Material.CHEST, loc.clone().add(0, 1, 1));
			for(Player p2 : Bukkit.getOnlinePlayers()) sendBreak(p2, Material.CHEST, loc.clone().add(0, 1, -1));
			TreasureChestListener.currentlyEnabled.remove(p.getUniqueId());
		}
		done = true;
	}
	boolean rewardsDone = false;

	public void giveRewards(){
		if(rewardsDone) return;
		rewardsDone = true;
		int i = 0;
		Menu m = new Menu("§0Rewards", 9);
		m.displayMenu(p);
		for(Reward reward : rewards){
			reward.giveReward();
			m.addItem(reward.displayItem, i);
			i++;
		}
	}

}
