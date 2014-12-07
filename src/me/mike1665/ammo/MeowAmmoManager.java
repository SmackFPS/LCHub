package me.mike1665.ammo;

import me.mike1665.Main.Main;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Listener;

import com.arrayprolc.coin.Multiplier;

public class MeowAmmoManager implements Listener {
	
	private static Main plugin;
	
	public static void initialize(Main plugin){
		MeowAmmoManager.plugin = plugin;
	}
	
	public static void giveMeowAmmo(OfflinePlayer p, int i) {
		i = i * Multiplier.coin(p);
		plugin.getConfig().set(p.getUniqueId() + ".MeowAmmo",
				plugin.getConfig().getInt(p.getUniqueId() + ".MeowAmmo", 0) + i);
		plugin.saveFile();
	}

	public static void takeMeowAmmo(OfflinePlayer p, int i) {
		i = i * Multiplier.coin(p);
		plugin.getConfig().set(p.getUniqueId() + ".MeowAmmo",
				plugin.getConfig().getInt(p.getUniqueId() + ".MeowAmmo", 0) - i);
		plugin.saveFile();
	}

	public static boolean hasEnoughMeowAmmo(OfflinePlayer p, int i) {
		i = i * Multiplier.coin(p);
		if (plugin.getConfig().getInt(p.getUniqueId() + ".MeowAmmo") >= i)
			return true;
		return false;
	}
	public static int balaceMeowAmmo(OfflinePlayer p) {
		int a;
		try {
			a = plugin.getConfig().getInt(p.getUniqueId() + ".MeowAmmo");
		}catch(Exception exc) {
			a = 0;
		}
		return a;
	}
}