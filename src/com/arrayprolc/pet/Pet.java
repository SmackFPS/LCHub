package com.arrayprolc.pet;

import me.mike1665.Main.Main;
import net.lightcraftmc.fusebox.event.ServerTickEvent;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Pet implements Listener {
	EntityType e;
	Player p;
	OfflinePlayer owner;
	Entity en;
	
	public Pet(EntityType entity, OfflinePlayer owner){
		Bukkit.getServer().getPluginManager().registerEvents(this, Main.getInstance());
		e = entity;
		this.owner = owner;
		if(owner.isOnline()){
			p = (Player) owner;
			spawn();
		}
	}
	
	public void spawn(){
		if(en != null || !en.isDead()){
			en.remove();
		}
		
		en = p.getWorld().spawnEntity(p.getLocation(), e);
		en.setCustomName("§b" + owner.getName() + "§7's §a" + WordUtils.capitalize(e.toString().replace("_", " ").toLowerCase()));
	}
	
	@EventHandler
	public void tick(ServerTickEvent e){
		if(en == null) return;
		if(en.isDead()) { spawn(); return; }
		if(en instanceof LivingEntity){
			LivingEntity e2 = (LivingEntity)en;
			e2.setHealth(20.0D);
		}
		if(!en.getWorld().getName().equals(p.getWorld().getName())){
			en.teleport(p);
		}
		if(en.getLocation().distance(p.getLocation()) > 10){
			en.teleport(p);
		}
		
	}
	

}
