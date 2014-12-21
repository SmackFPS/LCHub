package me.mike1665.coinapi;

import me.mike1665.Main.Main;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Listener;

import com.arrayprolc.coin.Multiplier;

public class LcCoinsAPI implements Listener {
	
	private static Main plugin;
	
	public static void initialize(){
		LcCoinsAPI.plugin = Main.getInstance().getInstance();
	}
	
	public static void givePoints(OfflinePlayer p, int i) {
		i = i * Multiplier.coin(p);
		plugin.getConfig().set(p.getUniqueId() + ".Coins",
				plugin.getConfig().getInt(p.getUniqueId() + ".Coins", 0) + i);
		plugin.saveFile();
		//String i2 = Integer.toString(i);
		//me.mike1665.mysql.MySQL.giveCoins(p, i2);
	}

	public static void takePoints(OfflinePlayer p, int i) {
		i = i * Multiplier.coin(p);
		plugin.getConfig().set(p.getUniqueId() + ".Coins",
				plugin.getConfig().getInt(p.getUniqueId() + ".Coins", 0) - i);
		plugin.saveFile();
		//String i2 = Integer.toString(i);
		//me.mike1665.mysql.MySQL.takeCoins(p, i2);
	}

	public static boolean hasEnough(OfflinePlayer p, int i) {
		i = i * Multiplier.coin(p);
		if (plugin.getConfig().getInt(p.getUniqueId() + ".Coins") >= i)
			return true;
		return false;
	}
	public static int balancePoints(OfflinePlayer p) {
		int a;
		try {
			a = plugin.getConfig().getInt(p.getUniqueId() + ".Coins");
		}catch(Exception exc) {
			a = 0;
		}
		return a;
	}
}