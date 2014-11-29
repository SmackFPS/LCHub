package fr.coco_gigpn.prodigygadget.util;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class UtilCooldown {

	public static HashMap<Player, Long> _active = new HashMap<Player, Long>();
	public static HashMap<Player, Double> _time = new HashMap<Player, Double>();
	public static HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<Player, BukkitRunnable>();

	  
	  public static double arrondi(double A, int B) {
		  return (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
	  }
}
