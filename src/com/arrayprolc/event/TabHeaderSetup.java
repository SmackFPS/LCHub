package com.arrayprolc.event;

import me.mike1665.Main.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.arrayprolc.bountifulupdate.BUtils;

public class TabHeaderSetup implements Listener {

	public Main plugin;

	public TabHeaderSetup(Main instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		BUtils.sendHeaderAndFooter(e.getPlayer(), "§9Light§bCraft §3Network", "§7We can change this message later.");
	}

}
