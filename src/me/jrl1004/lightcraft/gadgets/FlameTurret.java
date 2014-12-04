package me.jrl1004.lightcraft.gadgets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.mike1665.Main.Main;
import me.mike1665.particlelib.ParticleEffect;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class FlameTurret
{
	private final Random r;
	private final Location gun;

	public FlameTurret(final Entity entity)
	{
		this.gun = entity.getLocation();
		this.r = new Random();
		run();
	}

	public FlameTurret(final Location gun)
	{
		this.gun = gun;
		this.r = new Random();
		run();
	}

	private void run()
	{
		new BukkitRunnable()
		{

			@SuppressWarnings("deprecation")
			@Override
			public void run()
			{
				LivingEntity target = getTarget();
				if (target == null)
				{
					ParticleEffect.FLAME.display(gun, 0, 0, 0, 5, 1, Bukkit.getOnlinePlayers());
					return;
				}
				float xOff = (float) (gun.getX() - target.getLocation().getX());
				float yOff = (float) (gun.getY() - target.getLocation().getY());
				float zOff = (float) (gun.getZ() - target.getLocation().getZ());
				ParticleEffect.FLAME.display(gun, xOff, yOff, zOff, 5, 1, Bukkit.getOnlinePlayers());
			}
		}.runTaskTimer(Main.instance, 0, 2);
	}

	private LivingEntity getTarget()
	{
		List<Entity> entities = gun.getWorld().getEntities();
		if (entities.isEmpty())
			return null;
		List<LivingEntity> targets = new ArrayList<LivingEntity>();
		for (Entity e : entities)
		{
			if (e instanceof LivingEntity == false)
				continue;
			if (e.getLocation().distance(gun) > 10)
				continue;
			targets.add((LivingEntity) e);
		}
		if (targets.isEmpty())
			return null;
		return targets.get(r.nextInt(targets.size()));
	}
}
