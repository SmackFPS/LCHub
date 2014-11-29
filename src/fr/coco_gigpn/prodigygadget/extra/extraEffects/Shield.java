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
import fr.coco_gigpn.prodigygadget.util.MathUtils;
import fr.coco_gigpn.prodigygadget.util.UtilLocation;
import fr.coco_gigpn.prodigygadget.util.UtilParticle;
import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;
import fr.coco_gigpn.prodigygadget.util.UtilVector;

public class Shield implements Listener {

	private int particles = 120;
	private int particlesPerIteration = 8;
	private float size = 1F;
	private float xFactor = 1.4F;
	private float yFactor = 1.4F;
	private float zFactor = 1.4F;
	private float yOffset = 1F;

	/**
	 * Rotation of the ball.
	 */
	private double xRotation;

	private double yRotation;

	private double zRotation = 0;

	/**
	 * Internal Counter
	 */
	private int step;


	@EventHandler
	public void LocationUpdater(UpdateEvent event) {
		if (event.getType() == UpdateType.TICK) {

			for (Player p : EffectManager.effect.keySet()) {



				if (EffectManager.getEffect(p) == EffectType.Shield) {

					if (p.isValid()) {

						Location location = p.getLocation();
						if (UtilLocation.locationEvery2Second.containsKey(p)) {
							location = UtilLocation.locationEvery2Second.get(p);
						}

						Vector vector = new Vector();


						for (int i = 0; i < particlesPerIteration; i++) {
							step++;

							   float t = (MathUtils.PI / particles) * step;
					            float r = MathUtils.sin(t) * size;
					            float s = r * MathUtils.PI * t;

					            vector.setX(xFactor * r * MathUtils.cos(s));
					            vector.setZ(zFactor * r * MathUtils.sin(s));
					            vector.setY(yFactor  * MathUtils.cos(t) + yOffset);

							UtilVector.rotateVector(vector, xRotation, yRotation, zRotation);

							UtilParticle.sendParticleToLocation(location.add(vector), Particle.MAGIC_CRIT, 0, 0, 0, 0, 1);
							location.subtract(vector);
						}		




					}
				}

			}
		}
	}
}
