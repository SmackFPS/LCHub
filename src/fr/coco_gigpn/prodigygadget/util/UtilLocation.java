package fr.coco_gigpn.prodigygadget.util;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.coco_gigpn.prodigygadget.effect.EffectManager;
import fr.coco_gigpn.prodigygadget.extra.ExtraManager;
import fr.coco_gigpn.prodigygadget.particle.particles.CircleParticle;
import fr.coco_gigpn.prodigygadget.updater.UpdateType;
import fr.coco_gigpn.prodigygadget.updater.event.UpdateEvent;

public class UtilLocation implements Listener {

	public static HashMap<Player, Location> locationEverySecond = new HashMap<Player, Location>();
	public static HashMap<Player, Location> locationEvery2Second = new HashMap<Player, Location>();
	
	@EventHandler
	public void OnPlayerLeft(PlayerQuitEvent e) {

		Player p = e.getPlayer();
		if(locationEverySecond.containsKey(p)) {
			locationEverySecond.remove(p);
		}
		if(locationEvery2Second.containsKey(p)) {
			locationEvery2Second.remove(p);
		}

	}

	@EventHandler
	public void onLeave(PlayerKickEvent e) {
		Player p = e.getPlayer();

		if(locationEverySecond.containsKey(p)) {
			locationEverySecond.remove(p);
		}
		if(locationEvery2Second.containsKey(p)) {
			locationEvery2Second.remove(p);
		}

	}
	

	@EventHandler
	public void LocationUpdater(UpdateEvent event) {
		if (event.getType() == UpdateType.SEC) {

			for (Player p : CircleParticle.effect.keySet()) {
				locationEverySecond.remove(p);
				locationEverySecond.put(p, p.getLocation());
			}
			for (Player p : EffectManager.effect.keySet()) {

				if (ExtraManager.hasExtraEffect(p)) {
					locationEvery2Second.remove(p);
					locationEvery2Second.put(p, p.getLocation());
				}
			}

		}

	}

}
