package com.lightcraftmc.ammo;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Listener;

import com.lightcraftmc.coin.Multiplier;
import com.lightcraftmc.hub.main.Main;

public class FireWorksAmmoManager implements Listener {
	
	private static Main plugin;
	
	public static void initialize(){
		FireWorksAmmoManager.plugin = Main.getInstance();
	}
	
	public static void giveFireWorkAmmo(OfflinePlayer p, int i) {
		i = i * Multiplier.coin(p);
		plugin.getConfig().set(p.getUniqueId() + ".FireWorkAmmo",
				plugin.getConfig().getInt(p.getUniqueId() + ".FireWorkAmmo", 0) + i);
		plugin.saveFile();
	}

	public static void takeFireWorkAmmo(OfflinePlayer p, int i) {
		i = i * Multiplier.coin(p);
		plugin.getConfig().set(p.getUniqueId() + ".FireWorkAmmo",
				plugin.getConfig().getInt(p.getUniqueId() + ".FireWorkAmmo", 0) - i);
		plugin.saveFile();
	}

	public static boolean hasEnoughFireWorkAmmo(OfflinePlayer p, int i) {
		i = i * Multiplier.coin(p);
		if (plugin.getConfig().getInt(p.getUniqueId() + ".FireWorkAmmo") >= i)
			return true;
		return false;
	}
	public static int balaceFireWorkAmmo(OfflinePlayer p) {
		int a;
		try {
			a = plugin.getConfig().getInt(p.getUniqueId() + ".FireWorkAmmo");
		}catch(Exception exc) {
			a = 0;
		}
		return a;
	}
}