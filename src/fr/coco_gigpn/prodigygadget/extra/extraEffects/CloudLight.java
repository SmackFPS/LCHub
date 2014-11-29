package fr.coco_gigpn.prodigygadget.extra.extraEffects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

import fr.coco_gigpn.prodigygadget.effect.EffectManager;
import fr.coco_gigpn.prodigygadget.effect.EffectManager.EffectType;
import fr.coco_gigpn.prodigygadget.updater.UpdateType;
import fr.coco_gigpn.prodigygadget.updater.event.UpdateEvent;
import fr.coco_gigpn.prodigygadget.util.UtilLocation;
import fr.coco_gigpn.prodigygadget.util.UtilMath;
import fr.coco_gigpn.prodigygadget.util.UtilParticle;
import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;
import fr.coco_gigpn.prodigygadget.util.UtilRandoms;

public class CloudLight implements Listener {


	@EventHandler
	public void LocationUpdater(UpdateEvent event) {
		if (event.getType() == UpdateType.TICK) {

			for (Player p :  EffectManager.effect.keySet()) {

			

					if (EffectManager.getEffect(p) == EffectType.CloudLight) {
						
						if (p.isValid()) {

							Location loc = p.getLocation();
							if (UtilLocation.locationEvery2Second.containsKey(p)) {
								loc = UtilLocation.locationEvery2Second.get(p);
							}
							Location l = new Location(loc.getWorld(), loc.getX() , loc.getY() , loc.getZ());
						l.setY(p.getLocation().getY() + 3.7f);
						UtilParticle.sendParticleToLocation(l.add(
								UtilMath.randomRangeFloat(-0.4f, 0.4f), 0,
								UtilMath.randomRangeFloat(-0.5f, 0.5f)),
								Particle.DRIP_WATER, 0, 0, 0, 0, 1);
						UtilParticle.sendParticleToLocation(l.add(
								UtilMath.randomRangeFloat(-0.5f, 0.5f), 0,
								UtilMath.randomRangeFloat(-0.5f, 0.5f)),
								Particle.CLOUD, 0, 0, 0, 0, 3);
						UtilParticle.sendParticleToLocation(l.add(
								UtilMath.randomRangeFloat(-0.5f, 0.5f), 0,
								UtilMath.randomRangeFloat(-0.5f, 0.5f)),
								Particle.CLOUD, 0, 0, 0, 0, 3);
						

						
					}
				}
			}

		}
		
		if (event.getType() == UpdateType.SEC) {

			for (Player p :  EffectManager.effect.keySet()) {


					if (EffectManager.getEffect(p) == EffectType.CloudLight) {
						
						if (p.isValid()) {

					
						Vector direction = UtilRandoms.getRandomVector();

						Location loc = p.getLocation();
						if (UtilLocation.locationEvery2Second.containsKey(p)) {
							loc = UtilLocation.locationEvery2Second.get(p);
						}
						Location l2 = new Location(loc.getWorld(), loc.getX() , loc.getY() , loc.getZ());
						l2.setY(p.getLocation().getY() + 3.7f);

						direction.setY(-Math.abs(direction.getY() - 2));
						for (int i = 0; i < 28; i++) {
							float ratio = (float) i * 0.06f / 28;
							Vector v = direction.clone().multiply(ratio);
							l2.add(v);
							UtilParticle.sendParticleToLocation(l2.add(v),
									Particle.DRIP_LAVA, 0, 0, 0, 0, 1);
							if (i == 20) {
								l2.add(0, 0, 0.1f);
							}
							l2.subtract(v);
							
						}

						
					}
				}
			}

		}
	
	}
	
	
}
