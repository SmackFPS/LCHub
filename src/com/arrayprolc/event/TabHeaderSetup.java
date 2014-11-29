package com.arrayprolc.event;

import me.mike1665.Main.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.arrayprolc.bountifulupdate.BUtils;
import com.arrayprolc.coin.Multiplier;

public class TabHeaderSetup implements Listener {

	public Main plugin;

	public TabHeaderSetup(Main instance){
		plugin = instance;
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
	}

}
