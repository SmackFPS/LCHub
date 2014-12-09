package me.jrl1004.lightcraft.gadgets.parametrics;

import me.mike1665.Main.Main;
import me.mike1665.particles18.ParticleLib18;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

public class ParametricFlower
{
	private final Location source;
	private final ParticleLib18 effect;
	private final double tor = 2 * Math.PI;

	public ParametricFlower(Location start, ParticleLib18 effect)
	{
		source = start;
		this.effect = effect;
		runScript();
	}

	private void runScript()
	{
		new BukkitRunnable()
		{
			double _c = tor / 90;
			double d = 0D;
			double radius = 1;

			public void run()
			{
				if (d >= tor)
					d = 0;
				// x = 11cost-6cos(11/6t)
				// y = 11sint-6sin(11/6t)
				double xOffset = (11 * Math.cos(d)) - (6 * Math.cos((11 / 6) * d));
				double zOffset = (11 * Math.sin(d)) - (6 * Math.sin((11 / 6) * d));
				double yBuff = 0;
				double yCur = 0;
				Location displayLocation = source.clone();
				displayLocation.add(radius * xOffset, yCur, radius * zOffset);
				effect.sendToLocation(displayLocation);
				d += _c;
				if (yCur >= 2)
					yBuff = -0.10;
				if (yCur <= -1)
					yBuff = 0.10;
				yCur += yBuff;
			}
		}.runTaskTimer(Main.instance, 0, 2);
	}

}
