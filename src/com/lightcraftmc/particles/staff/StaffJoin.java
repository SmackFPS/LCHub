package com.lightcraftmc.particles.staff;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class StaffJoin implements Listener{
	
	@EventHandler
	public void staffJoin(PlayerJoinEvent e) {
		join(e.getPlayer());
	}
	
	public static boolean join(Player p){
		if (p.getName().equals("ArrayPro")) {
			com.lightcraftmc.extra.ExtraManager.Activate(p, com.lightcraftmc.effects.EffectManager.EffectType.Helix);
			return true;
		}
		if (p.getName().equals("mike1665")) {
			com.lightcraftmc.extra.ExtraManager.Activate(p, com.lightcraftmc.effects.EffectManager.EffectType.Helix);
			return true;
		}
		if (p.getName().equals("SmackFPS")) {
			com.lightcraftmc.extra.ExtraManager.Activate(p, com.lightcraftmc.effects.EffectManager.EffectType.Helix);
			return true;
		}
		if (p.getName().equals("JRL1004")) {
			com.lightcraftmc.extra.ExtraManager.Activate(p, com.lightcraftmc.effects.EffectManager.EffectType.Helix);
			return true;
		}
		if (p.getName().equals("Dablakbandit")) {
			com.lightcraftmc.extra.ExtraManager.Activate(p, com.lightcraftmc.effects.EffectManager.EffectType.Helix);
			return true;
		}
		if (p.getName().equals("OlsonElliot")) {
			com.lightcraftmc.extra.ExtraManager.Activate(p, com.lightcraftmc.effects.EffectManager.EffectType.Helix);
			return true;
		}
		return false;
	}

}
