package com.lightcraftmc.particles.staff;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class StaffJoin implements Listener{
	
	@EventHandler
	public void staffJoin(PlayerJoinEvent e) {
		if (e.getPlayer().getName().equals("ArrayPro")) {
			com.lightcraftmc.extra.ExtraManager.Activate(e.getPlayer(), com.lightcraftmc.effects.EffectManager.EffectType.Helix);
		}
		if (e.getPlayer().getName().equals("mike1665")) {
			com.lightcraftmc.extra.ExtraManager.Activate(e.getPlayer(), com.lightcraftmc.effects.EffectManager.EffectType.Helix);
		}
		if (e.getPlayer().getName().equals("SmackFPS")) {
			com.lightcraftmc.extra.ExtraManager.Activate(e.getPlayer(), com.lightcraftmc.effects.EffectManager.EffectType.Helix);
		}
		if (e.getPlayer().getName().equals("JRL1004")) {
			com.lightcraftmc.extra.ExtraManager.Activate(e.getPlayer(), com.lightcraftmc.effects.EffectManager.EffectType.Helix);
		}
		if (e.getPlayer().getName().equals("Dablakbandit")) {
			com.lightcraftmc.extra.ExtraManager.Activate(e.getPlayer(), com.lightcraftmc.effects.EffectManager.EffectType.Helix);
		}
		if (e.getPlayer().getName().equals("OlsonElliot")) {
			com.lightcraftmc.extra.ExtraManager.Activate(e.getPlayer(), com.lightcraftmc.effects.EffectManager.EffectType.Helix);
		}
	}

}
