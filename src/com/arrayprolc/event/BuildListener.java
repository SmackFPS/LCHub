package com.arrayprolc.event;

import me.mike1665.Main.Main;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class BuildListener implements Listener {

	public Main plugin;

	public BuildListener(Main instance){
		plugin = instance;
	}
	
	@EventHandler
	public void place(BlockPlaceEvent e){
		if(e.getPlayer().getGameMode() != GameMode.CREATIVE){
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void place(BlockBreakEvent e){
		if(e.getPlayer().getGameMode() != GameMode.CREATIVE){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e){
		if(e.getPlayer().getGameMode() != GameMode.CREATIVE){
			e.setCancelled(true);
		}
	}

}
