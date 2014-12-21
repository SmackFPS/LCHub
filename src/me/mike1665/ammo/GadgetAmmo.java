package me.mike1665.ammo;

import me.mike1665.Main.Main;

import org.bukkit.OfflinePlayer;

import com.arrayprolc.coin.Multiplier;

public class GadgetAmmo {

	private static Main plugin;

	public static void initialize() {
		GadgetAmmo.plugin = Main.getInstance().getInstance();
	}

	public static int balanceGadgetAmo(OfflinePlayer p, String gadget) {
		int a;
		try {
			a = plugin.getConfig().getInt(p.getUniqueId() + "." + gadget);
		} catch (Exception exc) {
			a = 0;
		}
		return a;
	}

	public static boolean hasGadgetAmo(OfflinePlayer p, String gadget, int i) {
		i = i * Multiplier.coin(p);
		if (plugin.getConfig().getInt(p.getUniqueId() + "." + gadget) >= i)
			return true;
		return false;
	}

	public static void removeGadgetAmo(OfflinePlayer p, String gadget, int amount) {
		amount = amount * Multiplier.coin(p);
		plugin.getConfig().set(p.getUniqueId() + "." + gadget,
						plugin.getConfig().getInt(p.getUniqueId() + "." + gadget, 0)
								- amount);
		plugin.saveFile();
	}

	public static void addGadgetAmo(OfflinePlayer p, String gadget, int amount) {
		amount = amount * Multiplier.coin(p);
		plugin.getConfig().set(p.getUniqueId() + "." + gadget, 
				plugin.getConfig().getInt(p.getUniqueId() + "." + gadget, 0) + amount);
		plugin.saveFile();
	}
}