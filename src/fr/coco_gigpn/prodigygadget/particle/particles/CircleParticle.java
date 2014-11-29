package fr.coco_gigpn.prodigygadget.particle.particles;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

import fr.coco_gigpn.prodigygadget.effect.EffectManager;
import fr.coco_gigpn.prodigygadget.effect.EffectManager.EffectType;
import fr.coco_gigpn.prodigygadget.extra.ExtraManager;
import fr.coco_gigpn.prodigygadget.particle.ParticleManager;
import fr.coco_gigpn.prodigygadget.particle.ParticleManager.ParticleType;
import fr.coco_gigpn.prodigygadget.updater.UpdateType;
import fr.coco_gigpn.prodigygadget.updater.event.UpdateEvent;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilLocation;
import fr.coco_gigpn.prodigygadget.util.UtilParticle;
import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;

public class CircleParticle implements Listener {

	double radialsPerStep = Math.PI / 16;
	float radius = 0.4f;
	float step = 0;

	public static HashMap<Player, ParticleType> effect = new HashMap<Player, ParticleType>();

	public static void Activate(final Player p, final ParticleType effect) {
		
		if (!EffectManager.hasEffect(p)) {
			
			EffectManager.effect.put(p, EffectType.CircleEffect);
			
			if (!ParticleManager.hasCircleEffect(p)) {

			CircleParticle.effect.put(p, effect);
			UtilLocation.locationEverySecond.put(p, p.getLocation());

		} else {
		
			CircleParticle.effect.remove(p);
			CircleParticle.effect.put(p, effect);
			UtilLocation.locationEverySecond.put(p, p.getLocation());
		}
	 } else { 
		 if (ParticleManager.hasCircleEffect(p)) {
			 CircleParticle.effect.remove(p);
				CircleParticle.effect.put(p, effect);
				UtilLocation.locationEverySecond.put(p, p.getLocation());
		 }
		 if (ExtraManager.hasExtraEffect(p)) {
				p.sendMessage(Messages.tooManyEffect.replace("&", "§"));
			}
	 }
	}

	@EventHandler
	public void ParticleAura(UpdateEvent event) {
		
		
		if (event.getType() == UpdateType.TICK) {

			for (Player p : CircleParticle.effect.keySet()) {

				if (EffectManager.getEffect(p) == EffectType.CircleEffect) {
					
					if (p.isValid()) {

					ParticleType effect = CircleParticle.effect.get(p);

					Vector v = new Vector(Math.cos(radialsPerStep * step)
							* radius, 0, Math.sin(radialsPerStep * step)
							* radius);
					Location l = UtilLocation.locationEverySecond.get(p);
					Location loc = new Location(p.getWorld(), l.getX(),
							l.getY() + 2, l.getZ());

					loc.add(v);

					if (effect == ParticleType.Heart) {
						UtilParticle.sendParticleToLocation(loc,
								Particle.HEART, 0, 0, 0, 0, 1);

					}
					if (effect == ParticleType.Music) {
						UtilParticle.sendParticleToLocation(loc, Particle.NOTE,
								0, 0, 0, 1, 1);
					}
					if (effect == ParticleType.Flame) {
						UtilParticle.sendParticleToLocation(loc,
								Particle.FLAME, 0, 0, 0, 0, 1);
					}
					if (effect == ParticleType.Water) {
						UtilParticle.sendParticleToLocation(loc,
								Particle.DRIP_WATER, 0, 0, 0, 0, 1);
					}
					if (effect == ParticleType.Lava) {
						UtilParticle.sendParticleToLocation(loc,
								Particle.DRIP_LAVA, 0, 0, 0, 0, 1);
					}
					if (effect == ParticleType.Spark) {
						UtilParticle.sendParticleToLocation(loc,
								Particle.FIREWORKS_SPARK, 0, 0, 0, 0, 1);
					}
					if (effect == ParticleType.Witch) {
						UtilParticle.sendParticleToLocation(loc,
								Particle.WITCH_MAGIC, 0, 0, 0, 0, 1);
					}
					if (effect == ParticleType.Enchantement) {
						UtilParticle.sendParticleToLocation(loc,
								Particle.ENCHANTMENT_TABLE, 0, 0, 0, 0, 1);
					}
					if (effect == ParticleType.AngryVillager) {
						UtilParticle.sendParticleToLocation(loc,
								Particle.ANGRY_VILLAGER, 0, 0, 0, 0, 1);
					}
					if (effect == ParticleType.MagicCrit) {
						UtilParticle.sendParticleToLocation(loc,
								Particle.MAGIC_CRIT, 0, 0, 0, 0, 1);
					}
					if (effect == ParticleType.Portal) {
						loc.setY(loc.getY() - 0.4f);
						UtilParticle.sendParticleToLocation(loc,
								Particle.PORTAL, 0, 0, 0, 0, 1);
					}
					if (effect == ParticleType.Rainbow) {
						UtilParticle.sendParticleToLocation(loc,
								Particle.RED_DUST, 0, 0, 0, 1, 1);
					}
					if (effect == ParticleType.Slime) {
						UtilParticle.sendParticleToLocation(loc,
								Particle.SLIME, 0, 0, 0, 0, 1);
					}
					if (effect == ParticleType.Snow) {
						UtilParticle.sendParticleToLocation(loc,
								Particle.SNOWBALL_POOF, 0, 0, 0, 0, 1);
					}
					if (effect == ParticleType.SnowShovel) {
						UtilParticle.sendParticleToLocation(loc,
								Particle.SNOW_SHOVEL, 0, 0, 0, 0, 1);
					}

					loc.subtract(v);

				}
					}

				
				}

			}
			
			step ++;

		
	}

}
