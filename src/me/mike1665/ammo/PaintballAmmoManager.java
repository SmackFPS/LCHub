package me.mike1665.ammo;

import me.mike1665.Main.Main;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Listener;

import com.arrayprolc.coin.Multiplier;

public class PaintballAmmoManager implements Listener {
	
	private static Main plugin;
	
	public static void initialize(Main plugin){
		PaintballAmmoManager.plugin = plugin;
	}
	
	public static void givePBAmmo(OfflinePlayer p, int i) {
		i = i * Multiplier.coin(p);
		plugin.getConfig().set(p.getUniqueId() + ".PaintBallAmmo",
				plugin.getConfig().getInt(p.getUniqueId() + ".PaintBallAmmo", 0) + i);
		plugin.saveFile();
	}

	public static void takePBAmmo(OfflinePlayer p, int i) {
		i = i * Multiplier.coin(p);
		plugin.getConfig().set(p.getUniqueId() + ".PaintBallAmmo",
				plugin.getConfig().getInt(p.getUniqueId() + ".PaintBallAmmo", 0) - i);
		plugin.saveFile();
	}

	public static boolean hasEnoughPBAmmo(OfflinePlayer p, int i) {
		i = i * Multiplier.coin(p);
		if (plugin.getConfig().getInt(p.getUniqueId() + ".PaintBallAmmo") >= i)
			return true;
		return false;
	}
	public static int balacePBAmmo(OfflinePlayer p) {
		int a;
		try {
			a = plugin.getConfig().getInt(p.getUniqueId() + ".PaintBallAmmo");
		}catch(Exception exc) {
			a = 0;
		}
		return a;
	}
}