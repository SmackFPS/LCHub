package com.arrayprolc.event;

import me.mike1665.Main.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.arrayprolc.rank.RankManager;

public class PlayerChat implements Listener {

	public Main plugin;

	public PlayerChat(Main instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onPLayerChat(AsyncPlayerChatEvent e){
		String format = RankManager.getFormat(RankManager.getRank(e.getPlayer()));
		format = format.replace("[name]", e.getPlayer().getName());
		format = format.replace("[message]", e.getMessage());
		String format2 = RankManager.getColor(RankManager.getRank(e.getPlayer()));
		e.getPlayer().setPlayerListName(format2 + e.getPlayer().getName());
		e.setFormat(format);
		
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		String format = RankManager.getColor(RankManager.getRank(e.getPlayer()));
		e.getPlayer().setPlayerListName(format + e.getPlayer().getName());
	}


}
