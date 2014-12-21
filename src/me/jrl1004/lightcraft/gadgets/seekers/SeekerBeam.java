package me.jrl1004.lightcraft.gadgets.seekers;

import net.lightcraftmc.fusebox.util.particles18.ParticleLib18;
import net.lightcraftmc.fusebox.util.particles18.ParticleLib18.ParticleType;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

public class SeekerBeam {
	private SeekerBeam() {
	}

	public static void launchBeam(Location start, Location end) {
		launchBeam(start, end, 20);
	}

	public static void launchBeam(Location start, Location end, int steps) {
		launchBeam(start, end, steps, 0, 0, 0);
	}

	public static void launchBeam(final Location start, Location end,
			final int steps, final double xOffset, final double yOffset,
			final double zOffset) {
		final double xDist = (start.getX() - end.getX()) / steps;
		final double yDist = (start.getY() - end.getY()) / steps;
		final double zDist = (start.getZ() - end.getZ()) / steps;
		new BukkitRunnable() {
			double xBump = xOffset / (steps / 2);
			double yBump = yOffset / (steps / 2);
			double zBump = zOffset / (steps / 2);
			int s = 0;
			boolean flipped = false;

			public void run() {
				if (s >= steps) {
					cancel();
					return;
				}
				if (!flipped && s >= (steps / 2)) {
					xBump *= -1;
					yBump *= -1;
					zBump *= -1;
				}
				Location target = start.clone();
				target = target.add(xDist, yDist, zDist);
				target = target.add(xBump, yBump, zBump);
				ParticleLib18 particle = new ParticleLib18(
						ParticleType.VILLAGER_HAPPY, 1, 1, 0);
				particle.sendToLocation(target);
			}
		};
	}

	private static double abs(double d) {
		if (d < 0)
			d = -d;
		return d;
	}

}
