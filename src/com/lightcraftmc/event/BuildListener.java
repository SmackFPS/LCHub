package com.lightcraftmc.event;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
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
	
	@EventHandler
	public void click(InventoryClickEvent e){
		Bukkit.broadcastMessage(e.getSlot() + "");	
	}

}
