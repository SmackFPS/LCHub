package com.lightcraftmc.event;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.lightcraftmc.bountifulupdate.BUtils;
import com.lightcraftmc.coin.Multiplier;
import com.lightcraftmc.hub.main.Main;
import com.lightcraftmc.rank.RankManager;
import com.lightcraftmc.rank.ServerRank;

public class TabHeaderSetup implements Listener {

	public Main plugin;
	public TabHeaderSetup(){
		plugin = Main.getInstance();
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		BUtils.sendHeaderAndFooter(e.getPlayer(), "§9Light§bCraft §3Network", "§7Buy Ranks, Gadgets and more at store.lightcraftmc.com");
		String sub = "";
		if(Multiplier.coin(e.getPlayer()) > 1){
			sub = sub + "§a" + Multiplier.coin(e.getPlayer()) + "x §aCoins";
		}
		if(Multiplier.token(e.getPlayer()) > 1){
			if(sub.equals("")){
			sub = sub + "§a" + Multiplier.coin(e.getPlayer()) + "x §dTokens";
			}else{
				sub = sub + "§7 ▪ §a" + Multiplier.coin(e.getPlayer()) + "x §dTokens";
			}
		}
		BUtils.sendTitle(e.getPlayer(), "", sub, 5, 5, 5);
		e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
		e.getPlayer().setGameMode(GameMode.ADVENTURE);
		sendBlankSpace(e.getPlayer());
		if(RankManager.hasRank(e.getPlayer(), ServerRank.MVP)){
			Bukkit.broadcastMessage(RankManager.getFormat(RankManager.getRank(e.getPlayer())) + " §ajoined the lobby.");
		}
		e.setJoinMessage(null);
	}
	
	public void sendBlankSpace(Player p){
		for(int i = 0; i < 25*4; i++){
			p.sendMessage("");
		}
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e){
		e.setQuitMessage(null);
	}
	
}
