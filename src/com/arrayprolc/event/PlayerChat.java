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
		if(e.getMessage().toLowerCase().contains("#CheckTPS")){
			e.setCancelled(true);
			e.getPlayer().sendMessage(TPSMeter.getServerTPS());
		}
		
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		String format = RankManager.getColor(RankManager.getRank(e.getPlayer()));
		e.getPlayer().setPlayerListName(format + e.getPlayer().getName());
	}


}
