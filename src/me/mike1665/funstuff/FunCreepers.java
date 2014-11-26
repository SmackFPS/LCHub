package me.mike1665.funstuff;

import java.util.List;

import me.mike1665.particlelib.ParticleEffect;

import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.util.Vector;

public class FunCreepers implements Listener{
	
	@EventHandler
	public void cancelExplosion(EntityExplodeEvent e) {
		if(e.getEntity() instanceof Creeper) {
			Location loc = e.getEntity().getLocation();
			e.setCancelled(true);
			e.blockList().clear();
			ParticleEffect.FIREWORKS_SPARK.display(loc, 1.0F, 1.0F, 1.0F, 0.0F, 20);
			ParticleEffect.LAVA.display(loc, 0.0F, 0.0F, 0.0F, 0.0F, 5);
			ParticleEffect.HEART.display(loc, 0.0F, 0.0F, 0.0F, 0.0F, 5);
			List<Entity> near = ((Entity) e).getNearbyEntities(10.0d, 10.0d, 10.0d);
			for(Entity z : near) {
				z.setVelocity(new Vector(0, 2, 0));
			}
		}
	}

}
