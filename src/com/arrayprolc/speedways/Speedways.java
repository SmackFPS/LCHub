package com.arrayprolc.speedways;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
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
		Block b = e.getPlayer().getLocation().getBlock();
		boolean b2 = false;

		if(b.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.REDSTONE_BLOCK)) b2 = true;

		if(b.getLocation().add(0, 0, 1).getBlock().getType().equals(Material.RAILS) || b.getLocation().add(0, 0, -1).getBlock().getType().equals(Material.RAILS) || b.getLocation().add(1, 0, 0).getBlock().getType().equals(Material.RAILS) || b.getLocation().add(-1, 0, 0).getBlock().getType().equals(Material.RAILS)){
			if(!b2){
				e.getPlayer().setWalkSpeed(0.6F);
			}else{
				e.getPlayer().setWalkSpeed(1.2F);
			}
			return;
		}

		e.getPlayer().setWalkSpeed(0.2F);
	}

}
