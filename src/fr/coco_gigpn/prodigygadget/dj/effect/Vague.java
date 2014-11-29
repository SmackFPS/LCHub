package fr.coco_gigpn.prodigygadget.dj.effect;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import fr.coco_gigpn.prodigygadget.util.UtilParticle;
import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;
import fr.coco_gigpn.prodigygadget.util.UtilRandoms;
import fr.coco_gigpn.prodigygadget.util.UtilVector;

public class Vague {

	/**
	 * Particles per arc
	 */
	public static int particles = 100;

	/**
	 * Lenght of line
	 */


	public static float lenght = 10;

	/**
	 * Turns the line by this angle each iteration around the x-axis
	 */
	public double angularVelocityX = Math.PI / 170;

	/**
	 * Turns the line by this angle each iteration around the z-axis
	 */
	public double angularVelocityZ = Math.PI / 170;

	/**
	 * Turns the line by this angle each iteration around the y-axis
	 */
	public static double angularVelocityY = Math.PI / 100;


	/**
	 * Current step. Works as counter
	 */
	protected static int step = 0;



	public static void Activate(Player p, final Location l1 , final Location l2 , final Location l3 ,final Location l4, int r1 , int r2 , int r3 , int r4) {
		final Vector direction = UtilRandoms.getRandomVectorline(r1);
		final Vector d2 = UtilRandoms.getRandomVectorlineZ(r2);
		final Vector d3 = UtilRandoms.getRandomVectorline(r3);
		final Vector d4 = UtilRandoms.getRandomVectorlineZ(r4);
		l1.add(0.5 , 0.5 , 0.5);
		l2.add(0.5 , 0.5 , 0.5);
		l3.add(0.5 , 0.5 , 0.5);
		l4.add(0.5 , 0.5 , 0.5);

		//direction.setY(- Math.abs(direction.getY()));

		final int i = Bukkit.getScheduler()
				.runTaskTimer(Main.schedule, new Runnable() {

					public void run() {

						final double yRotation = angularVelocityY * step;

						for (int i = 0; i < particles; i++) {
							float ratio = (float) i * lenght / particles;
							Vector v = direction.clone().multiply(ratio);
							UtilVector.rotateVector(v, 0, yRotation, 0);
							UtilParticle.sendParticleToLocation(l1.add(v), Particle.DRIP_LAVA, 0, 0,0, 0, 1);
							l1.subtract(v);
						}

						for (int i = 0; i < particles; i++) {
							float ratio = (float) i * lenght / particles;
							Vector v = d2.clone().multiply(ratio);
							UtilVector.rotateVector(v, 0, yRotation, 0);
							UtilParticle.sendParticleToLocation(l2.add(v), Particle.DRIP_LAVA, 0, 0,0, 0, 1);
							l2.subtract(v);
						}

						for (int i = 0; i < particles; i++) {
							float ratio = (float) i * lenght / particles;
							Vector v = d3.clone().multiply(ratio);
							UtilVector.rotateVector(v, 0, yRotation, 0);
							UtilParticle.sendParticleToLocation(l3.add(v), Particle.DRIP_LAVA, 0, 0,0, 0, 1);
							l3.subtract(v);
						}

						for (int i = 0; i < particles; i++) {
							float ratio = (float) i * lenght / particles;
							Vector v = d4.clone().multiply(ratio);
							UtilVector.rotateVector(v, 0, yRotation, 0);
							UtilParticle.sendParticleToLocation(l4.add(v), Particle.DRIP_LAVA, 0, 0,0, 0, 1);
							l4.subtract(v);
						}

						step --;

						if (step < -40) {

							step = 0;

						}


					}
				}, 1L, 1L).getTaskId();

		Bukkit.getScheduler().runTaskLater(Main.schedule,
				new Runnable() {

			@Override
			public void run() {

				Bukkit.getServer().getScheduler().cancelTask(i);

			}

		}, 20 * 20L);

	}

}
