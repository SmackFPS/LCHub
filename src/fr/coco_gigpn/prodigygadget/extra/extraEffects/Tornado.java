package fr.coco_gigpn.prodigygadget.extra.extraEffects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.coco_gigpn.prodigygadget.effect.EffectManager;
import fr.coco_gigpn.prodigygadget.effect.EffectManager.EffectType;
import fr.coco_gigpn.prodigygadget.updater.UpdateType;
import fr.coco_gigpn.prodigygadget.updater.event.UpdateEvent;
import fr.coco_gigpn.prodigygadget.util.UtilLocation;
import fr.coco_gigpn.prodigygadget.util.UtilParticle;
import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;

public class Tornado implements Listener {

	float LineNumber = 3f;
	float j = 0.0f;
	float radius = 0.3f;
	float heightEcart = 0.01f;

	@EventHandler
	public void LocationUpdater(UpdateEvent event) {
		if (event.getType() == UpdateType.TICK) {

			for (Player p :  EffectManager.effect.keySet()) {

			

					if (EffectManager.getEffect(p) == EffectType.Tornado) {
						if (p.isValid()) {

							Location l = p.getLocation();
							if (UtilLocation.locationEvery2Second.containsKey(p)) {
								l = UtilLocation.locationEvery2Second.get(p);
							}
							Location loc = new Location(l.getWorld(), l.getX() , l.getY() , l.getZ());
						loc.setY(loc.getY() + 2);

						for (int k = 0; k < LineNumber; k++) {

							loc.add(Math.cos(j) * radius, j * heightEcart,
									Math.sin(j) * radius);
							UtilParticle.sendParticleToLocation(loc,
									Particle.FIREWORKS_SPARK, 0, 0, 0, 0, 1);

						}

						j += 0.2f;
						if (j > 50) {

							j = 0;
						}
						}
					
				}
			}

		}
	}

}
