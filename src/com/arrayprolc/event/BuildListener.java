package com.arrayprolc.event;

import me.mike1665.Main.Main;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.arrayprolc.bountifulupdate.BUtils;
import com.arrayprolc.strings.MessageType;
import com.arrayprolc.strings.StringManager;

public class BuildListener implements Listener {

	public Main plugin;

	public BuildListener(Main instance){
		plugin = instance;
	}
	
	@EventHandler
	public void place(BlockPlaceEvent e){
		if(e.getPlayer().getGameMode() != GameMode.CREATIVE){
			e.setCancelled(true);
			BUtils.sendActionBar(e.getPlayer(), StringManager.getMessage("Please do not place blocks!", MessageType.ERROR));
		}
	}
	@EventHandler
	public void place(BlockBreakEvent e){
		if(e.getPlayer().getGameMode() != GameMode.CREATIVE){
			e.setCancelled(true);
			BUtils.sendActionBar(e.getPlayer(), StringManager.getMessage("Please do not break blocks!", MessageType.ERROR));
		}
	}

}
