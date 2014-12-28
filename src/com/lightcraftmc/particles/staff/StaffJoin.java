package com.lightcraftmc.particles.staff;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class StaffJoin implements Listener{
	
	@EventHandler
	public void staffJoin(PlayerJoinEvent e) {
		if (e.getPlayer().getName().equals("ArrayPro")) {
			me.mike1665.extra.ExtraManager.Activate(e.getPlayer(), me.mike1665.effects.EffectManager.EffectType.Helix);
		}
		if (e.getPlayer().getName().equals("mike1665")) {
			me.mike1665.extra.ExtraManager.Activate(e.getPlayer(), me.mike1665.effects.EffectManager.EffectType.Helix);
		}
		if (e.getPlayer().getName().equals("SmackFPS")) {
			me.mike1665.extra.ExtraManager.Activate(e.getPlayer(), me.mike1665.effects.EffectManager.EffectType.Helix);
		}
		if (e.getPlayer().getName().equals("JRL1004")) {
			me.mike1665.extra.ExtraManager.Activate(e.getPlayer(), me.mike1665.effects.EffectManager.EffectType.Helix);
		}
		if (e.getPlayer().getName().equals("Dablakbandit")) {
			me.mike1665.extra.ExtraManager.Activate(e.getPlayer(), me.mike1665.effects.EffectManager.EffectType.Helix);
		}
		if (e.getPlayer().getName().equals("OlsonElliot")) {
			me.mike1665.extra.ExtraManager.Activate(e.getPlayer(), me.mike1665.effects.EffectManager.EffectType.Helix);
		}
	}

}
