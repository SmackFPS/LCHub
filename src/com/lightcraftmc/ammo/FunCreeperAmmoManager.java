package com.lightcraftmc.ammo;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Listener;

import com.lightcraftmc.coin.Multiplier;
import com.lightcraftmc.hub.main.Main;

public class FunCreeperAmmoManager implements Listener {
	
	private static Main plugin;
	
	public static void initialize(){
		FunCreeperAmmoManager.plugin = Main.getInstance();
	}
	
	public static void giveCreeperAmmo(OfflinePlayer p, int i) {
		i = i * Multiplier.coin(p);
		plugin.getConfig().set(p.getUniqueId() + ".CreeperAmmo",
				plugin.getConfig().getInt(p.getUniqueId() + ".CreeperAmmo", 0) + i);
		plugin.saveFile();
	}

	public static void takeCreeperAmmo(OfflinePlayer p, int i) {
		i = i * Multiplier.coin(p);
		plugin.getConfig().set(p.getUniqueId() + ".CreeperAmmo",
				plugin.getConfig().getInt(p.getUniqueId() + ".CreeperAmmo", 0) - i);
		plugin.saveFile();
	}

	public static boolean hasEnoughCreeperAmmo(OfflinePlayer p, int i) {
		i = i * Multiplier.coin(p);
		if (plugin.getConfig().getInt(p.getUniqueId() + ".CreeperAmmo") >= i)
			return true;
		return false;
	}
	public static int balaceCreeperAmmo(OfflinePlayer p) {
		int a;
		try {
			a = plugin.getConfig().getInt(p.getUniqueId() + ".CreeperAmmo");
		}catch(Exception exc) {
			a = 0;
		}
		return a;
	}
}