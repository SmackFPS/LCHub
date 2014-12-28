package me.jrl1004.lightcraft.gadgets.seekers;

import me.mike1665.particles18.ParticleLib18;
import me.mike1665.particles18.ParticleLib18.ParticleType;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import com.lightcraftmc.hub.main.Main;

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
		System.out.println("Method start");
		final double xDist = (end.getX() - start.getX()) / steps;
		final double yDist = (end.getY() - start.getY()) / steps;
		final double zDist = (end.getZ() - start.getZ()) / steps;
		System.out.println("Runnable start");
		new BukkitRunnable() {
			double xBump = xOffset / (steps / 2);
			double yBump = yOffset / (steps / 2);
			double zBump = zOffset / (steps / 2);
			double _x = 0;
			double _y = 0;
			double _z = 0;
			int s = 0;
			boolean flipped = false;
			ParticleLib18 particle = new ParticleLib18(ParticleType.NOTE, 1, 1,
					0);

			public void run() {
				System.out.println("Running step " + s);
				if (s >= steps) {
					System.out.println("Cancelling");
					cancel();
					return;
				}
				if (!flipped && s >= (steps / 2)) {
					xBump *= -1;
					yBump *= -1;
					zBump *= -1;
					flipped = true;
					System.out.println("Bump values flipped");
				}
				Location target = start.clone()
						.add(s * xDist, s * yDist, s * zDist).add(_x, _y, _z);
				System.out.println("Current target calculated");
				particle.sendToLocation(target);
				s++;
				_x += xBump;
				_y += yBump;
				_z += zBump;
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}

	public static void launchArcBeam(final Location start, final Location end,
			final int steps) {
		System.out.println("Method start");
		final double xDist = (end.getX() - start.getX()) / steps;
		final double yDist = (end.getY() - start.getY()) / steps;
		final double zDist = (end.getZ() - start.getZ()) / steps;
		final double sin = Math.PI / steps;
		System.out.println("Runnable start");
		new BukkitRunnable() {
			int s = 0;
			ParticleLib18 particle = new ParticleLib18(ParticleType.DRIP_LAVA,
					1, 1, 0);

			public void run() {
				System.out.println("Running step " + s);
				if (s >= steps) {
					System.out.println("Cancelling");
					cancel();
					return;
				}
				Location target = start.clone().add(s * xDist, s * yDist,
						s * zDist);
				target.add(0, 10 * Math.sin(s * sin), 0);
				System.out.println("Current target calculated");
				particle.sendToLocation(target);
				s++;
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}

}
