package me.mike1665.ammo;

import me.mike1665.Main.Main;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Listener;

import com.arrayprolc.coin.Multiplier;

public class KittyCannonAmmoManager implements Listener {
	
	private static Main plugin;
	
	public static void initialize(){
		KittyCannonAmmoManager.plugin = Main.getInstance().getInstance();
	}
	
	public static void giveCatAmmo(OfflinePlayer p, int i) {
		i = i * Multiplier.coin(p);
		plugin.getConfig().set(p.getUniqueId() + ".CatAmmo",
				plugin.getConfig().getInt(p.getUniqueId() + ".CatAmmo", 0) + i);
		plugin.saveFile();
	}

	public static void takeCatAmmo(OfflinePlayer p, int i) {
		i = i * Multiplier.coin(p);
		plugin.getConfig().set(p.getUniqueId() + ".CatAmmo",
				plugin.getConfig().getInt(p.getUniqueId() + ".CatAmmo", 0) - i);
		plugin.saveFile();
	}

	public static boolean hasEnoughCatAmmo(OfflinePlayer p, int i) {
		i = i * Multiplier.coin(p);
		if (plugin.getConfig().getInt(p.getUniqueId() + ".CatAmmo") >= i)
			return true;
		return false;
	}
	public static int balaceCatAmmo(OfflinePlayer p) {
		int a;
		try {
			a = plugin.getConfig().getInt(p.getUniqueId() + ".CatAmmo");
		}catch(Exception exc) {
			a = 0;
		}
		return a;
	}
}