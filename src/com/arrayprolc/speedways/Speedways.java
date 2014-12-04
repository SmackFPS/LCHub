package com.arrayprolc.speedways;

import me.mike1665.Main.Main;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Speedways implements Listener {
	
	Main plugin;
	
	public Speedways(Main instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		if(!e.getPlayer().getLocation().getBlock().getType().equals(Material.RAILS)) return;
		Block b = e.getPlayer().getLocation().getBlock();
		if(b.getLocation().add(1, 0, 0).getBlock().getType().equals(Material.RAILS) || b.getLocation().add(-1, 0, 0).getBlock().getType().equals(Material.RAILS)){
			/*double velocityX = e.getPlayer().getLocation().getDirection().getX();
			double velocityY = e.getPlayer().getLocation().getDirection().getY();
			e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().setX(e.getPlayer().getLocation().getDirection().getX() * 3));*/
			return;
		}
		if(b.getLocation().add(0, 0, 1).getBlock().getType().equals(Material.RAILS) || b.getLocation().add(0, 0, -1).getBlock().getType().equals(Material.RAILS)){
			double velocityZ = e.getPlayer().getLocation().getDirection().getX();
			double velocityY = e.getPlayer().getLocation().getDirection().getY();
			e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().setZ(e.getPlayer().getLocation().getDirection().getZ() * 3));
			return;
		}
	}

}
