package me.jrl1004.lightcraft.gadgets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.mike1665.Main.Main;
import me.mike1665.particles18.ParticleLib18;
import me.mike1665.particles18.ParticleLib18.ParticleType;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class SpellTurret
{
	private final Random r;
	private final Location gun;

	public SpellTurret(final Entity entity)
	{
		this.gun = entity.getLocation().clone();
		this.r = new Random();
		run();
	}

	public SpellTurret(final Location gun)
	{
		this.gun = gun;
		this.r = new Random();
		run();
	}

	private void run()
	{
		new BukkitRunnable()
		{

			@Override
			public void run()
			{
				LivingEntity target = getTarget();
				if (target == null)
				{
					ParticleLib18 effect = new ParticleLib18(ParticleLib18.ParticleType.SPELL_MOB, 1, 4, 5);
					effect.sendToLocation(gun);
					return;
				}
				double xOff = gun.getX() - target.getLocation().getX();
				double yOff = gun.getY() - target.getLocation().getY();
				double zOff = gun.getZ() - target.getLocation().getZ();
				double _x = xOff / 10;
				double _y = yOff / 10;
				double _z = zOff / 10;
				ParticleLib18 effect = new ParticleLib18(ParticleType.SPELL_MOB, 1, 1, 0);
				for (int i = 0; i < 10; i++)
					effect.sendToLocation(gun.add(_x * i, _y * i, _z * i));
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
