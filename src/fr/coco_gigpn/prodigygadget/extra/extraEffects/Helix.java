package fr.coco_gigpn.prodigygadget.extra.extraEffects;

import java.util.ArrayList;

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

public class Helix implements Listener {



	float rayonDistance = 0.6f;
	float particleRotation = 0.2f;

	double count = 0;
	

	@EventHandler
	public void LocationUpdater(UpdateEvent event) {
		if (event.getType() == UpdateType.TICK) {

			for (Player p : EffectManager.effect.keySet()) {

				

					if (EffectManager.getEffect(p) == EffectType.Helix) {
						
						if (p.isValid()) {
							
							Location l = p.getLocation();
							if (UtilLocation.locationEvery2Second.containsKey(p)) {
								l = UtilLocation.locationEvery2Second.get(p);
							}
						
						double count2 = count;
						double hauteure2 = -1;
						double rayon2 = 1.5;

						ArrayList<Location> points2 = new ArrayList<Location>();
						ArrayList<Location> points = new ArrayList<Location>();

						while (true) {
							double nombre2 = Math.PI + (count2 * Math.PI) / 6;
							Location loc2 = new Location(p.getWorld(),l.getX()
									+ Math.cos(nombre2 * rayonDistance)
									* rayon2, l.getY()
									+ hauteure2,l.getZ()
									+ Math.sin(nombre2 * rayonDistance)
									* rayon2);
							Location loc3 = new Location(p.getWorld(), l.getX()
									+ Math.cos(nombre2 * rayonDistance)
									* -rayon2,l.getY()
									+ hauteure2, l.getZ()
									+ Math.sin(nombre2 * rayonDistance)
									* -rayon2);

							points2.add(loc2);
							points.add(loc3);

							if (count2 >= 36 + count)
								break;
							rayon2 -= 0.04;
							hauteure2 += 0.11;
							count2++;

						}
						// creation de la 2eme spirale
						for (Location l4 : points2) {

							UtilParticle.sendParticleToLocation(l4,
									Particle.RED_DUST, 0, 0, 0, 0, 1);

						}

						for (Location l3 : points) {

							UtilParticle.sendParticleToLocation(l3,
									Particle.RED_DUST, 0, 0, 0, 0, 1);

						}

						if (count >= 36.0f) {
							count = 0;

						}

						count += particleRotation;
						}

					
				}
			}

		}
	}

}
