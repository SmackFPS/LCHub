package fr.coco_gigpn.prodigygadget.util;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.util.Vector;

public final class UtilRandoms {

	public static final Random random = new Random(System.nanoTime());
	
	private UtilRandoms() {
		// No instance allowed
	}
	
	public static Vector getRandomVector() {
		double x, y, z;
		x = random.nextDouble() * 10 - 1;
		y = random.nextDouble() * 10 - 1;
		z = random.nextDouble() * 10 - 1;

		return new Vector(x, y, z).normalize();
	}
	
	public static Vector getRandomVectorline(int direction) {
		double x, y, z;
		
		int minz = 1;
		int maxz = 3;
		int rz = (int)(Math.random()*(maxz - minz) + minz);
		
		double miny = -1;
		double maxy = 1;
		double ry = (double)(Math.random()*(maxy - miny) + miny);
		
		
		x = direction;
		y = ry;
		z = rz;

		return new Vector(x, y, z).normalize();
	}
	
	public static Vector getRandomVectorlineZ(int direction) {
		double x, y, z;
		
		int minz = 1;
		int maxz = 3;
		int rz = (int)(Math.random()*(maxz - minz) + minz);
		
		double miny = -1;
		double maxy = 1;
		double ry = (double)(Math.random()*(maxy - miny) + miny);
		
		
		x = direction;
		y = ry;
		z = rz;

		return new Vector(z, y, x).normalize();
	}
	

	
	
	public static Vector getRandomCircleVector() {
		double rnd, x, z;
		rnd = random.nextDouble() * 2 * Math.PI;
		x = Math.cos(rnd);
		z = Math.sin(rnd);
		
		return new Vector(x, 0, z);
	}
	
	public static Vector getRandomCircleVectorLine() {
		double rnd, x, z , y;
		rnd = -10 + random.nextInt( -10 - -20 ) * 2 * Math.PI;
		y = -2 + random.nextInt(-1 - -2);
		x = Math.cos(rnd) ;
		z = Math.sin(rnd) ;
		
		return new Vector(x, y, z);
	}

	public static Material getRandomMaterial(Material[] materials) {
		return materials[random.nextInt(materials.length)];
	}
	
	public static double getRandomAngle() {
		return random.nextDouble() * 2 * Math.PI;
	}
	
}
