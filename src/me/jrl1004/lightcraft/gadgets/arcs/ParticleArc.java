package me.jrl1004.lightcraft.gadgets.arcs;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import com.lightcraftmc.fusebox.util.particles18.ParticleLib18;
import com.lightcraftmc.fusebox.util.particles18.ParticleLib18.ParticleType;
import com.lightcraftmc.hub.main.Main;

public class ParticleArc {
	private final ParticleType type;
	// World
	private final Location start;
	private final Location end;
	private final double arcHeight;
	private final double arcTime;

	public ParticleArc(ParticleType type, Location start, Location end,
			double height, double seconds) {
		this.type = type;
		this.start = start;
		this.end = end;
		this.arcHeight = height;
		this.arcTime = seconds;
	}

	public ParticleType getType() {
		return type;
	}

	public Location getStart() {
		return start;
	}

	public Location getEnd() {
		return end;
	}

	public double getArcHeight() {
		return arcHeight;
	}

	public double getArcTime() {
		return arcTime;
	}

	/**
	 * WARNING! THIS METHOD ACTIVATES A REPEATING TASK! PLEASE DO NOT RUN THIS
	 * MORE TIMES THAN NEEDED!
	 */
	public void beginArcing() {
		final double steps = arcTime * 20;
		final double xDist = (end.getX() - start.getX()) / steps;
		final double yDist = (end.getY() - start.getY()) / steps;
		final double zDist = (end.getZ() - start.getZ()) / steps;
		final double delta = Math.PI / steps;
		final ParticleLib18 particle = new ParticleLib18(type, 1, 1, 0);
		new BukkitRunnable() {
			int s = 0;

			public void run() {
				if (s >= steps) {
					cancel();
					return;
				}
				Location target = start.clone()
						.add(s * xDist, s * yDist, s * zDist)
						.add(0, arcHeight * Math.sin(s * delta), 0);
				particle.sendToLocation(target);
				s++;
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
}