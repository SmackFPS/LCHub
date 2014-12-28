package com.arrayprolc.event;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.lightcraftmc.hub.main.Main;

public class BuildListener implements Listener {

	public Main plugin;

	public BuildListener(){
		plugin = Main.getInstance();
	}
	
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e){
		if(e.getPlayer().getGameMode() != GameMode.CREATIVE){
			e.setCancelled(true);
		}
	}

}
