package com.arrayprolc.event;

import java.util.Random;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class VillagerSpawn implements Listener {

	Main plugin;

	public VillagerSpawn(Main instance){
		plugin = instance;
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(instance, new Runnable(){
			public void run(){
				for(World w : Bukkit.getWorlds()){
					if(amountOfVillagers(w) < 15){
					Location loc = w.getSpawnLocation();
					loc.setY(151);
					Location loc2 = getRandomLocation(loc);
					Villager v = loc2.getWorld().spawn(loc2, Villager.class);
					v.setCustomName("§7" + NameList.getNames()[randInt(0, NameList.getNames().length-1)] + "the Villager");
					}
				}
			}
		}, 0, 10);
	}

	@EventHandler
	public void villagerAttacked(EntityDamageByEntityEvent e){
		if(e.getDamager() instanceof Player && e.getEntity() instanceof Villager){
			Player damager = (Player) e.getDamager();
			final Villager entity = (Villager) e.getEntity();
			if(!entity.getCustomName().contains("the Villager")) { return; }
			final String currentName = entity.getCustomName();
			String[] ow = { "Ouch!", "That hurt!", "Hey, stop it!", "What did I do?", "Ouch!", "Stop!", "That hurts!", "Stop that!", "Owww"};
			String newName = ow[randInt(0, ow.length-1)];
			entity.setCustomName("§7" + newName);
			damager.sendMessage("§7" + currentName + ": " + newName);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
				public void run(){
					entity.setCustomName(currentName);
					entity.setCustomNameVisible(true);
				}
			}, 20*3);
		}
	}

	Random rand = new Random();
	int randInt(int min, int max) {
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public int amountOfVillagers(World w){
		int i = 0; for(Entity e : w.getEntities()){ if(e instanceof Villager){ i++;} }return i;
	}
	
	public Location getRandomLocation(Location origin){
		Location o2 = origin.add(randInt(-15, 15), 0, randInt(-15, 15));
		return o2;
	}
	@EventHandler(ignoreCancelled = true)
	public void onEntityDamage(EntityDamageEvent e){
		if(e.getCause().equals(DamageCause.VOID)){
			return;
		}
		if(e.getEntity() instanceof Player){
			return;
		}
		e.getEntity().setVelocity(e.getEntity().getLocation().getDirection().multiply(-0.8D));
		e.getEntity().playEffect(EntityEffect.HURT);
		((LivingEntity) e.getEntity()).setHealth(randInt(0, 5));
		for(Entity en : e.getEntity().getNearbyEntities(5, 5, 5)){
			if(en instanceof Player){
				Player target = (Player) en;
				target.playSound(e.getEntity().getLocation(), Sound.HURT_FLESH, 1, 1);
				
				e.getEntity().getWorld().playEffect(e.getEntity().getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
				
			}
		}
		if(e.getEntity() instanceof Villager)
			e.setCancelled(true);
	}
}
