package com.lightcraftmc.ammo;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Listener;

import com.lightcraftmc.coin.Multiplier;
import com.lightcraftmc.hub.main.Main;

public class PaintballAmmoManager implements Listener {
	
	private static Main plugin;
	
	public static void initialize(){
		PaintballAmmoManager.plugin = Main.getInstance();
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