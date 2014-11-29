package fr.coco_gigpn.prodigygadget.effect;

import java.util.HashMap;

import me.mike1665.Main.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;

import fr.coco_gigpn.prodigygadget.extra.ExtraManager;
import fr.coco_gigpn.prodigygadget.particle.ParticleManager;

public class EffectManager implements Listener {

	public enum EffectType {
		CircleEffect, Cloud, CloudSnow, CloudLight, Helix, Tornado, Vortex, FlameLilly, HourGlass, Shield, Fountain
	}

	public static HashMap<Player, EffectType> effect = new HashMap<Player, EffectType>();

	@EventHandler
	public void OnPlayerLeft(PlayerQuitEvent e) {

		Player p = e.getPlayer();
		if (hasEffect(p)) {
			removeEffect(p);
		}

	}

	@EventHandler
	public void onLeave(PlayerKickEvent e) {
		Player p = e.getPlayer();

		if (hasEffect(p)) {
			removeEffect(p);
		}

	}

	public static boolean hasEffect(Player p) {
		if (effect.containsKey(p)) {
			return true;
		} else {
			return false;
		}
	}

	public static void addEffect(Player p, EffectType type) {
		if (!effect.containsKey(p)) {
			effect.put(p, type);
		} else {
			removeEffect(p);
			effect.put(p, type);
			
		}
	}

	public static EffectType getEffect(Player p) {
		return EffectManager.effect.get(p);
	}

	public static void removeEffect(Player p) {
		if (hasEffect(p)) {
			if (ParticleManager.hasCircleEffect(p)) {
				ParticleManager.removeCircleEffect(p);
			}
			effect.remove(p);
			if(ExtraManager.hasExtraEffect(p)) {
				ExtraManager.removeExtraEffect(p);
			}
		
		}
	}

	public static void registerEvents(Main plugin) {

		PluginManager pm = plugin.getServer().getPluginManager();
		pm.registerEvents(new EffectManager(), plugin);

	}
}
