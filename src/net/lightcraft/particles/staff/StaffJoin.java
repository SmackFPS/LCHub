package net.lightcraft.particles.staff;

import net.lightcraftmc.fusebox.util.effects.EffectManager.EffectType;
import net.lightcraftmc.fusebox.util.extra.ExtraManager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class StaffJoin implements Listener{
	
	@EventHandler
	public void staffJoin(PlayerJoinEvent e) {
		if (e.getPlayer().getName().equals("ArrayPro")) {
			ExtraManager.Activate(e.getPlayer(), EffectType.Helix);
		}
		if (e.getPlayer().getName().equals("mike1665")) {
			ExtraManager.Activate(e.getPlayer(), EffectType.Helix);
		}
		if (e.getPlayer().getName().equals("SmackFPS")) {
			ExtraManager.Activate(e.getPlayer(), EffectType.Helix);
		}
		if (e.getPlayer().getName().equals("JRL1004")) {
			ExtraManager.Activate(e.getPlayer(), EffectType.Helix);
		}
		if (e.getPlayer().getName().equals("Dablakbandit")) {
			ExtraManager.Activate(e.getPlayer(), EffectType.Helix);
		}
		if (e.getPlayer().getName().equals("OlsonElliot")) {
			ExtraManager.Activate(e.getPlayer(), EffectType.Helix);
		}
	}

}
