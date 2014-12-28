package com.lightcraftmc.ammo;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Listener;

import com.lightcraftmc.coin.Multiplier;
import com.lightcraftmc.hub.main.Main;

public class EnderDogeAmmoManager implements Listener {
	
	private static Main plugin;
	
	public static void initialize(){
		EnderDogeAmmoManager.plugin = Main.getInstance();
	}
	
	public static void giveEnderDogeAmmo(OfflinePlayer p, int i) {
		i = i * Multiplier.coin(p);
		plugin.getConfig().set(p.getUniqueId() + ".EnderDogeAmmo",
				plugin.getConfig().getInt(p.getUniqueId() + ".EnderDogeAmmo", 0) + i);
		plugin.saveFile();
	}

	public static void takeEnderDogeAmmo(OfflinePlayer p, int i) {
		i = i * Multiplier.coin(p);
		plugin.getConfig().set(p.getUniqueId() + ".EnderDogeAmmo",
				plugin.getConfig().getInt(p.getUniqueId() + ".EnderDogeAmmo", 0) - i);
		plugin.saveFile();
	}

	public static boolean hasEnoughEnderDogeAmmo(OfflinePlayer p, int i) {
		i = i * Multiplier.coin(p);
		if (plugin.getConfig().getInt(p.getUniqueId() + ".EnderDogeAmmo") >= i)
			return true;
		return false;
	}
	public static int balaceEnderDogeAmmo(OfflinePlayer p) {
		int a;
		try {
			a = plugin.getConfig().getInt(p.getUniqueId() + ".EnderDogeAmmo");
		}catch(Exception exc) {
			a = 0;
		}
		return a;
	}
}